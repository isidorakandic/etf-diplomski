/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikProjekatDAO;
import dao.ProjekatDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.KorisnikProjekat;
import model.Projekat;
import org.primefaces.PrimeFaces;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "evidencijaKontroler")
@ViewScoped
public class EvidencijaKontorler implements Serializable {

    private List<KorisnikProjekat> lista;
    private String unetProjekat;
    private Integer konacnaOcena;

    @PostConstruct
    public void ucitajListu() {
        lista = KorisnikProjekatDAO.nadjiSve();
        unetProjekat = null;
        konacnaOcena = null; // da se ne vide kad refreshujemo prikaz
    }

    public String prikaziOdgovore(KorisnikProjekat kp) {
        if (kp.getStanjeOdgovora().equals("poslati")) {
            SessionUtils.getSession().setAttribute("korisnikProjekat", kp);
            return "/faces/admin/odgovoriRecenzenta?faces-redirect=true";
        } else {
            return "/faces/admin/evidencijaProjekata?faces-redirect=true";
        }
    }

    public String prikaziOcenu(KorisnikProjekat kp) {
        if (kp.getStanjeOdgovora().equals("poslati")) {
            return kp.getOcena().toString();
        } else {
            return "-";
        }
    }

    public void zapamtiKonacnuOcenu() {
        Projekat projekat = ProjekatDAO.dohvatiPoNazivu(unetProjekat);
        ProjekatDAO.zapamtiKonacnuOcenu(projekat, konacnaOcena);
        ucitajListu();
        PrimeFaces.current().ajax().update("forma"); // da bi konacna ocena bila prikazana u tabeli
    }

    public List<String> predloziNazivProjekta(String zadat) {
        List<String> rezultat = new ArrayList<>();
        List<Projekat> projekti = ProjekatDAO.dohvatiSveProjekte();
        for (Projekat projekat : projekti) {
            if (projekat.getNaziv().startsWith(zadat)) {
                rezultat.add(projekat.getNaziv());
            }
        }
        return rezultat;
    }

    public List<KorisnikProjekat> getLista() {
        return lista;
    }

    public void setLista(List<KorisnikProjekat> lista) {
        this.lista = lista;
    }

    public String getUnetProjekat() {
        return unetProjekat;
    }

    public void setUnetProjekat(String unetProjekat) {
        this.unetProjekat = unetProjekat;
    }

    public Integer getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(Integer konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

}
