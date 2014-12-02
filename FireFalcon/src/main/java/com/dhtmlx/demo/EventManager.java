/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhtmlx.demo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EventManager extends DHXEventsManager {
    @Autowired
    private SessionFactory sessionFactory;
    
        public EventManager(HttpServletRequest request) {
		super(request);
	}
 
	public Iterable<DHXEv> getEvents() {
		Session session = sessionFactory.openSession();
		List<DHXEv> evs = new ArrayList<DHXEv>();
		try {
			session = sessionFactory.openSession();
			evs = session.createCriteria(DHXEvent.class).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
 
    	return evs;
	}
 
	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		Session session = sessionFactory.openSession();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
 
			if (status == DHXStatus.UPDATE)
				session.update(event);
			else if (status == DHXStatus.DELETE)
				session.delete(event);
			else if (status == DHXStatus.INSERT)
				session.save(event);
 
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally{
			session.flush();
			session.close();
		}
		return status;
	}
 
	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new DHXEvent();
	}
}
