/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.AdminDAO;
import dao.KorisnikDAO;
import model.Korisnik;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import pomocni.SlanjeMejla;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "zahteviKontroler")
@SessionScoped
public class ZahteviKontroler implements Serializable {

    private List<Korisnik> lista;
    private String poruka;
    private Date datumUtil; // primefaces koristi ovaj

    @PostConstruct
    public void inicijalizacija() {
        lista = KorisnikDAO.dohvatiSveKorisnike();
        if (lista == null) {
            lista = new ArrayList<>();
        }
    }

    public String brojNeocenjenihProjekata(Korisnik korisnik) {
        if (korisnik.getStanje().equals("P")) {
            return "" + korisnik.brojNeocenjenihProjekata();
        } else {
            return "-";
        }
    }

    public boolean zahtevObradjen(Korisnik korisnik) {
        return korisnik.getStanje().equals("P") || korisnik.getStanje().equals("O");
    }

    public String dohvatiStanje(Korisnik korisnik) {
        switch (korisnik.getStanje()) {
            case "P":
                return "prihvacen";
            case "O":
                return "odbijen";
            default:
                return "neobradjen";
        }
    }

    public void prihvatiZahtev(Korisnik k) {
        AdminDAO.prihvatiZahtev(k);
        String naslov = "Baza recenzenata: Prijava prihvacena";
        String telo = "Poštovani " + k.getIme() + "\nUspešno ste registrovani u bazu recenzenata.";
        SlanjeMejla.posaljiMejl(k.getEmail(), naslov, telo);
        PrimeFaces.current().ajax().update("forma");
    }

    public void odbijZahtev(Korisnik k) {
        AdminDAO.odbijZahtev(k);
        String naslov = "Baza recenzenata: Prijava odbijena";
        String telo = "Poštovani " + k.getIme() + "\nVaš zahtev za registraciju u bazu recenzenata je odbijen.";
        SlanjeMejla.posaljiMejl(k.getEmail(), naslov, telo);
        PrimeFaces.current().ajax().update("forma");
    }

    public Date getDatumUtil() {
        return datumUtil;
    }

    public void setDatumUtil(Date datumUtil) {
        this.datumUtil = datumUtil;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public List<Korisnik> getLista() {
        return lista;
    }

    public void setLista(List<Korisnik> lista) {
        this.lista = lista;
    }

}
