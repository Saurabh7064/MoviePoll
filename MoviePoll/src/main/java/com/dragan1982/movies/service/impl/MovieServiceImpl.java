package com.dragan1982.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.dao.MovieDao;
import com.dragan1982.movies.model.Movie;
import com.dragan1982.movies.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Transactional
	public void saveMovie(Movie movie) {
		movieDao.saveMovie(movie);
	}
	
	@Transactional(readOnly = true)
	public List<Movie> listMovies() {
		return movieDao.listMovies();
	}

	@Transactional(readOnly = true)
	public Movie getMovie(Long id) {
		return movieDao.getMovie(id);
	}

	@Transactional
	public void deleteMovie(Long id) {
		movieDao.deleteMovie(id);

	}
	
	@Transactional
	public void voteMovie(Long id) {
		movieDao.voteMovie(id);

	}

}
