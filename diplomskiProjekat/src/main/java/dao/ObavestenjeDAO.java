/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.Obavestenje;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Isidora
 */
public class ObavestenjeDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void dodajObavestenje(Obavestenje o) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(o);
        transakcija.commit();
        sesija.close();
    }

    public static List<Obavestenje> dohvatiSvaObavestenjaSortiranaPoDatumu() {
        List<Obavestenje> obavestenja;
        Session sesija = factory.openSession();
        obavestenja = (ArrayList<Obavestenje>) sesija.createQuery("from Obavestenje o order by o.datum desc").list();
        sesija.close();
        return obavestenja;
    }
}
