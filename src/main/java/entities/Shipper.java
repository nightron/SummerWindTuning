package entities;

import java.math.BigInteger;

import javax.persistence.*;



@Entity
@Table(name="Shippers")
public class Shipper {

	@Id
	private BigInteger shipperID;
	
	private String companyName;
	
	private String phone;

	public Shipper(){
		this(null);
	}
	
	public Shipper(BigInteger shipperID){
		this.shipperID = shipperID;
	}

	public BigInteger getShipperID() {
		return shipperID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
