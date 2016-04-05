package mini_imdb.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="movieSeq", sequenceName="MOVIE_SEQ")
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="movieSeq")
	private int movieId;

	private String title;
	private int year;
	private int runtime;
	private String brief;
	private float rating;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<Genre> genreSet = new HashSet<Genre>();

	@Lob
	private byte[] poster;

	@ManyToMany(mappedBy="movieList")
	private List<Director> directorList = new ArrayList<Director>();
	
	@ManyToMany(mappedBy="movieList")
	private List<Writer> writerList = new ArrayList<Writer>();
	
	@OneToMany(mappedBy="movie")
	private List<MovieChar> movieCharList = new ArrayList<MovieChar>();
		
	@OneToMany(mappedBy="movie")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	
	public Movie() {
		super();
	}

}
