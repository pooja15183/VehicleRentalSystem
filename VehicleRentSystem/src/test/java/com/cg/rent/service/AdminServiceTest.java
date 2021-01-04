package com.cg.rent.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.rent.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



	@RunWith(SpringRunner.class)
	@WebMvcTest(value = AdminService.class)
	public class AdminServiceTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private AdminService adminService;
	
	    
	    @Test
	    public void testFetchAllUser() throws Exception{
	        String URI= "/admin/FetchAllUsers";
	     

	        User user = new User();
	        user.setUId(1);
	        user.setName("Sweety");
	        user.setPassword("sweety123");
	        user.setAddress("Rasipuram");
	        user.setContact("9630258741");
	        user.setEmail("sweety@msn.com");
	        
	        User user1 = new User();
	        user.setUId(2);
	        user.setName("Pooja");
	        user.setPassword("Pooja123");
	        user.setAddress("Salem");
	        user.setContact("9630258741");
	        user.setEmail("pooja@mail.com");
	        
	        List<User> userList = new ArrayList<>();
	        userList.add(user);
	        userList.add(user1);


	        String jsonInput = this.converttoJson(userList);

	        Mockito.when(adminService.fetchAllUsers()).thenReturn(userList);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }
	    
	    /**
	     * Convert Object into Json String by using Jackson ObjectMapper
	     * @param user
	     * @return
	     * @throws JsonProcessingException
	     */
	     
	    private String converttoJson(Object User) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(User);
	    }
	}

