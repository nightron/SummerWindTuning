package tuning.Loader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import entities.Customer;
import entities.Employee;
import entities.Shipper;


public class OrderWithOrderDetailsEmbedded {
	
	@Id
	private BigInteger orderID;

	private Date orderDate;

	private Date requiredDate;

	private Date shippedDate;
	private Shipper shipVia;

	private BigDecimal freight;

	private String shipName;

	private String shipAddress;

	private String shipCity;

	private String shipRegion;

	private String shipPostalCode;

	private String shipCountry;

	private Customer customer;

	private Employee employee;

	private List<OrderDetailsForEmbedding> orderDetails;
	
	public List<OrderDetailsForEmbedding> getOrderDetails() {
		return orderDetails;
	}

	public void addOrderDetailsForEmbedding(OrderDetailsForEmbedding orderDetailsForEmbedding){
		orderDetails.add(orderDetailsForEmbedding);
	}
	
	public void setOrderDetails(List<OrderDetailsForEmbedding> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public OrderWithOrderDetailsEmbedded() {
		this(null);
	}

	public OrderWithOrderDetailsEmbedded(BigInteger orderID) {
		this.orderID = orderID;
		this.orderDetails = new ArrayList<OrderDetailsForEmbedding>();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BigInteger getOrderID() {
		return orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Shipper getShipVia() {
		return shipVia;
	}

	public void setShipVia(Shipper shipVia) {
		this.shipVia = shipVia;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipRegion() {
		return shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public String getShipPostalCode() {
		return shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
}
