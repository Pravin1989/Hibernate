package com.pravin.onetomany;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToManyClient {
	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hbm.config.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Cart cart = new Cart();
		cart.setName("MyCart1");
		
		Cart cart1 = new Cart();
		cart1.setName("MyCart2");
		
		Items item1 = new Items("I10", 10, 1);
		Items item2 = new Items("I20", 20, 2);
		Set<Items> itemsSet = new HashSet<Items>();
		itemsSet.add(item1); itemsSet.add(item2);
		
		cart.setItems(itemsSet);
		cart.setTotal(10*1 + 20*2);
		
		Items item3 = new Items("Ford Aspire", 20, 2);
		Items item4 = new Items("Honda Amaze", 30, 4);
		Set<Items> itemsSet2 = new HashSet<Items>();
		itemsSet2.add(item3); itemsSet2.add(item4);
		
		cart1.setItems(itemsSet2);
		cart1.setTotal(20*2 + 30*4);
		
		try{
			session.save(cart);
			session.save(cart1);
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
