package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        comp.setFont(new Font("Segoe UI", Font.PLAIN, 15));
//        table.getColumn(column).set
        if(isSelected){
            if (row % 2 == 0) comp.setBackground(new Color(0xA62631));
            else comp.setBackground(new Color(0xA62631));
        }
        else{
            if (row % 2 == 0) comp.setBackground(new Color(0x505050));
            else comp.setBackground(new Color(0x262626));
        }


        this.setBorder(new EmptyBorder(5, 30, 6, 10));
        this.setPreferredSize(new Dimension(0, 41));

        return comp;
    }
}
