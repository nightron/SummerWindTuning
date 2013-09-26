package entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name="OrderDetails")
public class OrderDetails {

	@Id
	private int odID;


	@ManyToOne
	@JoinColumn(name="OrderID")
	private Order orderID;

	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product productID;
	private BigDecimal unitPrice;
	private int quantity;
	private float discount;
	
	public OrderDetails(int orderDetailsID,Order order,Product productID){
		this.odID = orderDetailsID;
		this.orderID = order;
		this.productID = productID;
	}

	public OrderDetails(int odID, Order orderID, Product productID,
			BigDecimal unitPrice, int quantity, float discount) {
		super();
		this.odID = odID;
		this.orderID = orderID;
		this.productID = productID;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}

	public OrderDetails() {
	}

	public int getOdID() {
		return odID;
	}

	public void setOdID(int odID) {
		this.odID = odID;
	}

	public Order getOrderID() {
		return orderID;
	}

	public void setOrderID(Order orderID) {
		this.orderID = orderID;
	}

	public Product getProductID() {
		return productID;
	}

	public void setProductID(Product productID) {
		this.productID = productID;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
}