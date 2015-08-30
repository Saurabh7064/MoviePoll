package com.dragan1982.movies.service;

import java.util.List;

import com.dragan1982.movies.model.Movie;

public interface MovieService {

	public void saveMovie(Movie movie);

	public List<Movie> listMovies();
	public Movie getMovie(Long id);

	public void deleteMovie(Long id);
	
	public void voteMovie(Long id);

}
