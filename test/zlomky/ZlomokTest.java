package zlomky;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZlomokTest {

    private final String zlomok1 = "20/12";
    private final String zlomok2 = "8/12";
    private final String zlomok3 = "1/2";
    private final String zlomok4 = "3/2";
    private final String zlomok5 = "4/2";
    private final String zlomok6 = "1/4";
    private final String zlomok7 = "1/3";
    private final String zlomok8 = "5/3";
    private final String zlomok9 = "2/4";
    private final String zlomok10 = "-1/2";
    private final String zlomok11 = "-1/4";
    private final String zlomok12 = "-3/4";
    private final String cislo = "5";
    private final String cislo2 = "2";

    @Test
    public void test() {
        final Zlomok zlomok = new Zlomok();

        // ROVNAKY MENOVATEL SUCET
        assertEquals("7/3", zlomok.scitaj(this.zlomok1, this.zlomok2));
        assertEquals("2", zlomok.scitaj(this.zlomok3, this.zlomok4));
        assertEquals("7/2", zlomok.scitaj(this.zlomok4, this.zlomok5));

        // NASOBOK MENOVATEL SUCET
        assertEquals("3/4", zlomok.scitaj(this.zlomok3, this.zlomok6));
        assertEquals("7/6", zlomok.scitaj(this.zlomok2, this.zlomok3));
        assertEquals("11/12", zlomok.scitaj(this.zlomok2, this.zlomok6));

        // ROZNE SUCET
        assertEquals("5/6", zlomok.scitaj(this.zlomok3, this.zlomok7));
        assertEquals("11/6", zlomok.scitaj(this.zlomok7, this.zlomok4));
        assertEquals("13/6", zlomok.scitaj(this.zlomok8, this.zlomok9));

        // ZAPORNE SUCET
        assertEquals("1", zlomok.scitaj(this.zlomok4, this.zlomok10));
        assertEquals("1/4", zlomok.scitaj(this.zlomok9, this.zlomok11));
        assertEquals("1/4", zlomok.scitaj(this.zlomok11, this.zlomok9));
        assertEquals("17/12", zlomok.scitaj(this.zlomok8, this.zlomok11));
        assertEquals("-3/4", zlomok.scitaj(this.zlomok10, this.zlomok11));
        assertEquals("-1/2", zlomok.scitaj(this.zlomok6, this.zlomok12));

        // CELE CISLO SUCET
        assertEquals("11/2", zlomok.scitaj(this.zlomok3, this.cislo));
        assertEquals("11/2", zlomok.scitaj(this.cislo, this.zlomok3));
        assertEquals("10", zlomok.scitaj(this.cislo, this.cislo));

        // NASOB
        assertEquals("3/4", zlomok.nasob(this.zlomok3, this.zlomok4));
        assertEquals("25/3", zlomok.nasob(this.cislo, this.zlomok8));
        assertEquals("-1/6", zlomok.nasob(this.zlomok10, this.zlomok7));
        assertEquals("1/3", zlomok.nasob(this.zlomok2, this.zlomok3));
        assertEquals("3/16", zlomok.nasob(this.zlomok11, this.zlomok12));
        assertEquals("1", zlomok.nasob(this.zlomok3, this.cislo2));

        // DELENIE
        assertEquals("3/2", zlomok.del(this.zlomok3, this.zlomok7));
        assertEquals("9/10", zlomok.del(this.zlomok4, this.zlomok8));
        assertEquals("-1", zlomok.del(this.zlomok3, this.zlomok10));
        assertEquals("-1", zlomok.del(this.zlomok10, this.zlomok3));
        assertEquals("1", zlomok.del(this.zlomok10, this.zlomok10));
    }
}
