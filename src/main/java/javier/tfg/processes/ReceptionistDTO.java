package javier.tfg.processes;

import javier.tfg.domain.Receptionist;

public class ReceptionistDTO {

	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String sessionToken;
	private boolean isReceptionist;
	public ReceptionistDTO(Receptionist receptionist) {
		this.id = receptionist.getId();
		this.name = receptionist.getName();
		this.lastName = receptionist.getLastName();
		this.email = receptionist.getEmail();
		this.sessionToken = receptionist.getSessionToken();
		this.isReceptionist = true;
	}

}
