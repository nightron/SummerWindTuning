package tuning.Loader;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entities.Category;
import entities.Customer;
import entities.Employee;
import entities.Order;
import entities.OrderDetails;
import entities.Product;
import entities.Shipper;
import entities.Supplier;

public class DataLoader {
	private DateFormat employeeDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	public DataLoader() {
		employeeDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	private org.w3c.dom.Document getDocument(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		Resource resource = new ClassPathResource(xmlFileName);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return documentBuilder.parse(resource.getFile());
	}

	private String getChildTagContent(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);

		if (nodeList.getLength() == 1) {
			return nodeList.item(0).getTextContent();
		}

		return null;
	}

	private Boolean zeroOrOneToBoolean(String stringBoolean) {
		if (stringBoolean.equals("0")) {
			return new Boolean(false);
		} else {
			return new Boolean(true);
		}
	}

	public Map<BigInteger, Supplier> loadSuppliers(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Supplier> result = new HashMap<BigInteger, Supplier>();

		NodeList suppliersList = document.getElementsByTagName("supplier");
		for (int i = 0; i < suppliersList.getLength(); ++i) {
			Node supplierNode = suppliersList.item(i);
			Element supplier = (Element) supplierNode;

			BigInteger supplierID = new BigInteger(supplier.getElementsByTagName("SupplierID").item(0).getTextContent());
			String companyName = supplier.getElementsByTagName("CompanyName").item(0).getTextContent();
			String contactName = supplier.getElementsByTagName("ContactName").item(0).getTextContent();
			String contactTitle = supplier.getElementsByTagName("ContactTitle").item(0).getTextContent();
			String address = supplier.getElementsByTagName("Address").item(0).getTextContent();
			String city = supplier.getElementsByTagName("City").item(0).getTextContent();
			String region = getChildTagContent(supplier, "Region");
			String postalCode = supplier.getElementsByTagName("PostalCode").item(0).getTextContent();
			String country = supplier.getElementsByTagName("Country").item(0).getTextContent();
			String phone = supplier.getElementsByTagName("Phone").item(0).getTextContent();
			String fax = getChildTagContent(supplier, "Fax");
			String homePage = getChildTagContent(supplier, "HomePage");

			// System.out.println("Dodaje " + supplierID + " " + companyName);
			Supplier newSupplier = new Supplier(supplierID, companyName);
			newSupplier.setContactName(contactName);
			newSupplier.setContactTitle(contactTitle);
			newSupplier.setAddress(address);
			newSupplier.setCity(city);
			newSupplier.setRegion(region);
			newSupplier.setPostalCode(postalCode);
			newSupplier.setCountry(country);
			newSupplier.setPhone(phone);
			newSupplier.setFax(fax);
			newSupplier.setHomePage(homePage);

			result.put(supplierID, newSupplier);
		}

		return result;
	}

	public Map<BigInteger, Category> loadCategories(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Category> result = new HashMap<BigInteger, Category>();

		NodeList categoriesList = document.getElementsByTagName("category");
		for (int i = 0; i < categoriesList.getLength(); ++i) {
			Node categoryNode = categoriesList.item(i);
			Element category = (Element) categoryNode;

			BigInteger categoryID = new BigInteger(category.getElementsByTagName("CategoryID").item(0).getTextContent());
			String categoryName = category.getElementsByTagName("CategoryName").item(0).getTextContent();
			String description = category.getElementsByTagName("Description").item(0).getTextContent();

			Category newCategory = new Category(categoryID, categoryName);
			newCategory.setDescription(description);

			result.put(categoryID, newCategory);
		}

		return result;
	}

	public Map<String, Customer> loadCustomers(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<String, Customer> result = new HashMap<String, Customer>();

		NodeList customerList = document.getElementsByTagName("customer");
		for (int i = 0; i < customerList.getLength(); ++i) {
			Node customerNode = customerList.item(i);
			Element customer = (Element) customerNode;

			String customerID = customer.getElementsByTagName("CustomerID").item(0).getTextContent();
			String companyName = customer.getElementsByTagName("CompanyName").item(0).getTextContent();
			String contactName = customer.getElementsByTagName("ContactName").item(0).getTextContent();
			String contactTitle = customer.getElementsByTagName("ContactTitle").item(0).getTextContent();
			String address = customer.getElementsByTagName("Address").item(0).getTextContent();
			String city = customer.getElementsByTagName("City").item(0).getTextContent();
			String region = getChildTagContent(customer, "Region");
			String postalCode = getChildTagContent(customer, "PostalCode");
			String country = customer.getElementsByTagName("Country").item(0).getTextContent();
			String phone = customer.getElementsByTagName("Phone").item(0).getTextContent();
			String fax = getChildTagContent(customer, "Fax");

			Customer newCustomer = new Customer(customerID, companyName);
			newCustomer.setContactName(contactName);
			newCustomer.setContactTitle(contactTitle);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setRegion(region);
			newCustomer.setPostalCode(postalCode);
			newCustomer.setCountry(country);
			newCustomer.setPhone(phone);
			newCustomer.setFax(fax);

			result.put(customerID, newCustomer);
		}

		return result;
	}

	public Map<BigInteger, Shipper> loadShippers(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Shipper> result = new HashMap<BigInteger, Shipper>();

		NodeList shippersList = document.getElementsByTagName("shipper");
		for (int i = 0; i < shippersList.getLength(); ++i) {
			Node shipperNode = shippersList.item(i);
			Element shipper = (Element) shipperNode;

			BigInteger shipperID = new BigInteger(shipper.getElementsByTagName("ShipperID").item(0).getTextContent());
			String companyName = shipper.getElementsByTagName("CompanyName").item(0).getTextContent();
			String phone = shipper.getElementsByTagName("Phone").item(0).getTextContent();

			Shipper newShipper = new Shipper(shipperID);
			newShipper.setCompanyName(companyName);
			newShipper.setPhone(phone);

			result.put(shipperID, newShipper);
		}

		return result;
	}

	public Map<BigInteger, Product> loadProducts(String xmlFileName, Map<BigInteger, Supplier> suppliers, Map<BigInteger, Category> categories)
			throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Product> result = new HashMap<BigInteger, Product>();

		NodeList productsList = document.getElementsByTagName("product");
		for (int i = 0; i < productsList.getLength(); ++i) {
			Node productNode = productsList.item(i);
			Element product = (Element) productNode;

			BigInteger productID = new BigInteger(product.getElementsByTagName("ProductID").item(0).getTextContent());
			String productName = product.getElementsByTagName("ProductName").item(0).getTextContent();
			Supplier supplier = suppliers.get(new BigInteger(product.getElementsByTagName("SupplierID").item(0).getTextContent()));
			Category category = categories.get(new BigInteger(product.getElementsByTagName("CategoryID").item(0).getTextContent()));
			String quantityPerUnit = product.getElementsByTagName("QuantityPerUnit").item(0).getTextContent();
			BigDecimal unitPrice = new BigDecimal(product.getElementsByTagName("UnitPrice").item(0).getTextContent());
			Short unitsInStock = new Short(product.getElementsByTagName("UnitsInStock").item(0).getTextContent());
			Short unitsOnOrder = new Short(product.getElementsByTagName("UnitsOnOrder").item(0).getTextContent());
			Short reorderLevel = new Short(product.getElementsByTagName("ReorderLevel").item(0).getTextContent());
			Boolean discontinued = zeroOrOneToBoolean(product.getElementsByTagName("Discontinued").item(0).getTextContent());

			Product newProduct = new Product(productID, productName, discontinued);
			newProduct.setSupplier(supplier);
			newProduct.setCategory(category);
			newProduct.setQuantityPerUnit(quantityPerUnit);
			newProduct.setUnitPrice(unitPrice);
			newProduct.setUnitsInStock(unitsInStock);
			newProduct.setUnitsOnOrder(unitsOnOrder);
			newProduct.setReorderLevel(reorderLevel);

			result.put(productID, newProduct);
		}

		return result;
	}

	public Map<BigInteger, Employee> loadEmployees(String xmlFileName) throws ParserConfigurationException, SAXException, IOException, ParseException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Employee> result = new HashMap<BigInteger, Employee>();
		Map<BigInteger, BigInteger> reportsToMap = new HashMap<BigInteger, BigInteger>();

		NodeList employeeList = document.getElementsByTagName("employee");
		for (int i = 0; i < employeeList.getLength(); ++i) {
			Node productNode = employeeList.item(i);
			Element employee = (Element) productNode;

			BigInteger employeeID = new BigInteger(employee.getElementsByTagName("EmployeeID").item(0).getTextContent());
			String lastName = employee.getElementsByTagName("LastName").item(0).getTextContent();
			String firstName = employee.getElementsByTagName("FirstName").item(0).getTextContent();
			String title = employee.getElementsByTagName("Title").item(0).getTextContent();
			String titleOfCourtesy = employee.getElementsByTagName("TitleOfCourtesy").item(0).getTextContent();
			Date birthDate = employeeDateFormat.parse(employee.getElementsByTagName("BirthDate").item(0).getTextContent());
			Date hireDate = employeeDateFormat.parse(employee.getElementsByTagName("HireDate").item(0).getTextContent());
			String address = employee.getElementsByTagName("Address").item(0).getTextContent();
			String city = employee.getElementsByTagName("City").item(0).getTextContent();
			String region = getChildTagContent(employee, "Region");
			String postalCode = employee.getElementsByTagName("PostalCode").item(0).getTextContent();
			String country = employee.getElementsByTagName("Country").item(0).getTextContent();
			String homePhone = employee.getElementsByTagName("HomePhone").item(0).getTextContent();
			String extension = employee.getElementsByTagName("Extension").item(0).getTextContent();
			String notes = employee.getElementsByTagName("Notes").item(0).getTextContent();
			BigInteger reportsTo = new BigInteger(employee.getElementsByTagName("ReportsTo").item(0).getTextContent());

			reportsToMap.put(employeeID, reportsTo);

			Employee newEmployee = new Employee(employeeID, firstName, lastName);
			newEmployee.setTitle(title);
			newEmployee.setTitleOfCourtesy(titleOfCourtesy);
			newEmployee.setBirthDate(birthDate);
			newEmployee.setHireDate(hireDate);
			newEmployee.setAddress(address);
			newEmployee.setCity(city);
			newEmployee.setRegion(region);
			newEmployee.setPostalCode(postalCode);
			newEmployee.setCountry(country);
			newEmployee.setHomePhone(homePhone);
			newEmployee.setExtension(extension);
			newEmployee.setNotes(notes);
			newEmployee.setReportsTo(result.get(reportsToMap.get(employeeID)));

			result.put(employeeID, newEmployee);
		}

		for (BigInteger employeeID : reportsToMap.keySet()) {
			Employee employee = result.get(employeeID);
			Employee reportsTo = result.get(reportsToMap.get(employeeID));
			employee.setReportsTo(reportsTo);
		}

		return result;
	}

	public Map<BigInteger, Order> loadOrders(String xmlFileName, Map<String, Customer> customers, Map<BigInteger, Employee> employees,
			Map<BigInteger, Shipper> shippers) throws ParserConfigurationException, SAXException, IOException, ParseException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, Order> result = new HashMap<BigInteger, Order>();

		NodeList orderList = document.getElementsByTagName("order");
		for (int i = 0; i < orderList.getLength(); ++i) {
			Node productNode = orderList.item(i);
			Element order = (Element) productNode;

			BigInteger orderID = new BigInteger(order.getElementsByTagName("OrderID").item(0).getTextContent());
			Customer customer = customers.get(order.getElementsByTagName("CustomerID").item(0).getTextContent());
			Employee employee = employees.get(order.getElementsByTagName("EmployeeID").item(0).getTextContent());
			Date orderDate = employeeDateFormat.parse(order.getElementsByTagName("OrderDate").item(0).getTextContent());
			Date requiredDate = employeeDateFormat.parse(order.getElementsByTagName("RequiredDate").item(0).getTextContent());
			Date shippedDate = employeeDateFormat.parse(order.getElementsByTagName("ShippedDate").item(0).getTextContent());
			Shipper shipper = shippers.get(new BigInteger(order.getElementsByTagName("ShipVia").item(0).getTextContent()));
			BigDecimal freight = new BigDecimal(order.getElementsByTagName("Freight").item(0).getTextContent());
			String shipName = order.getElementsByTagName("ShipName").item(0).getTextContent();
			String shipAddress = order.getElementsByTagName("ShipAddress").item(0).getTextContent();
			String shipCity = order.getElementsByTagName("ShipCity").item(0).getTextContent();
			String shipRegion = getChildTagContent(order, "ShipRegion");
			String shipPostalCode = getChildTagContent(order, "ShipPostalCode");
			String shipCountry = order.getElementsByTagName("ShipCountry").item(0).getTextContent();

			Order newOrder = new Order(orderID);
			newOrder.setCustomerID(customer);
			newOrder.setEmployeeID(employee);
			newOrder.setOrderDate(orderDate);
			newOrder.setRequiredDate(requiredDate);
			newOrder.setShippedDate(shippedDate);
			newOrder.setShipVia(shipper);
			newOrder.setFreight(freight);
			newOrder.setShipName(shipName);
			newOrder.setShipAddress(shipAddress);
			newOrder.setShipCity(shipCity);
			newOrder.setShipRegion(shipRegion);
			newOrder.setShipPostalCode(shipPostalCode);
			newOrder.setShipCountry(shipCountry);

			// System.out.println("Dodaje " + shipCountry + " " + orderDate);

			result.put(orderID, newOrder);
		}

		return result;
	}

	public Map<Integer, OrderDetails> loadOrderDetails(String xmlFileName, Map<BigInteger, Order> orders, Map<BigInteger, Product> products)
			throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);
		Map<Integer, OrderDetails> result = new HashMap<Integer, OrderDetails>();

		NodeList orderDetailsList = document.getElementsByTagName("orderdetail");
		for (int i = 0; i < orderDetailsList.getLength(); ++i) {
			Node orderDetailsNode = orderDetailsList.item(i);
			Element orderDetails = (Element) orderDetailsNode;

			int orderDetailsID = Integer.valueOf(orderDetails.getElementsByTagName("odID").item(0).getTextContent());
			Order order = orders.get(new BigInteger(orderDetails.getElementsByTagName("OrderID").item(0).getTextContent()));
			Product product = products.get(new BigInteger(orderDetails.getElementsByTagName("ProductID").item(0).getTextContent()));
			BigDecimal unitPrice = new BigDecimal(orderDetails.getElementsByTagName("UnitPrice").item(0).getTextContent());
			Integer quantity = new Integer(orderDetails.getElementsByTagName("Quantity").item(0).getTextContent());
			Float discount = new Float(orderDetails.getElementsByTagName("Discount").item(0).getTextContent());


			OrderDetails newOrderDetails = new OrderDetails(orderDetailsID, order, product);
			newOrderDetails.setUnitPrice(unitPrice);
			newOrderDetails.setQuantity(quantity);
			newOrderDetails.setDiscount(discount);
			
			result.put(new Integer(orderDetailsID), newOrderDetails);
		}
		return result;
	}

	public Map<BigInteger, OrderWithOrderDetailsEmbedded> loadOrdersWithEmbeddedOrderDetails(String xmlFileName, Map<String, Customer> customers,
			Map<BigInteger, Employee> employees, Map<BigInteger, Shipper> shippers) throws ParserConfigurationException, SAXException, IOException,
			ParseException {
		Document document = getDocument(xmlFileName);
		Map<BigInteger, OrderWithOrderDetailsEmbedded> result = new HashMap<BigInteger, OrderWithOrderDetailsEmbedded>();

		NodeList orderList = document.getElementsByTagName("order");
		for (int i = 0; i < orderList.getLength(); ++i) {
			Node productNode = orderList.item(i);
			Element order = (Element) productNode;

			BigInteger orderID = new BigInteger(order.getElementsByTagName("OrderID").item(0).getTextContent());
			Customer customer = customers.get(order.getElementsByTagName("CustomerID").item(0).getTextContent());
			Employee employee = employees.get(order.getElementsByTagName("EmployeeID").item(0).getTextContent());
			Date orderDate = employeeDateFormat.parse(order.getElementsByTagName("OrderDate").item(0).getTextContent());
			Date requiredDate = employeeDateFormat.parse(order.getElementsByTagName("RequiredDate").item(0).getTextContent());
			Date shippedDate = employeeDateFormat.parse(order.getElementsByTagName("ShippedDate").item(0).getTextContent());
			Shipper shipper = shippers.get(new BigInteger(order.getElementsByTagName("ShipVia").item(0).getTextContent()));
			BigDecimal freight = new BigDecimal(order.getElementsByTagName("Freight").item(0).getTextContent());
			String shipName = order.getElementsByTagName("ShipName").item(0).getTextContent();
			String shipAddress = order.getElementsByTagName("ShipAddress").item(0).getTextContent();
			String shipCity = order.getElementsByTagName("ShipCity").item(0).getTextContent();
			String shipRegion = getChildTagContent(order, "ShipRegion");
			String shipPostalCode = getChildTagContent(order, "ShipPostalCode");
			String shipCountry = order.getElementsByTagName("ShipCountry").item(0).getTextContent();

			OrderWithOrderDetailsEmbedded newOrder = new OrderWithOrderDetailsEmbedded(orderID);
			newOrder.setCustomer(customer);
			newOrder.setEmployee(employee);
			newOrder.setOrderDate(orderDate);
			newOrder.setRequiredDate(requiredDate);
			newOrder.setShippedDate(shippedDate);
			newOrder.setShipVia(shipper);
			newOrder.setFreight(freight);
			newOrder.setShipName(shipName);
			newOrder.setShipAddress(shipAddress);
			newOrder.setShipCity(shipCity);
			newOrder.setShipRegion(shipRegion);
			newOrder.setShipPostalCode(shipPostalCode);
			newOrder.setShipCountry(shipCountry);

			result.put(orderID, newOrder);
		}

		return result;
	}

	public void loadOrderDetailsAndEmbeddThemInOrder(String xmlFileName, Map<BigInteger, OrderWithOrderDetailsEmbedded> orders,
			Map<BigInteger, Product> products) throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlFileName);

		NodeList orderDetailsList = document.getElementsByTagName("orderdetail");
		for (int i = 0; i < orderDetailsList.getLength(); ++i) {
			Node orderDetailsNode = orderDetailsList.item(i);
			Element orderDetails = (Element) orderDetailsNode;

			BigInteger orderDetailsID = new BigInteger(orderDetails.getElementsByTagName("odID").item(0).getTextContent());
			OrderWithOrderDetailsEmbedded orderWithOrderDetailsEmbedded = orders.get(new BigInteger(orderDetails.getElementsByTagName("OrderID")
					.item(0).getTextContent()));
			Product product = products.get(new BigInteger(orderDetails.getElementsByTagName("ProductID").item(0).getTextContent()));
			BigDecimal unitPrice = new BigDecimal(orderDetails.getElementsByTagName("UnitPrice").item(0).getTextContent());
			Integer quantity = new Integer(orderDetails.getElementsByTagName("Quantity").item(0).getTextContent());
			Float discount = new Float(orderDetails.getElementsByTagName("Discount").item(0).getTextContent());

			OrderDetailsForEmbedding newOrderDetails = new OrderDetailsForEmbedding(orderDetailsID, product);
			newOrderDetails.setUnitPrice(unitPrice);
			newOrderDetails.setQuantity(quantity);
			newOrderDetails.setDiscount(discount);

			orderWithOrderDetailsEmbedded.addOrderDetailsForEmbedding(newOrderDetails);
		}

	}

}
