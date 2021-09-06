package projekt_einfach;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class methods implements buttons{
    JFrame frame;


    @Override
    public void aendern(String abteilung, JTable table, JDialog frame) {
        if (abteilung.equals("Rechnungen")) {

        }//end if Rechnungen
        else if (abteilung.equals("Kundendaten")){
            String Name = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
            String plz = table.getModel().getValueAt(table.getSelectedRow(),3).toString();
            String ort = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
            String land = table.getModel().getValueAt(table.getSelectedRow(),5).toString();
            String ansprech = table.getModel().getValueAt(table.getSelectedRow(),6).toString();
            String strasse =  table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
            String kid =  table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            System.out.println(Name+" PLZ "+plz+ " ORT "+ ort+ " LAND "+ land+ " ANSPRECH "+ ansprech + " STrasse "+ strasse + " KID " + kid);
            Connection connect = DbZugriff.connect();
            try {
                String sql = "UPDATE `kundendaten` SET `Name`=?,`PLZ`=?,`Ort`=?,`Land`=?,`Ansprechpartner`=?,`Strasse`=? WHERE `KID`=?;";
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setString(1, Name);
                pst.setString(2, plz);
                pst.setString(3, ort);
                pst.setString(4, land);
                pst.setString(5, ansprech);
                pst.setString(6, strasse);
                pst.setString(7, kid);

                pst.executeUpdate();
                connect.close();
                JOptionPane.showMessageDialog(frame, "update durchgefuehrt");
            } catch (SQLException b) {
                System.err.println("fehler");
                b.printStackTrace();
            }

        }//end if Kundendaten
        else if (abteilung.equals("Aufträge")){
            String sql = "UPDATE `auftrag` SET `Stunden`=? WHERE Auftragsnr =?";
            String wert1 = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            String wert2 = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
            Connection connect = DbZugriff.connect();
            try {
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setString(1, wert2);
                pst.setString(2, wert1);
                pst.executeUpdate();
                connect.close();
                JOptionPane.showMessageDialog(frame, "update durchgefuehrt");
            } catch (SQLException b) {
                System.err.println("fehler");
                b.printStackTrace();
            }

        }//end if aufträge
        else if (abteilung.equals("Rechnungsgegenstand")){
            String Name = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
            String honorar = table.getModel().getValueAt(table.getSelectedRow(),2).toString();
            String ust = table.getModel().getValueAt(table.getSelectedRow(),3).toString();
            String stundens = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
            String gegid =  table.getModel().getValueAt(table.getSelectedRow(), 0).toString();

            Connection connect = DbZugriff.connect();
            try {
                String sql = "UPDATE `regeg` SET `Gegenstand`=?,`Honorar`=?,`UST`=?,`Stundensatz`=? WHERE `GegID`=?;";
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setString(1, Name);
                pst.setString(2, honorar);
                pst.setString(3, ust);
                pst.setString(4, stundens);
                pst.setString(5, gegid);

                pst.executeUpdate();
                connect.close();
                JOptionPane.showMessageDialog(frame, "update durchgefuehrt");
            } catch (SQLException b) {
                System.err.println("fehler");
                b.printStackTrace();
            }
        }//end if rechnungsgegstand
    }//end aendern method


    // TODO: 28.08.2021 löschen klappt noch nicht. syntaxfehler/  java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax;
// check the manual that corresponds to your MariaDB server version for the right syntax to use near '? WHERE Auftragsnr =?' at line 1
    @Override
    public void loeschen(String sql,JTable table, JDialog frame) {
        int a = JOptionPane.showConfirmDialog(null, " Wirklich loeschen?", "Loeschen", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            //sql="DELETE FROM `auftrag` WHERE `auftragsnr`=?";
            int wert1 = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
            System.out.println(wert1);
            Connection connect = DbZugriff.connect();
            try {
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setInt(1, wert1);
                pst.executeUpdate();
                connect.close();
                JOptionPane.showMessageDialog(frame, "Gelöscht");
            } catch (SQLException b) {
                b.printStackTrace();
            }
        }
    }
    //funktioniert soweit
    @Override
    public void tabelleAnzeigen(String sql, JTable table) {                                                             // tabelle abfragen über erhaltene daten und in gui anzeigen
            Connection connect = DbZugriff.connect();																	// Datenbankverbindung
            try {
                PreparedStatement pst = connect.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }// end tabelleUbersicht

    public void erstellen(int abteilungnr) {
       /* if (abteilung.equals("Rechnungen")) {

        }//end if Rechnungen
        else if (abteilung.equals("Kundendaten")) {

        }//end if Kundendaten
        else if (abteilung.equals("Aufträge")) {

        }//end if Aufträge
        else if (abteilung.equals("Rechnungsgegenstand")) {
        }//end if Rechnungsgegenstand
*/
        int fall = abteilungnr;
        switch (fall){
            case 1:
                frame = new JFrame("GuiAuftragErstellen");
                frame.setContentPane(new GuiAuftragErstellen().getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                break;
            case 2:
                frame = new JFrame("GuiKundenErfassen");
                frame.setContentPane(new GuiKundenErfassen().getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                break;
            case 3:
                frame = new JFrame("GuiRechnungsgegErstellen");
                frame.setContentPane(new GuiRechnungsgegErstellen().getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                break;
            case 4:
                frame = new JFrame("GuiNeueRechnung");
                frame.setContentPane(new GuiNeueRechnung().getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                break;
        }

    }// end erstellen
}//end Class
