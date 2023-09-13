package UIComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GTabel extends JTable {

    public GTabel(DefaultTableModel model){
        super(model);
//        this.setDefaultRenderer(Object.class, new RoundedTableHeaderRenderer());
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setShowGrid(false);
        this.setBackground(new Color(0x262626));
        this.setForeground(Color.WHITE);
        this.getTableHeader().setDefaultRenderer(new TableHeaderRenderer());
        this.getTableHeader().setPreferredSize(new Dimension(0, 40));
        this.setRowHeight(35);
        this.setDefaultRenderer(Object.class, new TableCellRenderer());
        this.setShowGrid(false);
        this.setShowVerticalLines(false);

    }
    public void fix(JScrollPane scrollPane){
        scrollPane.setVerticalScrollBar(new ScrollBarCustom(this.getBackground(), new Color(0xA62631)));
        JPanel panel = new JPanel();
        panel.setBackground(this.getBackground());
        scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scrollPane.getViewport().setBackground(this.getBackground());
    }
}

