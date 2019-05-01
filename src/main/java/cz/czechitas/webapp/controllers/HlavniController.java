package cz.czechitas.webapp.controllers;

import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.sun.org.apache.bcel.internal.generic.*;
import cz.czechitas.webapp.*;
import cz.czechitas.webapp.Form.*;

@Controller
public class HlavniController {

    private List<Clovek> uzivatele = new CopyOnWriteArrayList<>();

    public HlavniController(){
        uzivatele.add(new Clovek("Nikol", "Plav", "n.plav@gmail.com", "600", "12345", "12345"));
        uzivatele.add(new Clovek("TePic", "Nazdar", "aho@cas.cz", "600", "12345", "12345"));
    }
    

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView index = new ModelAndView("index");
        return index;

    }

    @RequestMapping (value = "/prihlaseni", method = RequestMethod.GET)
    public ModelAndView zobrazPrihlaseni(){
        ModelAndView ukazPrihlaseni = new ModelAndView("prihlaseni");
        ukazPrihlaseni.addObject("uzivatel","");
        return ukazPrihlaseni;
    }

    @RequestMapping(value = "/prihlaseni", method = RequestMethod.POST)
    public ModelAndView PrihlasSe(ClovekPrihlas prihlasenyClovek) {
        Clovek uzivatel = porovnejUzivatele(prihlasenyClovek.getEmail(),prihlasenyClovek.getHeslo());

        //úspěšné přihlášení
        if (overeni(uzivatel) == true) {
            ModelAndView prihlaseni = new ModelAndView("redirect:/welcome");
            return prihlaseni;
        }
        else {

            //vrátí zpět na přihlášení s vyplněným mailem, pokud zadá špatné heslo
            if (vyhledejUzivatele(prihlasenyClovek.getEmail()) == true)
            {
                ModelAndView prihlaseni = new ModelAndView("prihlaseni");
                prihlaseni.addObject("uzivatel",prihlasenyClovek.getEmail());
                return prihlaseni;
            }

            // pokud nenajde email přihlašovaného - hodí na registraci
             else {
                ModelAndView prihlaseni = new ModelAndView("redirect:/registrace");
                return prihlaseni;
            }
        }
    }

    @RequestMapping (value = "/welcome") 
    public ModelAndView ukazWelcome(){
        ModelAndView welcome = new ModelAndView("welcome");
        return welcome;
    }

    @RequestMapping("/registrace")
    public ModelAndView zobrazRegistraci() {
        ModelAndView registrace = new ModelAndView("registrace");
        return registrace;
    }

    @RequestMapping(value = "/registrace", method = RequestMethod.POST)
    public ModelAndView vlozRegistraci(ClovekReg novyUzivatel) {
        Clovek novyClovek = new Clovek(novyUzivatel.getKrestniJmeno(),novyUzivatel.getPrijmeni(),novyUzivatel.getEmail(),
               novyUzivatel.getTelefon(),novyUzivatel.getHeslo(),novyUzivatel.getHeslo2());

        // registrace pouze, jestliže uvedený email ještě registrován není a zároveň obě hesla jsou shodná a ukáže se rovnou přihlášení
        if (vyhledejUzivatele(novyClovek.getEmail()) == false && novyClovek.getHeslo().equals(novyClovek.getHeslo2())) {
            uzivatele.add(novyClovek);
            ModelAndView registrace = new ModelAndView("redirect:/prihlaseni");
            return registrace;
        }
        else {
            ModelAndView registrace = new ModelAndView("redirect:/registrace");
            return registrace;
        }
    }

    @RequestMapping ("/vsichni")
    public ModelAndView zobrazVsechny(){
        ModelAndView vsichni = new ModelAndView("vsichni");
        vsichni.addObject("seznamVsech",uzivatele);
        return vsichni;
    }

    @RequestMapping("/zapomenute-heslo")
    public ModelAndView zobrazZapHeslo() {
        ModelAndView zapHeslo = new ModelAndView("zapomenute-heslo");
        return zapHeslo;
    }

    @RequestMapping (value = "/zapomenute-heslo", method = RequestMethod.POST)
    public ModelAndView zapHeslo(ClovekHeslo clovekHeslo) {
        ModelAndView zapHeslo = new ModelAndView("redirect:/prihlaseni");

        return zapHeslo;
    }

    private Clovek porovnejUzivatele (String email, String heslo)
    {
        for (Clovek osoba : uzivatele){
           if (osoba.getEmail().equals(email) && osoba.getHeslo().equals(heslo))
           {
               return osoba;
           }
        }
        return null;
    }

    private boolean vyhledejUzivatele (String email)
    {
        for (Clovek osoba : uzivatele) {
            if (osoba.getEmail().equals(email)) {
                return true;
            }
        }
         return false;
    }

    private boolean overeni(Clovek osubka){
        if (osubka == null){
            return false;
        }
        else {
            return true;
        }
    }




}
