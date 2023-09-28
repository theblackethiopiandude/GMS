package Containers;

import DatabaseComponents.Car;
import DatabaseComponents.Customer;
import UIComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DatabaseComponents.Connection.getConnection;

public class DeleteCarFrame extends GPanel implements MouseListener, ActionListener {
    private final GTabel searchTable;
    private final JScrollPane tableScrollPane;
    private final GButton cancelConfirm, yesConfirm , ok;
    private final ImagePanel deleteWarning, deleteDone;
    private DefaultTableModel tableModel;
    private String plateNumber;
    public DeleteCarFrame( JPanel callerPanel, int customerID){
        this.setLayout(null);
        this.setBounds(0, 0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));
        callerPanel.setVisible(false);
        this.tableModel = new DefaultTableModel(new Car().addToTable(customerID), new Object[]{"Plate Number", "Make", "Model", "VIN"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        GButton backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/undo.png"), new Point(47, 12));
        backButton.setBounds(181,21, 122, 49);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });

        searchTable = new GTabel(tableModel);
        searchTable.setAutoCreateRowSorter(false);
        searchTable.addMouseListener(this);

        tableScrollPane = new JScrollPane(searchTable);
        tableScrollPane.setBounds(314, 142, 783, 557);
        tableScrollPane.setBackground(new Color(0x262626));
        searchTable.fix(tableScrollPane);

        cancelConfirm = new GButton("Cancel", new Point(55, 15),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 15));
        yesConfirm =  new GButton("Yes", new Point(67, 15),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 15));
        ok = new GButton("OK", new Point(68, 15),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 15));

        deleteWarning = new ImagePanel(new ImageIcon("assets/images/deleteWarning.png"), new Dimension(575, 235));
        deleteWarning.setBounds(450, 219, 575, 235);
        deleteWarning.setVisible(false);

        deleteDone =  new ImagePanel(new ImageIcon("assets/images/deleted.png"), new Dimension(575, 235));
        deleteDone.setBounds(450, 219, 575, 235);
        deleteDone.setVisible(false);
        deleteDone.add(ok);

        cancelConfirm.addActionListener(this);
        yesConfirm.addActionListener(this);
        ok.addActionListener(this);

        cancelConfirm.setBounds(126, 165, 159, 46);
        yesConfirm.setBounds(291,165,159,46);
        ok.setBounds(208, 154, 159, 47);

        deleteWarning.add(cancelConfirm);
        deleteWarning.add(yesConfirm);

        this.add(deleteDone);
        this.add(deleteWarning);
        this.add(backButton);
        this.add(tableScrollPane);
        this.setVisible(true);
    }
    private int getColumnIndexByName(JTable table, String columnName) {
        for(int i =0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                System.out.println("Plate Column index: " + i);
                return i-1;
            }
        }
        return -1;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == searchTable){
            DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
            plateNumber = model.getValueAt(searchTable.getSelectedRow(), 0).toString();
            deleteWarning.setVisible(true);


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yesConfirm){
            try (PreparedStatement pst = getConnection().prepareStatement("DELETE FROM Cars WHERE PlateNo = ?")){
                pst.setString(1, plateNumber);
                pst.executeUpdate();
                deleteWarning.setVisible(false);
                deleteDone.setVisible(true);
            } catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
            DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
            model.removeRow(searchTable.getSelectedRow());
            searchTable.setModel(model);
            deleteWarning.setVisible(false);
            deleteDone.setVisible(true);
        }else if (e.getSource() == cancelConfirm) {
            deleteWarning.setVisible(false);
        }
        else if (e.getSource() == ok) {
            deleteDone.setVisible(false);
        }
    }
}
