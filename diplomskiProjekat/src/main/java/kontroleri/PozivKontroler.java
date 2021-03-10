/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.OdgovorDAO;
import dao.PitanjeDAO;
import dao.ProgramskiPozivDAO;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Odgovor;
import model.Pitanje;
import model.ProgramskiPoziv;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Isidora
 */
@ManagedBean
@Named(value = "pozivKontroler")
@SessionScoped
public class PozivKontroler implements Serializable {

    private boolean prikaziFormuZaPitanja = false;
    private boolean prikaziFormuZaOdgovore = false;
    private String nazivProgramskogPoziva;
    private ProgramskiPoziv poziv = null;
    private Pitanje pitanje = null;
    private String tekstPitanja;
    private String tekstOdgovora;

    private String poruka;

    public void dodajPitanje() {
        if (poziv == null) { // prvi odgovor je u pitanju
            poziv = new ProgramskiPoziv(nazivProgramskogPoziva);
            poziv = ProgramskiPozivDAO.sacuvajProgramskiPoziv(poziv);
        }
        prikaziFormuZaPitanja = true;
        tekstPitanja = null;
        PrimeFaces.current().ajax().update("forma:pitanja");
    }

    public void dodajOdgovor() {
        if (pitanje == null) {
            pitanje = new Pitanje(poziv, tekstPitanja);
            pitanje = PitanjeDAO.sacuvajPitanje(pitanje);
        }
        prikaziFormuZaOdgovore = true;
        tekstOdgovora = null; // da ponisti prethodni
        PrimeFaces.current().ajax().update("forma:odgovori");
    }

    public void daljeOdgovor() {
        Odgovor odgovor = new Odgovor(pitanje, tekstOdgovora);
        OdgovorDAO.sacuvajOdgovor(odgovor);
        prikaziFormuZaOdgovore = false;
        PrimeFaces.current().ajax().update("forma:odgovori");
    }

    public void daljePitanje() {
            prikaziFormuZaPitanja = false;
            pitanje = null;
            PrimeFaces.current().ajax().update("forma:pitanja");
    }

    public String zavrsi() {
            prikaziFormuZaPitanja = false;
            return "admin?faces-redirect=true";
  
    }

    public boolean isPrikaziFormuZaPitanja() {
        return prikaziFormuZaPitanja;
    }

    public void setPrikaziFormuZaPitanja(boolean prikaziFormuZaPitanja) {
        this.prikaziFormuZaPitanja = prikaziFormuZaPitanja;
    }

    public String getNazivProgramskogPoziva() {
        return nazivProgramskogPoziva;
    }

    public void setNazivProgramskogPoziva(String nazivProgramskogPoziva) {
        this.nazivProgramskogPoziva = nazivProgramskogPoziva;
    }

    public boolean isPrikaziFormuZaOdgovore() {
        return prikaziFormuZaOdgovore;
    }

    public void setPrikaziFormuZaOdgovore(boolean prikaziFormuZaOdgovore) {
        this.prikaziFormuZaOdgovore = prikaziFormuZaOdgovore;
    }

    public String getTekstPitanja() {
        return tekstPitanja;
    }

    public void setTekstPitanja(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public String getTekstOdgovora() {
        return tekstOdgovora;
    }

    public void setTekstOdgovora(String tekstOdgovora) {
        this.tekstOdgovora = tekstOdgovora;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
