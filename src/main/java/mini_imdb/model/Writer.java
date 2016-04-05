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

}
