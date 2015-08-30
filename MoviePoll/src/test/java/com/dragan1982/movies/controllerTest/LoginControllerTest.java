package com.dragan1982.movies.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dragan1982.movies.controller.LoginController;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	
		@Mock
	    private LoginController loginController;
	 
	    private MockMvc mockMvc;
	 
	    @Before
	    public void setup() {
	        
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	 
	        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();	 
	    }	    
	    
	    @Test
	    public void testLogin() throws Exception{
	    	
	    	this.mockMvc.perform(get("/login"))                
	                .andExpect(status().isOk())
	    	 		.andExpect(view().name("login"));
	    }
}