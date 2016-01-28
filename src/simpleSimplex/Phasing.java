package simpleSimplex;

import java.awt.Point;

import simplex.Helper;
import simplex.phases.Phase;
import zlomky.Zlomok;

public class Phasing extends Phase {

    private final String[][] input;
    private String change[];
    private Point pivot;
    private final int pomocne;
    private final String[] topTable;
    private final String[] leftTable;
    private final Zlomok zlomok;
    private boolean celeZle = false;

    public Phasing(final String[][] input, final int pomocne) {
        this.input = input;
        this.pomocne = pomocne;
        this.topTable = new String[this.input[0].length];
        this.leftTable = new String[pomocne];
        this.zlomok = new Zlomok();
    }

    @Override
    public String[][] start() {
        Helper.print(this.input);
        System.out.println();
        zapisPremenne();
        vypisPremenne(this.topTable);
        System.out.println();
        vypisPremenne(this.leftTable);
        System.out.println();

        String[][] oldInput = new String[this.input.length][this.input[0].length];
        oldInput = skopiruj(oldInput);
        while (true) {
            this.pivot = findPivot(this.input);

            if (this.pivot == null) {
                System.out.println("Asi koniec.. ci ?");
                if (!this.celeZle) {
                    dajVysledok();
                }
                return this.input;
            }

            zamen();
            System.out.print("Lava strana po zamene ->> ");
            vypisPremenne(this.leftTable);
            System.out.println();

            vydelMa();
            System.out.println("Matica po deleni:");
            Helper.print(this.input);

            iteruj();
            Helper.print(this.input);

            System.out.println("---------------------------------");
        }
    }

    private void dajVysledok() {
        for (int i = 0; i < (this.input.length - 1); i++) {
            if (this.leftTable[i].contains("x")) {
                System.out.println(this.leftTable[i] + " = " + this.input[i][this.input[i].length - 1]);
            }
        }
        final String z = this.input[this.input.length - 1][this.input[0].length - 1];
        if (this.zlomok.porovnaj(z, ">", "0")) {
            System.out.println("z = " + z);
        } else {
            System.out.println("Celeee zle.. ! z nesmie byt mensie ako 0.");
        }
    }

    private void iteruj() {
        final String[] riadokPivot = this.input[this.pivot.y];
        final String pivotString = this.input[this.pivot.y][this.pivot.x];

        for (int i = 0; i < this.input.length; i++) {
            final String[] nulovany = this.input[i];
            final String nasobicString = nulovany[this.pivot.x];

            if ((i != this.pivot.y)) {
                for (int j = 0; j < riadokPivot.length; j++) {
                    if (riadokPivot[j] != "0") {
                        String newNasobicString = nasobicString;
                        if ((this.zlomok.porovnaj(pivotString, "<", "0")
                                && (this.zlomok.porovnaj(nasobicString, "<", "0")))
                                || (this.zlomok.porovnaj(pivotString, ">", "0")
                                        && (this.zlomok.porovnaj(nasobicString, ">", "0")))
                                || (this.zlomok.porovnaj(pivotString, ">", "0")
                                        && (this.zlomok.porovnaj(nasobicString, "<", "0")))) {
                            newNasobicString = this.zlomok.nasob(nasobicString, "-1");
                        }
                        final String prenasobeny = this.zlomok.nasob(riadokPivot[j], newNasobicString);
                        this.input[i][j] = this.zlomok.scitaj(nulovany[j], prenasobeny);
                    }
                }
            }
        }
        System.out.println();
    }

    private void vydelMa() {
        final String newIntString = this.input[this.pivot.y][this.pivot.x];
        if (newIntString.equals("1")) {
            return;
        }
        for (int i = 0; i < this.input[0].length; i++) {
            if (!this.input[this.pivot.y][i].equals("0")) {
                this.input[this.pivot.y][i] = this.zlomok.del(this.input[this.pivot.y][i], newIntString);
            }
        }
    }

    private void zamen() {
        final String newChar = this.topTable[this.pivot.x];
        this.leftTable[this.pivot.y] = newChar;
    }

    private void vypisPremenne(final String[] table) {
        for (final String s : table) {
            System.out.print(s + " ");
        }
    }

    private String[][] skopiruj(final String[][] oldInput) {
        for (int i = 0; i < this.input.length; i++) {
            for (int j = 0; j < this.input[0].length; j++) {
                oldInput[i][j] = this.input[i][j];
            }
        }
        return oldInput;
    }

    private void zapisPremenne() {
        for (int i = 0; i < this.pomocne; i++) {
            this.leftTable[i] = "p" + (i + 1);
        }
        int pomocnaI = 0;
        for (int i = 0; i < this.input[0].length; i++) {
            if (i < (this.input[0].length - 1 - this.pomocne)) {
                this.topTable[i] = "x" + (i + 1);
                pomocnaI = i;
            } else if (i < (this.input[0].length - 1)) {
                this.topTable[i] = "p" + ((i - pomocnaI));
            } else {
                this.topTable[i] = "b";
            }
        }
    }

    private Point findPivot(final String[][] input) {
        Point pivot = null;
        boolean hladaj = true;
        final boolean[] hladajSirku = new boolean[input[0].length];
        final int spodokTab = input.length - 1;
        final int sirkaTab = input[0].length - 1;
        boolean mozemHladat = false;
        String najmensi = "";
        int najmenciPozicia = 0;
        String optimalnyDelenie = "";
        String optimalnyPivot = "";
        int optimalnyPivotPozicia = 0;
        int pocetNajmensich = 0;
        
        for(int i = 0; i < (sirkaTab);i++){
            hladajSirku[i] = true;
        }
        
        for(int i = 0; i < sirkaTab; i++){
            if(hladajSirku[i]){
                if(this.zlomok.porovnaj(input[spodokTab][i], "<", "0")){
                    najmensi = input[spodokTab][i];
                    najmenciPozicia = i;
                    mozemHladat = true;
                    break;
                }
            }
        }
        
        if(!mozemHladat){
            return null;
        }

        for (int i = 0; i < sirkaTab; i++) {
            if (this.zlomok.porovnaj(input[spodokTab][i], "<", "0")) {
                pocetNajmensich++;
            }
        }

        int pocitadlo = 0;
        while (hladaj) {
            for (int i = 0; i < sirkaTab; i++) {
                if (hladajSirku[i]) {
                    if (this.zlomok.porovnaj(input[spodokTab][i], "<", najmensi)) {
                        najmensi = input[spodokTab][i];
                        najmenciPozicia = i;
                    }
                }
            }
            System.out.println("Najmensi ->> " + najmensi + "; stlpec ->> " + (najmenciPozicia + 1));

            for (int i = 0; i < (spodokTab - 1); i++) {
                if (this.zlomok.porovnaj(input[i][najmenciPozicia], ">", "0")) {
                    optimalnyDelenie = this.zlomok.del(input[i][sirkaTab], input[i][najmenciPozicia]);
                    optimalnyPivotPozicia = i;
                    hladaj = false;
                    break;
                }
            }
            if (!hladaj) {
                String delenie = "";
                for (int i = 0; i < (spodokTab); i++) {
                    if (this.zlomok.porovnaj(input[i][najmenciPozicia], ">", "0")) {
                        delenie = this.zlomok.del(input[i][sirkaTab], input[i][najmenciPozicia]);
                        if (this.zlomok.porovnaj(delenie, "<", optimalnyDelenie)) {
                            optimalnyDelenie = delenie;
                            optimalnyPivot = input[i][najmenciPozicia];
                            optimalnyPivotPozicia = i;
                        }
                    }
                }
            }
            hladajSirku[najmenciPozicia] = false;
            pocitadlo++;
            if (pocitadlo == (pocetNajmensich)) {
                System.out.println("Nieco asi bude zle ...alebo to nema riesenie alebo degenerujeme...");
                this.celeZle = true;
                return null;
            }
        }
        pivot = new Point(najmenciPozicia, optimalnyPivotPozicia);
        System.out.println("Pivot  ->> " + optimalnyPivot + "; stlpec | riadok ->> " + (najmenciPozicia + 1) + " | "
                + (optimalnyPivotPozicia + 1));
        return pivot;
    }
}
