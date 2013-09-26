package entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Product {

	@Id
	private BigInteger productID;

	private String productName;

	private String quantityPerUnit;

	private BigDecimal unitPrice;

	private Short unitsInStock;

	private Short unitsOnOrder;

	private Short reorderLevel;

	private boolean discontinued;


	@ManyToOne
	@JoinColumn(name="Supplier")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name="Category")
	private Category category;

	public Product(){
		this(null, null, false);
	}
	
	public Product(long productID, String productName, boolean discontinued){
		this(BigInteger.valueOf(productID), productName, discontinued);
	}
	
	public Product(BigInteger productID, String productName, boolean discontinued) {
		this.productID = productID;
		this.productName = productName;
		this.discontinued = discontinued;
	}

	public BigInteger getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Short getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Short unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Short getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(Short unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public Short getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Short reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplierID) {
		this.supplier = supplierID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category categoryID) {
		this.category = categoryID;
	}
}
