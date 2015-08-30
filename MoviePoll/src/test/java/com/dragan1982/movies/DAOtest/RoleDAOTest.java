package com.dragan1982.movies.DAOtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@Transactional
public class RoleDAOTest {
	
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
	    public void testSaveRole() { 
	    
	        assertEquals(0, currentSession.createQuery("from Role").list().size());
	        Role role = new Role();
	        role.setRoleName("User");
	        role.setId(1L);
	        
	        @SuppressWarnings("unchecked")
			List<User> users = currentSession.createQuery("from User").list();
			
	        role.setUsers(users);
	       
	        currentSession.merge(role);
	        currentSession.flush();
	        assertEquals(1L, currentSession.createQuery("from Role").list().size());        
	       
	    }
	    
	    @Test 
	    public void testGetRole(){
	    	
	    	assertEquals(0, currentSession.createQuery("from Role").list().size());
	    	
	    	testSaveRole();	    	

	    	Role role = (Role)currentSession.load(Role.class, 1L);
	    	
	    	assertNotNull(role);
	    	assertEquals(1L, currentSession.createQuery("from Role").list().size());           
	    	
	    }
	    	    
	    @Test
	    public void testListRoles() {
	    	    
        assertTrue(currentSession.createQuery("from Role").list().isEmpty());
        
        testSaveRole();
      
        assertFalse(currentSession.createQuery("from Role").list().isEmpty());
        
	    }	   
	    
	    @Test 
	    public void testDeleteRole(){
	    		    	
	    	testSaveRole();
	    	
	    	currentSession.delete(currentSession.createQuery("from Role where rolename='User'").uniqueResult());
	        currentSession.flush(); 
	    	assertTrue(currentSession.createQuery("from Role").list().isEmpty());
	    	
	    }
}	   