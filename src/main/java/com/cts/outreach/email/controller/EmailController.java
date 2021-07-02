package com.cts.outreach.email.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.outreach.email.config.EmailConfig;
import com.cts.outreach.email.config.NewEventMailConfig;
import com.cts.outreach.email.config.ReportMailConfig;
import com.cts.outreach.email.entity.UserEntity;
import com.cts.outreach.email.model.NewEventMailReqModel;
import com.cts.outreach.email.model.ReportMailReqModel;
import com.cts.outreach.email.service.UserService;

@RestController
public class EmailController {
	
	private Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private NewEventMailConfig newEventMailConfig;
	
	@Autowired
	private ReportMailConfig reportMailConfig;
	
	@Autowired
	UserService userService;
	
	String recipients = "";
	
	@PostMapping("/v1/sendNewEventMail")
	public void sendNewEventMail(@RequestBody NewEventMailReqModel mailRequest) throws Exception{
		
		LOGGER.info("Received a request to send notification mail");
		
		List<UserEntity> users = userService.getUsers();
		users.forEach(((user) -> {
			if (user.getEmail().contains("test")) {
				
			} else {
				recipients = recipients + ", " + user.getEmail();
			}

		}));
		
		LOGGER.info(recipients);
		
        String mailContent = newEventMailConfig.getNeweventmailLine1() + newEventMailConfig.getNeweventmailLine2() +
        		newEventMailConfig.getNeweventmailLine3() + newEventMailConfig.getNeweventmailLine4() +
        		newEventMailConfig.getNeweventmailLine5() + newEventMailConfig.getNeweventmailLine6() +
        		newEventMailConfig.getNeweventmailLine7() + newEventMailConfig.getNeweventmailLine8() +
        		newEventMailConfig.getNeweventmailLink() + newEventMailConfig.getNeweventmailLine9() +
        		newEventMailConfig.getNeweventmailLine10() + mailRequest.getEventname() +
        		newEventMailConfig.getNeweventmailLine11() + mailRequest.getEventdate() +
        		newEventMailConfig.getNeweventmailLine12() + mailRequest.getLocation() + 
        		newEventMailConfig.getNeweventmailLine13();
        
        sendmail(recipients, newEventMailConfig.getNeweventsubject(), mailContent);
        
	}
	
	@PostMapping("/v1/sendReportInsightMail")
	public void sendReportInsightMail(@RequestBody ReportMailReqModel mailRequest) throws Exception{
		
		LOGGER.info("Received a request to send notification mail");
		
		recipients = "arunkumar.kulanthaivel@cognizant.com, klarun205@gmail.com";
		
		LOGGER.info(recipients);
		
        String mailContent = reportMailConfig.getReportinsightmailLine1() + reportMailConfig.getReportinsightmailLine2() +
        		reportMailConfig.getReportinsightmailLine3() + reportMailConfig.getReportinsightmailLine4() +
        		reportMailConfig.getReportinsightmailLine5() +
        		new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date()) +
        		reportMailConfig.getReportinsightmailLine6() +
        		mailRequest.getTotalEvents() + reportMailConfig.getReportinsightmailLine7() + 
        		mailRequest.getUpcomingEvents() + reportMailConfig.getReportinsightmailLine8() +
        		mailRequest.getTotalVolunteers() + reportMailConfig.getReportinsightmailLine9() +
        		reportMailConfig.getReportinsightmailLine10() + mailRequest.getEventGoodCount() +
        		reportMailConfig.getReportinsightmailLine11() + mailRequest.getEventAverageCount() +
        		reportMailConfig.getReportinsightmailLine12() + mailRequest.getEventLowCount() +
        		reportMailConfig.getReportinsightmailLine13() + mailRequest.getLocation1() +
        		reportMailConfig.getReportinsightmailLine14() + mailRequest.getLocation1Count() +
        		reportMailConfig.getReportinsightmailLine15() + mailRequest.getLocation2() +
        		reportMailConfig.getReportinsightmailLine16() + mailRequest.getLocation2Count() +
        		reportMailConfig.getReportinsightmailLine17() + mailRequest.getLocation2() +
        		reportMailConfig.getReportinsightmailLine18() + mailRequest.getLocation3Count() +
        		reportMailConfig.getReportinsightmailLine19() + mailRequest.getVolunteerGoodCount() +
        		reportMailConfig.getReportinsightmailLine20() + mailRequest.getVolunteerAverageCount() +
        		reportMailConfig.getReportinsightmailLine21() + mailRequest.getVolunteerLowCount() +
        		reportMailConfig.getReportinsightmailLine22();
        
        sendmail(recipients, reportMailConfig.getReportinsightsubject(), mailContent);
        
	}
	
	private void sendmail(String recipients, String subject, String mailContent) throws Exception {
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		Session session = Session.getDefaultInstance(props);
		
		MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailConfig.getFrom(), emailConfig.getFromname()));
        msg.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipients)
        );
        msg.setSubject(subject);
        msg.setContent(mailContent, "text/html");
        
        Transport transport = session.getTransport();
        
        try {
            transport.connect(emailConfig.getHost(), emailConfig.getUser(), emailConfig.getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            transport.close();
        }
	}

}
