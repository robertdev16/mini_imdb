package mini_imdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@DiscriminatorValue("Director")
public class Director extends Artist {

	@ManyToMany
	@JoinTable(name="DIRECTOR_MOVIE")
	private List<Movie> movieList = new ArrayList<Movie>();
	
	public Director() {
		super();
	}

	public Director(String name, String birthDate, String birthPlace) {
		super();
		this.name = name;
		setBirthDate(birthDate);
		this.birthPlace = birthPlace;
	}
	
	
	public void addMovie(Movie m){
		movieList.add(m);
		m.addDirector(this);
	}

	public void removeMovie(Movie m){
		m.removeDirector(this);
		movieList.remove(m);
	}
	
}
