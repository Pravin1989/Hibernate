package com.pravin.manytomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyToManyClient {
	SessionFactory sf;
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hbm.config.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Employee emp1, emp2, emp3;
		Project prj1, prj2, prj3;

		emp1 = new Employee("Pravin", 40000);
		emp2 = new Employee("Amit", 50000);
		emp3 = new Employee("Ankit", 60000);

		prj1 = new Project("DFA", 6);
		prj2 = new Project("Scorecard", 7);
		prj3 = new Project("DTF", 8);

		Set<Project> projects = new HashSet();

		projects.add(prj1);
		projects.add(prj2);
		projects.add(prj3);

		Set<Employee> employees = new HashSet();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);

		emp1.setProjects(projects);
		emp2.setProjects(projects);
		emp3.setProjects(projects);
		try {
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sf.close();
		}
	}

	private static void showEmployeeData(Session session) {
		Employee object = (Employee) session.get(Employee.class, 1L);
		System.out.println(object.getId() + " " + object.getName() + " " + object.getSalary());

		System.out.println(object.getProjects());
		
	}
}
