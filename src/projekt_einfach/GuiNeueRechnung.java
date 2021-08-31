package projekt_einfach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuiNeueRechnung extends JTable{
    private JComboBox comboBoxKunde;
    private JComboBox comboBoxAuftrag;
    private JComboBox comboBoxReGeg;
    private JButton speichernButton;
    private JButton hinzufügenButton;
    private JTable table1;
    private JLabel regegLabel;
    private JLabel aufrtagLabel;
    private JLabel kundeLabel;
    private JLabel datumLabel;
    private JLabel datumAnzeigenLabel;
    private JPanel panel;

    public GuiNeueRechnung() {
       ComboBoxKundeInhalt(); // befüllt die erste box mit werten aus db
       ComboBoxReGegInhalt(); // befüllt die letzte box mit werten aus db


        hinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBoxKunde.addItemListener(new ItemListener() { //Listener der je nachdem was in CBoxKunde ausgewählt, die passenden Aufträge in der CboxAuftrag anzeigt
            @Override
            public void itemStateChanged(ItemEvent e) {
                String boxInhaltKunde = comboBoxKunde.getSelectedItem().toString();
                String sql = "SELECT kundendaten.Name, auftrag.Auftragsnr FROM kundendaten " +
                        "INNER JOIN auftrag ON kundendaten.KID = auftrag.KID WHERE Name LIKE '"+boxInhaltKunde + "' ";
                try {
                    comboBoxAuftrag.removeAllItems();
                    Connection con = DbZugriff.connect();
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                        comboBoxAuftrag.addItem(rs.getString("Auftragsnr"));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }//end catch

            }
        });//end itemListener
        comboBoxAuftrag.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });//end itemListener
        comboBoxReGeg.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {


            }
        });//end itemListener
    }//end constructor

    public JPanel getPanel() {
        return panel;
    } // getter für panel


    private void ComboBoxReGegInhalt(){
        String sql= "SELECT * FROM regeg";
        Connection con = DbZugriff.connect();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                comboBoxReGeg.addItem(rs.getString("Gegenstand"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }// end ComboBoxReGegInhalt // Db abfrage und befüllen der box mit den werten
    private void ComboBoxKundeInhalt(){
        try {
            Connection connect = DbZugriff.connect();
            String sql = "SELECT * FROM `kundendaten` WHERE 1;";
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                comboBoxKunde.addItem(rs.getString("Name"));
            }
            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }// end catch
    }// end ComboBoxKundeInhalt // Db abfrage und befüllen der box mit den werten

    private void createUIComponents() {
        table1 = new JTable(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});    }
}//end class
