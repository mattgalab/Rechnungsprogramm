package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuiStammdaten {
    private JTextField textFieldStrasse;
    private JTextField textFieldHausnummer;
    private JTextField textFieldPlz;
    private JPanel main;
    private JPanel stammdatenUnter;
    private JTextField textFieldName;
    private JTextField textFieldOrt;
    private JTextField textFieldSteuernummer;
    private JTextField textFieldBenutzer;
    private JTextField textFieldPasswort;
    private JLabel LabelName;
    private JLabel LabelStrasse;
    private JLabel labelPlz;
    private JLabel labelHausnummer;
    private JLabel labelSteuernummer;
    private JLabel labelOrt;
    private JLabel labelBenutzer;
    private JLabel labelPasswort;
    private JButton buttonSpeichernAendern;
    private JPanel title;
    private JDialog frame;

    public JPanel getMain() {
        return main;
    }

    public GuiStammdaten() {
        stammdatenanzeigen();
        buttonSpeichernAendern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            stammdatenSpeichernAendern();
            }
        });

    }


    public void stammdatenanzeigen(){
        Connection connect = DbZugriff.connect();

        try
        {
            //String sql = "SELECT * FROM kundendaten";
            String sql = "SELECT `Name`, `Strasse`, `Hausnr`, `PLZ`, `Ort`, `Steuernummer`, `Benutzer`, `Passwort` FROM stammdaten WHERE 1;";
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next())
            {
                String name = rs.getString("name");
                String strasse = rs.getString("strasse");
                String hausNr = rs.getString("hausnr");
                String plz = rs.getString("plz");
                String ort = rs.getString("ort");
                String steuerNr = rs.getString("steuernummer");
                String benutzer = rs.getString("benutzer");
                String passwort = rs.getString("passwort");

                textFieldName.setText(name);
                textFieldStrasse.setText(strasse);
                textFieldHausnummer.setText(hausNr);
                textFieldPlz.setText(plz);
                textFieldOrt.setText(ort);
                textFieldSteuernummer.setText(steuerNr);
                textFieldBenutzer.setText(benutzer);
                textFieldPasswort.setText(passwort);
            }//end while
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }

    }//end class stammdatenanzeigen
    public void stammdatenSpeichernAendern(){
        String name = textFieldName.getText();
        String strasse = textFieldStrasse.getText();
        String hausNr =textFieldHausnummer.getText();
        String plz = textFieldPlz.getText();
        String ort = textFieldOrt.getText();
        String steuerNr = textFieldSteuernummer.getText();
        String benutzer = textFieldBenutzer.getText();
        String passwort = textFieldPasswort.getText();
// TODO: 27.08.2021 überprüfung ob felder leer sind
        try {
            Connection con = DbZugriff.connect();
            String sql = "UPDATE `stammdaten` SET `Name`=?,`Strasse`=?,`Hausnr`=?,`PLZ`=?,`Ort`=?," +
                    "`Steuernummer`=?,`Benutzer`=?,`Passwort`=? WHERE 1;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, textFieldName.getText());
            pst.setString(2, textFieldStrasse.getText());
            pst.setString(3, textFieldHausnummer.getText());
            pst.setString(4, textFieldPlz.getText());
            pst.setString(5, textFieldOrt.getText());
            pst.setString(6, textFieldSteuernummer.getText());
            pst.setString(7, textFieldBenutzer.getText());
            pst.setString(8, textFieldPasswort.getText());
            if (name.equals("") && strasse.equals("") && hausNr.equals("") && plz.equals("") && ort.equals("") && steuerNr.equals("") ) {
                JOptionPane.showMessageDialog(frame, "Bitte vollständig ausfüllen!");
            }
            else {
                pst.executeUpdate();
            }
            con.close();

            JOptionPane.showMessageDialog(frame, "update erfolgreich");
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(frame," Fehler "+throwables);
            throwables.printStackTrace();
        }
    }

}
