package com.lsf.cep;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lsf.entity.Stock;


public class DBopenConnection {
	SessionFactory Sfact;
	Session session;
	public void DBopen_me() {
		 Sfact = new Configuration().configure().buildSessionFactory();
		 session = Sfact.openSession();
		/*List<Stock> stk = session.createSQLQuery("SELECT * FROM stock").addEntity(Stock.class).list();
		System.out.println(stk.get(0).getPrc());*/
	}
}
