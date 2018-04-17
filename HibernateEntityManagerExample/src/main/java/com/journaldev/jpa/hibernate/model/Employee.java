package com.journaldev.jpa.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "table_employee_entity")
public class Employee {
	@Id
	@TableGenerator(
            name = "SHAPE_GEN",
            table = "ID_Generator",
            pkColumnName = "name",
            valueColumnName = "sequence",
            allocationSize = 1,
            initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SHAPE_GEN")
	private int employeeId;

	private String name;
	private double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "employe_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + employeeId + ", Name=" + name + ",Salary=" + salary + "]";
	}
}
