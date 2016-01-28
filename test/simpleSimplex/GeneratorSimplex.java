package simpleSimplex;

import java.util.Random;

public class GeneratorSimplex {

    private final int pocetRovnic;
    private final int pocetX;
    private final int pocetP;
    private final String[][] output;

    public GeneratorSimplex() {
        final Random randomGenerator = new Random();
        this.pocetRovnic = randomGenerator.nextInt(3) + 2;
        this.pocetX = randomGenerator.nextInt(4) + 2;
        this.pocetP = this.pocetRovnic;
        this.output = new String[this.pocetRovnic + 1][this.pocetX + this.getPocetP() + 1];

    }

    public String[][] generuj() {
        final Random randomGenerator = new Random();
        final int pocitadloP = 0;
        for (int i = 0; i < (this.pocetRovnic); i++) {
            for (int j = 0; j < (this.getPocetP() + this.pocetX + 1); j++) {
                if (j < this.pocetX) {
                    this.output[i][j] = String.valueOf(randomGenerator.nextInt(30) - randomGenerator.nextInt(20));
                } else if (j < (this.getPocetP() + this.pocetX)) {
                    if (i == (j - this.pocetX)) {
                        this.output[i][j] = "1";
                    } else {
                        this.output[i][j] = "0";
                    }
                } else {
                    this.output[i][j] = String.valueOf(randomGenerator.nextInt(30) - randomGenerator.nextInt(20));
                }
            }
        }
        for (int i = 0; i < (this.getPocetP() + this.pocetX + 1); i++) {
            if (i < this.pocetX) {
                final int gene = randomGenerator.nextInt(30) - randomGenerator.nextInt(10);
                if ((gene != 0) && (gene > 0)) {
                    this.output[this.pocetRovnic][i] = "-" + String.valueOf(gene);
                } else {
                    this.output[this.pocetRovnic][i] = String.valueOf(gene);
                }
            } else {
                this.output[this.pocetRovnic][i] = "0";
            }
        }

        return this.output;
    }

    public int getPocetP() {
        return this.pocetP;
    }
}
