package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UnterMenu extends methods{
    private JButton erfassenButton;
    private JButton aendernButton;
    private JTable table;
    private JLabel title;
    private JScrollPane tableScroll;
    private JPanel main;
    private JButton loeschenButton;
    private JButton refreshButton;
    private JPanel titlePanel;
    private JPanel tablePanel;
    private JPanel buttonsPanel;
    private JDialog frame;

    public JPanel getUnterMenu() {
        return main;
    }

    public UnterMenu(String anzeigen, String loeschen, String aendern, String abteilung ) {
        tabelleAnzeigen(anzeigen,table);
        title.setText(abteilung);
        erfassenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        aendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aendern(title.getText() , table, frame);
            }
        });
        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            loeschen(loeschen, table,frame);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              tabelleAnzeigen(anzeigen, table);
            }
        });
    }// end construtöööör
}//end class

