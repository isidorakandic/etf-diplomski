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
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;




public class LoginDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Korisnik dohvatiKorisnika(String korisnickoIme, String lozinka) {
        Korisnik nadjen;
        Session sesija = factory.openSession();
        String HQL = "from Korisnik where korisnickoIme = :ime and lozinka = :lozinka";
        Query upit = sesija.createQuery(HQL);
        upit.setParameter("ime", korisnickoIme);
        upit.setParameter("lozinka", lozinka);
        nadjen = (Korisnik) upit.uniqueResult();
        sesija.close();
        return nadjen;
    }

    public static boolean korisnikPrihvacen(Korisnik k) {
        Korisnik nadjen;
        boolean rezultat;
        Session sesija = factory.openSession();
        String HQL = "from Korisnik where korisnickoIme = :ime and stanje = 'P'";
        Query upit = sesija.createQuery(HQL);
        upit.setParameter("ime", k.getKorisnickoIme());
        nadjen = (Korisnik) upit.uniqueResult();
        if (nadjen != null) {
            rezultat = nadjen.getStanje().equals("P");
        } else {
            rezultat = false;
        }
        sesija.close();
        return rezultat;
    }

    public static Korisnik dohvatiKorisnikaPoId(int id) {
        Korisnik k;
        Session sesija = factory.openSession();
        k = (Korisnik) sesija.get(Korisnik.class, id);
        sesija.close();
        return k;
    }
}
