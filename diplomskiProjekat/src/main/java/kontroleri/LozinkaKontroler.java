/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikDAO;
import dao.LozinkaDAO;
import model.Korisnik;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Korisnik;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;
import util.SessionUtils;
import pomocni.*;

/**
 *
 * @author Isidora
 */
@ManagedBean
@Named(value = "LozinkaKontroler")
@SessionScoped
public class LozinkaKontroler implements Serializable {

    private String poruka;
    private String korime;
    private String mejl;

    private String staraLozinka;
    private String novaLozinka;
    private String potvrdaNoveLozinke;

    public void zaboravljenaLozinka() {
        if (potvrdiKorimeIMejl()) {
            String generisanaLozinka = generisiLozinku();
            // nova lozinka se salje na mejl
            String naslov = "Baza recenzenata - Nova lozinka";
            Korisnik k = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
            String telo = "Poštovani/a " + k.getIme() + ",\nVaša nova lozinka je: " + generisanaLozinka + ".\nData lozinka važi 15 minuta." ;
            SlanjeMejla.posaljiMejl(mejl, naslov, telo);
            LozinkaDAO.postaviPrivremenuLozinku(k, generisanaLozinka);
            poruka = "Privremena lozinka je poslata na Vašu imejl adresu.";
        }
        PrimeFaces.current().ajax().update("forma:poruka");
    }

    private boolean potvrdiKorimeIMejl() {
        Korisnik k = KorisnikDAO.dohvatiKorisnikaPoKorisnickomImenu(korime);
        if (k == null) {
            poruka = "Dato korisničko ime ne postoji u sistemu!";
            return false;
        }
        if (!k.getEmail().equals(mejl)) {
            poruka = "Imejl je pogrešna!";
            return false;
        }
        HttpSession sesija = SessionUtils.getSession();
        sesija.setAttribute("korisnik", k);
        return true;
    }

    private String generisiLozinku() {
        int leftLimit = 33;
        int rightLimit = 126;
        int targetStringLength = 20;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public String promeniLozinku() {
        HttpSession sesija = SessionUtils.getSession();
        Korisnik k = (Korisnik) sesija.getAttribute("korisnik");
        staraLozinka = DigestUtils.sha256Hex(staraLozinka);
        if (!k.getLozinka().equals(staraLozinka)) {
            poruka = "Pogresna trenutna lozinka!";
            PrimeFaces.current().ajax().update("forma");
            return "promenaLozinke?faces-redirect=true";
        }
        if (k.getPrivremenaLozinkaPostavljena()!= null){
            long postavljena = k.getPrivremenaLozinkaPostavljena();
            long sad = System.currentTimeMillis();
            long proslo = TimeUnit.MILLISECONDS.toMinutes(sad-postavljena);
            if (proslo > 15){
                poruka = "Privremena lozinka je istekla!";
                PrimeFaces.current().ajax().update("forma");
                return "promenaLozinke?faces-redirect=true";
            }
        }
        if (!novaLozinka.equals(potvrdaNoveLozinke)) {
            poruka = "Nova lozinka i potvrda iste moraju biti identične!";
            PrimeFaces.current().ajax().update("forma");
            return "promenaLozinke?faces-redirect=true";
        }
        novaLozinka = DigestUtils.sha256Hex(novaLozinka);
        potvrdaNoveLozinke = DigestUtils.sha256Hex(potvrdaNoveLozinke);
        LozinkaDAO.promeniLozinku(k, novaLozinka);
        return "/faces/korisnik/pocetna?faces-redirect=true";
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getKorime() {
        return korime;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getNovaLozinka() {
        return novaLozinka;
    }

    public void setNovaLozinka(String novaLozinka) {
        this.novaLozinka = novaLozinka;
    }

    public String getPotvrdaNoveLozinke() {
        return potvrdaNoveLozinke;
    }

    public void setPotvrdaNoveLozinke(String potvrdaNoveLozinke) {
        this.potvrdaNoveLozinke = potvrdaNoveLozinke;
    }

}
