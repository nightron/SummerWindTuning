package dao;

import java.util.Map;

import entities.Shipper;
import entities.Supplier;


public interface ListingDAO {

	public Map<String, Integer> q1_countOrdersByCountry();
	public Map<String, Double> q2_averageOrderTimeByYear();
	public Map<Supplier, Integer> q3_soldProductsBySupplier();
	public Map<String, Double> q4_amountByDayOfWeek();
	public Map<Supplier, Map<String, Double>> q5_valueOfSoldProductsBySupplierByYear();
	public Map<Shipper, Map<String, Double>> q6_averageValueOfShippedProductsByShipperByYear();
}
