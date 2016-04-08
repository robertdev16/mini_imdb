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
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,	Locale.US);
	
	public Comment() {
		super();
	}
	
	public Comment(User user, Movie movie, String date, String time, float rating, String title, String content) {
		super();
		setUser(user);
		setMovie(movie);
		setDate(date);
		setTime(time);
		this.rating = rating;
		this.title = title;
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.addComment(this);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		movie.addComment(this);
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
		return tf.format(time);
	}

	public void setTime(String time) {
		try {
			this.time = tf.parse(time);
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

	@Override
	public String toString() {
		return getTime() + "   " + getDate() + "   movie: " + movie.getTitle() +
				"   posted by " + user.getLoginName() + "\n"
				+ "Title: " + title + "\n"+ content + "\n";
	}
	
}
