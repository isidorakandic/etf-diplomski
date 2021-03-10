package model;
// Generated Dec 6, 2019 10:16:38 PM by Hibernate Tools 4.3.1



/**
 * KorisnikOdgovorId generated by hbm2java
 */
public class KorisnikOdgovorId  implements java.io.Serializable {


     private int idodgovor;
     private int idpitanje;
     private int idkorisnik;
     private int idprojekat;

    public KorisnikOdgovorId() {
    }

    public KorisnikOdgovorId(int idodgovor, int idpitanje, int idkorisnik, int idprojekat) {
       this.idodgovor = idodgovor;
       this.idpitanje = idpitanje;
       this.idkorisnik = idkorisnik;
       this.idprojekat = idprojekat;
    }
   
    public int getIdodgovor() {
        return this.idodgovor;
    }
    
    public void setIdodgovor(int idodgovor) {
        this.idodgovor = idodgovor;
    }
    public int getIdpitanje() {
        return this.idpitanje;
    }
    
    public void setIdpitanje(int idpitanje) {
        this.idpitanje = idpitanje;
    }
    public int getIdkorisnik() {
        return this.idkorisnik;
    }
    
    public void setIdkorisnik(int idkorisnik) {
        this.idkorisnik = idkorisnik;
    }
    public int getIdprojekat() {
        return this.idprojekat;
    }
    
    public void setIdprojekat(int idprojekat) {
        this.idprojekat = idprojekat;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof KorisnikOdgovorId) ) return false;
		 KorisnikOdgovorId castOther = ( KorisnikOdgovorId ) other; 
         
		 return (this.getIdodgovor()==castOther.getIdodgovor())
 && (this.getIdpitanje()==castOther.getIdpitanje())
 && (this.getIdkorisnik()==castOther.getIdkorisnik())
 && (this.getIdprojekat()==castOther.getIdprojekat());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdodgovor();
         result = 37 * result + this.getIdpitanje();
         result = 37 * result + this.getIdkorisnik();
         result = 37 * result + this.getIdprojekat();
         return result;
   }   


}


