package com.dragan1982.movies.controllerTest;

import static org.mockito.Matchers.anyLong;
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

import com.dragan1982.movies.controller.MovieController;
import com.dragan1982.movies.dao.UserDao;
import com.dragan1982.movies.model.Movie;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.service.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {
	
	@Mock
	private MovieService mockMovieService;
	
	@Mock
	private UserDao mockUserDao;		
	
	@InjectMocks
	private MovieController movieController;
	
	private MockMvc mockMvc;
	
	private Principal principal = new Principal() {
		
		@Override
		public String getName() {
			return "principalName";
		}
	};  
	
	@Before
	public void setUp(){
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
	}
	
		
	@Test
	public void testGetMovieList() throws Exception {
		
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
        movie1.setId(1L);
        movie2.setId(2L);
        
	    List<Movie> mockMoviesList = Arrays.asList(movie1, movie2);
	    
       when(mockMovieService.listMovies()).thenReturn(mockMoviesList);
              
       User user = new User();
       user.setId(1);
       user.setHasVoted(true);
       user.setUsername("user1");               
       
       when(mockUserDao.findUserByName(principal.getName())).thenReturn(user);
       
        this.mockMvc.perform(get("/").principal(principal))
        .andExpect(status().isOk())
        .andExpect(model().attribute("movieList", mockMovieService.listMovies()))
        .andExpect(model().attribute("username", principal.getName()))  
        .andExpect(model().attribute("hasVoted", user.isHasVoted()))
        .andExpect(model().size(3))
 		.andExpect(view().name("/rank")); 
        
	}


@Test
public void testAddMovie() throws Exception {      
   
    this.mockMvc.perform(get("/addMovie").principal(principal))
    .andExpect(status().isOk())
    .andExpect(model().attributeExists("movie"))
    .andExpect(model().attribute("username", principal.getName()))  
    .andExpect(model().size(2))
	.andExpect(view().name("/movie/movieForm"));      
    
}

@Test
public void testEditMovies() throws Exception {
	
	Movie movie1 = new Movie();
	Movie movie2 = new Movie();
    movie1.setId(1L);
    movie2.setId(2L);
    
    List<Movie> mockMoviesList = Arrays.asList(movie1, movie2);
   
   when(mockMovieService.listMovies()).thenReturn(mockMoviesList);        
   
    this.mockMvc.perform(get("/editMovies").principal(principal))
    .andExpect(status().isOk())
    .andExpect(model().attribute("movieList", mockMovieService.listMovies()))
    .andExpect(model().attributeExists("movie"))
    .andExpect(model().attribute("username", principal.getName()))  
    .andExpect(model().size(3))
	.andExpect(view().name("/movie/editMovies"));      
    
}

@Test
public void testGetMovie() throws Exception {	
	
	Movie movie = new Movie();
	
	when(mockMovieService.getMovie(anyLong())).thenReturn(movie);
   
    this.mockMvc.perform(get("/getMovie/{movieId}", anyLong()))
    .andExpect(status().isOk())
    .andExpect(model().attributeExists("movie"))
    .andExpect(model().attribute("edit", "editMovie"))
    .andExpect(model().size(2))
	.andExpect(view().name("/movie/movieForm"));      
    
}

@Test
public void testSaveMovie() throws Exception {	

	Movie movie = new Movie();
	movie.setId(1L);
	movie.setGenre("Action");
	movie.setTitle("Rambo");
	movie.setActors("Silvester Stalone");
	movie.setYear("1988");
	movie.setAddedBy(principal.getName());
	
	
	mockMovieService.saveMovie(movie);

    this.mockMvc.perform(post("/save").principal(principal)    
    .param("title", movie.getTitle())
    .param("genre", movie.getGenre())
    .param("year", movie.getYear())
    .param("actors", movie.getActors())
    .param("addedBy", movie.getAddedBy()))   
    
    .andExpect(model().attributeExists("movie"))
    .andExpect(model().size(1))
    .andExpect(status().isFound())
    .andExpect(redirectedUrl("/rank"));
}

@Test
public void testDeleteMovie() throws Exception {
	
Movie movie = new Movie();
movie.setId(1L);
	
    this.mockMvc.perform(get("/deleteMovie/{movieId}", movie.getId()))
   .andExpect(status().isFound())
   .andExpect(redirectedUrl("/editMovies"));
}

@Test
public void testVoteMovie() throws Exception {
	
	Movie movie = new Movie();	
 	
	User user = new User();
	
	when(mockUserDao.findUserByName(principal.getName())).thenReturn(user);
	
	mockUserDao.userVoted(user.getId(), true);
	
	mockMovieService.voteMovie(movie.getId());	
	
	this.mockMvc.perform(get("/vote", movie.getId(), principal.getName()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/"));	
}

}
