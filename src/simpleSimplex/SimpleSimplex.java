package simpleSimplex;

import simplex.api.SimplexMethod;
import zlomky.Zlomok;

public class SimpleSimplex implements SimplexMethod {

    private final String[][] inputIPhase;
    private String[][] inputIIPhase;
    private final int pomocne;
    private final Zlomok zlomok;

    public SimpleSimplex(final String[][] input, final int pomocne) {
        this.inputIPhase = input;
        this.pomocne = pomocne;
        this.zlomok = new Zlomok();
    }

    @Override
    public String[][] start() {
        final Phasing phsing = new Phasing(this.inputIPhase, this.pomocne);
        phsing.start();
        return null;
    }

}
