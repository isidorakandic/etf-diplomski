package dao;

import java.util.ArrayList;
import java.util.List;
import model.ProgramskiPoziv;
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
public class ProgramskiPozivDAO {

    private static final SessionFactory factory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static ProgramskiPoziv sacuvajProgramskiPoziv(ProgramskiPoziv p) {
        Session sesija = factory.openSession();
        Transaction transakcija = sesija.beginTransaction();
        sesija.persist(p);
        transakcija.commit();
        sesija.close();
        return p;
    }

    public static List<ProgramskiPoziv> dohvatiSveProgramskePozive() {
        List<ProgramskiPoziv> projekti;
        Session sesija = factory.openSession();
        projekti = (ArrayList<ProgramskiPoziv>) sesija.createQuery("from ProgramskiPoziv").list();
        sesija.close();
        return projekti;
    }

    public static ProgramskiPoziv dohvatiPoNazivu(String naziv) {
        Session sesija = factory.openSession();
        Query upit = sesija.createQuery("from ProgramskiPoziv where naziv = :naziv");
        upit.setParameter("naziv", naziv);
        ProgramskiPoziv rezultat = (ProgramskiPoziv) upit.uniqueResult();
        sesija.close();
        return rezultat;
    }
}
