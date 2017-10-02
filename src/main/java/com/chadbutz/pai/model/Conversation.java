package com.chadbutz.pai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "conversation")
public class Conversation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2231832900585771719L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String dialogue;

	private String response;
	
	private String data;
	
	private String action;

	public Conversation(String dialogue, String response, String data, String action) {
		this.dialogue = dialogue;
		this.response = response;
		this.data = data;
		this.action = action;
	}

	Conversation() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDialogue() {
		return dialogue;
	}

	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}