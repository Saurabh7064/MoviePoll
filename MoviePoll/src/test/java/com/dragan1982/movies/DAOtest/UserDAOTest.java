package com.dragan1982.movies.DAOtest;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.model.Role;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.model.UserStatus;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@Transactional
public class UserDAOTest {
	
		@Autowired
		private SessionFactory sessionFactory;
		private Session currentSession;
		

	    @Before
	    public void setUp() throws Exception {
	        
	        currentSession = sessionFactory.getCurrentSession();
	    }	    

	   
	    @Test
	    public void shouldHaveASessionFactory() {
	        assertNotNull(sessionFactory);
	    }
	    
	    @Test
	    public void testSaveUser() {
	        assertEquals(0, currentSession.createQuery("from User").list().size());
	        User user = new User();
	        user.setUsername("User1");
	        user.setId(1);
	        user.setPassword("pwd");
	        user.setStatus(UserStatus.ACTIVE);
	        	        
	        @SuppressWarnings("unchecked")
			List<Role> roles = currentSession.createQuery("from Role").list();
	        
	        user.setRoles(roles); 
	       
	        currentSession.merge(user);
	        currentSession.flush();
	        assertEquals(1, currentSession.createQuery("from User").list().size());        
	       
	    }
	    
	    @Test 
	    public void testGetUser(){
	    	
	    	assertEquals(0, currentSession.createQuery("from User").list().size());
	    	
	    	testSaveUser();	    	

	    	User user = (User)currentSession.load(User.class, 1);
	    	
	    	assertNotNull(user);
	    	assertEquals(1, currentSession.createQuery("from User").list().size());           
	    	
	    }
	    	    
	    @Test
	    public void testListUsers() {
	    	    
        assertTrue(currentSession.createQuery("from User").list().isEmpty());
        
        testSaveUser();
      
        assertFalse(currentSession.createQuery("from User").list().isEmpty());
        
	    }	   
	    
	    @Test 
	    public void testDeleteUser(){
	    		    	
	    	testSaveUser();
	    	
	    	currentSession.delete(currentSession.createQuery("from User where username='User1'").uniqueResult());
	        currentSession.flush(); 
	    	assertTrue(currentSession.createQuery("from User").list().isEmpty());
	    	
	    }
	    
	    @Test 
	    public void testUserVote(){
	    		    	
	    	testSaveUser();
	    	
	    	User user = (User)currentSession.createQuery("from User where username='User1'").uniqueResult();
	    	assertEquals(false, user.isHasVoted());
	    	user.setHasVoted(true);
	    	assertEquals(true, user.isHasVoted());    	
	    	
	    }
}	   