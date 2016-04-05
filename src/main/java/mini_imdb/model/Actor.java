package mini_imdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Actor")
public class Actor extends Artist {

	@OneToMany(mappedBy="actor", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<MovieChar> movieCharList = new ArrayList<MovieChar>();

	public Actor() {
		super();
	}

	public Actor(String name, String birthPlace, String biography, String birthDate) {
		super();
		this.name = name;
		this.birthPlace = birthPlace;
		this.biography = biography;
		setBirthDate(birthDate);
	}
	
	public void addMovieChar(MovieChar mc){
		movieCharList.add(mc);
		mc.setActor(this);
	}

	public void removeMovieChar(MovieChar mc){
		mc.setActor(null);
		movieCharList.remove(mc);
	}
	
}
