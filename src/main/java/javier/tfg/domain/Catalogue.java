package javier.tfg.domain;

import java.util.Date;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Catalogue {
	@Id
	private Long id;
	@Index
	private Long serviceId;
	private Long productId;
	private float price;
	private String productName;
	private String productDescription;
	private String productImageURL;
	private int productType;
	
	public Catalogue() {
		// TODO Auto-generated constructor stub
	}

	public Catalogue(Long serviceId, Long productId, float price, String productName, 
			String productDescription, String productImageURL, int productType) {
		this.serviceId = serviceId;
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productImageURL = productImageURL;
	}

	public Catalogue(Long serviceId, Long productId, float price, String productName, 
			String productDescription, String productImageURL) {
		this.serviceId = serviceId;
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productImageURL = productImageURL;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public Long getId() {
		return id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductImageURL() {
		return productImageURL;
	}

	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
	
}
