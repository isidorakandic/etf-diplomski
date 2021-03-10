package model;
// Generated Dec 6, 2019 10:16:38 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Projekat generated by hbm2java
 */
public class Projekat implements java.io.Serializable {

    private Integer idprojekat;
    private ProgramskiPoziv programskiPoziv;
    private String naziv;
    private String rukovodilac;
    private String nioRukovodioca;
    private String zvanjeRukovodioca;
    private String angazovanjeRukovodioca;
    private String lokacijaDokumenata;
    private String oblast;
    private Date datum;
    private Integer konacnaOcena;
    private Set korisnikProjekats = new HashSet(0);

    public Projekat() {
    }

    public Projekat(ProgramskiPoziv programskiPoziv, String naziv, String rukovodilac, String oblast, Date datum) {
        this.programskiPoziv = programskiPoziv;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.oblast = oblast;
        this.datum = datum;
    }

    public Projekat(ProgramskiPoziv programskiPoziv, String naziv, String rukovodilac, String nioRukovodioca, String zvanjeRukovodioca, String angazovanjeRukovodioca, String lokacijaDokumenata, String oblast, Date datum, Integer konacnaOcena, Set korisnikProjekats) {
        this.programskiPoziv = programskiPoziv;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.nioRukovodioca = nioRukovodioca;
        this.zvanjeRukovodioca = zvanjeRukovodioca;
        this.angazovanjeRukovodioca = angazovanjeRukovodioca;
        this.lokacijaDokumenata = lokacijaDokumenata;
        this.oblast = oblast;
        this.datum = datum;
        this.konacnaOcena = konacnaOcena;
        this.korisnikProjekats = korisnikProjekats;
    }
    
    public Projekat(Projekat originalni){
        this.programskiPoziv = originalni.programskiPoziv;
        this.naziv = originalni.naziv;
        this.rukovodilac = originalni.rukovodilac;
        this.nioRukovodioca = originalni.nioRukovodioca;
        this.zvanjeRukovodioca = originalni.zvanjeRukovodioca;
        this.angazovanjeRukovodioca = originalni.angazovanjeRukovodioca;
        this.lokacijaDokumenata = originalni.lokacijaDokumenata;
        this.oblast = originalni.oblast;
        this.datum = originalni.datum;
        this.konacnaOcena = originalni.konacnaOcena;
        this.korisnikProjekats = originalni.korisnikProjekats;
    }

    public String stanjeProjekta(Korisnik k) {
        Iterator<KorisnikProjekat> iterator = korisnikProjekats.iterator();
        while (iterator.hasNext()) {
            KorisnikProjekat tekuci = iterator.next();
            if (tekuci.getKorisnik().getIdkorisnik().equals(k.getIdkorisnik())) {
                return tekuci.getStanjeOdgovora();
            }
        }
        return "";
    }

    public Integer getIdprojekat() {
        return this.idprojekat;
    }

    public void setIdprojekat(Integer idprojekat) {
        this.idprojekat = idprojekat;
    }

    public ProgramskiPoziv getProgramskiPoziv() {
        return this.programskiPoziv;
    }

    public void setProgramskiPoziv(ProgramskiPoziv programskiPoziv) {
        this.programskiPoziv = programskiPoziv;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getRukovodilac() {
        return this.rukovodilac;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNioRukovodioca() {
        return this.nioRukovodioca;
    }

    public void setNioRukovodioca(String nioRukovodioca) {
        this.nioRukovodioca = nioRukovodioca;
    }

    public String getZvanjeRukovodioca() {
        return this.zvanjeRukovodioca;
    }

    public void setZvanjeRukovodioca(String zvanjeRukovodioca) {
        this.zvanjeRukovodioca = zvanjeRukovodioca;
    }

    public String getAngazovanjeRukovodioca() {
        return this.angazovanjeRukovodioca;
    }

    public void setAngazovanjeRukovodioca(String angazovanjeRukovodioca) {
        this.angazovanjeRukovodioca = angazovanjeRukovodioca;
    }

    public String getLokacijaDokumenata() {
        return this.lokacijaDokumenata;
    }

    public void setLokacijaDokumenata(String lokacijaDokumenata) {
        this.lokacijaDokumenata = lokacijaDokumenata;
    }

    public String getOblast() {
        return this.oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public Date getDatum() {
        return this.datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getKonacnaOcena() {
        return this.konacnaOcena;
    }

    public void setKonacnaOcena(Integer konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public Set getKorisnikProjekats() {
        return this.korisnikProjekats;
    }

    public void setKorisnikProjekats(Set korisnikProjekats) {
        this.korisnikProjekats = korisnikProjekats;
    }

}
