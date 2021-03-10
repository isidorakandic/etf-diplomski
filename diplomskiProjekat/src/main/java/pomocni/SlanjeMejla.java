package pomocni;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Isidora
 */
public class SlanjeMejla {

    public static void posaljiMejl(String primalac, String naslov, String telo) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(25);
            email.setAuthenticator(new DefaultAuthenticator("bazarecenzenata@gmail.com", "bazarecenzenata123!"));
            email.setFrom("bazarecenzenata@gmail.com");
            email.setSubject(naslov);
            email.setMsg(telo);
            email.addTo(primalac);
            email.setStartTLSRequired(true);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(SlanjeMejla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void posaljiMejlNaViseAdresa(List<String> primaoci, String naslov, String telo) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(25);
            email.setAuthenticator(new DefaultAuthenticator("bazarecenzenata@gmail.com", "bazarecenzenata123!"));
            email.setFrom("bazarecenzenata@gmail.com");
            email.setSubject(naslov);
            email.setMsg(telo);
            for (String primalac : primaoci) {
                email.addTo(primalac);
            }
            email.setStartTLSRequired(true);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(SlanjeMejla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
