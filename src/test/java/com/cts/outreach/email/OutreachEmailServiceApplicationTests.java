package com.cts.outreach.email;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cts.outreach.email.controller.EmailController;
import com.cts.outreach.email.entity.UserEntity;
import com.cts.outreach.email.model.NewEventMailReqModel;
import com.cts.outreach.email.model.ReportMailReqModel;
import com.cts.outreach.email.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.cts.outreach.event"})
@AutoConfigureMockMvc
@SpringBootTest
class OutreachEmailServiceApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	MvcResult mvcResult;
	
	@Autowired
	EmailController eventController;
	
	@MockBean
	UserService userService;
	
	@Test
	public void sendNewEventMailTest() throws Exception {
		NewEventMailReqModel mailRequest = new NewEventMailReqModel(
				"", "test event1", "test location1", "test date1");
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(new UserEntity("user1", "password1", "klarun205@gmail.com", "USER"));
		users.add(new UserEntity("user2", "password2", "testmail2", "USER"));
		users.add(new UserEntity("user3", "password3", "testmail3", "USER"));
		
		Mockito.when(userService.getUsers()).thenReturn(users);
		
		this.mockMvc.perform(post("/v1/sendNewEventMail")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(mailRequest)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void sendReportInsightMailTest() throws Exception {
		ReportMailReqModel mailRequest = new ReportMailReqModel("25", "6", "120",
				"40", "60", "20", "Chennai", "25", "Coimbatore", "22", "Cochin", "21", "12", "18", "8");
		
		this.mockMvc.perform(post("/v1/sendReportInsightMail")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(mailRequest)))
		.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
    }

}
