package mini_imdb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="commentSeq", sequenceName="COMMENT_SEQ")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="commentSeq")
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movie;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date time;
	
	private String title;
	private String content;
	private float rating;
	
	public Comment() {
		super();
	}

}
