package com.gymwer.dpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.gymwer.util.base.entity.Entity;

public class Ticket extends Entity {

	
	private Plan plan;
	private String code;
	private LocalDateTime finished; 
	private List<TicketDetail> ticketDetailList;
	
	
	
	public Ticket(int id) {
		
		super(id);
		
	}






	public Plan getPlan() {
		return plan;
	}



	public String getCode() {
		return code;
	}



	public LocalDateTime getFinished() {
		return finished;
	}







	public void setPlan(Plan plan) {
		this.plan = plan;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public void setFinished(LocalDateTime finished) {
		this.finished = finished;
	}



	public List<TicketDetail> getTicketDetailList() {
		return ticketDetailList;
	}



	public void setTicketDetailList(List<TicketDetail> ticketDetailList) {
		this.ticketDetailList = ticketDetailList;
	}

}
