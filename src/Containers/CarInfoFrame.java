package Containers;

import UIComponents.GButton;
import UIComponents.GPanel;
import UIComponents.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DatabaseComponents.Connection.getConnection;

public class CarInfoFrame extends GPanel implements ActionListener{
    private final GButton historyButton;
    private final JFrame callerFrame;
    public CarInfoFrame(JFrame callerFrame, JPanel callerPanel, String plateNumber) {
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));
        this.callerFrame = callerFrame;

        GButton backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));
        historyButton = new GButton("History", new Point(33, 12), 37, new Color(0x262626),new Font("Segoe UI", Font.PLAIN, 15));

        JLabel customerInfo = new JLabel("Customer Information");
        JLabel carInfo = new JLabel("Car Information");
        customerInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        customerInfo.setForeground(Color.WHITE);
        carInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        carInfo.setForeground(Color.WHITE);

        ImagePanel customerInfoPanel = new ImagePanel(new ImageIcon("assets/images/customerInfoPanel.png"), new Dimension(623,431));
        ImagePanel carInfoPanel = new ImagePanel(new ImageIcon("assets/images/carInfoPanel.png"), new Dimension(623,431));
        customerInfoPanel.setLayout(null);
        carInfoPanel.setLayout(null);

        JLabel customerName = new JLabel("Customer Name");
        JLabel customerPhone = new JLabel("Customer Phone NO.");
        JLabel customerAddress = new JLabel("Full Address");

        JLabel carPlate = new JLabel("Plate No");
        JLabel carMake = new JLabel("Make");
        JLabel carModel = new JLabel("Model");
        JLabel carVin = new JLabel("VIN");

        JLabel customerNameHolder = new JLabel();
        JLabel customerPhoneHolder = new JLabel();
        JLabel customerAddressHolder = new JLabel();

        JLabel carPlateHolder = new JLabel();
        JLabel carMakeHolder = new JLabel();
        JLabel carModelHolder = new JLabel();
        JLabel carVinHolder = new JLabel();
        JLabel[] holderLabels = new JLabel[]{customerNameHolder, customerPhoneHolder, customerAddressHolder, carPlateHolder, carMakeHolder, carModelHolder, carVinHolder};

        JLabel []nameLabels = {customerName, customerPhone, customerAddress, carPlate, carMake, carModel, carVin};
        for (JLabel label:nameLabels){
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
            label.setForeground(Color.WHITE);
        }

        for (JLabel label:holderLabels){
            label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            label.setForeground(Color.WHITE);//Change this font later
        }
        // Populate data into Labels
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM CarInfo WHERE PlateNo = ?")){
            pst.setString(1, plateNumber);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                customerNameHolder.setText(rs.getString("Name"));
                customerPhoneHolder.setText(rs.getString("PhoneNumber"));
                customerAddressHolder.setText(rs.getString("Address"));
                carPlateHolder.setText(rs.getString("PlateNo"));
                carMakeHolder.setText(rs.getString("Make"));
                carModelHolder.setText(rs.getString("Model"));
                carVinHolder.setText(rs.getString("VIN"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


        customerName.setBounds(35, 32, 99,17);
        customerPhone.setBounds(35, 133, 128, 17);
        customerAddress.setBounds(35, 234, 74, 17);
        carPlate.setBounds(38, 35, 53,17);
        carMake.setBounds(38, 136, 34 ,17);
        carModel.setBounds(38, 237, 39,17);
        carVin.setBounds(38, 338, 23 ,17);

        customerNameHolder.setBounds(35, 65, 168,22);
        customerPhoneHolder.setBounds(35, 163, 168,22);
        customerAddressHolder.setBounds(35, 267, 168,22);
        carPlateHolder.setBounds(38, 68, 168,22);
        carMakeHolder.setBounds(38, 169, 168,22);
        carModelHolder.setBounds(38, 267, 168,22);
        carVinHolder.setBounds(38, 371, 168,22);

        backButton.setBounds(271,26, 71, 43);
        historyButton.setBounds(970, 26, 113, 43);
        customerInfo.setBounds(87, 151, 170, 20);
        carInfo.setBounds(700, 151, 130,20);

        customerInfoPanel.setBounds(52,184, 623,431);
        carInfoPanel.setBounds(684,184, 623,431);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });
        historyButton.addActionListener(this);

        customerInfoPanel.add(customerName);
        customerInfoPanel.add(customerPhone);
        customerInfoPanel.add(customerAddress);

        customerInfoPanel.add(customerNameHolder);
        customerInfoPanel.add(customerPhoneHolder);
        customerInfoPanel.add(customerAddressHolder);

        carInfoPanel.add(carPlate);
        carInfoPanel.add(carMake);
        carInfoPanel.add(carModel);
        carInfoPanel.add(carVin);

        carInfoPanel.add(carPlateHolder);
        carInfoPanel.add(carMakeHolder);
        carInfoPanel.add(carModelHolder);
        carInfoPanel.add(carVinHolder);

        this.add(backButton);
        this.add(historyButton);
        this.add(customerInfo);
        this.add(carInfo);
        this.add(customerInfoPanel);
        this.add(carInfoPanel);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == historyButton){
            this.setVisible(false);
            callerFrame.add(new HistoryFrame(this));
        }
    }
}
