package simplex.phases;

import java.awt.Point;

import simplex.Helper;
import zlomky.Zlomok;

public class IPhase extends Phase {

    private final String[][] input;
    private String change[];
    private Point pivot;
    private final int pomocne;
    private final int umele;
    private final String[] topTable;
    private final String[] leftTable;
    private final Zlomok zlomok;

    public IPhase(final String[][] input, final int pomocne, final int umele) {
        this.input = input;
        this.pomocne = pomocne;
        this.umele = umele;
        this.topTable = new String[input[0].length];
        this.leftTable = new String[this.topTable.length - pomocne - umele];
        this.zlomok = new Zlomok();
    }

    @Override
    public String[][] start() {
        boolean opakuj = true;
        Helper.print(this.input);
        System.out.println();
        zapisPremenne();
        System.out.println("---------------------------------");
        boolean niecoJeZle = false;

        while (opakuj) {
            String[][] oldInput = new String[this.input.length][this.input[0].length];
            oldInput = skopiruj(oldInput);
            this.pivot = findPivot(this.input);
            zamen();
            vydelMa();
            iteruj();
            Helper.print(this.input);
            System.out.println("---------------------------------");
            int kontroluj = 0;
            for (int i = 0; i < this.input[0].length; i++) {
                if (!this.input[this.input.length - 1][i].equals("0")) {
                    kontroluj++;
                }
            }
            if (kontroluj == 0) {
                opakuj = false;
            }
            int somZle = 0;
            for (int i = 0; i < this.input.length; i++) {
                for (int j = 0; j < this.input[0].length; j++) {
                    if (oldInput[i][j].equals(this.input[i][j])) {
                        somZle++;
                    }
                }
            }
            if (somZle == (this.input[0].length * this.input.length)) {
                niecoJeZle = true;
                break;
            }
        }
        if (niecoJeZle) {
            System.out.println("WTF");
            try {
                throw new Exception();
            } catch (final Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return this.input;
    }

    private String[][] skopiruj(final String[][] oldInput) {
        for (int i = 0; i < this.input.length; i++) {
            for (int j = 0; j < this.input[0].length; j++) {
                oldInput[i][j] = this.input[i][j];
            }
        }
        return oldInput;
    }

    public String[] getLefTable() {
        return this.leftTable;
    }

    private void iteruj() {
        final String[] riadokPivot = this.input[this.pivot.x];
        final String pivotString = this.input[this.pivot.x][this.pivot.y];

        for (int i = 0; i < this.input.length; i++) {
            final String[] nulovany = this.input[i];
            final String nasobicString = nulovany[this.pivot.y];

            if ((i != this.pivot.x)) {
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
        final String newInt = this.input[this.pivot.x][this.pivot.y];
        for (int i = 0; i < this.input[0].length; i++) {
            if (!this.input[this.pivot.x][i].equals("0")) {
                this.input[this.pivot.x][i] = this.zlomok.del(this.input[this.pivot.x][i], newInt);
            }
        }
    }

    private void zamen() {
        final String newChar = this.topTable[this.pivot.y];
        this.leftTable[this.pivot.x] = newChar;
    }

    private void vypisPremenne() {
        for (final String s : this.topTable) {
            System.out.print(s + ", ");
        }

        System.out.println();

        for (final String s : this.leftTable) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private void zapisPremenne() {
        for (int i = 0; i < this.umele; i++) {
            this.leftTable[i] = "u" + (i + 1);
        }
        int pomocnaI = 0, pomocnaJ = 0;
        for (int i = 0; i < this.input[0].length; i++) {
            if (i < (this.input[0].length - 1 - this.pomocne - this.umele)) {
                this.topTable[i] = "x" + (i + 1);
                pomocnaI = i;
            } else if (i < (this.input[0].length - 1 - this.umele)) {
                this.topTable[i] = "p" + ((i - pomocnaI));
                pomocnaJ = i;
            } else if (i < (this.input[0].length - 1)) {
                this.topTable[i] = "u" + ((i - pomocnaJ));
            } else {
                this.topTable[i] = "b";
            }
        }
    }

    private Point findPivot(final String[][] input) {
        final int top = input.length - 1;

        String smallestString = input[top][0];

        Point point = new Point(top, 0);

        for (int i = 0; i < (input[top].length - 1); i++) {
            if (this.zlomok.porovnaj(smallestString, ">", input[top][i])) {
                smallestString = input[top][i];
                point = new Point(top, i);
            }
        }

        Point pivot = new Point(0, point.y);
        smallestString = this.zlomok.del(input[0][input[0].length - 1], input[0][point.y]);

        for (int i = 0; i < (top - 1); i++) {
            // if (this.zlomok.porovnaj(input[i][point.y], ">", "0")) {
                if (this.zlomok.porovnaj(smallestString, ">",
                        this.zlomok.del(input[i][input[i].length - 1], input[i][point.y]))) {
                    smallestString = this.zlomok.del(input[i][input[i].length - 1], input[i][point.y]);
                    pivot = new Point(i, point.y);
                }
            // }
        }

        System.out.println("pivot :" + input[pivot.x][pivot.y] + "  ,   na bode [" + pivot.x + "," + pivot.y + "]");
        System.out.println();
        return pivot;
    }

}