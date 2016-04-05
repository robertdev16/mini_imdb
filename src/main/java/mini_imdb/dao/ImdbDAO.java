package mini_imdb.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ImdbDAO {

	private EntityManagerFactory emf;

	public ImdbDAO(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	



}
