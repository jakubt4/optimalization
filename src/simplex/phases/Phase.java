package simplex.phases;

import simplex.api.SimplexMethod;
import zlomky.Zlomok;

public abstract class Phase implements SimplexMethod {

    private final Zlomok zlomok = new Zlomok();

    public int parse(final String string) {
        return Integer.parseInt(string);
    }
}
