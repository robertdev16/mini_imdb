package mini_imdb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="charSeq", sequenceName="CHAR_SEQ")
public class MovieChar {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="charSeq")
	private int charId;

	private String name;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="artistId")
	private Actor actor;


	public MovieChar() {
		super();
	}

}
