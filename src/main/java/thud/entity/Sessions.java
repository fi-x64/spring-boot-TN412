package thud.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Sessions {
	@Id
	private String id;
	private String name;
	private Type type;
	@ManyToMany(mappedBy = "sessions")
	private Set<Bookables> bokBookables = new HashSet<>();

	public enum Type {
		Breakfast, Morning, Lunch, Afternoon, Evening
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Sessions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sessions(String name, Type type) {
		super();
		this.name = name;
		this.type = type;

	}

	@Override
	public String toString() {
		return "Sessions [id=" + id + ", name=" + name + ", type=" + type + ", bokBookables=" + bokBookables + "]";
	}


}
