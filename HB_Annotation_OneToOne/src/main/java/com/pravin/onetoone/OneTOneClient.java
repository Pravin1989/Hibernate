package com.pravin.onetoone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneTOneClient {
	public static void main(String[] args) {
		/*Configuration cfg=new Configuration();
		cfg.configure("hbm.config.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();*/
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();
		
		Voter voter1=new Voter("Pravin","Latur");
		Voter voter2=new Voter("Ajit","Latur");
		Voter voter3=new Voter("Ankit","Latur");
		
		Vote vote1=new Vote("BJP");
		Vote vote2=new Vote("NCP");
		Vote vote3=new Vote("Congress");
		
		voter1.setVote(vote1);
		vote1.setVoter(voter1);
		
		voter2.setVote(vote2);
		vote2.setVoter(voter2);
		
		voter3.setVote(vote3);
		vote3.setVoter(voter3);
		
		entityManager.persist(voter1);
		entityManager.persist(voter2);
		entityManager.persist(voter3);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		/*try{
			session.save(voter1);
			session.save(voter2);
			session.save(voter3);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			sf.close();
		}*/
		
	
	}
}
