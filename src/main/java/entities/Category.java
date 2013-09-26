package entities;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;




@Entity
public class Category {
	
	@Id
	@Column(name="CategoryID")
	private BigInteger categoryID;

	@Column(name="Description")
	private String description;
	
	@Column(name="CategoryName")
	private String categoryName;

	@Column(name="Picture")
	private byte[] picture;
	
	public BigInteger getCategoryID() {
		return categoryID;
	}

	public Category(){
		this(null, null);
	}
	
	public Category(long categoryId, String categoryName){
		this(BigInteger.valueOf(categoryId), categoryName);
	}
	
	public Category(BigInteger categoryId, String categoryName) {
		this.categoryID = categoryId;
		this.categoryName = categoryName;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}




}
