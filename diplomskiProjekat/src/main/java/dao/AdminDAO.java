/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Korisnik;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class AdminDAO {

   private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void prihvatiZahtev(Korisnik k) {
        Session sesija = factory.openSession();
        Korisnik nadjen =(Korisnik) sesija.get(Korisnik.class, k.getIdkorisnik());
        Transaction transakcija = sesija.beginTransaction();
        nadjen.setStanje("P");
        sesija.update(nadjen); 
        transakcija.commit();
        sesija.close();
    }

    public static void odbijZahtev(Korisnik k) {
        Session sesija = factory.openSession();
        Korisnik nadjen =(Korisnik) sesija.get(Korisnik.class, k.getIdkorisnik());
        Transaction transakcija = sesija.beginTransaction();
        nadjen.setStanje("O");
        sesija.update(nadjen); 
        transakcija.commit();
        sesija.close();
    }

}
