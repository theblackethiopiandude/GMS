package UIComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableImageRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(table.isCellSelected(row, column)){
            if (row % 2 == 0) comp.setBackground(new Color(0xA62631));
            else comp.setBackground(new Color(0xA62631));
        }
        else{
            if (row % 2 == 0) comp.setBackground(new Color(0x505050));
            else comp.setBackground(new Color(0x262626));
        }
        if (column == 0) {
            // Set the constant image in the first column
            ImageIcon imageIcon = new ImageIcon("assets/images/person.png");
            setIcon(imageIcon);
            setHorizontalAlignment(JLabel.CENTER);
            setHorizontalAlignment(JLabel.CENTER);
//            setText(""); // No text in this column
        } else {
            // For other columns, use default rendering
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
//        this.setBorder(new EmptyBorder(10, 10,10,0));
        return comp;
    }
}
