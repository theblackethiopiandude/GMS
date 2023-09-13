package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableHeaderRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        comp.setBackground(new Color(0x262626));
        comp.setForeground(Color.WHITE);
        comp.setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.setBorder(new EmptyBorder(0, 15, 0, 5));
        return comp;
    }

}
