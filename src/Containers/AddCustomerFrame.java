package Containers;

import DatabaseComponents.CarSearch;
import UIComponents.GButton;
import UIComponents.GPanel;
import UIComponents.GTextField;
import UIComponents.ImagePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DatabaseComponents.Connection.getConnection;

public class AddCustomerFrame extends GPanel implements ActionListener{
    private final JTextField [] textFields;
    private final ImagePanel confirmPanel;
    private final GButton saveButton, clearButton, backButton, yesButtonOnConfirm, cancelButtonOnConfirm;
    private JLabel customerNameOnConfirm = new JLabel();
    private JLabel customerPhoneOnConfirm = new JLabel();
    private JLabel customerAddressOnConfirm = new JLabel();

    private JLabel carPlateOnConfirm = new JLabel();
    private JLabel carMakeOnConfirm = new JLabel();
    private JLabel carModelOnConfirm = new JLabel();
    private JLabel carVinOnConfirm = new JLabel();
    private JLabel[] labels = new JLabel[]{customerNameOnConfirm, customerPhoneOnConfirm, customerAddressOnConfirm, carPlateOnConfirm, carMakeOnConfirm, carModelOnConfirm, carVinOnConfirm};
    private final ImagePanel emptyWarning;
    private final DefaultTableModel mainSearchCarModel;
    public AddCustomerFrame(JPanel callerPanel, DefaultTableModel mainSearchCarModel) {
        callerPanel.setVisible(false);
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));

        this.mainSearchCarModel = mainSearchCarModel;

        backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));

        JLabel customerInfo = new JLabel("Customer Information");
        JLabel carInfo = new JLabel("Car Information");
        customerInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        customerInfo.setForeground(Color.WHITE);
        carInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        carInfo.setForeground(Color.WHITE);

        ImagePanel customerInfoPanel = new ImagePanel(new ImageIcon("assets/images/customerInfoPanel.png"), new Dimension(623,431));
        ImagePanel carInfoPanel = new ImagePanel(new ImageIcon("assets/images/carInfoPanel.png"), new Dimension(623,431));
        confirmPanel = new ImagePanel(new ImageIcon("assets/images/confirmRegistration.png"), new Dimension(547,442));
        emptyWarning = new ImagePanel(new ImageIcon("assets/images/emptyWarning.png"), new Dimension(395,168));
        customerInfoPanel.setLayout(null);
        carInfoPanel.setLayout(null);
        confirmPanel.setLayout(null);
        emptyWarning.setLayout(null);
        emptyWarning.setVisible(false);
        emptyWarning.setBounds(442,281,395,168);

        JLabel customerName = new JLabel("Customer Name");
        JLabel customerPhone = new JLabel("Customer Phone NO.");
        JLabel customerAddress = new JLabel("Full Address");

        JLabel carPlate = new JLabel("Plate No");
        JLabel carMake = new JLabel("Make");
        JLabel carModel = new JLabel("Model");
        JLabel carVin = new JLabel("VIN");

        GTextField customerNameField = new GTextField();
        GTextField customerPhoneField = new GTextField();
        GTextField customerAddressField = new GTextField();

        GTextField carPlateField = new GTextField();
        GTextField carMakeField = new GTextField();
        GTextField carModelField = new GTextField();
        GTextField carVinField = new GTextField();

        saveButton = new GButton("Save", new Point(54, 15), 51, new Color(0x262626), new Font("Arial",Font.BOLD, 25));
        clearButton = new GButton("Clear", new Point(51, 15), 51, new Color(0x262626), new Font("Arial",Font.BOLD, 25));
        yesButtonOnConfirm = new GButton("Yes", new Point(50, 15), 22, new Color(0xA62631), new Font("Arial",Font.BOLD, 15));
        cancelButtonOnConfirm = new GButton("Cancel", new Point(53, 14), 22, new Color(0xA62631), new Font("Arial",Font.BOLD, 15));


        clearButton.addActionListener(this);
        saveButton.addActionListener(this);

        JLabel []PanelLabels = {customerName, customerPhone, customerAddress, carPlate, carMake, carModel, carVin};
        textFields = new JTextField[]{customerNameField, customerPhoneField, customerAddressField, carPlateField,carMakeField, carModelField, carVinField};
        for (JLabel label:PanelLabels){
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
            label.setForeground(Color.WHITE);
        }

        customerName.setBounds(35, 32, 99,17);
        customerPhone.setBounds(35, 133, 128, 17);
        customerAddress.setBounds(35, 234, 74, 17);
        carPlate.setBounds(38, 35, 53,17);
        carMake.setBounds(38, 136, 34 ,17);
        carModel.setBounds(38, 237, 39,17);
        carVin.setBounds(38, 338, 23 ,17);

        customerNameField.setBounds(35, 65, 168,22);
        customerPhoneField.setBounds(35, 163, 168,22);
        customerAddressField.setBounds(35, 267, 168,22);
        carPlateField.setBounds(38, 68, 168,22);
        carMakeField.setBounds(38, 169, 168,22);
        carModelField.setBounds(38, 267, 168,22);
        carVinField.setBounds(38, 371, 168,22);


        backButton.setBounds(271,26, 71, 43);
        clearButton.setBounds(505,663,161,61);
        saveButton.setBounds(682,663,161,61);

        yesButtonOnConfirm.setBounds(275, 387, 151, 44);
        cancelButtonOnConfirm.setBounds(120, 387, 151, 44);

        customerInfo.setBounds(87, 151, 170, 20);
        carInfo.setBounds(700, 151, 130,20);

        customerInfoPanel.setBounds(52,184, 623,431);
        carInfoPanel.setBounds(684,184, 623,431);
        confirmPanel.setBounds(393,99,547,442);

        confirmPanel.add(yesButtonOnConfirm);
        confirmPanel.add(cancelButtonOnConfirm);
        confirmPanel.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });
        yesButtonOnConfirm.addActionListener(this);
        cancelButtonOnConfirm.addActionListener(this);

        customerInfoPanel.add(customerName);
        customerInfoPanel.add(customerNameField);
        customerInfoPanel.add(customerPhone);
        customerInfoPanel.add(customerPhoneField);
        customerInfoPanel.add(customerAddress);
        customerInfoPanel.add(customerAddressField);

        carInfoPanel.add(carPlate);
        carInfoPanel.add(carPlateField);
        carInfoPanel.add(carMake);
        carInfoPanel.add(carMakeField);
        carInfoPanel.add(carModel);
        carInfoPanel.add(carModelField);
        carInfoPanel.add(carVin);
        carInfoPanel.add(carVinField);

        this.add(emptyWarning);
        this.add(confirmPanel);
        this.add(backButton);
        this.add(clearButton);
        this.add(saveButton);
        this.add(customerInfo);
        this.add(carInfo);
        this.add(customerInfoPanel);
        this.add(carInfoPanel);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton){
            for (JTextField textField : textFields) {
                textField.setText("");
            }
        }else if (e.getSource() == saveButton){
            boolean empty = false;
            for (JTextField textField : textFields) {
                if (textField.getText().equals(""))
                    empty = true;
            }
            if (empty){
                GButton close = new GButton("Close", new Point(36, 7), 20, new Color(0xA62631), new Font("Arial",Font.BOLD, 15));
                close.setBounds(141, 106, 109, 32);
                close.addActionListener(event -> emptyWarning.setVisible(false));
                emptyWarning.add(close);
                emptyWarning.setVisible(true);
            } else{
                for (JTextField textField : textFields) {

                    textField.setEditable(false);
                }
                for (int i=0;i< textFields.length;i++){
                    labels[i].setText(textFields[i].getText());
                    labels[i].setFont(new Font("Segoe UI", Font.BOLD, 9));
                    labels[i].setForeground(Color.WHITE);
                    textFields[i].setEditable(false);
                }
                customerNameOnConfirm.setBounds(194,192, 44, 12);
                customerPhoneOnConfirm.setBounds(191,216,119,12 );
                customerAddressOnConfirm.setBounds(173,241, 119, 12);

                carPlateOnConfirm.setBounds(146,264, 119, 12);
                carMakeOnConfirm.setBounds(162,289,119, 12 );
                carModelOnConfirm.setBounds(151,312, 119, 12);
                carVinOnConfirm.setBounds(140,336, 119, 12);

                confirmPanel.add(customerNameOnConfirm);
                confirmPanel.add(customerPhoneOnConfirm);
                confirmPanel.add(customerAddressOnConfirm);
                confirmPanel.add(carPlateOnConfirm);
                confirmPanel.add(carMakeOnConfirm);
                confirmPanel.add(carModelOnConfirm);
                confirmPanel.add(carVinOnConfirm);

                backButton.setVisible(false);
                clearButton.setVisible(false);
                saveButton.setVisible(false);
                confirmPanel.setVisible(true);
            }
        } else if (e.getSource() == yesButtonOnConfirm) {
            //Add to Database
            try(PreparedStatement pst = getConnection().prepareStatement("INSERT INTO Customers(Name, Address, PhoneNumber) VALUES (?,?,?)")) {

                pst.setString(1, customerNameOnConfirm.getText());
                pst.setString(2, customerAddressOnConfirm.getText());
                pst.setString(3, customerPhoneOnConfirm.getText());

                pst.executeUpdate();
                System.out.println("Customer Added");


            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            int id = 0;
            try (PreparedStatement pst = getConnection().prepareStatement("SELECT ID FROM Customers WHERE Name = ? AND PhoneNumber = ?")){

                pst.setString(1, customerNameOnConfirm.getText());
                pst.setString(2, customerPhoneOnConfirm.getText());

                ResultSet rs = pst.executeQuery();
                while(rs.next()) {
                    id = rs.getInt("ID");
                    System.out.println(id);
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            try(PreparedStatement pst = getConnection().prepareStatement("INSERT INTO Cars (PlateNo, Make, Model, VIN, CustomerID) VALUES (?,?,?,?,?)")) {

                pst.setString(1, carPlateOnConfirm.getText());
                pst.setString(2, carMakeOnConfirm.getText());
                pst.setString(3, carModelOnConfirm.getText());
                pst.setString(4, carVinOnConfirm.getText());
                pst.setInt(5, id);

                pst.executeUpdate();
                System.out.println("Car Added");

                confirmPanel.setVisible(false);
                backButton.setVisible(true);
                clearButton.setVisible(true);
                saveButton.setVisible(true);

                mainSearchCarModel.addRow(new CarSearch().getRow(carPlateOnConfirm.getText()));
                for (JTextField textField : textFields) {
                    textField.setEditable(true);
                    textField.setText("");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == cancelButtonOnConfirm) {
            for (JTextField textField: textFields) {
                textField.setEditable(true);
            }
            backButton.setVisible(true);
            clearButton.setVisible(true);
            saveButton.setVisible(true);
            confirmPanel.setVisible(false);
        }
    }
}
