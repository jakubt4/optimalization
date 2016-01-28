package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import simpleSimplex.SimpleSimplex;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;

    public void init() {
        this.setSize(600, 400);
        this.setTitle("Simplexing");

        this.setLayout(null);

        final JTextArea area = new JTextArea();
        final String eol = System.getProperty("line.separator");
        final JLabel label = new JLabel("Zadajte priklad (?):");
        label.setToolTipText("<html>" + "1 3 19 17 1 0 13" + "<br>" + "-2 12 -10 -8 0 1 -9" + "<br>"
                + "-18 -2 -3 -1 0 0 0" + "<br>" + "Zlomok :  x/y" + "<br>" + "ENTER za poslednym riadkom!" + "<br>"
                + "MEDZERAMI oddelovat iba cisla!" + "</html>");
        label.setBounds(10, 10, 200, 25);
        area.setBounds(10, 40, 200, 250);
        area.setBorder(BorderFactory.createBevelBorder(0));

        final JTextArea pocitane = new JTextArea();
        pocitane.setBounds(230, 40, 360, 250);
        pocitane.setEditable(false);
        
        final JScrollPane scrollFrame = new JScrollPane(pocitane);
        pocitane.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(360, 250));
        scrollFrame.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        final JLabel pomocneLabel = new JLabel("Pocet pomocnych");
        scrollFrame.setBounds(230, 40, 360, 250);
        pomocneLabel.setBounds(10, 310, 125, 30);
        final JTextField pomocneField = new JTextField();
        pomocneField.setBounds(140, 310, 40, 30);
        final JButton pocitaj = new JButton("Pocitaj");
        pocitaj.setBounds(200, 310, 100, 30);
        pocitaj.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                pocitane.setText("");
                final String input = area.getText();
                char kontrolny = ' ';
                int pocetZnakovVriadku = 0;
                int pomocnePoc = 0;
                while (kontrolny != '\n') {
                    kontrolny = input.charAt(pomocnePoc);
                    if (kontrolny == ' ') {
                        pocetZnakovVriadku++;
                    }
                    pomocnePoc++;
                }
                pocetZnakovVriadku++;

                final String[] lines = input.split("\r\n|\r|\n");
                final int pocetRiadkov = lines.length;

                final String[][] simplexInput = new String[pocetRiadkov][pocetZnakovVriadku];

                pomocnePoc = 0;
                for (int i = 0; i < pocetRiadkov; i++) {
                    for (int j = 0; j < pocetZnakovVriadku; j++) {
                        String s = "";
                        kontrolny = 'x';
                        while ((kontrolny != ' ') && (kontrolny != '\n')) {
                            kontrolny = input.charAt(pomocnePoc);
                            if ((kontrolny != ' ') && (kontrolny != '\n')) {
                                s = s + kontrolny;
                            }
                            pomocnePoc++;
                        }
                        simplexInput[i][j] = s;
                    }
                }

                final SimpleSimplex simpelx = new SimpleSimplex(simplexInput, Integer.valueOf(pomocneField.getText()),
                        pocitane, true);
                simpelx.start();

                String s = "";
                for (int i = 0; i < pocetRiadkov; i++) {
                    for (int j = 0; j < pocetZnakovVriadku; j++) {
                        if (j == (pocetZnakovVriadku - 1)) {
                            s = s + simplexInput[i][j];
                        } else {
                            s = s + simplexInput[i][j] + " ";
                        }
                    }
                    s = s + "\n";
                }
                // pocitane.setText(s);
                // pocitane.setText(String.valueOf(pocetZnakovVriadku) + '\n' +
                // String.valueOf(pocetRiadkov));

            }
        });
        
        this.add(scrollFrame);
        this.add(pomocneField);
        this.add(pomocneLabel);
        this.add(pocitaj);
        this.add(area);
        this.add(label);

        this.setVisible(true);
    }

}

// 1 3 19 17 1 0 13
// -2 12 -10 -8 0 1 -9
// -18 -2 -3 -1 0 0 0