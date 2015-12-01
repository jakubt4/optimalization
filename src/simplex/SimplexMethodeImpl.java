package simplex;

import simplex.api.SimplexMethod;
import simplex.phases.IPhase;
import zlomky.Zlomok;

public class SimplexMethodeImpl implements SimplexMethod{



    private String[][] inputIPhase;
    private String[][] inputIIPhase;
    private final int umele;
    private final int pomocne;
    private final Zlomok zlomok;

    public SimplexMethodeImpl(final String[][] input, final int pomocne, final int umele) {
        this.inputIPhase = input;
        this.pomocne = pomocne;
        this.umele = umele;
        this.zlomok = new Zlomok();
    }

    @Override
    public String[][] start() {
        final IPhase iphase = new IPhase(this.inputIPhase, this.pomocne, this.umele);
        this.inputIPhase = iphase.start();
        pareseNewInputForIIPhase();
        final IIPhase iiphase = new IIPhase(this.inputIIPhase, this.pomocne, iphase.getLefTable());
        this.inputIIPhase = iiphase.start();
        result(this.inputIIPhase, iiphase);
        return null;
    }

    private void result(final String[][] inputIIPhase, final IIPhase iiphase) {
        final String[] left = iiphase.getLefTable();
        for (int i = 0; i < (inputIIPhase.length - 1); i++) {
            if (left[i].contains("x")) {
                System.out.println(left[i] + "  =  " + inputIIPhase[i][inputIIPhase[i].length - 1]);
            }
        }
        System.out.println(
                "z  =  " + this.zlomok.nasob(inputIIPhase[inputIIPhase.length - 1][inputIIPhase[0].length - 1], "-1"));
    }

    private void pareseNewInputForIIPhase() {
        final int newMainLenght = this.inputIPhase[0].length - this.umele;
        this.inputIIPhase = new String[this.inputIPhase.length - 1][newMainLenght];
        for (int i = 0; i < (this.inputIPhase.length - 1); i++) {
            for (int j = 0; j < (this.inputIIPhase[0].length - 1); j++) {
                this.inputIIPhase[i][j] = this.inputIPhase[i][j];
            }
        }

        for (int i = 0; i < this.inputIIPhase.length; i++) {
            this.inputIIPhase[i][newMainLenght - 1] = this.inputIPhase[i][this.inputIPhase[0].length - 1];
        }
    }
}
