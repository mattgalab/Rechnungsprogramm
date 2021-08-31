package projekt_einfach;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GuiAuftragErstellen {

    private JComboBox comboBoxKunde;
    private JTable tableKunde;
    private JComboBox comboBoxRegeg;
    private JTable tableRechnungsgeg;
    private JLabel titleLabel;
    private JLabel kundeLabel;
    private JLabel datumLabel;
    private JLabel datumAnzeigenLabel;
    private JPanel panel;
    private JPanel panelKunde;
    private JButton buttonSpeichern;
    private LocalDate datumObj;
    private JDialog frame;


    public GuiAuftragErstellen() {
        datumObj = LocalDate.now();
        DateTimeFormatter datumFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String df = datumObj.format(datumFormat);
        datumAnzeigenLabel.setText(df);
        CBoxKundenname();
        buttonSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    auftragSpeichern();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Gespeichert");
            }
        });
        comboBoxKunde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tblausgabeKundendaten();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }

    private void CBoxKundenname() {                                                                //  abfrage für den inhalt der combobox
        try {


        Connection connect = DbZugriff.connect();
        String sql = "SELECT `Name` FROM kundendaten WHERE 1";
        PreparedStatement pst = connect.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);

        while (rs.next()) {
            comboBoxKunde.addItem(rs.getString("Name"));
        }
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void tblausgabeKundendaten() throws SQLException {

        //boxInhalt = (String) comboBoxKunde.getSelectedItem();
       String boxWert = comboBoxKunde.getSelectedItem().toString();
        Connection connect = DbZugriff.connect();
        String sql = "SELECT `KID`, `Name`, `Strasse`, `PLZ`, `Ort`, `Land`, `Ansprechpartner` FROM kundendaten WHERE name  LIKE '" + boxWert + "'";
        PreparedStatement pst = connect.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
        tableKunde.setModel(DbUtils.resultSetToTableModel(rs));                                                         //ResultSet in Tabelle
        connect.close();

    }

    private void auftragSpeichern() throws SQLException{
        Object kundenID =tableKunde.getModel().getValueAt(0,0);

        System.out.println(kundenID.toString());
        Connection connect = DbZugriff.connect();
        String sql = "INSERT INTO `auftrag`(`Auftragsnr`, `KID`, `Stunden`, `Datum`) VALUES (NULL,'"+ kundenID.toString() +"','0', '"+datumObj+"')";
        PreparedStatement insert = connect.prepareStatement(sql);
        insert.executeUpdate();
        connect.close();
        System.out.println("auftrag gespeichert");

    }
}
