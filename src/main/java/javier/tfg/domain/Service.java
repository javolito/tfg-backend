package javier.tfg.domain;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Service {
	@Id
	private Long id;
	private String name;
	private String description;
	@Index
	private boolean barService;
	private boolean temp;
	private Date startHour;
	private Date endHour;
	private String imageURL;
	
	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Service(String name, String description, boolean barService) {
		this.name = name;
		this.description = description;
		this.barService = barService;
	}
	
	public Service(String name, String description, boolean barService, boolean temp, Date startHour, Date endHour) {
		super();
		this.name = name;
		this.description = description;
		this.barService = barService;
		this.temp = temp;
		this.startHour = startHour;
		this.endHour = endHour;
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


	public boolean isBarService() {
		return barService;
	}

	public void setBarService(boolean barService) {
		this.barService = barService;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTemp() {
		return temp;
	}

	public void setTemp(boolean temp) {
		this.temp = temp;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
