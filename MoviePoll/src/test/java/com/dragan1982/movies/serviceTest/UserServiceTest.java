package com.dragan1982.movies.serviceTest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.dragan1982.movies.dao.impl.UserDaoImpl;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.service.UserService;
import com.dragan1982.movies.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
		@Mock
	    private UserDaoImpl mockUserDao;
		
		@InjectMocks
	    private UserService userService = new UserServiceImpl();	
	    
	    @Test
	    public void testSaveUser() {
	    	
	    	User user = new User();
	    	
	    	doAnswer(new Answer<User>() {
                @Override
                public User answer(InvocationOnMock invocation) throws Throwable {
                    User user = (User) invocation.getArguments()[0];
                    user.setId(1);
                    return user;
                }
            }).when(mockUserDao).saveUser(any(User.class));
	    		    	
	    	assertEquals(0, user.getId());
	    	
	    	mockUserDao.saveUser(user);
	    	
	    	assertNotNull(user.getId());
	        assertSame(1, user.getId());
	    }
	    
	    @Test
	    public void testListUsers() {
	    	
	    	User user1 = new User();
	    	User user2 = new User();
	    	user1.setId(1);
	    	user2.setId(2);
	    	
	    	List<User> userList = Arrays.asList(user1, user2);
	    	
	    	assertTrue(mockUserDao.listUsers().isEmpty()); 
	    	 
	    	when(mockUserDao.listUsers()).thenReturn(userList);
	    	
	    	assertFalse(mockUserDao.listUsers().isEmpty());
	    	assertNotNull(mockUserDao.listUsers().get(1)); 	
	    }
	    
	    @Test
	    public void testGetUser() {
	    	
	    	User user = new User();
	    	user.setId(1);
	    	
	    	assertNull(mockUserDao.getUser(user.getId()));
	    	
	    	when(mockUserDao.getUser(anyInt())).thenReturn(user);
	    	
	    	assertNotNull(mockUserDao.getUser(user.getId()));
	    	
	    }
	    
	    @Test
	    public void testDeleteUser() { 
	    	
	    	doNothing().when(mockUserDao).deleteUser(anyInt());
	    		   	
	    	mockUserDao.deleteUser(anyInt());
	    	
	    	verify(mockUserDao, times(1)).deleteUser(anyInt());
	    	
	 	}  
	    
	    @Test
	    public void testUserVote() throws Exception { 
	    	
	    	User user = new User();
	    	
	    	doNothing().when(mockUserDao).userVoted(user.getId(), true);
	    	
	    	mockUserDao.userVoted(user.getId(), true);
	    	
	    	verify(mockUserDao, times(1)).userVoted(user.getId(), true);	    	
	    }
}	    





