package com.pravin.hb;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

public class HB_ClientCache {
	public static void main(String[] args) {
		System.out.println("Temp Dir:"+System.getProperty("java.io.tmpdir"));
		
		//Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		
		/*saveEmployee("Pravin Budage",40000, "Kalyani Nagar", "Pune","411014");
		saveEmployee("Suresh Patil",50000, "Baner Road", "Pune","411015");
		saveEmployee("Mangesh Gaikwad",60000, "Nigadi", "Pune","411016");*/
		
		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Transaction otherTransaction = otherSession.beginTransaction();
		
		printStats(stats, 0);
		
		Employee emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 1);
		
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 2);
		
		//clear first level cache, so that second level cache is used
		session.evict(emp);
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 3);
		
		/*emp = (Employee) session.load(Employee.class, 3L);
		printData(emp, stats, 4);*/
		
		emp = (Employee) otherSession.load(Employee.class, 1L);
		printData(emp, stats, 5);
		
		emp = (Employee) otherSession.load(Employee.class, 1L);
		printData(emp, stats, 6);
		
		//Release resources
		transaction.commit();
		otherTransaction.commit();
		sessionFactory.close();
	}

	private static void printStats(Statistics stats, int i) {
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());//Get global number of entity fetchs
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());//Global number of cacheable entities/collections successfully retrieved from the cache
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());//Global number of cacheable entities/collections not found in the cache and loaded from the database.
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());//Global number of cacheable entities/collections put in the cache
		System.out.println("End : ***** " + i + " *****");
	}

	private static void printData(Employee emp, Statistics stats, int count) {
		System.out.println(count+":: Name="+emp.getName()+", Zipcode="+emp.getAddress().getZipcode());
		printStats(stats, count);
	}
	
	public static void saveEmployee(String name, double salary, String addressLine, String city,String zip) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Employee emp = new Employee();
			Address address=new Address();
			address.setAddressLine1(addressLine);
			address.setCity(city);
			address.setZipcode(zip);
			address.setEmployee(emp);
			
			emp.setName(name);
			emp.setSalary(salary);
			emp.setAddress(address);
			session.save(emp);
			tr.commit();
		} catch (HibernateException e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
}
}