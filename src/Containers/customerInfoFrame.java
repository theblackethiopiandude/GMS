package Containers;

import UIComponents.ColorPanel;
import UIComponents.GButton;
import UIComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DatabaseComponents.Connection.getConnection;

public class customerInfoFrame extends GPanel implements ActionListener{
    private final JFrame callerFrame;
    private final int id;
    private JLabel customerNameHolder, customerPhoneHolder, customerAddressHolder, carPlateHolder, carMakeHolder, carModelHolder, carVinHolder, notifyLabel;
    private final GButton updateButton, addButton, deleteButton, cancelCarInfo, backButton, addCarInfo, cancelConfirm, yesConfirm, ok;
    private final ColorPanel customerPanel, addCarPanel, confirmationPanel;

    public customerInfoFrame(JFrame callerFrame, JPanel callerPanel, int id) {
        this.setLayout(null);
        this.setBounds(0, 0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));
        this.callerFrame = callerFrame;
        callerPanel.setVisible(false);
        backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/undo.png"), new Point(47, 12));
        backButton.setBounds(181,21, 122, 49);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });
        this.id = id;

        customerNameHolder = new JLabel();
        customerPhoneHolder = new JLabel();
        customerAddressHolder = new JLabel();

        JLabel customerHolderLabels[] = {customerNameHolder, customerPhoneHolder, customerAddressHolder};

        for (JLabel label: customerHolderLabels) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
        }

        getUser();
        customerPanel = new ColorPanel(20, new ImageIcon("assets/images/personLarge.png"), new Point(244, 21), new ImageIcon("assets/images/line.png"), new Point(40, 177), new Point(40, 277), new Point(40, 377));
        customerPanel.setBounds(135, 160, 1096, 448);
        customerPanel.setPreferredSize(new Dimension(1096, 448));

        addCarPanel = new ColorPanel(20, new ImageIcon("assets/images/newCarBackground.png"), new Point(134, 59));
        addCarPanel.setBounds(236, 69, 895, 615);
        addCarPanel.setPreferredSize(new Dimension(895, 615));
        addCarPanel.setVisible(false);

        confirmationPanel = new ColorPanel(20, new ImageIcon("assets/images/saveCar.png"), new Point(367, 52));
        confirmationPanel.setBounds(254, 73, 859, 662);
        confirmationPanel.setPreferredSize(new Dimension(859, 662));
        confirmationPanel.setVisible(false);
        notifyLabel = new JLabel("This Car Will Be Added");
        notifyLabel.setForeground(Color.WHITE);
        notifyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        notifyLabel.setBounds(297, 191, 300, 27);

        ColorPanel summaryPanel = new ColorPanel(20);
        summaryPanel.setColor(new Color(0xA6A3A1));
        summaryPanel.setBounds(115, 236, 629, 279);
        summaryPanel.setPreferredSize(new Dimension(629, 279));

        JLabel carPlate = new JLabel("Plate No:");
        JLabel carMake = new JLabel("Make:");
        JLabel carModel = new JLabel("Model:");
        JLabel carVin = new JLabel("VIN:");
        JLabel summary = new JLabel("Summary");
        summary.setForeground(Color.WHITE);
        summary.setFont(new Font("Arial", Font.BOLD, 18));
        summary.setBounds(266, 23, 100, 20);
        carMake.setBounds(67,56, 60, 24);
        carPlate.setBounds(67,104, 80, 24);
        carModel.setBounds(67,152, 65, 24);
        carVin.setBounds(67,200, 40, 24);

        carPlateHolder = new JLabel();
        carMakeHolder = new JLabel();
        carModelHolder = new JLabel();
        carVinHolder = new JLabel();

        carMake.setBounds(154,56, 100, 24);
        carPlate.setBounds(154,104, 100, 24);
        carModel.setBounds(154,152, 100, 24);
        carVin.setBounds(154,200, 100, 24);

        JLabel carHolderLabels[] = {carPlateHolder, carMakeHolder, carModelHolder, carVin};
        JLabel viewLabels[] = {carPlate, carMake, carModel, carVin};
        for (JLabel label: carHolderLabels) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            summaryPanel.add(label);
        }
        for (JLabel label: viewLabels) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            summaryPanel.add(label);
        }


        updateButton = new GButton("Update Customer Info", new Point(44,33),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 20));
        addButton = new GButton("Add Car", new Point(110,33),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 20));
        deleteButton = new GButton("Delete Car", new Point(99, 33),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 20));
        cancelCarInfo = new GButton("Cancel", new Point(99, 30),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 18));
        addCarInfo = new GButton("Add Car", new Point(99,30),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 18));
        cancelConfirm = new GButton("Cancel", new Point(99, 30),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 18));
        yesConfirm =  new GButton("Yes", new Point(99, 30),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 18));
        ok = new GButton("Ok", new Point(99, 30),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 18));
        ok.setVisible(false);

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        cancelCarInfo.addActionListener(this);
        addCarInfo.addActionListener(this);
        cancelConfirm.addActionListener(this);
        yesConfirm.addActionListener(this);
        ok.addActionListener(this);

        updateButton.setBounds(674, 56, 297, 88);
        addButton.setBounds(674, 180, 297, 88);
        deleteButton.setBounds(674, 304, 297, 88);
        cancelCarInfo.setBounds(134,511,268, 80);
        addCarInfo.setBounds(493, 511, 268, 80);
        cancelConfirm.setBounds(214, 544, 199, 58);
        yesConfirm.setBounds(447,544,199,58);
        ok.setBounds(330, 533,199, 58);

        customerNameHolder.setBounds(40, 144, 350, 20);
        customerPhoneHolder.setBounds(40, 244, 350, 20);
        customerAddressHolder.setBounds(40, 344, 350, 20 );


        customerPanel.add(customerNameHolder);
        customerPanel.add(customerPhoneHolder);
        customerPanel.add(customerAddressHolder);
        customerPanel.add(updateButton);
        customerPanel.add(addButton);
        customerPanel.add(deleteButton);

        addCarPanel.add(cancelCarInfo);
        addCarPanel.add(addCarInfo);

        confirmationPanel.add(summaryPanel);
        confirmationPanel.add(yesConfirm);
        confirmationPanel.add(cancelConfirm);
        confirmationPanel.add(notifyLabel);


        this.add(backButton);
        this.add(confirmationPanel);
        this.add(addCarPanel);
        this.add(customerPanel);
        this.setVisible(true);
    }
    public void getUser(){
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM Customers WHERE ID = ?")){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                customerNameHolder.setText(rs.getString("Name"));
                customerPhoneHolder.setText(rs.getString("PhoneNumber"));
                customerAddressHolder.setText(rs.getString("Address"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton){
            this.setVisible(false);
            callerFrame.add(new DeleteCarFrame(this, id));
        }
        if (e.getSource() == addButton){
            customerPanel.setVisible(false);
            backButton.setVisible(false);
            addCarPanel.setVisible(true);
        }
        else if (e.getSource() == cancelCarInfo){
            addCarPanel.setVisible(false);
            customerPanel.setVisible(true);
            backButton.setVisible(true);
        } else if (e.getSource() == addCarInfo) {
            customerPanel.setVisible(false);
            backButton.setVisible(false);
            addCarPanel.setVisible(false);
            confirmationPanel.setVisible(true);
        } else if (e.getSource() == cancelConfirm) {
            customerPanel.setVisible(true);
            backButton.setVisible(true);
            addCarPanel.setVisible(true);
            confirmationPanel.setVisible(false);
        }else if (e.getSource() == yesConfirm){
            cancelConfirm.setVisible(false);
            yesConfirm.setVisible(false);
            notifyLabel.setText("Added Succesfully!");
            confirmationPanel.setImage(new ImageIcon("assets/images/done.png"),new Point(365, 45));
            ok.setVisible(true);
        }else if (e.getSource() == ok){
            confirmationPanel.setVisible(false);
        }
    }
}
