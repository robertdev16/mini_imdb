package mini_imdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="userSeq", sequenceName="USER_SEQ")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSeq")
	private int userId;
	
	private String loginName;
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	public User() {
		super();
	}

}
