package entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="supplier")
public class Supplier {
	@Id
	private BigInteger supplierID;

	private String companyName;

	private String contactName;

	private String contactTitle;

	private String address;

	private String city;

	private String region;

	private String postalCode;

	private String country;

	private String phone;

	private String fax;

	private String homePage;
	
	@OneToMany
	Collection<Product> products = new HashSet<Product>(0);

	public Supplier() {
	}

	public Supplier(BigInteger supplierID, String companyName, Set<Product> products) {
		super();
		this.supplierID = supplierID;
		this.companyName = companyName;
		this.products = products;
	}

	public Supplier(long supplierID, String companyName){
		this(BigInteger.valueOf(supplierID), companyName);
	}
	
	public Supplier(BigInteger supplierID, String companyName) {
		this.supplierID = supplierID;
		this.companyName = companyName;
		this.products = new ArrayList<Product>();
	}


	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public BigInteger getSupplierID() {
		return supplierID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
}