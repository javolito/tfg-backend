package javier.tfg.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class RoomBook {
	@Id
	private Long id;
	@Index
	private Long customerId;
	private List<String> nfcKeys;
	@Index
	private boolean active;
	@Index
	private int roomNumber;
	private Date startDate;
	private Date endDate;
	private int people;

	public RoomBook() {
		// TODO Auto-generated constructor stub
	}

	public RoomBook(Long customerId, boolean active, int roomNumber, Date startDate, Date endDate, int people) {
		super();
		this.customerId = customerId;
		this.nfcKeys = new ArrayList<>();
		this.nfcKeys.add("");
		this.active = active;
		this.roomNumber = roomNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.people = people;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<String> getNfcKeys() {
		return nfcKeys;
	}

	public void setNfcKeys(List<String> nfcKeys) {
		this.nfcKeys = nfcKeys;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public boolean addNFCKey(String nfcTag){
		if(this.nfcKeys == null){
			this.nfcKeys = new ArrayList<>();
		}
		if(!this.nfcKeys.contains(nfcTag)){
			this.nfcKeys.add(nfcTag);
			return true;
		}
		return false;
	}

}
