package com.revature.pojos;

import java.sql.Date;

public class TRForm 
{
	private String eventName;
	private String eventTime;
	private String eventType;
	private Date eventDate;
	private int eventCost;
	private String eventGradingFormat;
	private String passingGrade;
	private String eventDescription;
	private String grade;
	private String status;
	private int formID;
	private int EID;
	private double payback;
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventCost() {
		return eventCost;
	}

	public void setEventCost(int eventCost) {
		this.eventCost = eventCost;
	}

	public String getEventGradingFormat() {
		return eventGradingFormat;
	}

	public void setEventGradingFormat(String eventGradingFormat) {
		this.eventGradingFormat = eventGradingFormat;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public TRForm(String eventName, String eventTime, String eventType, Date eventDate, int eventCost,
			String eventGradingFormat, String passingGrade, String eventDescription) {
		super();
		this.eventName = eventName;
		this.eventTime = eventTime;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.eventCost = eventCost;
		this.eventGradingFormat = eventGradingFormat;
		this.passingGrade = passingGrade;
		this.eventDescription = eventDescription;
		switch(eventType)
		{
		case"University Courses":
			this.payback = .80;
			break;
		case"Seminar":
			this.payback = .60;
			break;
		case"Certification Preparation Class":
			this.payback = .75;
			break;
		case"Certification":
			this.payback = 1;
			break;
		case"Technical Training":
			this.payback = .90;
			break;
		case"Others":
			this.payback = .30;
			break;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFormID() {
		return formID;
	}

	public void setFormID(int formID) {
		this.formID = formID;
	}

	public int getEID() {
		return EID;
	}

	public void setEID(int eID) {
		EID = eID;
	}

	public double getPayback() {
		return payback;
	}

	public void setPayback(int payback) {
		this.payback = payback;
	}

}
