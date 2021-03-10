/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.google.common.collect.Lists;
import dao.KorisnikDAO;
import dao.KorisnikOdgovorDAO;
import dao.KorisnikProjekatDAO;
import dao.OdgovorDAO;
import dao.ProjekatDAO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Korisnik;
import model.KorisnikOdgovor;
import model.KorisnikOdgovorId;
import model.KorisnikProjekat;
import model.Odgovor;
import model.Pitanje;
import model.ProgramskiPoziv;
import model.Projekat;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "ocenjivanjeKontroler")
@ViewScoped
public class OcenjivanjeKontroler implements Serializable {
    
    private String[] selektovaniOdgovori;
    private int ocena;
    
    private List<Pitanje> listaPitanja;
    private Projekat projekat;
    private Korisnik korisnik;
    
    @PostConstruct
    public void inicijalizacija() {
        projekat = (Projekat) SessionUtils.getSession().getAttribute("projekat");
        korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        KorisnikProjekat korisnikProjekat = KorisnikProjekatDAO.nadjiPoKorisnikuIProjektu(korisnik, projekat);
        ProgramskiPoziv programskiPoziv = projekat.getProgramskiPoziv();
        Set<Pitanje> pitanja = programskiPoziv.getPitanjes();
        listaPitanja = Lists.newArrayList(pitanja);
        
        if (korisnikProjekat.getStanjeOdgovora().equals("sacuvani")) {
            for (Pitanje pitanje : listaPitanja) {
                korisnikProjekat = KorisnikProjekatDAO.nadjiPoKorisnikuIProjektu(korisnik, projekat);
                Set<KorisnikOdgovor> korisnikOdgovori = korisnikProjekat.getKorisnikOdgovors();
                korisnikOdgovori.removeIf(((KorisnikOdgovor ko) -> !Objects.equals(ko.getPitanje().getIdpitanje(), pitanje.getIdpitanje())));  // hocemo samo odgovore na tekuce pitanje!
                Iterator<KorisnikOdgovor> iterator = korisnikOdgovori.iterator();
                String[] tekstoviOdgovora = new String[korisnikOdgovori.size()];
                int indeks = 0;
                while (iterator.hasNext()) {
                    tekstoviOdgovora[indeks++] = iterator.next().getOdgovor().getTekst();
                }
                pitanje.setTekstoviOdgovora(tekstoviOdgovora);
            }
            ocena = korisnikProjekat.getOcena();
        }
    }
    
    public List<Pitanje> dohvatiPitanja() {
        ProgramskiPoziv programskiPoziv = projekat.getProgramskiPoziv();
        Set<Pitanje> pitanja = programskiPoziv.getPitanjes();
        return Lists.newArrayList(pitanja);
    }
    
    public Map<String, String> napraviMapuOdgovora(Pitanje pitanje) {
        Set<Odgovor> odgovori = pitanje.getOdgovors();
        Iterator<Odgovor> iteratorOdgovora = odgovori.iterator();
        Map<String, String> mapa = new HashMap<>();
        while (iteratorOdgovora.hasNext()) {
            Odgovor o = iteratorOdgovora.next();
            mapa.put(o.getTekst(), o.getTekst());
        }
        return mapa;
    }
    
    public String samoSacuvaj() {
        sacuvajOdgovore();
        KorisnikProjekat korisnikProjekat = dohvatiKorisnikProjekat();
        KorisnikProjekatDAO.izmeniOcenu(korisnikProjekat, ocena);
        KorisnikProjekatDAO.izmeniStanjeOdgovora(korisnikProjekat, "sacuvani");
        return "/faces/korisnik/pocetna?faces-redirect=true";
    }
    
    public String sacuvajIZakljucaj() {
        sacuvajOdgovore();
        KorisnikProjekat korisnikProjekat = dohvatiKorisnikProjekat();
        KorisnikProjekatDAO.izmeniOcenu(korisnikProjekat, ocena);
        KorisnikProjekatDAO.izmeniStanjeOdgovora(korisnikProjekat, "poslati");
        return "/faces/korisnik/pocetna?faces-redirect=true";
    }
    
    private void sacuvajOdgovore() {
        KorisnikProjekat korisnikProjekat = dohvatiKorisnikProjekat();
        // ako su postojali prethodni obrisi ih prvo pa onda cuvaj nove!
        if (korisnikProjekat.getStanjeOdgovora().equals("sacuvani")) {
            obrisiStareOdgovore();
        }
        for (Pitanje pitanje : listaPitanja) {
            for (String tekstOdgovora : pitanje.getTekstoviOdgovora()) {
                Odgovor odgovor = OdgovorDAO.dohvatiPoPitanjuITekstu(pitanje, tekstOdgovora);
                KorisnikOdgovorId id = new KorisnikOdgovorId(odgovor.getIdodgovor(), pitanje.getIdpitanje(), korisnikProjekat.getKorisnik().getIdkorisnik(), korisnikProjekat.getProjekat().getIdprojekat());
                KorisnikOdgovor novi = new KorisnikOdgovor(id, korisnikProjekat, odgovor, pitanje);
                KorisnikOdgovorDAO.dodajKorisnikOdgovor(novi);
            }
        }
    }
    
    private KorisnikProjekat dohvatiKorisnikProjekat() {
        return KorisnikProjekatDAO.nadjiPoKorisnikuIProjektu(korisnik, projekat);
    }
    
    private void obrisiStareOdgovore() {
        KorisnikProjekat korisnikProjekat = KorisnikProjekatDAO.nadjiPoKorisnikuIProjektu(korisnik, projekat); // proba
        Set<KorisnikOdgovor> korisnikOdgovori = korisnikProjekat.getKorisnikOdgovors(); // stari odgovori
        for (KorisnikOdgovor ko : korisnikOdgovori) {
            KorisnikOdgovorDAO.obrisi(ko);
        }
        
    }
    
    public String[] getSelektovaniOdgovori() {
        return selektovaniOdgovori;
    }
    
    public void setSelektovaniOdgovori(String[] selektovaniOdgovori) {
        this.selektovaniOdgovori = selektovaniOdgovori;
    }
    
    public int getOcena() {
        return ocena;
    }
    
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    
    public List<Pitanje> getListaPitanja() {
        return listaPitanja;
    }
    
    public void setListaPitanja(List<Pitanje> listaPitanja) {
        this.listaPitanja = listaPitanja;
    }
    
}
