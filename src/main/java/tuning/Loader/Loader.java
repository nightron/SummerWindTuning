package tuning.Loader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.CustomerDemographicsDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductDAO;
import dao.ShipperDAO;
import dao.SupplierDAO;
import entities.Category;
import entities.Customer;
import entities.Employee;
import entities.Order;
import entities.OrderDetails;
import entities.Product;
import entities.Shipper;
import entities.Supplier;

//import javax.ejb.*;
//
//@Stateless

@Component
public class Loader {

	private static final Logger logger = LogManager.getLogger(Loader.class);

	protected EntityManager entityManager;
	protected EntityManagerFactory entityManagerFactory;

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private CustomerDemographicsDAO customerDemographicDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderDetailsDAO orderDetailsDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShipperDAO shipperDAO;
	@Autowired
	private SupplierDAO supplierDAO;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// @Transactional
	public void loadData() {
		DataLoader dataLoader = new DataLoader();
		try {
			Map<BigInteger, Supplier> suppliers = dataLoader.loadSuppliers("suppliers.xml");
			System.out.println(suppliers.keySet());
			logger.info("loadData : Persisting " + suppliers.size() + " suppliers");
			for (Supplier s : suppliers.values()) {
				supplierDAO.create(s);
			}
			Map<String, Customer> customers = dataLoader.loadCustomers("customers.xml");
			
			logger.info("loadData : Persisting " + customers.size() + " customers");
			for (Customer c : customers.values()) {
				customerDAO.create(c);
			}

			
			Map<BigInteger, Category> categories = dataLoader.loadCategories("categories.xml");
			logger.info("loadData : Persisting " + categories.size() + " categories");
			for (Category c : categories.values()) {
				categoryDAO.create(c);
			}

			Map<BigInteger, Shipper> shippers = dataLoader.loadShippers("shippers.xml");
			logger.info("loadData : Persisting " + shippers.size() + " shippers");
			for (Shipper s : shippers.values()) {
				shipperDAO.create(s);
			}

			Map<BigInteger, Product> products = dataLoader.loadProducts("products.xml", suppliers, categories);
			logger.info("loadData : Persisting " + products.size() + " products");
			for (Product p : products.values())
				productDAO.create(p);

			Map<BigInteger, Employee> employees = dataLoader.loadEmployees("employees.xml");
			logger.info("loadData : Persisting " + employees.size() + " employees");
			for (Employee e : employees.values()){
				try{employeeDAO.create(e);
				}
				catch (Exception es){
					logger.warn(es.getMessage());
				}
			}
			
			logger.info("Loading orders with details");

			Map<BigInteger, Order> orders = dataLoader.loadOrders("orders_rand_10000.xml", customers, employees, shippers);
			orders.putAll(dataLoader.loadOrders("orders_rand_20000.xml", customers, employees, shippers));

			Map<Integer, OrderDetails> orderDetails = dataLoader.loadOrderDetails("orderdetails_rand_10000.xml", orders, products);
			orderDetails.putAll(dataLoader.loadOrderDetails("orderdetails_rand_20000.xml", orders, products));

			Map<Order, List<OrderDetails>> orderToOrderDetails = buildOrderToOrderDetailsMap(orderDetails);
			
			for (OrderDetails od : orderDetails.values()){
				od.getOrderID().addOrderDetails(od);
			}
			
			List<Order> orderList = new ArrayList<Order>(orders.values());
			
			
			logger.info("Orders persisting:");
			
			int size = orderList.size();
			int i = 0;
			long start, stop = 0;
			StringBuilder builder = new StringBuilder();
			for (int j = 1000; j < size; j += 1000) {
				start = System.currentTimeMillis();
				entityManager.getTransaction().begin();
				while (i < j) {
				//	entityManager.persist(orderList.get(i));
					orderDAO.create(orderList.get(i));
					i++;
				}
				entityManager.getTransaction().commit();

				stop = System.currentTimeMillis();
				builder.append((j / 1000) + " : " + (stop - start) + "\n");
				logger.info("Orders round: " + j/1000 + " : " + (stop-start));
			}
			System.out.println(builder.toString());

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public void setCustomerDemographicDAO(CustomerDemographicsDAO customerDemographicDAO) {
		this.customerDemographicDAO = customerDemographicDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
		this.orderDetailsDAO = orderDetailsDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void setShipperDAO(ShipperDAO shipperDAO) {
		this.shipperDAO = shipperDAO;
	}

	public void setSupplierDAO(SupplierDAO supplierDAO) {
		this.supplierDAO = supplierDAO;
	}

	private static Map<Order, List<OrderDetails>> buildOrderToOrderDetailsMap(Map<Integer, OrderDetails> orderDetails) {
		Map<Order, List<OrderDetails>> result = new HashMap<Order, List<OrderDetails>>();

		for (OrderDetails orderDetailsInstance : orderDetails.values()) {
			Order order = orderDetailsInstance.getOrderID();

			if (result.containsKey(order) == false) {
				result.put(order, new ArrayList<OrderDetails>());
			}

			result.get(order).add(orderDetailsInstance);
		}

		return result;
	}

}
