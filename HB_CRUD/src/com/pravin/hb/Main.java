package com.pravin.hb;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		/*
		 * m.saveEmployee("Suresh", 40000, "Nanded", 98653214);
		 * m.saveEmployee("Swapnil", 60000, "Latur", 98653456);
		 * m.saveEmployee("Dhananjay",70000, "Beed", 98987614);
		 */
		// m.retrieveEmployeeDetails();
		//m.deleteEmployee();
		m.updateEmployee();
	}

	/**
	 * @param name
	 * @param salary
	 * @param city
	 * @param phone
	 * 
	 *            This method is used to save the employee
	 */
	public void saveEmployee(String name, int salary, String city, int phone) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSalary(salary);
			emp.setCity(city);
			emp.setPhone(phone);
			session.save(emp);
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Retrieve Employee Details
	 */
	public void retrieveEmployeeDetails() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			List<Employee> empList = session.createQuery("from Employee")
					.list();
			Iterator<Employee> it = empList.iterator();
			while (it.hasNext()) {
				Employee e = it.next();
				System.out.println(e.getId() + "|" + e.getName() + "|"
						+ e.getSalary() + "|" + e.getPhone());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			String queryString = "from Employee where phone = :phone";
			Query query = session.createQuery(queryString);
			query.setInteger("phone", 98653456);
			Employee employee = (Employee) query.uniqueResult();
			session.delete(employee);
			System.out.println("Record Deleted");
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * This method is for Updating the Employee Record
	 */
	public void updateEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			String queryString = "from Employee where salary = :salary";
			Query query = session.createQuery(queryString);
			query.setInteger("salary", 50000);
			Employee employee = (Employee) query.uniqueResult();
			employee.setPhone(12345687);
			employee.setName("Ajit");
			session.update(employee);
			System.out.println("Record Updated");
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}