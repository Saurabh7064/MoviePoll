package com.dragan1982.movies.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
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

import com.dragan1982.movies.dao.impl.MovieDaoImpl;
import com.dragan1982.movies.model.Movie;
import com.dragan1982.movies.service.MovieService;
import com.dragan1982.movies.service.impl.MovieServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
	
		@Mock
	    private MovieDaoImpl mockMovieDao;
		
		@InjectMocks
	    private MovieService movieService = new MovieServiceImpl();	
	    
	    @Test
	    public void testSaveMovie() {
	    	
	    	Movie movie = new Movie();
	    	
	    	doAnswer(new Answer<Movie>() {
                @Override
                public Movie answer(InvocationOnMock invocation) throws Throwable {
                    Movie movie = (Movie) invocation.getArguments()[0];
                    movie.setId(1L);
                    return movie;
                }
            }).when(mockMovieDao).saveMovie(any(Movie.class)); // doAnswer with an movie object when void method saveMovie() is tested/called
	    		    	
	    	assertNull(movie.getId()); // check if movie object exists before saving movie
	    	
	    	mockMovieDao.saveMovie(movie);
	    	
	    	assertNotNull(movie.getId()); // check if movie object exists after saving movie
	        assertSame(1L, movie.getId()); // check movie object assigned id
	    }
	    
	    @Test
	    public void testListMovies() {
	    	
	    	Movie movie1 = new Movie();
	    	Movie movie2 = new Movie();
	    	movie1.setId(1L);
	    	movie2.setId(2L);
	    	
	    	List<Movie> moviesList = Arrays.asList(movie1, movie2);
	    	
	    	assertTrue(mockMovieDao.listMovies().isEmpty()); 
	    	 
	    	when(mockMovieDao.listMovies()).thenReturn(moviesList);
	    	
	    	assertFalse(mockMovieDao.listMovies().isEmpty());
	    	assertNotNull(mockMovieDao.listMovies().get(1)); 	
	    }
	    
	    @Test
	    public void testGetMovie() {
	    	
	    	Movie movie = new Movie();
	    	movie.setId(1L);
	    	
	    	assertNull(mockMovieDao.getMovie(movie.getId()));
	    	
	    	when(mockMovieDao.getMovie(anyLong())).thenReturn(movie);
	    	
	    	assertNotNull(mockMovieDao.getMovie(movie.getId()));
	    	
	    }
	    
	    @Test
	    public void testDeleteMovie() { 
	    	
	    	doNothing().when(mockMovieDao).deleteMovie(anyLong());
	    		   	
	    	mockMovieDao.deleteMovie(anyLong());
	    	
	    	verify(mockMovieDao, times(1)).deleteMovie(anyLong());
	    	
	 	}  
	    
	    @Test
	    public void testVoteMovie() throws Exception { 
	    	
	    	doNothing().when(mockMovieDao).deleteMovie(anyLong());
	    	
	    	mockMovieDao.voteMovie(anyLong());
	    	
	    	verify(mockMovieDao, times(1)).voteMovie(anyLong());
	    }	    
}
