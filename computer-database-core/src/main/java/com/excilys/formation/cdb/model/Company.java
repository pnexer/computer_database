package com.excilys.formation.cdb.model;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	public Company() {
		super();
		name = "";
		id = null;
	}

	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String res = "";
		res += " id= " + this.id;
		res += "| nom = " + this.name;
		return res;
	}
}
