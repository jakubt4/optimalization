package simplex;

import simplex.phases.Phase;
import zlomky.Zlomok;

public class IIPhase extends Phase {

    private final String[][] input;
    private final int pomocne;
    private final String[] lefTable;
    private final String[] topTable;
    private final Zlomok zlomok;

    public IIPhase(final String[][] inputIIPhase, final int pomocne, final String[] lefTable) {
        this.input = inputIIPhase;
        this.pomocne = pomocne;
        this.lefTable = lefTable;
        this.topTable = new String[this.input[0].length];
        this.zlomok = new Zlomok();
    }

    @Override
    public String[][] start() {
        boolean opakuj = true;
        Helper.print(this.input);
        System.out.println();
        zapisPremenne();
        System.out.println("---------------------------------");

        while (opakuj) {
            //this.pivot = findPivot(this.input);
            //zamen();
            //vydelMa();
            //iteruj();
            Helper.print(this.input);
            System.out.println("---------------------------------");
            int kontroluj = 0;
            for (int i = 0; i < (this.input[0].length-1); i++) {
                if(this.zlomok.porovnaj(this.input[this.input.length - 1][i], "<", "0")){
                    kontroluj++;
                }
            }
            if (kontroluj == 0) {
                opakuj = false;
            }
        }
        return this.input;
    }

    public String[] getLefTable() {
        return this.lefTable;
    }

    private void zapisPremenne() {
        int pomocnaI = 0, pomocnaJ = 0;
        for (int i = 0; i < this.input[0].length; i++) {
            if (i < (this.input[0].length - 1 - this.pomocne)) {
                this.topTable[i] = "x" + (i + 1);
                pomocnaI = i;
            } else if (i < (this.input[0].length - 1)) {
                this.topTable[i] = "p" + ((i - pomocnaI));
                pomocnaJ = i;
            } else if (i < (this.input[0].length - 1)) {
                this.topTable[i] = "u" + ((i - pomocnaJ));
            } else {
                this.topTable[i] = "b";
            }
        }
    }

}
