package entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="customerdemographics")
public class CustomerDemographics {

	@Id
	@Column(name="ID")
	private BigInteger id;

	@OneToMany(fetch=FetchType.LAZY)
	private Collection <Customer> customers;

	@Column(name="CustomerDescription")
	private String customerDescription;
	
	public CustomerDemographics(String customerDescription){
		//this.id = new BigInteger();
		this.customers = new ArrayList<Customer>();
		this.customerDescription = customerDescription;
	}
	
	public BigInteger getId() {
		return id;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}
	
	public void addCustomers(Customer customers){
		this.customers.add(customers);
	}
	
	public void addCustomers(List<Customer> customers){
		this.customers.addAll(customers);
	}
	
	public String getCustomerDescription(){
		return customerDescription;
	}
}