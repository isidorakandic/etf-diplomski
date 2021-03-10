/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import dao.ProjekatDAO;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Korisnik;
import model.KorisnikProjekat;
import model.Projekat;
import org.primefaces.PrimeFaces;
import pomocni.Dokument;
import util.SessionUtils;

/**
 *
 * @author Isidora
 */
@ManagedBean(name = "pregledProjekataKontroler")
@SessionScoped
public class PregledProjekataKontroler implements Serializable {

    private List<Projekat> projekti;

    private final String putanjaZaPrikaz = "/diplomskiProjekat/uploads/";
    private final String putanjaZaCuvanje = "D:\\diplomski\\diplomskiProjekat\\src\\main\\webapp\\uploads\\";

    private ArrayList<Dokument> dokumenta;
    private boolean prikaziDokumenta = false;

    public List<Projekat> dohvatiProjekte() {
        if (SessionUtils.getSession().getAttribute("admin") != null) {
            projekti = ProjekatDAO.dohvatiSveProjekte();
        } else {
            Korisnik korisnik = (Korisnik) SessionUtils.getSession().getAttribute("korisnik");
            Set<KorisnikProjekat> korisnikProjekti = korisnik.getKorisnikProjekats();
            Iterator<KorisnikProjekat> iterator = korisnikProjekti.iterator();
            projekti = new ArrayList<>();
            while (iterator.hasNext()) {
                projekti.add(iterator.next().getProjekat());
            }
        }
        return projekti;
    }

    public void ucitajDokumenta(String lokacijaDokumenata) {
        File folder = new File(putanjaZaCuvanje + lokacijaDokumenata);
        dokumenta = new ArrayList<>();
        for (File dokument : folder.listFiles()) {
            String adresaDokumenta = putanjaZaPrikaz + lokacijaDokumenata + "/" + dokument.getName() + "?faces-redirect=true";
            String imeUFajlSistemu = dokument.getName();
            // hocemo da skinemo onaj auto generisani drugi deo poruke
            int noviKraj = imeUFajlSistemu.lastIndexOf("-");
            String imeDokumenta = imeUFajlSistemu.substring(0, noviKraj);
            Dokument novi = new Dokument(adresaDokumenta, imeDokumenta);
            dokumenta.add(novi);
        }
        prikaziDokumenta = true;
        PrimeFaces.current().ajax().update("dokumenti");
    }

    public String idiNazad() {
        if (SessionUtils.getSession().getAttribute("admin") != null) {
            return "/faces/admin/admin?faces-redirect=true";
        } else {
            return "/faces/koeisnik/pocetna?faces-redirect=true";
        }
    }

    public boolean isPrikaziDokumenta() {
        return prikaziDokumenta;
    }

    public void setPrikaziDokumenta(boolean prikaziDokumenta) {
        this.prikaziDokumenta = prikaziDokumenta;
    }

    public ArrayList<Dokument> getDokumenta() {
        return dokumenta;
    }

    public void setDokumenta(ArrayList<Dokument> dokumenta) {
        this.dokumenta = dokumenta;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    public void setProjekti(List<Projekat> projekti) {
        this.projekti = projekti;
    }

}
