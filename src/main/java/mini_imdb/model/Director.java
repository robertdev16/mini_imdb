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

}
