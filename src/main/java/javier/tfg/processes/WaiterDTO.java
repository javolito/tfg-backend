package javier.tfg.processes;

import javier.tfg.domain.Waiter;

public class WaiterDTO {

	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String sessionToken;
	
	public WaiterDTO(Waiter waiter) {
		this.id = waiter.getId();
		this.name = waiter.getName();
		this.lastName = waiter.getLastName();
		this.email = waiter.getEmail();
		this.sessionToken = waiter.getSessionToken();
	}

}
