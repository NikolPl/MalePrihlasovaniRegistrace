package cz.czechitas.webapp.Form;

import cz.czechitas.webapp.*;

public class ClovekReg {

    private String krestniJmeno;
    private String prijmeni;
    private String email;
    private String telefon;
    private String heslo;
    private String heslo2;

    public ClovekReg() {
    }

    public ClovekReg(String krestniJmeno, String prijmeni, String email, String telefon, String heslo, String heslo2) {
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefon = telefon;
        this.heslo = heslo;
        this.heslo2 = heslo2;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public void setKrestniJmeno(String newValue) {
        krestniJmeno = newValue;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String newValue) {
        prijmeni = newValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String newValue) {
        telefon = newValue;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String newValue) {
        heslo = newValue;
    }

    public String getHeslo2() {
        return heslo2;
    }

    public void setHeslo2(String newValue) {
        heslo2 = newValue;
    }


}
