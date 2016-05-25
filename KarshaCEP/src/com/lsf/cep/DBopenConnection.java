package com.lsf.cep;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBopenConnection {
	public void DBopen_me() {
		SessionFactory SFact = new Configuration().configure().buildSessionFactory();
		Session session = SFact.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		
		String query = "SELECT date as AllDates,PRC,`Pseudo-PRC` FROM top10 WHERE PERMNO = 66800";
		SQLQuery q = session.createSQLQuery(query);	
		List data = q.list();
		System.out.println(data);
	}
}
