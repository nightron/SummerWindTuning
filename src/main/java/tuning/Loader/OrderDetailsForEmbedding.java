package tuning.Loader;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import entities.Product;


public class OrderDetailsForEmbedding {
	@Id
	private BigInteger orderDetailsID;

	private BigDecimal unitPrice;

	private Integer quantity;

	private Float discount;

	//@DBRef
	private Product product;

	public OrderDetailsForEmbedding() {
		this(null, null);
	}

	public OrderDetailsForEmbedding(long orderDetailsID,Product product){
		this(BigInteger.valueOf(orderDetailsID), product);
	}
	
	public OrderDetailsForEmbedding(BigInteger orderDetailsID, Product product) {
		this.orderDetailsID = orderDetailsID;
		this.product = product;
	}

	public BigInteger getOrderDetailsID() {
		return orderDetailsID;
	}

	public Product getProduct() {
		return product;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Product getProducts() {
		return product;
	}

	public void setProducts(Product products) {
		this.product = products;
	}

}
