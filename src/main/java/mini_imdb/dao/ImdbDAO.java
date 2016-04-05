package mini_imdb.dao;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mini_imdb.model.Movie;

public class ImdbDAO {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	private Query query;

	public ImdbDAO(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	
	public List<Movie> movieList(){
		List<Movie> movies = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			query = em.createQuery("FROM Movie m");
			movies = query.getResultList();
			
			tx.commit();
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
	
	public<T> void  saveEntity(T t){
		if (t == null)
			return;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.persist(t);
			
			tx.commit();
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


	private static byte [] loadPic(String filename) {
        Path p = FileSystems.getDefault().getPath(filename);
        byte [] fileData = null;
        try {
			fileData = Files.readAllBytes(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

}
