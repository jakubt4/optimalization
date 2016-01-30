package doubleSimplex;

import simplex.api.SimplexMethod;

public class DoubleSimplex implements SimplexMethod {

    private final String[][] input;
    private final int pomocne;
    private final int umele;

    public DoubleSimplex(final String[][] input, final int pomocne, final int umele) {
        this.input = input;
        this.pomocne = pomocne;
        this.umele = umele;
    }

    @Override
    public String[][] start() {
        final PhasingDouble phase = new PhasingDouble(this.input, this.pomocne, this.umele);
        return null;
    }

}
