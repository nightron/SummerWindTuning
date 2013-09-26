package dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Shipper;
import entities.Supplier;

public class ListingDAOImpl implements ListingDAO {

	@PersistenceContext(unitName = "persistenceUN")
	protected EntityManager entityManager;

	public Map<String, Integer> q1_countOrdersByCountry() {
		entityManager.createQuery(" select count(orderr) from entities.Order orderr group by orderr.shipCountry ")
				.getResultList();
		return null;
	}

	public Map<String, Double> q2_averageOrderTimeByYear() {
//		entityManager
//				.createQuery(
//						"select avg(DATEDIFF(ShippedDate, OrderDate)), year(ShippedDate) from Orders group by year(ShippedDate)")
//				.getResultList();
		List result = entityManager.createQuery("SELECT AVG(DATE_PART('day', ShippedDate\\:\\:timestamp-OrderDate\\:\\:timestamp)), DATE_PART('year',ShippedDate) " +
				"FROM Order " +
				"GROUP BY DATE_PART('year',ShippedDate)")
				.getResultList();
		return null;
	}

	public Map<Supplier, Integer> q3_soldProductsBySupplier() {
		// List result =
		// entityManager.createQuery("select orderDetails.quantity, supplier.companyName from"
		// +
		// " entities.OrderDetails as orderDetails " +
		// " inner join entities.Product as product where orderDetails.product = product.productID "
		// +
		// " inner join join entities.Supplier where supplier on product.supplier = supplier.supplierID"
		// ).getResultList();
		return null;
	}

	public Map<String, Double> q4_amountByDayOfWeek() {

		return null;
	}

	public Map<Supplier, Map<String, Double>> q5_valueOfSoldProductsBySupplierByYear() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Shipper, Map<String, Double>> q6_averageValueOfShippedProductsByShipperByYear() {
		// TODO Auto-generated method stub
		return null;
	}

}
