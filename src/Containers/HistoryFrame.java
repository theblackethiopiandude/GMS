package Containers;

import DatabaseComponents.History;
import DatabaseComponents.Service;
import UIComponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HistoryFrame extends GPanel implements ActionListener, MouseListener {
    private GButton addButton, saveButton, cancelButton;
    private final GTabel historyTable;
    private DefaultTableModel tableModel;
    private final JScrollPane tableScrollPane;
    private final GTextArea notesField, historyNotes;
    private JLabel service;
    private final ImagePanel  addHistory, viewHistory;
    private final String plateNumber;
    private JComboBox comboBox;
    public HistoryFrame(String plateNumber, JPanel callerPanel){
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));

        this.plateNumber = plateNumber;

        GButton backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));
        addButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/addIcon.png"), new Point(27, 14));

        backButton.setBounds(271,26, 71, 43);
        addButton.setBounds(970, 26, 71, 43);

        addButton.addActionListener(this);
        this.tableModel = new DefaultTableModel(new History().addToTable(plateNumber), new Object[]{"   Service", "Notes"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        historyTable = new GTabel(tableModel);
        historyTable.setAutoCreateRowSorter(false);
        historyTable.addMouseListener(this);

        tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.setBounds(155, 154, 704, 499);
        tableScrollPane.setBackground(new Color(0x262626));
        historyTable.fix(tableScrollPane);

        addHistory = new ImagePanel(new ImageIcon("assets/images/historyEntry.png"), new Dimension(377, 519));
        addHistory.setBounds(916, 152, 377, 519);
        addHistory.setVisible(false);

        viewHistory = new ImagePanel(new ImageIcon("assets/images/historyEntry.png"), new Dimension(377, 519));
        viewHistory.setBounds(916, 152, 377, 519);
        viewHistory.setVisible(false);

        JLabel notesLabel = new JLabel("Notes");
        notesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        notesLabel.setForeground(Color.WHITE);
        notesLabel.setBounds(30, 176, 70, 20);

        JLabel notesLabel1 = new JLabel("Notes");
        notesLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        notesLabel1.setForeground(Color.WHITE);
        notesLabel1.setBounds(30, 176, 70, 20);

        JLabel serviceLabel = new JLabel("Service");
        serviceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        serviceLabel.setForeground(Color.WHITE);
        serviceLabel.setBounds(30, 95, 70, 20);

        service = new JLabel();
        service.setFont(new Font("Arial", Font.PLAIN, 18));
        service.setForeground(Color.WHITE);
        service.setBounds(30, 122, 320, 20);

        JLabel service1 = new JLabel("Services");
        service1.setFont(new Font("Arial", Font.PLAIN, 18));
        service1.setForeground(Color.WHITE);
        service1.setBounds(30, 63, 320, 20);

        notesField = new GTextArea(10, 29);
        notesField.setFont(new Font("Arial", Font.PLAIN, 18));
        notesField.setForeground(new Color(0x262626));
        notesField.setCaretColor(Color.BLACK);
        notesField.setBackground(Color.WHITE);
        notesField.setBorder(new EmptyBorder(0,4,0,0));
        notesField.setBounds(30, 203, 315, 228);

        historyNotes = new GTextArea(10, 29);
        historyNotes.setFont(new Font("Arial", Font.PLAIN, 18));
        historyNotes.setForeground(new Color(0x262626));
        historyNotes.setCaretColor(Color.BLACK);
        historyNotes.setBackground(Color.WHITE);
        historyNotes.setBorder(new EmptyBorder(0,10,0,0));
        historyNotes.setEditable(false);
        historyNotes.setBounds(30, 203, 315, 228);



        cancelButton = new GButton("Cancel", new Point(35, 12),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 10));
        saveButton =  new GButton("Save", new Point(40, 12),20, new Color(0xA62631),new Font("Arial", Font.BOLD, 10));

        cancelButton.setBounds(69, 470, 102, 35);
        saveButton.setBounds(206, 470, 102, 35);

        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);

        comboBox = new JComboBox<>(new Service().getServices());
//        comboBox.setPreferredSize(new Dimension(100, 50));
        comboBox.setBackground(new Color(0x262626));
        comboBox.setBorder(new EmptyBorder(0,0,0,0));
        comboBox.setForeground(Color.WHITE);
        comboBox.setMaximumRowCount(4);
        comboBox.setBounds(25, 90, 330, 31);

        addHistory.add(service1);
        addHistory.add(comboBox);
        addHistory.add(notesLabel);
        addHistory.add(notesField);
        addHistory.add(cancelButton);
        addHistory.add(saveButton);

        viewHistory.add(serviceLabel);
        viewHistory.add(service);
        viewHistory.add(notesLabel1);
        viewHistory.add(historyNotes);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });

        this.add(backButton);
        this.add(addButton);
        this.add(tableScrollPane);
        this.add(addHistory);
        this.add(viewHistory);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            viewHistory.setVisible(false);
            addHistory.setVisible(true);
        } else if (e.getSource() == cancelButton) {
            notesField.setText("");
            comboBox.setSelectedIndex(0);
            addHistory.setVisible(false);
        } else if (e.getSource() == saveButton) {
            new History().insertIntoDatabase(plateNumber, new Service().getServiceID(comboBox.getSelectedItem().toString()), notesField.getText());
            tableModel.addRow(new History().getLastRow());
            notesField.setText("");
            comboBox.setSelectedIndex(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == historyTable){
            DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
            service.setText(model.getValueAt(historyTable.getSelectedRow(), 0).toString());
            historyNotes.setText(model.getValueAt(historyTable.getSelectedRow(), 1).toString());
            addHistory.setVisible(false);
            viewHistory.setVisible(true);
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
