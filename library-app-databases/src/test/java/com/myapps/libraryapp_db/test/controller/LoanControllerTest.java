package com.myapps.libraryapp_db.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.test.util.TestUtil;

@SpringBootTest
public class LoanControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mvc;
	private TestUtil testUtil;

	@BeforeEach
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		testUtil = new TestUtil();
	}

	@Test
	public void testGetAll() throws Exception {
		String uri = "/loans";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		Loan[] allLoans = testUtil.mapFromJson(content, Loan[].class);
		assertTrue(allLoans.length > 0);
	}
	

}
