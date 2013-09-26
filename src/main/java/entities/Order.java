package entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	BigInteger orderID;

	@ManyToOne
	@JoinColumn(name = "CustomerID")
	Customer customerID;

	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	Employee employeeID;

	private Date orderDate;

	private Date requiredDate;

	private Date shippedDate;

	@ManyToOne
	@JoinColumn(name = "ShipVia")
	private Shipper shipVia;
	private BigDecimal freight;
	private String shipName;
	private String shipAddress;
	private String shipCity;
	private String shipRegion;
	private String shipPostalCode;
	private String shipCountry;

	@OneToMany(cascade=CascadeType.ALL)
	private Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();

	public Order() {
	}

	public Order(BigInteger orderId) {
		this.orderID = orderId;
	}

	public Order(BigInteger orderID, Customer customerID, Employee employeeID, Date orderDate, Date requiredDate, Date shippedDate, Shipper shipVia,
			BigDecimal freight, String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.shipVia = shipVia;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
	}

	public BigInteger getOrderID() {
		return orderID;
	}

	public void setOrderID(BigInteger orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

	public Employee getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
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
	
	public void addOrderDetails(OrderDetails orderDetails){
		this.orderDetails.add(orderDetails);
	}
	
	public void removeOrdderDetails(OrderDetails orderDetails){
		this.orderDetails.remove(orderDetails);
	}

}
