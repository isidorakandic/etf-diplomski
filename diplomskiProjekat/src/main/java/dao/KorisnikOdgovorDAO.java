/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.KorisnikOdgovor;
import model.KorisnikProjekat;
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
public class KorisnikOdgovorDAO {
    
    private static final SessionFactory factory;
    
    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public static void dodajKorisnikOdgovor(KorisnikOdgovor korisnikOdgovor) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.save(korisnikOdgovor);
        transakcija.commit();
        sesija.close();
    }
    
    public static void obrisi(KorisnikOdgovor ko) {
        Session sesija = factory.openSession();
        KorisnikOdgovor izBaze = (KorisnikOdgovor) sesija.get(KorisnikOdgovor.class, ko.getId());
        Transaction transakcija = sesija.beginTransaction();
        sesija.delete(izBaze);
        transakcija.commit();
        sesija.close();
    }
    
//    public static List<Odgovor> nadjiOdgovoreKorinsika(KorisnikProjekat kp, Pitanje p) {
//        Session sesija = factory.openSession();
//        Query upit = sesija.createQuery("select odgovor from KorisnikOdgovor where korisnikProjekat.id = :idKP and pitanje.idpitanje = :idP");
//        upit.setParameter("idKP", kp.getId());
//        upit.setParameter("idP", p.getIdpitanje());
//        return upit.list();
//    }
}
