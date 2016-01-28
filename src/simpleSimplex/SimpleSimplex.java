package simpleSimplex;

import javax.swing.JTextArea;

import simplex.api.SimplexMethod;

public class SimpleSimplex implements SimplexMethod {

    private final String[][] inputIPhase;
    private String[][] inputIIPhase;
    private final int pomocne;
    private JTextArea pocitane;
    private boolean vypisujem;

    public SimpleSimplex(final String[][] input, final int pomocne) {
        this.inputIPhase = input;
        this.pomocne = pomocne;
    }

    public SimpleSimplex(final String[][] input, final int pomocne, final JTextArea pocitane, final boolean vypisujem) {
        this.inputIPhase = input;
        this.pocitane = pocitane;
        this.pomocne = pomocne;
        this.vypisujem = vypisujem;
    }

    @Override
    public String[][] start() {
        final Phasing phsing = new Phasing(this.inputIPhase, this.pomocne, this.pocitane, this.vypisujem);
        phsing.start();
        return null;
    }

}
