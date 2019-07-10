package javier.tfg.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Customer {
	@Id
	private Long id;
	private String name;
	private String lastName;
	@Index
	private String email;
	private String password;
	@Index
	private String sessionToken;
	private String oneSignalId;
	private String stripeId;
	@Index
	private int roomNumber;
	@Index
	private String nfcKey;
	
	public String getNfcKey() {
		return nfcKey;
	}

	public void setNfcKey(String nfcKey) {
		this.nfcKey = nfcKey;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String lastName, String email, String password, int roomNumber) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roomNumber = roomNumber;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getOneSignalId() {
		return oneSignalId;
	}

	public void setOneSignalId(String oneSignalId) {
		this.oneSignalId = oneSignalId;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

}
