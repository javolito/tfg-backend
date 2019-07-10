package javier.tfg.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Purchase {
	@Id
	private Long id;
	@Index
	private Long serviceId;
	@Index
	private Long customerId;
	private Long waiterId;
	private String productsList;
	private Long catalogId;
	private Date payDate;
	private Date bookDate;
	private double amount;
	
	public Purchase() {
		// TODO Auto-generated constructor stub
	}

	public Purchase(Long serviceId, Long customerId, Long catalogId, Date payDate, Date bookDate, double amount) {
		this.serviceId = serviceId;
		this.customerId = customerId;
		this.catalogId = catalogId;
		this.payDate = payDate;
		this.bookDate = bookDate;
		this.amount = amount;
	}
	
	public Purchase(Long serviceId, Long customerId, String productsList, Long waiterId, Date payDate, Date bookDate, double amount) {
		this.serviceId = serviceId;
		this.customerId = customerId;
		this.waiterId = waiterId;
		this.productsList = productsList;
		this.payDate = payDate;
		this.bookDate = bookDate;
		this.amount = amount;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}

	public String getProductsList() {
		return productsList;
	}

	public void setProductsList(String productsList) {
		this.productsList = productsList;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
