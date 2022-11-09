package thud.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Bookables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String grouppString;
	private String title;
	private String notes;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookings",joinColumns = @JoinColumn(name="bookables_id"),
	inverseJoinColumns = @JoinColumn(name = "sessions_id"))
	private List<Sessions> sessions = new ArrayList<>();
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookings",joinColumns = @JoinColumn(name="bookables_id"),
	inverseJoinColumns = @JoinColumn(name = "days_id"))
	private List<Days> days = new ArrayList<>();
	public Bookables() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bookables(String grouppString, String title, String notes, List<Sessions> sessions, List<Days> days) {
		super();
		this.grouppString = grouppString;
		this.title = title;
		this.notes = notes;
		this.sessions = sessions;
		this.days = days;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrouppString() {
		return grouppString;
	}
	public void setGrouppString(String grouppString) {
		this.grouppString = grouppString;
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
	public List<Sessions> getSessions() {
		return sessions;
	}
	public void setSessions(List<Sessions> sessions) {
		this.sessions = sessions;
	}
	public List<Days> getDays() {
		return days;
	}
	public void setDays(List<Days> days) {
		this.days = days;
	}
	@Override
	public String toString() {
		return "Bookables [id=" + id + ", grouppString=" + grouppString + ", title=" + title + ", notes=" + notes
				+ ", sessions=" + sessions + ", days=" + days + "]";
	}
}
