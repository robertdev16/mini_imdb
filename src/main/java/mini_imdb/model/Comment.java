package mini_imdb.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
	
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	
	public Comment() {
		super();
	}
	
	public Comment(User user, Movie movie, String date, String time, String title, String content, float rating) {
		super();
		this.user = user;
		this.movie = movie;
		setDate(date);
		setTime(time);
		this.title = title;
		this.content = content;
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getDate() {
		return df.format(date);
	}

	public void setDate(String date) {
		try {
			this.date = df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTime() {
		return df.format(time);
	}

	public void setTime(String time) {
		try {
			this.time = df.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCommentId() {
		return commentId;
	}
	
}
