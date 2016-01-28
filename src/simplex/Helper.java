package simplex;

import javax.swing.JTextArea;

public abstract class Helper {

    public static void print(final String[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void print(final String[][] input, final JTextArea pocitane, final boolean vypisujem) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (vypisujem) {
                    pocitane.setText(pocitane.getText() + input[i][j] + "  ");
                }
                System.out.print(input[i][j] + "  ");
            }
            if (vypisujem) {
                pocitane.setText(pocitane.getText() + "\n");
            }
            System.out.println();
        }
    }
}
