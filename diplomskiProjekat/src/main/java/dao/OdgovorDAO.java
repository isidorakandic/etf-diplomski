/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Odgovor;
import model.Pitanje;
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
public class OdgovorDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Odgovor sacuvajOdgovor(Odgovor odgovor) {
        Odgovor sacuvan;
        Session sesija = factory.openSession();
        Transaction trnasakcija = sesija.beginTransaction();
        sesija.save(odgovor);
        sacuvan = odgovor;
        trnasakcija.commit();
        sesija.close();
        return sacuvan;
    }

    public static Odgovor dohvatiPoPitanjuITekstu(Pitanje pitanje, String tekst) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Odgovor where pitanje.idpitanje = :idpitanje and tekst = :tekst");
        upit.setParameter("idpitanje", pitanje.getIdpitanje());
        upit.setParameter("tekst", tekst);
        Odgovor rezultat = (Odgovor) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }

    public static Odgovor dohvatiPoId(int id) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Odgovor where idodgovor = :id");
        upit.setParameter("id", id);
        Odgovor rezultat = (Odgovor) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }
}
