/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.Korisnik;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Isidora
 */
public class KorisnikDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void dodajKorisnika(Korisnik k) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(k);
        transakcija.commit();
        sesija.close();
    }

    public static Korisnik dohvatiPoId(int id) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Korisnik where idkorisnik = :id");
        upit.setParameter("id", id);
        Korisnik rezultat = (Korisnik) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }

    public static List<Korisnik> dohvatiSveKorisnike() {
        List<Korisnik> korisnici;
        Session sesija = factory.openSession();
        korisnici = (List<Korisnik>) sesija.createQuery("from Korisnik where korisnickoIme!='admin'").list();
        sesija.close();
        return korisnici;
    }

    public static Korisnik dohvatiKorisnikaPoKorisnickomImenu(String korisnickoIme) {
        Korisnik nadjen;
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Korisnik where korisnickoIme = :korisnickoIme");
        upit.setParameter("korisnickoIme", korisnickoIme);
        nadjen = (Korisnik) upit.uniqueResult();
        sesija.close();
        return nadjen;
    }

    public static void izmeniKorisnika(Korisnik korisnik) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.update(korisnik);
        sesija.flush();
        transakcija.commit();
        sesija.close();
    }

    public static List<String> dohvatiSveMejloveRegistrovanihKorisnika() {
        Session sesija = factory.openSession();
        List<String> mejlovi = sesija.createQuery("select email from Korisnik where stanje = 'P'").list();
        sesija.close();
        return mejlovi;
    }
}
