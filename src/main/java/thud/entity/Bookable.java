package thud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookables")
public class Bookable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "groupName")
	private String group;

	@Column(name = "img")
	private String img;

	@Column(name = "title")
	private String title;

	@Column(name = "notes")
	private String notes;

	public Bookable() {

	}

	public Bookable(Long id, String group, String img, String title, String notes) {
		this.id = id;
		this.group = group;
		this.img = img;
		this.title = title;
		this.notes = notes;
	}

	public Bookable(String group, String img, String title, String notes) {
		this.group = group;
		this.img = img;
		this.title = title;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	@Override
	public String toString() {
		return "Bookable [id=" + id + ", group=" + group + ", title=" + title + ", notes=" + notes + "]";
	}
}
