package com.gymwer.dpa.entity;

import java.util.List;

import com.gymwer.set.entity.Person;

public class Client extends Person {

	private String birthday;
	private float weight;
	private float height;
	private char age;
    private int sex;
    private List<Ticket> ticketList;
    private List<Ticket> ticketHistoricalList;
	
	public Client(int id) {
		
		super(id); 
		
	}

	public String getBirthday() {
		return birthday;
	}

	public float getWeight() {
		return weight;
	}

	public float getHeight() {
		return height;
	}

	public char getAge() {
		return age;
	}

	public int getSex() {
		return sex;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public List<Ticket> getTicketHistoricalList() {
		return ticketHistoricalList;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setAge(char age) {
		this.age = age;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public void setTicketHistoricalList(List<Ticket> ticketHistoricalList) {
		this.ticketHistoricalList = ticketHistoricalList;
	}
	
	
	

}
