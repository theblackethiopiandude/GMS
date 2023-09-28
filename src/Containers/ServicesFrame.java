package Containers;

import DatabaseComponents.Service;
import UIComponents.GButton;
import UIComponents.GPanel;
import UIComponents.ImagePanel;
import UIComponents.ServicePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicesFrame extends GPanel implements ActionListener {
    private final JPanel callerPanel;
    private final ImagePanel addServicePanel;
    private final JTextField serviceField;

    GButton backButton, addButton, saveButton, cancelButton;
    public ServicesFrame(JPanel callerPanel){
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));

        this.callerPanel = callerPanel;
        this.callerPanel.setVisible(false);

        this.serviceField = new JTextField();
        serviceField.setBackground(new Color(0x262626));
        serviceField.setForeground(Color.WHITE);
        serviceField.setCaretColor(Color.WHITE);
        serviceField.setBorder(new EmptyBorder(0,0,0,0));
        serviceField.setFont(new Font("Arial", Font.PLAIN, 18));
        serviceField.setBounds(30, 115, 315, 20);

        this.addServicePanel = new ImagePanel(new ImageIcon("assets/images/addService.png"), new Dimension(377, 228));
        this.addServicePanel.setBounds(504, 240, 377, 228);
        this.addServicePanel.setVisible(false);

        cancelButton = new GButton("Cancel", new Point(31, 11),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 12));
        saveButton =  new GButton("Save", new Point(37, 11),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 12));

        cancelButton.setBounds(69, 179, 102, 35);
        saveButton.setBounds(206, 179, 102, 35);

        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);

        this.backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));
        this.addButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/addIcon.png"), new Point(27, 14));

        ServicePanel itemPanel = new ServicePanel("Oil Change",new ImageIcon("assets/images/oil.png"));

        backButton.setBounds(271,26, 71, 43);
        addButton.setBounds(970, 26, 71, 43);

        itemPanel.setBounds(134, 97, 262, 299);


        backButton.addActionListener(this);
        addButton.addActionListener(this);

        addServicePanel.add(serviceField);
        addServicePanel.add(cancelButton);
        addServicePanel.add(saveButton);

        this.add(addServicePanel);
        this.add(backButton);
        this.add(addButton);
        this.add(itemPanel);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == backButton){
            this.setVisible(false);
            callerPanel.setVisible(true);
        } else if (event.getSource() == addButton) {
            addServicePanel.setVisible(true);
        } else if (event.getSource() == cancelButton) {
            serviceField.setText("");
            addServicePanel.setVisible(false);
        } else if (event.getSource() == saveButton) {
            if (serviceField.getText().equals("")){
                Timer timer = new Timer(800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        serviceField.setText(""); // Clear the text
                    }
                });

                serviceField.setText("Service Not Provided");

                timer.setRepeats(false);
                timer.start();
            }else {
                new Service().insertIntoDatabase(serviceField.getText());
                Timer timer = new Timer(800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        serviceField.setText(""); // Clear the text
                        addServicePanel.setVisible(false);
                    }
                });

                serviceField.setText("Added Successfully");


                timer.setRepeats(false);
                timer.start();
            }
        }
    }
}
