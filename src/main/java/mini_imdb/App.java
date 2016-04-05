package mini_imdb;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mini_imdb.dao.ImdbDAO;

public class App {
	
	private static final EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("mini_imdb");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	
	private static ImdbDAO imdbDAO;
	
	public static void main(String[] args) {
		imdbDAO = new ImdbDAO(emf);

		
		emf.close();
	}


}
