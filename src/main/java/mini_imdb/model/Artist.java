package mini_imdb.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="artistSeq", sequenceName="ARTIST_SEQ")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ARTIST_TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class Artist {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="artistSeq")
	protected int artistId;

	protected String name;
	protected String birthPlace;
	protected String biography;
	
	@Temporal(TemporalType.DATE)
	protected Date birthDate;
	
	@Lob
	@ElementCollection
	protected List<byte[]> photoList = new ArrayList<byte[]>();

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getBirthDate() {
		return df.format(birthDate);
	}

	public void setBirthDate(String birthDate) {
		try {
			this.birthDate = df.parse(birthDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<byte[]> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<byte[]> photoList) {
		this.photoList = photoList;
	}

	public int getArtistId() {
		return artistId;
	}

	
	
}
