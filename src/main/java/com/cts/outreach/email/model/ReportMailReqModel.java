package com.cts.outreach.email.model;

public class ReportMailReqModel {
	
	private String totalEvents;
	private String upcomingEvents;
	private String totalVolunteers;
	private String eventGoodCount;
	private String eventAverageCount;
	private String eventLowCount;
	private String location1;
	private String location2;
	private String location3;
	private String location1Count;
	private String location2Count;
	private String location3Count;
	private String volunteerGoodCount;
	private String volunteerAverageCount;
	private String volunteerLowCount;
	

	public String getTotalEvents() {
		return totalEvents;
	}


	public String getUpcomingEvents() {
		return upcomingEvents;
	}


	public String getTotalVolunteers() {
		return totalVolunteers;
	}


	public String getEventGoodCount() {
		return eventGoodCount;
	}


	public String getEventAverageCount() {
		return eventAverageCount;
	}


	public String getEventLowCount() {
		return eventLowCount;
	}


	public String getLocation1() {
		return location1;
	}


	public String getLocation2() {
		return location2;
	}


	public String getLocation3() {
		return location3;
	}


	public String getLocation1Count() {
		return location1Count;
	}


	public String getLocation2Count() {
		return location2Count;
	}


	public String getLocation3Count() {
		return location3Count;
	}


	public String getVolunteerGoodCount() {
		return volunteerGoodCount;
	}


	public String getVolunteerAverageCount() {
		return volunteerAverageCount;
	}


	public String getVolunteerLowCount() {
		return volunteerLowCount;
	}


	public ReportMailReqModel(String totalEvents, String upcomingEvents, String totalVolunteers, String eventGoodCount,
			String eventAverageCount, String eventLowCount, String location1, String location2, String location3,
			String location1Count, String location2Count, String location3Count, String volunteerGoodCount,
			String volunteerAverageCount, String volunteerLowCount) {
		super();
		this.totalEvents = totalEvents;
		this.upcomingEvents = upcomingEvents;
		this.totalVolunteers = totalVolunteers;
		this.eventGoodCount = eventGoodCount;
		this.eventAverageCount = eventAverageCount;
		this.eventLowCount = eventLowCount;
		this.location1 = location1;
		this.location2 = location2;
		this.location3 = location3;
		this.location1Count = location1Count;
		this.location2Count = location2Count;
		this.location3Count = location3Count;
		this.volunteerGoodCount = volunteerGoodCount;
		this.volunteerAverageCount = volunteerAverageCount;
		this.volunteerLowCount = volunteerLowCount;
	}
}
