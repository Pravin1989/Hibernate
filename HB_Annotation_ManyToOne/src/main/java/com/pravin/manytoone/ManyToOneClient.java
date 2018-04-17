package com.pravin.manytoone;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyToOneClient {
	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hbm.config.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Cart cart = new Cart();
		cart.setName("MyCart1");
		
		Items item1 = new Items("I10", 10, 1, cart);
		Items item2 = new Items("I20", 20, 2, cart);
		Set<Items> itemsSet = new HashSet<Items>();
		itemsSet.add(item1); itemsSet.add(item2);
		
		cart.setTotal(10*1 + 20*2);
		
		try{
			session.save(item1);
			session.save(item2);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			sf.close();
		}
	}
}
