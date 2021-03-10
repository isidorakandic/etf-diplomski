/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikDAO;
import dao.ObavestenjeDAO;
import model.Obavestenje;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pomocni.SlanjeMejla;

/**
 *
 * @author Isidora
 */
@ManagedBean
@Named(value = "obavestenjeKontroler")
@SessionScoped
public class ObavestenjeKontroler implements Serializable {

    private String naslov;
    private String tekst;
    private Date vreme_kreiranja;
    private String potpis;
    private String tip;

    private List<Obavestenje> obavestenja;

    public ObavestenjeKontroler() {
        obavestenja = ObavestenjeDAO.dohvatiSvaObavestenjaSortiranaPoDatumu();
        if (obavestenja == null) {
            obavestenja = new ArrayList<>();
        }
    }

    public void posaljiObavestenje() {
        if (tip.equals("mejl")) {
            posaljiObavestenjeNaMejl();
        } else if (tip.equals("profil")) {
            postaviObavestenjeNaProfil();
        }
    }

    private void posaljiObavestenjeNaMejl() {
        List<String> mejlingLista = KorisnikDAO.dohvatiSveMejloveRegistrovanihKorisnika();
        SlanjeMejla.posaljiMejlNaViseAdresa(mejlingLista, naslov, tekst + "\n\n" + potpis);
    }

    private void postaviObavestenjeNaProfil() {
        vreme_kreiranja = new Date();
        Obavestenje o = new Obavestenje(naslov, tekst, potpis, vreme_kreiranja);
        ObavestenjeDAO.dodajObavestenje(o);
    }

    public List<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(List<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getVreme_kreiranja() {
        return vreme_kreiranja;
    }

    public void setVreme_kreiranja(Date vreme_kreiranja) {
        this.vreme_kreiranja = vreme_kreiranja;
    }

    public String getPotpis() {
        return potpis;
    }

    public void setPotpis(String potpis) {
        this.potpis = potpis;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}
