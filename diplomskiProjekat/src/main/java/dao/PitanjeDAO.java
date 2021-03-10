/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pitanje;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Isidora
 */
public class PitanjeDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Pitanje sacuvajPitanje(Pitanje p) {
        Pitanje sacuvano;
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(p);
        sacuvano = p;
        transakcija.commit();
        sesija.close();
        return sacuvano;
    }

}
