package cni.tn.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue
	private long id;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "DAATE")
	private Date daate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDaate() {
		return daate;
	}

	public void setDaate(Date daate) {
		this.daate = daate;
	}

	public Person(long id, String firstName, String lastName, String address, Date daate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.daate = daate;
	}

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String address, Date daate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.daate = daate;
	}

}
