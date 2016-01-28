package simpleSimplex;

import org.junit.Assert;
import org.junit.Test;

public class SimpleSimplexTest {

    SimpleSimplex simplex;
    GeneratorSimplex geneSimpl;

    // z = 2x1 + x2
    // -x1 + x2 + p1 = 2
    // x1 + 2x2 + p2 = 10
    // 3x1 + x2 + p3 = 15
    private final String testInputSimplex1[][] = { { "-1", "1", "1", "0", "0", "2" }, { "1", "2", "0", "1", "0", "10" },
            { "3", "1", "0", "0", "1", "15" }, { "-2", "-1", "0", "0", "0", "0" } };

    // z = 2x1 + 3x2
    // x1 + 2x2 + p1 = 10
    // x1 + x2 + p2 = 7
    private final String testInputSimplex2[][] = { { "1", "2", "1", "0", "10" }, { "1", "1", "0", "1", "7" },
            { "-2", "-3", "0", "0", "0" } };

    // z = (7/2)x1 + 3x2 + (3/2)x3
    // 4x1 + 3x2 + p1 = 1200
    // 10x1 + 5x2 + (1/2)x3 + p2 = 3500
    // 3x1 + 5x2 + 6x3 + p3 = 5000
    private final String testInputSimplex3[][] = { { "4", "3", "0", "1", "0", "0", "1200" },
            { "10", "5", "1/2", "0", "1", "0", "3500" }, { "3", "5", "6", "0", "0", "1", "5000" },
            { "-7/2", "-3", "-3/2", "0", "0", "0", "0" } };

    // z = 3x1 + 2x2
    // x1 -10x2 + p1 = 10
    // -x1 + x2 + p2 = 40
    private final String testInputSimplex4[][] = { { "1", "-10", "1", "0", "10" }, { "-1", "1", "0", "1", "40" },
            { "-3", "-2", "0", "0", "0" } };

    private final int pomocne1 = 3;
    private final int pomocne2 = 2;
    private final int pomocne3 = 3;
    private final int pomocne4 = 2;

    @Test
    public void simplexTest() {
        this.simplex = new SimpleSimplex(this.testInputSimplex1, this.pomocne1, null, false);
        Assert.assertNotNull(this.simplex);
        this.simplex.start();
        System.out.println("*************************************************************");
    }

    @Test
    public void simplex2Test() {
        this.simplex = new SimpleSimplex(this.testInputSimplex2, this.pomocne2, null, false);
        Assert.assertNotNull(this.simplex);
        this.simplex.start();
        System.out.println("*************************************************************");
    }

    @Test
    public void simplex3Test() {
        this.simplex = new SimpleSimplex(this.testInputSimplex3, this.pomocne3, null, false);
        Assert.assertNotNull(this.simplex);
        this.simplex.start();
        System.out.println("*************************************************************");
    }

    @Test
    public void simplex4Test() {
        this.simplex = new SimpleSimplex(this.testInputSimplex4, this.pomocne4, null, false);
        Assert.assertNotNull(this.simplex);
        this.simplex.start();
        System.out.println("*************************************************************");
    }

    @Test
    public void generujSimplexTest() {
        for (int i = 0; i < 5; i++) {
            this.geneSimpl = new GeneratorSimplex();
            final String[][] testGeneratorOutput = this.geneSimpl.generuj();
            this.simplex = new SimpleSimplex(testGeneratorOutput, this.geneSimpl.getPocetP(), null, false);
            this.simplex.start();
            System.out.println("*************************************************************");
        }
    }
}
