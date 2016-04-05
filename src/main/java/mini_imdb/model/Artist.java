package mini_imdb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private int artistId;

	private String name;
	private String birthPlace;
	private String biography;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Lob
	@ElementCollection
	private List<byte[]> photoList = new ArrayList<byte[]>();



}
