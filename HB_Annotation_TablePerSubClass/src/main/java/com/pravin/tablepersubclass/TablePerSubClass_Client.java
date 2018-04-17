package com.pravin.tablepersubclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TablePerSubClass_Client {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hbm.config.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		
		Employee e1=new Employee();  
	    e1.setName("sonoo");
	      
	    Regular_Employee e2=new Regular_Employee();  
	    e2.setName("Vivek Kumar");  
	    e2.setSalary(50000);  
	    e2.setBonus(5);  
	      
	    Contract_Employee e3=new Contract_Employee();  
	    e3.setName("Arjun Kumar");  
	    e3.setPay_per_hour(1000);  
	    e3.setContract_duration("15 hours");  
	    
		try {
			session.save(e1);
			session.save(e2);
			session.save(e3);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sf.close();
		}

	}
}
