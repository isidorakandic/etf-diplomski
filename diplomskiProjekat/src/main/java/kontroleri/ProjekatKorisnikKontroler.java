/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikProjekatDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Korisnik;
import model.KorisnikProjekat;
import model.Projekat;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "projekatKorisnikKontroler")
@ViewScoped
public class ProjekatKorisnikKontroler {

    public String stanjeProjekta(Projekat p) {
        Korisnik korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        return p.stanjeProjekta(korisnik);
    }

    public String predjiNaOcenjivanje(Projekat projekat) {
        Korisnik korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        KorisnikProjekat kp = KorisnikProjekatDAO.nadjiPoKorisnikuIProjektu(korisnik, projekat);
        if (kp.getStanjeOdgovora().equals("poslati")) {
            SessionUtils.getSession().setAttribute("korisnikProjekat", kp);
            return "/faces/korisnik/pregledPoslatihOdgovora?faces-redirect=true";
        } else {
            SessionUtils.getSession().setAttribute("projekat", projekat);
            return "/faces/korisnik/ocenjivanjeProjekata?faces-redirect=true";
        }
    }

}
