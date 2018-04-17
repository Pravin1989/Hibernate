package com.journaldev.jpa.hibernate.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.journaldev.jpa.hibernate.model.Employee;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();
		Employee employee1 = new Employee();
		employee1.setName("Sujit");
		employee1.setSalary(70000.00);
		System.out.println("Saving Employee to Database");

		entityManager.persist(employee1);
		entityManager.getTransaction().commit();
		System.out.println("Generated Employee ID = " + employee1.getEmployeeId());

		// get an object using primary key.
		Employee emp = entityManager.find(Employee.class, employee1.getEmployeeId());
		System.out.println("got object " + emp);

		// get all the objects from Employee table
		/*@SuppressWarnings("unchecked")
		List<Employee> listEmployee = entityManager.createQuery("SELECT e FROM Employee e").getResultList();

		if (listEmployee == null) {
			System.out.println("No employee found . ");
		} else {
			for (Employee empl : listEmployee) {
				System.out.println("Employee name= " + empl.getName() + ", Employee id " + empl.getEmployeeId());
			}
		}
		// remove and entity
		entityManager.getTransaction().begin();
		System.out.println("Deleting Employee with ID = " + emp.getEmployeeId());
		entityManager.remove(emp);
		entityManager.getTransaction().commit();
*/
		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();

	}
}
