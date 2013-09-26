package entities;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="Employees")
public class Employee {

	@Id
	@Column(name = "EmployeeID")
	private BigInteger employeeID;
	@Column(name = "LastName")
	private String lastName;
	@Column(name = "FirstName")
	private String firstName;
	@Column(name = "Title")
	private String title;
	@Column(name = "TitleOfCourtesy")
	private String titleOfCourtesy;
	@Column(name = "BirthDate")
	private Date birthDate;
	@Column(name = "HireDate")
	private Date hireDate;
	@Column(name = "Address")
	private String address;
	@Column(name = "City")
	private String city;
	@Column(name = "Region")
	private String region;
	@Column(name = "PostalCode")
	private String postalCode;
	@Column(name = "Country")
	private String country;
	@Column(name = "HomePhone")
	private String homePhone;
	@Column(name = "Extension")
	private String extension;
	@Column(name = "Photo")
	private byte[] photo;
	@Column(name = "Notes", length=1024 )
	private String notes;
	
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="reportsTo")
	private Employee reportsTo;
    
//    @OneToMany(mappedBy="reportsTo")
//    private Set<Employee> subordinates = new HashSet<Employee>();

	private String photoPath;
	
	public Employee(){
		this(null, null, null);
	}
	
	public Employee(BigInteger employeeID, String firstName, String lastName) {
		this.employeeID = employeeID;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public BigInteger getEmployeeID() {
		return employeeID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleOfCourtesy() {
		return titleOfCourtesy;
	}

	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
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

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Employee getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
//	public String toString(){
//		return employeeID + ", " + lastName 
//				+", " + firstName
//				+", " + title
//				+", " + city
//				+", " + region
//				+", " + postalCode
//				+", " + country
//				+", " + homePhone
//				+", " + extension
//				+", " + photo
//				+", " + notes;
//	}

}
