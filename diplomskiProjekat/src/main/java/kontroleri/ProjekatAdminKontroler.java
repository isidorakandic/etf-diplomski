/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikDAO;
import dao.KorisnikProjekatDAO;
import dao.ProjekatDAO;
import dao.ProgramskiPozivDAO;
import model.Projekat;
import model.Korisnik;
import model.KorisnikProjekat;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.KorisnikProjekatId;
import model.ProgramskiPoziv;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "projekatAdminKontroler")
@SessionScoped
public class ProjekatAdminKontroler implements Serializable {

    private Projekat projekat;
    private UploadedFile fajl;
    private String poruka;

    
    private boolean prviDeo = true;

    private String unetKorisnik;
    private String unetProjekat;
    private String unetPoziv;
    private List<Korisnik> korisnici;
    private List<Projekat> projekti;
    private List<ProgramskiPoziv> pozivi;

    private final String putanjaZaCuvanje = "D:\\diplomski\\diplomskiProjekat\\src\\main\\webapp\\uploads\\";

    @PostConstruct
    public void inicijalizacija() {
        korisnici = KorisnikDAO.dohvatiSveKorisnike();
        projekti = ProjekatDAO.dohvatiSveProjekte();
        pozivi = ProgramskiPozivDAO.dohvatiSveProgramskePozive();
        projekat = new Projekat(); // pa ce vrednosti da budu upisane
    }

    public void obrisi(Projekat projekat) {
        ProjekatDAO.obrisi(projekat);
        PrimeFaces.current().ajax().update("forma");

    }

    public String izmeni(Projekat p) {
        projekat =p;
        poruka = "nova poruka";
        return "izmeniProjekat?faces-redirect=true";

    }

    public List<String> predloziKorisnickoIme(String zadat) { // admin
        List<String> rezultat = new ArrayList<>();
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().startsWith(zadat)) {
                rezultat.add(korisnik.getKorisnickoIme());
            }
        }
        return rezultat;
    }

    public List<String> predloziNazivProjekta(String zadat) { // admin
        List<String> rezultat = new ArrayList<>();
        for (Projekat projekat : projekti) {
            if (projekat.getNaziv().startsWith(zadat)) {
                rezultat.add(projekat.getNaziv());
            }
        }
        return rezultat;
    }

    public List<String> predloziNazivPoziva(String zadat) { // admin
        List<String> rezultat = new ArrayList<>();
        for (ProgramskiPoziv poziv : pozivi) {
            if (poziv.getNaziv().startsWith(zadat)) {
                rezultat.add(poziv.getNaziv());
            }
        }
        return rezultat;
    }

    public void dodeliProjekat() { // admin
        Date datum = new Date();
        Korisnik trazeniKorisnik = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().equals(unetKorisnik)) {
                trazeniKorisnik = korisnik;
                break;
            }
        }
        Projekat trazeniProjekat = null;
        for (Projekat projekatPetlja : projekti) {
            if (projekatPetlja.getNaziv().equals(unetProjekat)) {
                trazeniProjekat = projekatPetlja;
                break;
            }
        }
        KorisnikProjekatId id = new KorisnikProjekatId(trazeniKorisnik.getIdkorisnik(), trazeniProjekat.getIdprojekat());
        KorisnikProjekat kp = new KorisnikProjekat(id, trazeniKorisnik, trazeniProjekat, datum, "nema");
        KorisnikProjekatDAO.dodajKorisnikProjekat(kp);
        poruka = "Projekat uspešno dodeljen recenzentu!";
        PrimeFaces.current().ajax().update("forma:poruka");

    }

    public void oduzmiProjekat() { // admin
        int idk = 0;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().equals(unetKorisnik)) {
                idk = korisnik.getIdkorisnik();
                break;
            }
        }
        int idp = 0;
        for (Projekat projekatPetlja : projekti) {
            if (projekatPetlja.getNaziv().equals(unetProjekat)) {
                idp = projekatPetlja.getIdprojekat();
                break;
            }
        }
        KorisnikProjekatDAO.obrisiKorisnikProjekat(idk, idp);
        poruka = "Projekat uspešno oduzet recenzentu!";
        PrimeFaces.current().ajax().update("forma:poruka");
    }

    public void kreirajProjekatPrviDeo() { // podeljeno u dva dela da bi mogla da se procita vrednost za naziv foldera
        Date datum = new Date();
        String nazivFoldera = projekat.getNaziv() + "-" + generisiNazivFoldera();
        String lokacijaDokumenata = nazivFoldera;
        projekat.setLokacijaDokumenata(lokacijaDokumenata);
        ProgramskiPoziv poziv = ProgramskiPozivDAO.dohvatiPoNazivu(unetPoziv);
        projekat.setProgramskiPoziv(poziv);
        projekat.setDatum(datum);
        prviDeo = false;
        PrimeFaces.current().ajax().update("panel", "panel2");
    }

    public String kreirajProjekatDrugiDeo() {
        ProjekatDAO.dodajProjekat(projekat);
        poruka = "Projekat uspešno dodat u sistem!";
        prviDeo = true;
        return "kreirajProjekat?faces-redirect=true";
    }
    
    public void izmeniProjekat(){
        if (unetPoziv != null) {
            ProgramskiPoziv poziv = ProgramskiPozivDAO.dohvatiPoNazivu(unetPoziv);
            projekat.setProgramskiPoziv(poziv);
        }
        ProjekatDAO.izmeniProjekat(projekat);
        poruka = "Projekat uspešno izmenjen!";
        PrimeFaces.current().ajax().update("forma:poruka");
    }
    
    public void sacuvajFajlJSF(FileUploadEvent e) {
        fajl = e.getFile();
        sacuvajFajl(fajl);
    }

    private String sacuvajFajl(UploadedFile fajl) {
        String konacnoImeFajla = null;
        try (InputStream input = fajl.getInputstream()) {
            String  nazivFoldera = projekat.getLokacijaDokumenata();
            String putanja = putanjaZaCuvanje + nazivFoldera;
            Path folder = Paths.get(putanja);
            if (!Files.exists(folder)) {
                try {
                    Files.createDirectories(folder);
                } catch (IOException ex) {
                    Logger.getLogger(RegistracijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String imeFajla = FilenameUtils.getBaseName(fajl.getFileName());
            String ekstenzija = FilenameUtils.getExtension(fajl.getFileName());
            Path temp = Files.createTempFile(folder, imeFajla + "-", "." + ekstenzija);
            Files.copy(input, temp, StandardCopyOption.REPLACE_EXISTING);
            konacnoImeFajla = temp.getFileName().toString(); // ***
        } catch (IOException ex) {
            Logger.getLogger(RegistracijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return konacnoImeFajla;
    }

    private String generisiNazivFoldera() {
        int leftLimit = 65;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public Projekat getProjekat() {
        return projekat;
    }

    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    public void setProjekti(List<Projekat> projekti) {
        this.projekti = projekti;
    }

    public String getUnetKorisnik() {
        return unetKorisnik;
    }

    public void setUnetKorisnik(String unetKorisnik) {
        this.unetKorisnik = unetKorisnik;
    }

    public String getUnetProjekat() {
        return unetProjekat;
    }

    public void setUnetProjekat(String unetProjekat) {
        this.unetProjekat = unetProjekat;
    }

    public boolean isPrviDeo() {
        return prviDeo;
    }

    public void setPrviDeo(boolean prviDeo) {
        this.prviDeo = prviDeo;
    }

    public UploadedFile getFajl() {
        return fajl;
    }

    public void setFajl(UploadedFile fajl) {
        this.fajl = fajl;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getUnetPoziv() {
        return unetPoziv;
    }

    public void setUnetPoziv(String unetPoziv) {
        this.unetPoziv = unetPoziv;
    }

}
