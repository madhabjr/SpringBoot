package com.example.springlearn.models;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="GAMES")
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gId;
	private String gamename;
	private String size;
	private String email;
	private String ratings;
	private String image;
	@Column(length = 5000)
	private String description;
	
	@ManyToOne(cascade=CascadeType.MERGE)	
	@JsonIgnore
	private User user;

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Games(int gId, String gamename, String size, String email, String ratings, String image, String description,
			User user) {
		super();
		this.gId = gId;
		this.gamename = gamename;
		this.size = size;
		this.email = email;
		this.ratings = ratings;
		this.image = image;
		this.description = description;
		this.user = user;
	}

	public Games() {
		super();
	}

	@Override
	public String toString() {
		return "Games [gId=" + gId + ", gamename=" + gamename + ", size=" + size + ", email=" + email + ", ratings="
				+ ratings + ", image=" + image + ", description=" + description + ", user=" + user + "]";
	}

	
	
}

