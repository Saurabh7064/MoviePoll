package com.dragan1982.movies.controllerTest;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dragan1982.movies.controller.UserController;
import com.dragan1982.movies.dao.UserDao;
import com.dragan1982.movies.model.Role;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.model.UserStatus;
import com.dragan1982.movies.service.RoleService;
import com.dragan1982.movies.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	private UserService mockUserService;
	
	@Mock
	private RoleService mockRoleService;
	
	@Mock
	private UserDao mockUserDao;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;
	
	private Principal principal = new Principal() {
		
		@Override
		public String getName() {
			return "principalName";
		}
	};  
	
	@Before
	public void setUp(){
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void testUserList() throws Exception {
		
		User user1 = new User();
		User user2 = new User();
        user1.setId(1);
        user2.setId(2);
        
	    List<User> mockUserList = Arrays.asList(user1, user2);
	    
       when(mockUserService.listUsers()).thenReturn(mockUserList);
              
       User user = new User();
       user.setId(1);   
       
       when(mockUserDao.findUserByName(principal.getName())).thenReturn(user);
       
        this.mockMvc.perform(get("/editUsers").principal(principal))
        .andExpect(status().isOk())        
		.andExpect(model().attributeExists("user"))
        .andExpect(model().attribute("userList", mockUserService.listUsers()))
        .andExpect(model().attribute("username", principal.getName()))
        .andExpect(model().attribute("hasVoted", user.isHasVoted()))
        .andExpect(model().attribute("roleList", mockRoleService.listRoles()))
        .andExpect(model().size(5))
 		.andExpect(view().name("/user/editUsers"));  
        
	}
	
	@Test
	public void testAddUser() throws Exception {      
		   
		this.mockMvc.perform(get("/addUser").principal(principal))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("roleList")) 	   
		.andExpect(model().size(2))
		.andExpect(view().name("/user/userForm"));      
		    
	}
	
	@Test
	public void testSaveUser() throws Exception {	
		
		Role Admin = new Role();
		Role User = new Role();
		List<Role> mockRoles = Arrays.asList(Admin, User);
		
		when(mockRoleService.listRoles()).thenReturn(mockRoles);
				
		User user = new User();
		user.setId(1);
		user.setUsername("username");
		user.setStatus(UserStatus.ACTIVE);
		user.setPassword("userpassword");
		user.setRoles(mockRoleService.listRoles());		
				
		mockUserService.saveUser(user);

	    this.mockMvc.perform(post("/saveUser")   
	    .param("username", user.getUsername()) 
	    .param("password", user.getPassword())
	    .param(UserStatus.ACTIVE.toString(), user.getStatus().toString())
	    .param("roles", user.getRoles().toString()))	    
	    
	    .andExpect(status().isOk())
	    .andExpect(model().attributeExists("user"))
	    .andExpect(model().attribute("roleList", mockRoles))
	    .andExpect(model().size(2))	    
	    .andExpect(view().name("/user/userForm"));	       
	}
	
	@Test
	public void testGetUser() throws Exception {	
		
		User user = new User();
		
		when(mockUserService.getUser(anyInt())).thenReturn(user);
		
		Role Admin = new Role();
		Role User = new Role();
		List<Role> mockRoles = Arrays.asList(Admin, User);
		
		when(mockRoleService.listRoles()).thenReturn(mockRoles);
	   
	    this.mockMvc.perform(get("/getUser/{userId}", anyInt()))
	    .andExpect(status().isOk())
	    .andExpect(model().attributeExists("user"))
	    .andExpect(model().attribute("edit", "editUser"))
	    .andExpect(model().attribute("roleList", mockRoles))
	    .andExpect(model().size(3))
		.andExpect(view().name("/user/userForm"));      
	    
	}	
	
	@Test
	public void testResetVote() throws Exception {
		
		User user = new User();
		
		this.mockMvc.perform(get("/resetVote/{userId}", user.getId()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/editUsers"));	
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
	User user = new User();	
		
	    this.mockMvc.perform(get("/deleteUser/{userId}", user.getId()))
	   .andExpect(status().isFound())
	   .andExpect(redirectedUrl("/editUsers"));
	}

}