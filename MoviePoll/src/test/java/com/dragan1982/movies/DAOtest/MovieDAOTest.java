package com.dragan1982.movies.DAOtest;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.dragan1982.movies.model.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@Transactional
public class MovieDAOTest {
	
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
	    public void testSaveMovie() {
	        assertEquals(0, currentSession.createQuery("from Movie").list().size());
	        Movie movie = new Movie();
	        movie.setTitle("Rambo");
	        movie.setId(1L);
	        movie.setGenre("action");
	        movie.setYear("1980");
	        movie.setActors("Silvester Stalone");
	        movie.setAddedBy("User");
	        
	        currentSession.merge(movie);
	        currentSession.flush();
	        assertEquals(1, currentSession.createQuery("from Movie").list().size());        
	       
	    }
	    
	    @Test 
	    public void testGetMovie(){
	    	
	    	assertEquals(0, currentSession.createQuery("from Movie").list().size());
	    	
	    	testSaveMovie();	    	

	    	Movie movie = (Movie)currentSession.load(Movie.class, 1L);
	    	
	    	assertNotNull(movie);
	    	assertEquals(1, currentSession.createQuery("from Movie").list().size());           
	    	
	    }
	    	    
	    @Test
	    public void testListMovies() {
	    	    
        assertTrue(currentSession.createQuery("from Movie").list().isEmpty());
        
        testSaveMovie();
      
        assertFalse(currentSession.createQuery("from Movie").list().isEmpty());
        
	    }	   
	    
	    @Test 
	    public void testDeleteMovie(){
	    		    	
	    	testSaveMovie();
	    	
	    	currentSession.delete(currentSession.createQuery("from Movie where title='Rambo'").uniqueResult());
	        currentSession.flush(); 
	    	assertTrue(currentSession.createQuery("from Movie").list().isEmpty());
	    	
	    }
	    
	    @Test 
	    public void testVoteMovie(){
	    		    	
	    	testSaveMovie();
	    	
	    	Movie movie = (Movie)currentSession.createQuery("from Movie where title='Rambo'").uniqueResult();
	    	assertEquals(0, movie.getVotes());
	    	movie.setVotes(movie.getVotes()+1);
	    	assertEquals(1, movie.getVotes());    	
	    	
	    }
}
	   

