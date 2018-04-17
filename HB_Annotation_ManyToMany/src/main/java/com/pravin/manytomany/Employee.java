package com.pravin.manytomany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "table_employee1")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "employee_id")
	private long id;

	@Column(name = "employee_name")
	private String name;

	@Column(name = "employee_salary")
	private double salary;

	@ManyToMany(cascade = CascadeType.ALL)// By default it is LAZY Loading
	@JoinTable(name = "table_Employee_Project", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = { @JoinColumn(name = "project_id") })
	
	private Set<Project> projects;

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee() {
	}

	public Employee(String name, double salary) {
		this.salary = salary;
		this.name = name;
	}

}