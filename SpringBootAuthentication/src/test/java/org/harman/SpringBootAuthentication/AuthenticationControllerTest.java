package org.harman.SpringBootAuthentication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.samsung.controller.AuthenticationController;
import org.samsung.dao.AuthenticationService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

	@Mock
	private AuthenticationService authenticationService;
	
	
	@InjectMocks
	private AuthenticationController authenticationController;

	
	private MockMvc mockMVC;
	
	@Before
	public void setup() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		mockMVC=MockMvcBuilders.standaloneSetup(authenticationController).build();
		
	}
	
	
	@Test
	public void defaultMethodTest() {
		
		try {
			mockMVC.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void validateUserTest(){
		try{
			MultiValueMap<String,String> params=new LinkedMultiValueMap<>();

			params.add("username", "suraj.patil@harman.com");
			params.add("password","patil");
			
			Mockito.when(authenticationService.validateCredentials("suraj.patil@harman.com", "patil")).thenReturn(true);
			
			mockMVC.perform(MockMvcRequestBuilders.post("/validate").params(params))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("success"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
