/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocni;

/**
 *
 * @author Isidora
 */
public class Dokument {

    private final String adresaDokumenta;
    private final String imeDokumenata;

    public Dokument(String adresaDokumenta, String imeDokumenata) {
        this.adresaDokumenta = adresaDokumenta;
        this.imeDokumenata = imeDokumenata;
    }

    public String getAdresaDokumenta() {
        return adresaDokumenta;
    }

    public String getImeDokumenata() {
        return imeDokumenata;
    }

}
