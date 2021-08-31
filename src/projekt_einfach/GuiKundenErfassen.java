package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuiKundenErfassen {
    private JPanel main;
    private JPanel kundenErfassen;
    private JLabel LabelName;
    private JLabel labelOrt;
    private JLabel labelAnsprech;
    private JLabel LabelStrasse;
    private JLabel labelPlz;
    private JPanel title;
    private JTextField textFieldStrasse;
    private JTextField textFieldPlz;
    private JTextField textFieldName;
    private JTextField textFieldOrt;
    private JTextField textFieldAnsprech;
    private JButton buttonSpeichern;
    private JPanel panel;
    private JTextField textFieldLand;
    private JLabel landLabel;
    private JDialog frame;

    public GuiKundenErfassen() {
        buttonSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String strasse = textFieldStrasse.getText();
                String plz = textFieldPlz.getText();
                String ort = textFieldOrt.getText();
                String land = textFieldLand.getText();
                String ansprechP = textFieldAnsprech.getText();

                String sql = "insert into kundendaten ( KID,`Ansprechpartner`,`Land`,`Name`,`Ort`, PLZ,`Strasse`) " +
                        "values (null,'"+ansprechP+"', '"+land+"','"+name+"','"+ort+"', '"+plz+"', '"+strasse+"');";

                try {
                    Connection connect = DbZugriff.connect();
                    assert connect != null;
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.executeUpdate();
                    connect.close();
                    JOptionPane.showMessageDialog(frame, "Neuer Kunde gespeichert!");
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {

    }
}
