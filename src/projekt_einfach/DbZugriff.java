package projekt_einfach;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbZugriff {
    static Connection connect = null;                                                                                   // Klasse connection liefert uns die Basis fÃ¼r alle Datenbankverbindungen
                                                                                    // Klasse connection liefert uns die Basis fÃ¼r alle Datenbankverbindunge

    public static Connection connect(){
        try {
            String db_driver = "com.mysql.cj.jdbc.Driver";                                                                  //was brauchen wir fÃ¼r die Verbindung zur Datenbank
            String db_url = "jdbc:mysql://localhost:3306/db_huibuh";                                                        //Datenbanktreiber, Datenbank-url und Datenbankname, Benutzername, Passwort
            String db_username ="root";                                                                                // --> String Variablen
            String db_pass = "";

            Class.forName(db_driver);                                                                                         // zugriff auf den treiber aus der library des mysql-Connectors
            connect = DriverManager.getConnection(db_url, db_username, db_pass);                                           //AuflÃ¶sung des treibernamens aus der String-Variable db_driver --> Suche in der Library
            //System.out.println("Datenbankverbindung hergestellt");

            return connect;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}







