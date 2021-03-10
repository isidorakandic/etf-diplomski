/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.google.common.collect.Lists;
import dao.KorisnikDAO;
import dao.KorisnikProjekatDAO;
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
@ManagedBean(name = "pregledOcenaKontroler")
@ViewScoped
public class PregledOcenaKontroler  implements Serializable  {
     private String[] selektovaniOdgovori;
    private int ocena;
    
    private List<Pitanje> listaPitanja;
    private Projekat projekat;
    private Korisnik korisnik;
    
    private KorisnikProjekat korisnikProjekat;
    
    @PostConstruct
    public void inicijalizacija() {
        korisnikProjekat = (KorisnikProjekat) SessionUtils.getSession().getAttribute("korisnikProjekat");
        projekat = korisnikProjekat.getProjekat();
        korisnik = korisnikProjekat.getKorisnik();
        ProgramskiPoziv programskiPoziv = projekat.getProgramskiPoziv();
        Set<Pitanje> pitanja = programskiPoziv.getPitanjes();
        listaPitanja = Lists.newArrayList(pitanja);
        
      //  if (korisnikProjekat.getStanjeOdgovora().equals("sacuvani")) {
            // treba ucitati odgovore!
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
      //  }
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

    public KorisnikProjekat getKorisnikProjekat() {
        return korisnikProjekat;
    }

    public void setKorisnikProjekat(KorisnikProjekat korisnikProjekat) {
        this.korisnikProjekat = korisnikProjekat;
    }

}
