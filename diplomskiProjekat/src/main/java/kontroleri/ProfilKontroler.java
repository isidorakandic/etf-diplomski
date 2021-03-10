/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.KorisnikDAO;
import model.Korisnik;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean
@Named(value = "profilKontroler")
@SessionScoped
public class ProfilKontroler implements Serializable {

    private UploadedFile novaBiografija;

    private Korisnik korisnik = new Korisnik();
    private String poruka;

    private final String putanjaZaPrikaz = "/diplomskiProjekat/uploads/";
    private final String putanjaZaCuvanje = "D:\\diplomski\\diplomskiProjekat\\src\\main\\webapp\\uploads";

    public boolean inicijalizacija() {
        // kada admin pristupa njegov atribut nije korisnik pa je on null, ali ce ga admin postaviti na vrednost za kog korisnika zeli da cita podatke
        korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
        return true;
    }

    public void izmeniProfil() {
        if (novaBiografija != null) {
            String naziv_biografije = sacuvajFajl(novaBiografija);
            korisnik.setNazivBiografije(naziv_biografije);
        }
        KorisnikDAO.izmeniKorisnika(korisnik);
        poruka = "Izmene uspešno zapamćene!";
        PrimeFaces.current().ajax().update("forma");
    }

    public String biografijaLink() {
        return putanjaZaPrikaz + korisnik.getNazivBiografije() + "?faces-redirect=true";
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

    public UploadedFile getNovaBiografija() {
        return novaBiografija;
    }

    public void setNovaBiografija(UploadedFile biografija) {
        this.novaBiografija = biografija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
