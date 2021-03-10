/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.LoginDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Korisnik;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "korisnikKontroler")
@SessionScoped
public class KorisnikKontroler implements Serializable {

    public int brojNeocenjenihProjekataIspis() {
        Korisnik trenutniKorisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        int brojProjekata = trenutniKorisnik.brojNeocenjenihProjekata();
        return brojProjekata;
    }

    public boolean prijavaPrihvacena() {
        Korisnik korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        return korisnik.getStanje().equals("P");
    }

    public String stanjePrijave() {
        Korisnik korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        switch (korisnik.getStanje()) {
            case "P":
                return "prihvacna";
            case "O":
                return "odbijena";
            default:
                return "neobradjena";
        }
    }
}
