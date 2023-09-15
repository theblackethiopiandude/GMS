package Containers;

import DatabaseComponents.Customer;
import UIComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static DatabaseComponents.Connection.getConnection;

public class UpdateCustomerFrame extends GPanel implements MouseListener, KeyListener {
    private final JFrame callerFrame;
    private final GSearchField searchField;

    private final GTabel searchTable;
    private final JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    public UpdateCustomerFrame(JFrame callerFrame, JPanel callerPanel) {
        this.setLayout(null);
        this.setBounds(0, 0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));
        this.callerFrame = callerFrame;
        callerPanel.setVisible(false);

        searchField = new GSearchField(new ImageIcon("assets/images/personSearch.png"), new Point(41, 12), new Dimension(44, 41), "Search Name");
        this.tableModel = new DefaultTableModel(new Customer().addToTable(), new Object[]{ "Customer Name", "Phone Number", "Address"}) {
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

        tableModel.addColumn("");
        searchTable = new GTabel(tableModel);
        searchTable.getColumnModel().moveColumn(3, 0);
        searchTable.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());
        searchTable.getColumnModel().getColumn(0).setMinWidth(0);
        searchTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        searchTable.setAutoCreateRowSorter(false);
        searchTable.addMouseListener(this);

        tableScrollPane = new JScrollPane(searchTable);
        tableScrollPane.setBounds(314, 142, 783, 557);
        tableScrollPane.setBackground(new Color(0x262626));
        searchTable.fix(tableScrollPane);

        searchField.setBounds(385, 72, 584,60); // make this 500 x 45
        searchField.addKeyListener(this);

        this.add(backButton);
        this.add(searchField);
        this.add(tableScrollPane);
        this.setVisible(true);
    }
    private int getColumnIndexByName(JTable table, String columnName) {
        for(int i =0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                System.out.println("Column index: " + i);
                return i-1;
            }
        }
        return -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == searchTable){
            this.setVisible(false);
            DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
            System.out.println(model.getValueAt(searchTable.getSelectedRow(),  getColumnIndexByName(searchTable, "Address")));
            String name, phoneNumber, address;
            if (!searchField.getText().equals("")){
                name = model.getValueAt(searchTable.getRowSorter().convertRowIndexToModel(searchTable.getSelectedRow()), getColumnIndexByName(searchTable, "Customer Name")).toString();
                phoneNumber = model.getValueAt(searchTable.getRowSorter().convertRowIndexToModel(searchTable.getSelectedRow()), getColumnIndexByName(searchTable, "Phone Number")).toString();
                address = model.getValueAt(searchTable.getRowSorter().convertRowIndexToModel(searchTable.getSelectedRow()), getColumnIndexByName(searchTable, "Address")).toString();
            }else{
                name = model.getValueAt(searchTable.getSelectedRow(), getColumnIndexByName(searchTable, "Customer Name")).toString();
                phoneNumber = model.getValueAt(searchTable.getSelectedRow(), getColumnIndexByName(searchTable, "Phone Number")).toString();
                address = model.getValueAt(searchTable.getSelectedRow(), getColumnIndexByName(searchTable, "Address")).toString();
            }
            int ID = -1;
            try (PreparedStatement pst = getConnection().prepareStatement("SELECT ID FROM Customers WHERE Name = ? AND PhoneNumber = ? AND Address = ?")){
                pst.setString(1, name);
                pst.setString(2, phoneNumber);
                pst.setString(3, address);
                ResultSet rs = pst.executeQuery();
                while(rs.next()) {
                    ID = rs.getInt("ID");
                }
            } catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
            callerFrame.add(new customerInfoFrame(callerFrame,  this, ID));
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == searchField) {
            if (!searchField.getText().equals("")) {
                /*this.tableModel = new DefaultTableModel(new Customer().addToTable(), new Object[]{ "Customer Name", "Phone Number", "Address"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Make all cells uneditable
                    }
                };*/
                tableScrollPane.setVisible(true);
                tableModel = (DefaultTableModel) searchTable.getModel();
                TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(tableModel);
                searchTable.setRowSorter(tableRowSorter);
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(getColumnIndexByName(searchTable, "Customer Name"), SortOrder.ASCENDING)));
                tableRowSorter.setRowFilter(RowFilter.regexFilter(searchField.getText(), getColumnIndexByName(searchTable, "Customer Name")));
            }
        }
    }
}
