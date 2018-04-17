package com.pravin.manytomany;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "table_project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int id;

	@Column(name = "project_name")
	private String name;

	@Column(name = "project_duration")
	private int duration;

	@ManyToMany
	private Set<Employee> emp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return name;
	}

	public void setProjectName(String projectName) {
		this.name = projectName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Set<Employee> getEmp() {
		return emp;
	}

	public void setEmp(Set<Employee> emp) {
		this.emp = emp;
	}

	public Project() {
	}

	public Project(String name, int duration) {
		this.duration = duration;
		this.name = name;
	}
	@Override
	public String toString() {
		return this.name+" "+this.id+" "+this.duration;
	}
}
