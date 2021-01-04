package com.cg.rent.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.rent.model.User;
import com.cg.rent.service.AdminServiceImpl;
import com.cg.rent.service.UserManagementServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminServiceImpl adminService;

	@MockBean
	private UserManagementServiceImpl userManager;

	/**
	 * testCreateAdmin()
	 * testing whether admin is created.
	 * @throws Exception
	 */
	@Test
	public void testCreateAdmin() throws Exception
	{
		String URI ="/admin/newAdmin";
		User user =  new User();
		user.setUId(2);
		user.setAddress("Bangalore");
		user.setContact("1234567890");
		user.setEmail("poorni@gmail.com");
		user.setName("Poornisha");
		user.setRole("Admin");
		user.setPassword("password");

		String jsonInput = this.converttoJson(user);
		Mockito.when(userManager.registerUser(Mockito.any(User.class))).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	/**
	 * getUserById()
	 * Getting user by id.
	 * @throws Exception
	 */
	@Test
	public void getUserById() throws Exception{
		String URI ="/admin/FetchUserById/{UId}";
		User user = new User();
		user.setUId(3);
		user.setName("Pooja");
		user.setEmail("sweety@gmail.com");
		user.setContact("1234567890");
		user.setAddress("Rasipuram");
		user.setRole("Admin");
		user.setPassword("password");
		String jsonInput = this.converttoJson(user);
		Mockito.when(adminService.getUserById(Mockito.anyInt())).thenReturn(user);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 21).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);

	}
	/**
	 * testFetchAllUser()
	 * fetching all user.
	 * @throws Exception
	 */
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
	 * @param ticket
	 * @return
	 * @throws JsonProcessingException
	 */
	private String converttoJson(Object vehicle) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(vehicle);
	}
}