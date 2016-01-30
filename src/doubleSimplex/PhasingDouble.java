package doubleSimplex;

import zlomky.Zlomok;

public class PhasingDouble {

    private final String[][] input;
    private final int pomocne;
    private final String[] topTable;
    private final String[] leftTable;
    private final Zlomok zlomok;
    private final int umele;

    public PhasingDouble(final String[][] input, final int pomocne, final int umele) {
        this.input = input;
        this.pomocne = pomocne;
        this.umele = umele;
        this.topTable = new String[this.input[0].length];
        this.leftTable = new String[pomocne];
        this.zlomok = new Zlomok();
    }
}
