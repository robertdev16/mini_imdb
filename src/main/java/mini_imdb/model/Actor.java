package mini_imdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Actor")
public class Actor extends Artist {

	@OneToMany(mappedBy="actor")
	private List<MovieChar> movieCharList = new ArrayList<MovieChar>();


	public Actor() {
		super();
	}

}
