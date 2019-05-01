package cz.czechitas.webapp.Form;

public class ClovekPrihlas {

    private String email;
    private String heslo;

    public ClovekPrihlas() {
    }

    public ClovekPrihlas(String email, String heslo) {
        this.email = email;
        this.heslo = heslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String newValue) {
        heslo = newValue;
    }
}
