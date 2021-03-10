/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
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
public class ProjekatDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void dodajProjekat(Projekat p) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(p);
        transakcija.commit();
        sesija.close();
    }

    public static Projekat dohvatiPoId(int id) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Projekat where idprojekat = :id");
        upit.setParameter("id", id);
        Projekat rezultat = (Projekat) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }

    public static Projekat dohvatiPoNazivu(String naziv) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from Projekat where naziv = :naziv");
        upit.setParameter("naziv", naziv);
        Projekat rezultat = (Projekat) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }

    public static void zapamtiKonacnuOcenu(Projekat p, int ocena) {
        Session sesija = factory.openSession();
        Projekat izBaze = (Projekat) sesija.get(Projekat.class, p.getIdprojekat());
        Transaction transakcija = sesija.beginTransaction();
        izBaze.setKonacnaOcena(ocena);
        sesija.update(izBaze);
        transakcija.commit();
        sesija.close();
    }

    public static List<Projekat> dohvatiSveProjekte() {
        List<Projekat> projekti;
        Session sesija = factory.openSession();
        projekti = (ArrayList<Projekat>) sesija.createQuery("from Projekat").list();
        sesija.close();
        return projekti;
    }

    public static void obrisi(Projekat projekat) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        Query upit = sesija.createQuery("delete from Projekat where idprojekat = :id");
        upit.setParameter("id", projekat.getIdprojekat());
        upit.executeUpdate();
        transakcija.commit();
        sesija.close();
    }
    
     public static void izmeniProjekat(Projekat projekat) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.update(projekat);
        sesija.flush();
        transakcija.commit();
        sesija.close();
    }
}
