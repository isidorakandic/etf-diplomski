package model;
// Generated Dec 6, 2019 10:16:38 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Odgovor generated by hbm2java
 */
public class Odgovor  implements java.io.Serializable {


     private Integer idodgovor;
     private Pitanje pitanje;
     private String tekst;
     private Set korisnikOdgovors = new HashSet(0);

    public Odgovor() {
    }

	
    public Odgovor(Pitanje pitanje, String tekst) {
        this.pitanje = pitanje;
        this.tekst = tekst;
    }
    public Odgovor(Pitanje pitanje, String tekst, Set korisnikOdgovors) {
       this.pitanje = pitanje;
       this.tekst = tekst;
       this.korisnikOdgovors = korisnikOdgovors;
    }
   
    public Integer getIdodgovor() {
        return this.idodgovor;
    }
    
    public void setIdodgovor(Integer idodgovor) {
        this.idodgovor = idodgovor;
    }
    public Pitanje getPitanje() {
        return this.pitanje;
    }
    
    public void setPitanje(Pitanje pitanje) {
        this.pitanje = pitanje;
    }
    public String getTekst() {
        return this.tekst;
    }
    
    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    public Set getKorisnikOdgovors() {
        return this.korisnikOdgovors;
    }
    
    public void setKorisnikOdgovors(Set korisnikOdgovors) {
        this.korisnikOdgovors = korisnikOdgovors;
    }




}


