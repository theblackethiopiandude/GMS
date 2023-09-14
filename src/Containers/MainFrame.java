package Containers;

import DatabaseComponents.CarSearch;
import UIComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends GPanel implements ActionListener, KeyListener, MouseListener {
    private final GButton updateCustomer, services, help;
    private final GSearchField searchField;
    private final JFrame callerFrame;

    private final GTabel searchTable;
    private final JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;
    private final JComponent [] componentsToDisable;
    public MainFrame(JFrame callerFrame){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1366, 768));
        this.callerFrame = callerFrame;
        searchField = new GSearchField();
        this.tableModel = new DefaultTableModel(new CarSearch().addToTable(), new Object[]{ "Customer Name", "Plate Number", "Phone Number", "Make", "Model" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        tableModel.addColumn("");
        searchTable = new GTabel(tableModel);
        searchTable.getColumnModel().moveColumn(5, 0);
        searchTable.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());
        searchTable.getColumnModel().getColumn(0).setMinWidth(0);
        searchTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        searchTable.setAutoCreateRowSorter(false);
        searchTable.addMouseListener(this);

        tableScrollPane = new JScrollPane(searchTable);
        tableScrollPane.setBounds(314, 142, 783, 557);
        tableScrollPane.setBackground(new Color(0x262626));
        searchTable.fix(tableScrollPane);

        ImagePanel imagePanel = new ImagePanel(new ImageIcon("assets/images/carPanel.png"), new Dimension(900, 252));
        JLabel startRegistration = new JLabel("Start Registration");
        GButton addCustomer = new GButton(10, new Color(0xA62631), new ImageIcon("assets/images/addPerson.png"), new Point(66,9));


        updateCustomer = new GButton("Update Customer\nProfile", new Point(13, 39), 10, new Color(0x262626), new ImageIcon("assets/images/updateCustomer.png"), new Point(181,41));
        services = new GButton("Services", new Point(70, 54), 10, new Color(0x262626), new ImageIcon("assets/images/services.png"), new Point(145,45));
        help =new GButton("Help", new Point(72, 52),10, new Color(0x262626), new ImageIcon("assets/images/help.png"), new Point(149,41));


        imagePanel.setBounds(233, 209, 900,252);

        searchField.setBounds(385, 72, 584,60);
        searchField.addKeyListener(this);
        startRegistration.setBounds(20, 18, 372, 65);
        addCustomer.setBounds(20, 100, 186,52);

        updateCustomer.setBounds(233,495,262,138);
        services.setBounds(551,495,262,138);
        services.addActionListener(this);
        help.setBounds(873, 495, 262,138);

        startRegistration.setFont(new Font("Alexandria", Font.PLAIN, 48));
        startRegistration.setForeground(Color.WHITE);

        this.setLayout(null);
        imagePanel.setLayout(null);

        imagePanel.add(startRegistration);
        imagePanel.add(addCustomer);

        this.add(searchField);
        this.add(tableScrollPane);
        this.add(imagePanel);

        this.add(updateCustomer);
        this.add(services);
        this.add(help);

        componentsToDisable = new JComponent[]{imagePanel, updateCustomer, services, help};
        tableScrollPane.setVisible(false);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == services){
            callerFrame.add(new ServicesFrame(this));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == searchField){
            if (!searchField.getText().equals("")){
                for (JComponent jComponent : componentsToDisable) jComponent.setVisible(false);
                tableScrollPane.setVisible(true);
                tableModel = (DefaultTableModel) searchTable.getModel();
                TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(tableModel);
                searchTable.setRowSorter(tableRowSorter);
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(getColumnIndexByName(searchTable, "Plate Number"),SortOrder.ASCENDING)));
                tableRowSorter.setRowFilter(RowFilter.regexFilter(searchField.getText(),getColumnIndexByName(searchTable, "Plate Number")));
            }
            else {
                for (JComponent jComponent : componentsToDisable) jComponent.setVisible(true);
                tableScrollPane.setVisible(false);
            }
        }
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
            callerFrame.add(new CarInfoFrame(callerFrame,  this, model.getValueAt(searchTable.getRowSorter().convertRowIndexToModel(searchTable.getSelectedRow()), getColumnIndexByName(searchTable, "Plate Number")).toString()));
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
}