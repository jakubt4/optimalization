package parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final String rovnice;
    private final String cena;
    private final String typ;

    private int rovniceInt[][];
    private int cenaInt[];

    private final List<String> premenne = new ArrayList<>();

    public Parser(final String typ, final String cena, final String rovnice) {
        this.typ = typ;
        this.cena = cena;
        this.rovnice = rovnice;
    }

    public String getRovnice() {
        return this.rovnice;
    }

    public String getCena() {
        return this.cena;
    }

    public int[][] getRovniceInt() {
        parseRovniceToInt();
        return this.rovniceInt;
    }

    public List<String> getPremenne() {
        parseRovniceToInt();
        return this.premenne;
    }

    public void checkMax(){
        if (this.typ.equals("MIN")) {
            parseRovniceToInt();
        }
    }

    private void parseRovniceToInt() {
        int i = 0, indexPremennej, posladnyIndexPremennejPlus, posladnyIndexPremennejMinus, posladnyIndexPremennejRovne;
        String premenna;
        while ((indexPremennej = this.rovnice.indexOf('x', i)) != -1) {
            System.out.println(i + "          " + indexPremennej);
            i = indexPremennej;
            posladnyIndexPremennejPlus = this.rovnice.indexOf('+', i);
            posladnyIndexPremennejMinus = this.rovnice.indexOf('-', i);
            if ((posladnyIndexPremennejMinus < posladnyIndexPremennejPlus) && (posladnyIndexPremennejMinus > 0)) {
                premenna = this.rovnice.substring(i, posladnyIndexPremennejMinus - 1);
            } else if (posladnyIndexPremennejPlus >= 0) {
                System.out.println("----------------------------");
                System.out.println(posladnyIndexPremennejPlus);
                System.out.println("----------------------------");
                premenna = this.rovnice.substring(i, posladnyIndexPremennejPlus);
            } else {
                posladnyIndexPremennejPlus = this.rovnice.indexOf('>', i);
                posladnyIndexPremennejMinus = this.rovnice.indexOf('<', i);
                posladnyIndexPremennejRovne = this.rovnice.indexOf('=', i);
                if ((posladnyIndexPremennejMinus < posladnyIndexPremennejPlus) && (posladnyIndexPremennejMinus > 0)) {
                    premenna = this.rovnice.substring(i, posladnyIndexPremennejMinus);
                } else if ((posladnyIndexPremennejMinus > (posladnyIndexPremennejPlus - 1))
                        && (posladnyIndexPremennejPlus > 0)) {
                    premenna = this.rovnice.substring(i, posladnyIndexPremennejPlus - 1);
                } else {
                    premenna = this.rovnice.substring(i, posladnyIndexPremennejRovne - 1);
                }
            }
            System.out.println(premenna);
            System.out.println("-----------------------");
            if(!this.premenne.contains(premenna)){
                this.premenne.add(premenna);
            }
            i = indexPremennej + 1;
        }
    }

}
