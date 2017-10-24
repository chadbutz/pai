package com.chadbutz.pai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "volunteer")
public class Volunteer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2231832900585771719L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;
	
	private String email;
	
	private String confirmed;

	public Volunteer(String firstName, String lastName, String email, String confirmed) {
		this.confirmed = confirmed;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	Volunteer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}