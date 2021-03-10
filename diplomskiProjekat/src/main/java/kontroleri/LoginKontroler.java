/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.LoginDAO;
import model.Korisnik;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;
import util.SessionUtils;


@ManagedBean(name = "LoginKontroler")
@SessionScoped
public class LoginKontroler implements Serializable {

    private String korime;
    private String lozinka;

    private String poruka;

    public String login() {
        lozinka = DigestUtils.sha256Hex(lozinka);
        Korisnik korisnik = LoginDAO.dohvatiKorisnika(korime, lozinka);
        if (korisnik == null) {
            poruka = "Uneto korisnicko ime i lozinka nisu prepoznati!";
            PrimeFaces.current().ajax().update("forma:poruka");
            return "login";
        }
        HttpSession sesija = SessionUtils.getSession();
        sesija.setAttribute("korisnik", korisnik);
        poruka = null;
        switch (korime) {
            case "admin":
                sesija.setAttribute("admin", true);
                return "/faces/admin/admin?faces-redirect=true";
            default:
                sesija.setAttribute("recenzent", true);
                return "/faces/korisnik/pocetna?faces-redirect=true";
        }
    }

    public String logout() {
        HttpSession sesija = SessionUtils.getSession();
        sesija.invalidate();
        return "/faces/index?faces-redirect=true";
    }

    public String getKorime() {
        return korime;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
