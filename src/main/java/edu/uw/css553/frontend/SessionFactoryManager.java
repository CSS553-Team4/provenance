package edu.uw.css553.frontend;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by clbur_000 on 6/2/2015.
 */
public class SessionFactoryManager {
    public static SessionFactory factory;
    public SessionFactoryManager (){
        factory = new Configuration().configure().buildSessionFactory();
    }
    public SessionFactory getFactory() {
        return factory;
    }
}
