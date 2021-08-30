package projekt_einfach;

import javax.swing.*;

public interface buttons {
     String sql = null;
     void aendern(String abteilung,JTable table, JDialog frame);
     void loeschen(String sql,JTable table, JDialog frame);
     void tabelleAnzeigen(String sql, JTable table);                                                                    // tabelle abfragen über erhaltene daten und in gui anzeigen


}
