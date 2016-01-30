package simpleSimplex;

import org.junit.Test;

import doubleSimplex.DoubleSimplex;

public class DoubleSimplexTest {

    private final String trstInputDoubleSimplex1[][] = { { "2", "1", "-1", "0", "0", "1", "0", "0", "11" },
            { "1", "3", "0", "-1", "0", "0", "1", "0", "11" }, { "1", "4", "0", "0", "-1", "0", "0", "1", "16" },
            { "2", "10", "0", "0", "0", "0", "0", "0", "0" }, { "-4", "-8", "1", "1", "1", "-1", "-1", "-1", "-38" } };

    private final String trstInputDoubleSimplex2[][] = { { "1", "2", "-1", "0", "1", "0", "21" },
            { "3", "2", "0", "-1", "0", "1", "27" }, { "9", "10", "0", "0", "0", "0", "0" },
            { "-4", "-4", "1", "1", "-1", "-1", "-48" } };

    private final int pomocne2 = 2;

    private final int umele2 = 2;

    @Test
    public void test() {
        final DoubleSimplex doubleSimplex = new DoubleSimplex(this.trstInputDoubleSimplex2, this.pomocne2, this.umele2);
        doubleSimplex.start();
    }
}
