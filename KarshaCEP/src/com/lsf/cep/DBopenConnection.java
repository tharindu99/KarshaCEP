package com.lsf.cep;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lsf.entity.Stock;

public class DBopenConnection {
	public void DBopen_me() {
		SessionFactory SFact = new Configuration().configure().buildSessionFactory();
		Session session = SFact.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		
		List<Stock> stk = session.createSQLQuery("SELECT * FROM stock").addEntity(Stock.class).list();
		System.out.println(stk.get(0).getPrc());
	}
}
