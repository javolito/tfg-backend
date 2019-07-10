package javier.tfg.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Product {
	@Id
	private Long id;
	private String name;
	private int type;
	private String description;
	private String imageURL;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Product(String name, String description, int type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
