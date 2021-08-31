package projekt_einfach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuiRechnungsgegErstellen {
    private JPanel panel;
    private JCheckBox bildungsauftragCheckBox;
    private JLabel titleRegegLabel;
    private JLabel rechnungsgegenstandLabel;
    private JLabel honorarLabel;
    private JLabel stundensatzLabel;
    private JLabel USTLabel;
    private JTextField textFieldRegeg;
    private JTextField textFieldHonrar;
    private JTextField textFieldStundensatz;
    private JLabel anzeigeUSTLabel;
    private JButton speichernButton;
    private boolean checkBoxClicked;
    private JDialog frame;

    public GuiRechnungsgegErstellen() {
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String reGeg = textFieldRegeg.getText();
                double stundensatz = 0;
                double honorar = 0;
                double ust = 0;

                    if(reGeg.equals("")) {
                        JOptionPane.showMessageDialog(frame,"Textfeld darf nicht leer sein!");
                    }

                    else if (checkBoxClicked) {
                        stundensatz = 0;
                        honorar = Double.parseDouble(textFieldHonrar.getText());
                        ust = 0;
                    }

                    else {
                        stundensatz = Double.parseDouble(textFieldStundensatz.getText());
                        honorar = 0;
                        ust = 0.19;
                    }

                    String sql = "insert into regeg (`Gegenstand`, `Honorar`, `Stundensatz`, `UST`) " +
                            "values ('"  + reGeg + "','" + honorar + "','" + stundensatz + "','" + ust + "');";
                try
                {
                    Connection connect = DbZugriff.connect();
                    assert connect != null;
                    PreparedStatement insert_dataset = connect.prepareStatement(sql);
                    insert_dataset.executeUpdate();
                    connect.close();

                } catch (SQLException a)
                {
                    a.printStackTrace();
                }
            }
        });
        bildungsauftragCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    checkBoxClicked = true;
                    anzeigeUSTLabel.setText("- Keine -");
                    textFieldStundensatz.setEnabled(false);
                    textFieldHonrar.setEnabled(true);
                }
                else
                {//checkbox has been deselected
                    checkBoxClicked = false;
                    anzeigeUSTLabel.setText("19%");
                    textFieldHonrar.setEnabled(false);
                    textFieldStundensatz.setEnabled(true);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }


}
