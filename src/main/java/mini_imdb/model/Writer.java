package mini_imdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@DiscriminatorValue("Writer")
public class Writer extends Artist {

	@ManyToMany
	@JoinTable(name="WRITER_MOVIE")
	private List<Movie> movieList = new ArrayList<Movie>();
	
	public Writer() {
		super();
	}

	public Writer(String name, String birthPlace, String biography, String birthDate) {
		super();
		this.name = name;
		this.birthPlace = birthPlace;
		this.biography = biography;
		setBirthDate(birthDate);
	}
	
	
	public void addMovie(Movie m){
		movieList.add(m);
		m.addWriter(this);
	}

	public void removeMovie(Movie m){
		m.removeWriter(this);
		movieList.remove(m);
	}
	
}
