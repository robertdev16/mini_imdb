package mini_imdb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mini_imdb.App;
import mini_imdb.model.Actor;
import mini_imdb.model.Movie;
import mini_imdb.model.User;

public class ImdbDAO {

	private static Logger logger = LoggerFactory.getLogger(ImdbDAO.class);	

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private Query query;

	public ImdbDAO(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}


	public<T> void  saveEntity(T t){
		if (t == null)
			return;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.persist(t);
			
			tx.commit();
			logger.info("saveEntity: {}", t.getClass().getName());
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public<T> void updateEntity(T t){
		if (t == null)
			return;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.merge(t);
			
			tx.commit();
			logger.info("updateEntity: {}", t.getClass().getName());
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public<T> void deleteEntity(T t){
		if (t == null)
			return;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(t);
			
			tx.commit();
			logger.info("deleteEntity: {}", t.getClass().getName());
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Movie> movieList(){
		List<Movie> movies = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			query = em.createQuery("SELECT m FROM Movie m");
			movies = query.getResultList();
			
			tx.commit();
			logger.info("Get Movie List: {} movies found!", (movies == null) ? 0 : movies.size());
			if (movies != null)
				movies.forEach(System.out::println);
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return movies;
	}
	
	public Movie findMovieByTitle(String title){
		Movie m = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			query = em.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :title");
			query.setParameter("title", "%" + title + "%");
			m = (Movie) query.getSingleResult();
			
			tx.commit();
			logger.info("findMovieByTitle: {}", (m == null) ? title +" Failed!" : m.getTitle() +" Found!");
			if (m != null){
				App.writePic(m.getPoster(), m.getTitle()+".jpg");
				System.out.println(m.toString());
			}
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return m;
	}
	
	public Actor findActorByName(String name){
		Actor actor = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			query = em.createQuery("SELECT a FROM Actor a WHERE a.name LIKE :name");
			query.setParameter("name", "%" + name + "%");
			actor = (Actor) query.getSingleResult();
			
			tx.commit();
			logger.info("findActorByName: {}", (actor == null) ? name +" Failed!" : actor.getName() +" Found!");
			if (actor != null)
				System.out.println(actor.toString());
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return actor;
	}

	public User findUserByLoginName(String loginName){
		User user = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			query = em.createQuery("SELECT u FROM User u WHERE u.loginName LIKE :loginName");
			query.setParameter("loginName", "%" + loginName + "%");
			user = (User) query.getSingleResult();
			
			tx.commit();
			logger.info("findUserByLoginName: {}", (user == null) ? loginName +" Failed!" : user.getLoginName() +" Found!");
		} catch (PersistenceException e) {
			if (tx != null) {
				System.out.println("Rolling back:" + e);
				tx.rollback();
		}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return user;
	}
	
}
