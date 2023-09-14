package Containers;

import UIComponents.GButton;
import UIComponents.GPanel;
import UIComponents.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        ImagePanel customerInfoPanel = new ImagePanel(new ImageIcon("assets/images/infoPanel.png"), new Dimension(623,431));
        ImagePanel carInfoPanel = new ImagePanel(new ImageIcon("assets/images/infoPanel.png"), new Dimension(623,431));
        customerInfoPanel.setLayout(null);
        carInfoPanel.setLayout(null);

        JLabel customerName = new JLabel("Customer Name");
        JLabel customerPhone = new JLabel("Customer Phone NO.");
        JLabel customerAddress = new JLabel("Full Address");

        JLabel carPlate = new JLabel("Plate No");
        JLabel carMake = new JLabel("Make");
        JLabel carModel = new JLabel("Model");
        JLabel carVin = new JLabel("VIN");

        JLabel []PanelLabels = {customerName, customerPhone, customerAddress, carPlate, carMake, carModel, carVin};
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

        carInfoPanel.add(carPlate);
        carInfoPanel.add(carMake);
        carInfoPanel.add(carModel);
        carInfoPanel.add(carVin);

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
