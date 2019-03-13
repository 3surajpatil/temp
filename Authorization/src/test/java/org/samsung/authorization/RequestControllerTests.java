package org.samsung.authorization;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.samsung.controller.RequestController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestControllerTests {

	private MockMvc mockMVC;
	
	
	@InjectMocks
	private RequestController requestController;
	
	@Before
	public void setup() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		mockMVC=MockMvcBuilders.standaloneSetup(requestController).build();		
	}
	
	
	@Test
	public void welcomeTest() {
		
		try {
			mockMVC.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public void validateUserTest(){
		
		
		
	}


}
