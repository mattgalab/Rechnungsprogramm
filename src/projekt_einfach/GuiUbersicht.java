package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiUbersicht extends methods {

    private JPanel main;
    private JButton rechnungenButton;
    private JButton kundendatenButton;
    private JButton auftraegeButton;
    private JButton rechnungsgegenstandButton;
    private JButton stammdatenButton;
    private JTable table;
    private JLabel titel;
    private JLabel logo;
    private JScrollPane tableScroll;
    String loeschen;
    String aendern;
    String anzeigen;
    String abteilung;

    public GuiUbersicht() {
        rechnungenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loeschen="DELETE FROM `auftrag` WHERE `Auftragsnr`=?";
                aendern= "UPDATE `rechnung` SET `Re-Nr`=?,`KID`=?,`GegID`=?,`Auftragsnr`=?,`SID`=? WHERE 1;";
                anzeigen= "SELECT * FROM `rechnung` WHERE 1;";
                JFrame frame = new JFrame("Rechnungen");
                frame.setContentPane(new UnterMenu(anzeigen,loeschen,aendern,frame.getTitle()).getUnterMenu());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);



            }
        });
        kundendatenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loeschen="DELETE FROM `kundendaten` WHERE `KID`=?;";
                 anzeigen= "SELECT * FROM `kundendaten`;";
                JFrame frame = new JFrame("Kundendaten");
                abteilung=frame.getTitle();
                frame.setContentPane(new UnterMenu(anzeigen,loeschen,aendern,frame.getTitle()).getUnterMenu());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        auftraegeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loeschen="DELETE FROM `auftrag` WHERE `Auftragsnr`=?;";
                 anzeigen= "SELECT auftrag.Auftragsnr, auftrag.KID, kundendaten.Name, regeg.Gegenstand, " +
                                    "auftrag.Stunden FROM kundendaten INNER JOIN auftrag ON kundendaten.KID = auftrag.KID " +
                                    "INNER JOIN regeg ON auftrag.GegID= regeg.GegID;";
                JFrame frame = new JFrame("Aufträge");
                abteilung=frame.getTitle();
                frame.setContentPane(new UnterMenu(anzeigen,loeschen,aendern,frame.getTitle()).getUnterMenu());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        rechnungsgegenstandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loeschen="DELETE FROM `regeg` WHERE `GegID`=?;";
                 anzeigen= "SELECT * FROM `regeg` WHERE 1;";
                JFrame frame = new JFrame("Rechnungsgegenstand");
                abteilung=frame.getTitle();
                frame.setContentPane(new UnterMenu(anzeigen,loeschen,aendern,frame.getTitle()).getUnterMenu());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        stammdatenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 28.08.2021 Stammdaten erstellen 
                JFrame frame = new JFrame("GuiStammdaten");
                frame.setContentPane(new GuiStammdaten().getMain());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        String sql = "SELECT auftrag.Auftragsnr, auftrag.KID, kundendaten.Name, regeg.Gegenstand," +
                "auftrag.Stunden FROM kundendaten INNER JOIN auftrag ON kundendaten.KID = auftrag.KID " +
                "INNER JOIN regeg ON auftrag.GegID= regeg.GegID;";
        tabelleAnzeigen(sql,table);
    }

    public JPanel getMain() {
        return main;
    }

    public static void hauptfenster() {
        JFrame frame = new JFrame("GuiUbersicht");
        frame.setContentPane(new GuiUbersicht().getMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }//end constructor

}//end class
