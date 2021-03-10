/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikDAO;
import model.Korisnik;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "registracijaKontroler")
@SessionScoped
public class RegistracijaKontroler implements Serializable {

    private Korisnik korisnik;
    private String potvrdaLozinke;
    private UploadedFile biografija;

    private String poruka;
    private boolean prikaziFormu;

    private final String putanjaZaCuvanje = "D:\\diplomski\\diplomskiProjekat\\uploads\\";

    @PostConstruct
    public void inicijalizacija() {
        korisnik = new Korisnik();
        prikaziFormu = true;
    }

    public void registracijaZahtev() {
        // provera da li su lozinka i potvrda_loz identicne!
        if (!korisnik.getLozinka().equals(potvrdaLozinke)) {
            poruka = "Lozinka i potvrda lozinke moraju biti identicni!";
            PrimeFaces.current().ajax().update("forma:poruka");
        }
        // provera da li je korisnicko ime zauzeto
        if (KorisnikDAO.dohvatiKorisnikaPoKorisnickomImenu(korisnik.getKorisnickoIme()) != null) {
            poruka = "Korisnicko ime vec postoji!";
            PrimeFaces.current().ajax().update("forma:poruka");
        }
        korisnik.setLozinka(DigestUtils.sha256Hex(korisnik.getLozinka()));
        if (biografija != null) {
            korisnik.setNazivBiografije(sacuvajFajl(biografija));
        }
        korisnik.setStanje("I");
        KorisnikDAO.dodajKorisnika(korisnik);
        poruka = "Zahtev za registraciju je poslat!";
        prikaziFormu = false;
        PrimeFaces.current().ajax().update("forma");
    }

    private String sacuvajFajl(UploadedFile fajl) {
        String konacnoImeFajla = null;
        try (InputStream input = fajl.getInputstream()) {
            Path folder = Paths.get(putanjaZaCuvanje);
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public UploadedFile getBiografija() {
        return biografija;
    }

    public void setBiografija(UploadedFile biografija) {
        this.biografija = biografija;
    }

    public boolean isPrikaziFormu() {
        return prikaziFormu;
    }

    public void setPrikaziFormu(boolean prikaziFormu) {
        this.prikaziFormu = prikaziFormu;
    }

}
