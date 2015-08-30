package com.dragan1982.movies.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dragan1982.movies.dao.MovieDao;
import com.dragan1982.movies.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveMovie(Movie movie) {		
		getSession().merge(movie);

	}

	@SuppressWarnings("unchecked")
	public List<Movie> listMovies() {
		
		Criteria c = getSession().createCriteria(Movie.class);		
		c.addOrder(Order.desc("votes"));
		return c.list();		
	}

	public Movie getMovie(Long id) {
		return (Movie) getSession().get(Movie.class, id);
	}

	public void deleteMovie(Long id) {

		Movie movie = getMovie(id);

		if (null != movie) {
			getSession().delete(movie);
		}

	}
	
	public void voteMovie(Long movieId) {

		Movie movie = getMovie(movieId);

		if (null != movie) {
			
			synchronized (this) {
				movie.setVotes(movie.getVotes()+1);	
			}
								
		}		
	}

	private Session getSession() {
		Session sess = getSessionFactory().getCurrentSession();
		if (sess == null) {
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
