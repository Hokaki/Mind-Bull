package com.firefalcon.model;

import java.util.ArrayList;
 
import javax.servlet.http.HttpServletRequest;
 
import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
 
public class Planner extends DHXEventsManager {
 
       public Planner(HttpServletRequest request) {
             super(request);
       }
 
       @Override
       public Iterable getEvents() {
             ArrayList events = new ArrayList();
 
             DHXEvent ev1 = new DHXEvent();
             ev1.setId(1);
             ev1.setStart_date("01/23/2013 05:00");
             ev1.setEnd_date("01/23/2013 09:00");
             ev1.setText("Demo event #1");
 
             DHXEvent ev2 = new DHXEvent();
             ev2.setId(2);
             ev2.setStart_date("01/24/2013 05:00");
             ev2.setEnd_date("01/24/2013 09:00");
             ev2.setText("Demo event #2");
 
             DHXEvent ev3 = new DHXEvent();
             ev3.setId(3);
             ev3.setStart_date("01/25/2013 05:00");
             ev3.setEnd_date("01/25/2013 09:00");
             ev3.setText("Demo event #3");
 
             events.add(ev1);
             events.add(ev2);
             events.add(ev3);
 
             return events;
       }
}