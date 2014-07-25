package com.gymwer.dpa.entity;

import java.time.LocalDateTime;

import com.gymwer.util.base.entity.Entity;

public class TicketDetail extends Entity {

	private Site site;
	private int item;
	private LocalDateTime finished;
	
	public TicketDetail(int id) {
		
		super(id);
		
	}

	public Site getSite() {
		return site;
	}

	public int getItem() {
		return item;
	}

	public LocalDateTime getFinished() {
		return finished;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public void setFinished(LocalDateTime finished) {
		this.finished = finished;
	}

}
