package com.yonaxtics.gymwer.dpa.ticket.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.dpa.plan.entity.*;
import com.yonaxtics.gymwer.dpa.ticketDetail.entity.*;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public class Ticket extends Entity {
	
	private Plan plan;
	private String code;
	private LocalDateTime finished; 
	private List<TicketDetail> ticketDetailList;	
	
	public Ticket(int id) {		
		super(id);		
	}
	
	@Override
	public boolean isEmpty() {	
		return code== null || code == "";
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
