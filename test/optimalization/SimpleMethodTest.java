package optimalization;

import org.junit.Test;

public class SimpleMethodTest {

    @Test
    public void SimpleMethodTestPrint() {
        SimpleMethodImpl simpl = new SimpleMethodImpl(5, 7);
        simpl.print();
        simpl.calculate();
    }
}
