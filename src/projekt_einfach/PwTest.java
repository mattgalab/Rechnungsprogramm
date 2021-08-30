package projekt_einfach;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PwTest {

	static Connection connect = null;                                                                                   // Klasse connection liefert uns die Basis fÃ¼r alle Datenbankverbindungen
	static String ben ;
	static String pw ;
	static String name;
	static String pass;

	public static void pwtest()
	{
		connect=DbZugriff.connect();
		try
		{
			//ben =Login.getTextFieldName();
			//pw = Login.getPasswordField();
			String checkBe = "select * from db_huibuh.stammdaten"; //where Benutzer='"+ ben +"'and Passwort= '"+ pw +"'";
			assert connect != null;
			Statement antwort = connect.createStatement();
			ResultSet rs = antwort.executeQuery(checkBe);
			while (rs.next())
			{
				 name = rs.getString("Benutzer");
				 pass = rs.getString("Passwort");
				System.out.println("name aus tabelle "+name );
				System.out.println("Passwort aus tabelle "+pass);
			}
			connect.close();
		}
		catch (SQLException err)
		{
			err.printStackTrace();
		}
		System.out.println("bentzer aus textfeld " + ben);
		System.out.println("passwort aus textfeld " + pw);

		if(ben.equals(name) && pw.equals(pass) ) //STRING mit Equals
		{
			System.out.println("zugang ja");

		}

		else
		{
			System.out.println(" zugang  nein");

		}



	}


}




