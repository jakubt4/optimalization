package parser;

import java.util.List;

import org.junit.Test;

public class ParserTest {

    private final String rovnice = "3x1 + 2x2 >= 21\n3x1+2x2>=27";
    private final String cena = "z(x)=9x1+10x2";
    private final String typ = "MAX";

    @Test
    public void parserTest(){
        Parser parser = new Parser(this.typ, this.cena, this.rovnice);
        System.out.println(parser.getRovnice());
        System.out.println();
        System.out.println(parser.getCena());
        System.out.println();
        List<String> prem = parser.getPremenne();
        for (String p : prem) {
            System.out.println(p);
        }
    }

}
