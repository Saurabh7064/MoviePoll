package com.dragan1982.movies.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragan1982.movies.dao.UserDao;
import com.dragan1982.movies.model.Movie;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.service.MovieService;
import com.dragan1982.movies.service.UserService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = {"/", "/rank"})
	public String movieList(Map<String, Object> map, Principal principal) {

		map.put("movieList", movieService.listMovies());
		
		if(principal != null){
		String name = principal.getName(); //get logged in username
		map.put("username", name);
		map.put("hasVoted", userDao.findUserByName(name).isHasVoted());
		}
		
		return "/rank";
	}
	
	@RequestMapping("/addMovie")
	public String addMovie(Map<String, Object> map, Principal principal) {

		map.put("movie", new Movie());
		
		if(principal != null){
			String name = principal.getName(); 
			map.put("username", name);
			}
		
		return "/movie/movieForm";
	}
	
	@RequestMapping("/editMovies")
	public String editMovies(Map<String, Object> map, Principal principal) {

		map.put("movie", new Movie());
		map.put("movieList", movieService.listMovies());
		
		if(principal != null){
			String name = principal.getName(); 
			map.put("username", name);
			}
		
		return "/movie/editMovies";
	}

	@RequestMapping("/getMovie/{movieId}")
	public String getMovie(@PathVariable Long movieId, Map<String, Object> map) {

		Movie movie = movieService.getMovie(movieId);

		map.put("movie", movie);
		map.put("edit", "editMovie");

		return "/movie/movieForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			 
			return "/movie/movieForm";
		 
		} else {
		 
		    // form input is ok
		
		String princ = principal.getName();
		movie.setAddedBy(princ);
		
		movieService.saveMovie(movie);
		
		return "redirect:/rank";
		}
	}

	@RequestMapping("/deleteMovie/{movieId}")
	public String deleteMovie(@PathVariable("movieId") Long id) {

		movieService.deleteMovie(id);

		return "redirect:/editMovies";
	}	
	
	@RequestMapping("/vote")
	public String userVote(HttpServletRequest request, @RequestParam(value="movieId", required=false) Long movieId, Principal principal ) {
		
		String principalName = null;
		if(principal != null && movieId != null){
			principalName = principal.getName(); 
			
			User user = userDao.findUserByName(principalName);
			
			if(!user.isHasVoted()){ // check db if user has already voted
				userService.userVoted(user.getId(), true); 
				movieService.voteMovie(movieId);  
			}
		}
		return "redirect:/";
		
	}	
}