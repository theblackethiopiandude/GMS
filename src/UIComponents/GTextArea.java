package UIComponents;

import javax.swing.*;
import java.awt.*;

public class GTextArea extends JTextArea{
//    final private int arc;
//    public GTextArea(Dimension size, int arc){
//        this.setPreferredSize(size);
//        this.arc = arc;
//    }
//    @Override
//    public void paintComponent(Graphics graphics){
//        super.paintComponent(graphics);
//        Graphics2D graphics2D = (Graphics2D) graphics;
//        graphics2D.setPaint(this.getBackground());
//        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), arc, arc);
//    }
    public GTextArea(int row, int column){
        super(row, column);
    }
}
