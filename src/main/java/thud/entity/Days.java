package thud.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
	
@Entity
public class Days {
	@Id
	private String id;
	private String name;
	private Type type;
	@ManyToMany(mappedBy = "days")
	private Set<Bookables> bokBookables = new HashSet<>();
	public enum Type {
		Sunday, Monday, Tuesday, Wednesday, Thursday,Friday,Saturday
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

	public Days() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Days(String id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Days [id=" + id + ", name=" + name + ", type=" + type + ", bokBookables=" + bokBookables + "]";
	}
	
	


}

