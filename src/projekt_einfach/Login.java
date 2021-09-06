package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame {
    private JLabel logo;
    private JLabel titel;
    private JPasswordField passwordField;
    private JTextField textFieldName;
    private JLabel lblName;
    private JLabel lblPassword;
    private JPanel login;
    private JButton buttonLogin;
    private JDialog frame1;
    private static JFrame frame;

    public JPanel getLogin() {
        return login;
    }

    public Login() {

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connect=DbZugriff.connect();
                String name = textFieldName.getText();
                String pass = String.valueOf(passwordField.getPassword()) ;
                String namedb = null;
                String passdb =null;
                try
                {
                    String checkBe = "select * from db_huibuh.stammdaten"; //where Benutzer='"+ ben +"'and Passwort= '"+ pw +"'";
                    assert connect != null;
                    Statement antwort = connect.createStatement();
                    ResultSet rs = antwort.executeQuery(checkBe);
                    while (rs.next())
                    {
                        namedb = rs.getString("Benutzer");
                        passdb = rs.getString("Passwort");
                        System.out.println("name aus tabelle "+namedb );
                        System.out.println("Passwort aus tabelle "+passdb);
                    }
                    connect.close();
                }//end try-block
                catch (SQLException err)
                {
                    err.printStackTrace();
                }
                System.out.println("passwort: " + pass + "  //  name: " + name );
                if (namedb.equals(name) && passdb.equals(pass)){

                    frame.dispose();
                    JOptionPane.showMessageDialog(frame1,"Success! You're in!");
                    GuiUbersicht.hauptfenster();
                }
                else{
                    JOptionPane.showMessageDialog(frame1,"Invalid password. Try again.","Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void login() {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().getLogin());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}//end class
