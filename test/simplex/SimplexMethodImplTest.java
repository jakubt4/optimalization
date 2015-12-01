package simplex;

import org.junit.Assert;
import org.junit.Test;

public class SimplexMethodImplTest {

    private SimplexMethodeImpl simplexMethodeImpl;

    /**
     * REAL DATA TEST
     * --------------------------------------------
     *  z(x) = 9x1 + 10x2
     *
     *  x1  + 2x2 - p1 + u1 = 21
     *  3x1 + 2x2 - p2 + u2 = 27
     *
     *  w(u) = -4x1 -4x2 + p1 + p2 - u1 - u2 -48
     *  -------------------------------------------
     */
    private final String testInputSimplex[][] = { { "1", "2", "-1", "0", "1", "0", "21" },
            { "3", "2", "0", "-1", "0", "1", "27" }, { "9", "10", "0", "0", "0", "0", "0" },
            { "-4", "-4", "1", "1", "-1", "-1", "-48" } };

    private final String testInputSimplex2[][] = { { "2", "1", "-1", "0", "0", "1", "0", "0", "11" },
            { "1", "3", "0", "-1", "0", "0", "1", "0", "11" }, { "1", "4", "0", "0", "-1", "0", "0", "1", "16" },
            { "2", "10", "0", "0", "0", "0", "0", "0", "0" }, { "-4", "-8", "1", "1", "1", "-1", "-1", "-1", "-38" } };

    private final int umele = 2;

    private final int pomocne = 2;

    @Test
    public void simplexMethodImplTest1() {
        this.simplexMethodeImpl = new SimplexMethodeImpl(this.testInputSimplex, this.pomocne, this.umele);
        Assert.assertNotNull(this.simplexMethodeImpl);
        this.simplexMethodeImpl.start();
        System.out.println();
        System.out.println();
    }

    @Test
    public void simplexMethodImplTest2() {
        this.simplexMethodeImpl = new SimplexMethodeImpl(this.testInputSimplex2, 3, 3);
        Assert.assertNotNull(this.simplexMethodeImpl);
        this.simplexMethodeImpl.start();
        System.out.println();
        System.out.println();
    }
}
