package model;
// Generated Dec 6, 2019 10:16:38 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * KorisnikProjekat generated by hbm2java
 */
public class KorisnikProjekat  implements java.io.Serializable {


     private KorisnikProjekatId id;
     private Korisnik korisnik;
     private Projekat projekat;
     private Date datumDodeljivanja;
     private Integer ocena;
     private String stanjeOdgovora;
     private Set korisnikOdgovors = new HashSet(0);

    public KorisnikProjekat() {
    }

	
    public KorisnikProjekat(KorisnikProjekatId id, Korisnik korisnik, Projekat projekat, Date datumDodeljivanja, String stanjeOdgovora) {
        this.id = id;
        this.korisnik = korisnik;
        this.projekat = projekat;
        this.datumDodeljivanja = datumDodeljivanja;
        this.stanjeOdgovora = stanjeOdgovora;
    }
    public KorisnikProjekat(KorisnikProjekatId id, Korisnik korisnik, Projekat projekat, Date datumDodeljivanja, Integer ocena, String stanjeOdgovora, Set korisnikOdgovors) {
       this.id = id;
       this.korisnik = korisnik;
       this.projekat = projekat;
       this.datumDodeljivanja = datumDodeljivanja;
       this.ocena = ocena;
       this.stanjeOdgovora = stanjeOdgovora;
       this.korisnikOdgovors = korisnikOdgovors;
    }
   
    public KorisnikProjekatId getId() {
        return this.id;
    }
    
    public void setId(KorisnikProjekatId id) {
        this.id = id;
    }
    public Korisnik getKorisnik() {
        return this.korisnik;
    }
    
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    public Projekat getProjekat() {
        return this.projekat;
    }
    
    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }
    public Date getDatumDodeljivanja() {
        return this.datumDodeljivanja;
    }
    
    public void setDatumDodeljivanja(Date datumDodeljivanja) {
        this.datumDodeljivanja = datumDodeljivanja;
    }
    public Integer getOcena() {
        return this.ocena;
    }
    
    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
    public String getStanjeOdgovora() {
        return this.stanjeOdgovora;
    }
    
    public void setStanjeOdgovora(String stanjeOdgovora) {
        this.stanjeOdgovora = stanjeOdgovora;
    }
    public Set getKorisnikOdgovors() {
        return this.korisnikOdgovors;
    }
    
    public void setKorisnikOdgovors(Set korisnikOdgovors) {
        this.korisnikOdgovors = korisnikOdgovors;
    }




}


