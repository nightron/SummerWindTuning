package entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Customers")
public class Customer {

	@Id
	@Column(name="customerID")
	private String customerID;

	@Column(name="CompanyName")
	private String companyName;
	@Column(name="ContactName")
	private String contactName;
	@Column(name="ContactTitle")
	private String contactTitle;
	@Column(name="Address")
	private String address;
	@Column(name="City")
	private String city;
	@Column(name="Region")
	private String region;
	@Column(name="PostalCode")
	private String postalCode;
	@Column(name="Country")
	private String country;
	@Column(name="Phone")
	private String phone;
	@Column(name="Fax")
	private String fax;

	@OneToMany(fetch=FetchType.LAZY)
	private Collection<CustomerDemographics> customerDemographics;

	public Collection<CustomerDemographics> getCustomerDemographics() {
		return customerDemographics;
	}

	public void setCustomerDemographics(
			Collection<CustomerDemographics> customerDemographics) {
		this.customerDemographics = customerDemographics;
	}

	public Customer() {
		this(null, null);
	}

	public Customer(String customerID, String companyName) {
		
		this.customerID = customerID;
		this.companyName = companyName;
		this.customerDemographics = new ArrayList<CustomerDemographics>();
	}

	public String getCustomerID() {
		return customerID;
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

}