package zlomky;

public class Zlomok implements ZlomokApi {

    @Override
    public String scitaj(String zlomok1, String zlomok2) {
        if(!zlomok1.contains("/")){
            zlomok1 = parsujNaZlomok(parseToInt(zlomok1));
        }

        if (!zlomok2.contains("/")) {
            zlomok2 = parsujNaZlomok(parseToInt(zlomok2));
        }

        int citatel1 = getCitatel(zlomok1);
        final int menovatel1 = getMenovatel(zlomok1);

        int citatel2 = getCitatel(zlomok2);
        final int menovatel2 = getMenovatel(zlomok2);

        if (menovatel1 == menovatel2) {
            final int citatel = citatel1 + citatel2;
            if ((citatel % menovatel1) == 0) {
                return String.valueOf(citatel / menovatel1);
            } else {
                return najdiBaseAkJe(citatel, menovatel1);
            }
        } else if (((menovatel1 % menovatel2) == 0) || ((menovatel2 % menovatel1) == 0)) {
            int menovatel;
            if (menovatel1 > menovatel2) {
                menovatel = menovatel1;
            } else {
                menovatel = menovatel2;
            }

            citatel1 = (menovatel / menovatel1) * citatel1;
            citatel2 = (menovatel / menovatel2) * citatel2;
            final int citatel = citatel1 + citatel2;

            return najdiBaseAkJe(citatel, menovatel);
        } else {
            final int menovatel = menovatel1 * menovatel2;

            citatel1 = (menovatel / menovatel1) * citatel1;
            citatel2 = (menovatel / menovatel2) * citatel2;
            final int citatel = citatel1 + citatel2;

            return najdiBaseAkJe(citatel, menovatel);
        }
    }

    @Override
    public String nasob(String zlomok1, String zlomok2) {
        if (!zlomok1.contains("/")) {
            zlomok1 = parsujNaZlomok(parseToInt(zlomok1));
        }

        if (!zlomok2.contains("/")) {
            zlomok2 = parsujNaZlomok(parseToInt(zlomok2));
        }

        int citatel1 = getCitatel(zlomok1);
        int menovatel1 = getMenovatel(zlomok1);

        final int citatel2 = getCitatel(zlomok2);
        final int menovatel2 = getMenovatel(zlomok2);

        citatel1 = citatel1 * citatel2;
        menovatel1 = menovatel1 * menovatel2;

        return najdiBaseAkJe(citatel1, menovatel1);
    }

    @Override
    public String del(String zlomok1, String zlomok2) {
        if (!zlomok1.contains("/")) {
            zlomok1 = parsujNaZlomok(parseToInt(zlomok1));
        }

        if (!zlomok2.contains("/")) {
            zlomok2 = parsujNaZlomok(parseToInt(zlomok2));
        }

        int citatel1 = getCitatel(zlomok1);
        int menovatel1 = getMenovatel(zlomok1);

        final int citatel2 = getCitatel(zlomok2);
        final int menovatel2 = getMenovatel(zlomok2);

        citatel1 = citatel1 * menovatel2;
        menovatel1 = menovatel1 * citatel2;

        if ((citatel1 < 0) && (menovatel1 < 0)) {
            citatel1 = citatel1 * (-1);
            menovatel1 = menovatel1 * (-1);
        } else if ((menovatel1 < 1) && (citatel1 > 1)) {
            citatel1 = citatel1 * (-1);
            menovatel1 = menovatel1 * (-1);
        }

        return najdiBaseAkJe(citatel1, menovatel1);
    }

    private String najdiBaseAkJe(int citatel, int menovatel) {
        final boolean base = true;
        int chodCez = 0;
        int testEsteCez = 0;
        while (true) {
            for (int i = 2; i <= 10; i++) {
                if (((citatel % i) == 0) && ((menovatel % i) == 0)) {
                    citatel = citatel / i;
                    menovatel = menovatel / i;
                    chodCez++;
                }
            }
            if (chodCez == testEsteCez) {
                if ((citatel == 0) || (menovatel == 0)) {
                    return "0";
                }
                if (menovatel == 1) {
                    return String.valueOf(citatel);
                }
                if ((citatel > 0) && (menovatel > 0) && (citatel == menovatel)) {
                    return "1";
                } else if (citatel < 0) {
                    final int newCitatel = citatel * (-1);
                    if (newCitatel == menovatel) {
                        return "-1";
                    }
                }
                return citatel + "/" + menovatel;
            }
            testEsteCez = chodCez;
        }
    }

    private int getCitatel(final String zlomok) {
        return Integer.valueOf(zlomok.substring(0, zlomok.indexOf("/")));
    }

    private int getMenovatel(final String zlomok) {
        return Integer.valueOf(zlomok.substring(zlomok.indexOf("/") + 1, zlomok.length()));
    }


    private String parsujNaZlomok(final int cislo) {
        return cislo + "/1";
    }

    private int parseToInt(final String c) {
        return Integer.parseInt(c);
    }

    public boolean porovnaj(String zlomok1, final String znakNerovnosti, String zlomok2) {
        if (!zlomok1.contains("/")) {
            zlomok1 = parsujNaZlomok(parseToInt(zlomok1));
        }

        if (!zlomok2.contains("/")) {
            zlomok2 = parsujNaZlomok(parseToInt(zlomok2));
        }

        final double citatel1 = getCitatel(zlomok1);
        final double menovatel1 = getMenovatel(zlomok1);

        final double citatel2 = getCitatel(zlomok2);
        final double menovatel2 = getMenovatel(zlomok2);

        switch(znakNerovnosti){
            case ">":

                if ((citatel1 / menovatel1) > (citatel2 / menovatel2)) {
                    return true;
                } else {
                    return false;
                }
            case "<":
                if ((citatel1 / menovatel1) < (citatel2 / menovatel2)) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }
}
