package com.cts.outreach.email.model;

public class NewEventMailReqModel {
	
	private String recipients;
	private String eventname;
	private String location;
	private String eventdate;
	public String getRecipients() {
		return recipients;
	}
	public String getEventname() {
		return eventname;
	}
	public String getLocation() {
		return location;
	}
	public String getEventdate() {
		return eventdate;
	}
	public NewEventMailReqModel(String recipients, String eventname, String location, String eventdate) {
		super();
		this.recipients = recipients;
		this.eventname = eventname;
		this.location = location;
		this.eventdate = eventdate;
	}

}

