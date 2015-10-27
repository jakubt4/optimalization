package optimalization;

import java.util.Random;

import optimalization.api.SimplexMethod;

public class SimpleMethodImpl implements SimplexMethod {

    private final int base;
    private final int pomocne;
    private final int cena = 1;
    private final int pravaStrana = 1;

    private final int[][] matica;
    private final String[] zmeny;
    private final int fullX;
    private final int fullY;

    public SimpleMethodImpl(final int base, final int pomocne) {
        this.base = base;
        this.pomocne = pomocne;
        this.fullX = base + pomocne + this.pravaStrana + 1;
        this.fullY = pomocne + this.cena + 1;
        this.matica = new int[this.fullY][this.fullX];
        this.zmeny = new String[pomocne];
        vyplnZmeny();
        genBase();
        genPom();
        genRs();
        genZ();
    }

    private void genZ() {
        for(int i = 1; i <= this.base; i++){
            Random ran = new Random();
            this.matica[this.fullY - 1][i] = (-1) * (ran.nextInt(10));
        }
    }

    private void vyplnZmeny() {
        for (int i = 0; i < this.pomocne; i++) {
            this.zmeny[i] = "   p" + (i + 1);
        }
    }

    private void genBase() {
        for (int i = 1; i < (this.fullY - 1); i++) {
            for (int j = 1; j <= this.base; j++) {
                Random ran = new Random();
                this.matica[i][j] = ran.nextInt(10);
            }
        }
    }

    private void genPom() {
        for (int i = 1; i < this.fullY; i++) {
            for (int j = this.base + 1; j <= (this.base + this.pomocne); j++) {
                if ((j - this.base) == i) {
                    this.matica[i][j] = 1;
                } else {
                    this.matica[i][j] = 0;
                }
            }
        }
    }

    private void genRs(){
        for (int i = 1; i < (this.fullY - 1); i++) {
            Random ran = new Random();
            this.matica[i][this.fullX - 1] = ran.nextInt(100);
        }
    }

    @Override
    public void print() {
        int b = 1;
        int p = 1;
        for (int i = 0; i < this.fullY; i++) {
            for (int j = 0; j < this.fullX; j++) {
                if ((j == 0) && (i == 0)) {
                    System.out.print("     ");
                } else if (i == 0) {
                    if (j <= this.base) {
                        System.out.print("   x" + b);
                        b++;
                    } else {
                        if (p <= this.pomocne) {
                        System.out.print("   p" + p);
                        p++;
                        } else {
                            System.out.print("    b");
                        }
                    }
                } else if (j == 0) {
                    if (i <= this.pomocne) {
                        System.out.print(this.zmeny[i - 1]);
                    } else {
                        System.out.print("   -z");
                    }
                } else {
                    // Random ran = new Random();
                    // System.out.print(" " + ran.nextInt(10));
                    if (i == (this.fullY - 1)) {
                        if (this.matica[i][j] < 0) {
                            System.out.print("   " + this.matica[i][j]);
                        } else {
                            System.out.print("    " + this.matica[i][j]);
                        }
                    } else {
                        System.out.print("    " + this.matica[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public void calculate() {
        while (true) {
            if (!checkZaporne()) {
                break;
            }

        }
    }

    private boolean checkZaporne() {
        for (int i = 1; i <= this.base; i++) {
            if (this.matica[this.fullY - 1][i] < 0) {
                return true;
            }
        }
        return false;
    }
}
