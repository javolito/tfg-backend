package javier.tfg.processes;

import javier.tfg.domain.Customer;

public class CustomerDTO {
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String sessionToken;
	private String oneSignalId;
	private int roomNumber;
	private String stripeId;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerDTO(Customer customer){
		this.id = customer.getId();
		this.name = customer.getName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
		this.sessionToken = customer.getSessionToken();
		this.oneSignalId = customer.getOneSignalId();
		this.roomNumber = customer.getRoomNumber();
		this.stripeId = customer.getStripeId();
	}

}
