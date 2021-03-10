/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Korisnik;
import model.KorisnikProjekat;
import model.Projekat;
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
public class KorisnikProjekatDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void dodajKorisnikProjekat(KorisnikProjekat kp) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(kp);
        transakcija.commit();
        sesija.close();
    }

    public static void obrisiKorisnikProjekat(int idk, int idp) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Query upit = sesija.createQuery("delete from KorisnikProjekat where korisnik.idkorisnik = :idk and projekat.idprojekat = :idp");
        upit.setParameter("idk", idk);
        upit.setParameter("idp", idp);
        upit.executeUpdate();
        transakcija.commit();
        sesija.close();
    }

    public static KorisnikProjekat nadjiPoKorisnikuIProjektu(Korisnik korisnik, Projekat projekat) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from KorisnikProjekat where korisnik.idkorisnik = :idk and projekat.idprojekat = :idp");
        upit.setParameter("idk", korisnik.getIdkorisnik());
        upit.setParameter("idp", projekat.getIdprojekat());
        KorisnikProjekat rezultat = (KorisnikProjekat) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }

    public static void izmeniStanjeOdgovora(KorisnikProjekat korisnikProjekat, String novoStanje) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Query upit = sesija.createQuery("update KorisnikProjekat set stanjeOdgovora = :stanje where id = :id");
        upit.setParameter("stanje", novoStanje);
        upit.setParameter("id", korisnikProjekat.getId());
        upit.executeUpdate();
        transakcija.commit();
        sesija.close();
    }

    public static void izmeniOcenu(KorisnikProjekat korisnikProjekat, Integer novaOcena) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Query upit = sesija.createQuery("update KorisnikProjekat set ocena = :ocena where id = :id");
        upit.setParameter("ocena", novaOcena);
        upit.setParameter("id", korisnikProjekat.getId());
        upit.executeUpdate();
        transakcija.commit();
        sesija.close();
    }

    public static List<KorisnikProjekat> nadjiSve() {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from KorisnikProjekat");
        List<KorisnikProjekat> rezultat = upit.list();
        sesija.close();
        return rezultat;
    }
}
