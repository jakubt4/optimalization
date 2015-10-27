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
            this.matica[i][this.fullX - 1] = ran.nextInt(100) + 1;
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

    int najmensiaCena = 0;
    int vahaNajmensieCena;

    double najmensiPodiel;
    int vahaNajmensiPodiel;

    int pocetZapornych;

    @Override
    public void calculate() {
        while (true) {
            if (!checkZaporne()) {
                break;
            }

            findSmallest();
            findAllZaporne();
            boolean mamCo = false;
            int p = this.pocetZapornych;
            while (p != 0) {
                mamCo = prepocitajMaticu();
                if (mamCo) {
                    break;
                }
                p--;
            }

            System.out.println(this.najmensiPodiel + "     " + this.vahaNajmensiPodiel);

            if (!mamCo) {
                break;
            }

            break;
        }
    }

    private void findAllZaporne() {
        int p = 0;
        for (int i = 1; i <= this.base; i++) {
            if (this.matica[this.fullY - 1][i] < 0) {
                p++;
            }
        }
        this.pocetZapornych = p;
    }

    private boolean prepocitajMaticu() {
        this.najmensiPodiel = (double) this.matica[1][this.fullX - 1] / (double) this.matica[1][this.vahaNajmensieCena];
        this.vahaNajmensiPodiel = 1;
        boolean mamCo = false;
        for (int i = 1; i <= this.pomocne; i++) {
            if (this.matica[i][this.vahaNajmensieCena] > 0) {
                double podiel;
                podiel = (double) this.matica[i][this.fullX - 1] / (double) this.matica[i][this.vahaNajmensieCena];
                System.out.println(this.matica[i][this.fullX - 1] + " / " + this.matica[i][this.vahaNajmensieCena]);

                if (podiel <= this.najmensiPodiel) {
                    this.najmensiPodiel = podiel;
                    this.vahaNajmensiPodiel = i;
                    mamCo = true;
                }
            }
        }
        return mamCo;
    }

    private void findSmallest() {
        for (int i = 1; i <= this.base; i++) {
            if (this.matica[this.fullY - 1][i] < this.najmensiaCena) {
                this.najmensiaCena = this.matica[this.fullY - 1][i];
                this.vahaNajmensieCena = i;
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

    private int najdiNajvacse(){
        int najvacsie = this.matica[1][this.fullX - 1];
        for(int i = 1; i < (this.fullY-1); i++){
            if (this.matica[i][this.fullX - 1] > najvacsie) {
                najvacsie = this.matica[i][this.fullX - 1];
            }
        }
        return najvacsie;
    }
}
