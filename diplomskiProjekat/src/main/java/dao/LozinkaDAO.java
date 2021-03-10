/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Korisnik;
import org.hibernate.Query;
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
public class LozinkaDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void promeniLozinku(Korisnik korisnik, String novaLozinka) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Korisnik nadjen = (Korisnik) sesija.get(Korisnik.class, korisnik.getIdkorisnik());
        nadjen.setLozinka(novaLozinka);
        nadjen.setPrivremenaLozinkaPostavljena(null); // ako je prethodna bila privremena
        sesija.update(nadjen);
        transakcija.commit();
        sesija.close();

    }

    public static void postaviPrivremenuLozinku(Korisnik korisnik, String novaLozinka) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Korisnik nadjen = (Korisnik) sesija.get(Korisnik.class, korisnik.getIdkorisnik());
        nadjen.setLozinka(novaLozinka);
        nadjen.setPrivremenaLozinkaPostavljena(System.currentTimeMillis());
        sesija.update(nadjen);
        transakcija.commit();
        sesija.close();
    }

    

}
