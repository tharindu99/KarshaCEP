package com.lsf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class stockPrice_grp {

	String AllDates;
	Double PRC;
	Double PseudoPRC;
	Double Turnover;
	
	@Id
	public String getAllDates() {
		return AllDates;
	}
	public void setAllDates(String allDates) {
		AllDates = allDates;
	}
	public Double getPRC() {
		return PRC;
	}
	public void setPRC(Double pRC) {
		PRC = pRC;
	}
	public Double getPseudoPRC() {
		return PseudoPRC;
	}
	public void setPseudoPRC(Double pseudoPRC) {
		PseudoPRC = pseudoPRC;
	}
	public Double getTurnover() {
		return Turnover;
	}
	public void setTurnover(Double turnover) {
		Turnover = turnover;
	}
	
}
