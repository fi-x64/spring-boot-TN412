package thud.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String img;
	private String title;
	private String notes;
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "users",orphanRemoval = true)
	private List<Privilege> privileges = new ArrayList<>();

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String name, String img, String title, String notes, String role, List<Privilege> privileges) {
		super();
		this.name = name;
		this.img = img;
		this.title = title;
		this.notes = notes;
		this.role = role;
		this.privileges = privileges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", img=" + img + ", title=" + title + ", notes=" + notes
				+ ", role=" + role + ", privileges=" + privileges + "]";
	}
	
	
}
