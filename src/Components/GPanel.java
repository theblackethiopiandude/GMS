package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics) {
        float[] fractions = {0.0f, 0.35f, 1.0f};
        var colors  = new Color[]{new Color(0x262626), new Color(0xF21326), new Color(0x262626)};

        Point2D start = new Point2D.Float(0, getHeight() /2);
        Point2D end = new Point2D.Float(getWidth(), getHeight() /2);
        LinearGradientPaint gradient = new LinearGradientPaint(start, end, fractions, colors);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(gradient);

        graphics2D.fillRect(0, 0, getWidth(), getHeight());
//        super.paintComponent(graphics);
    }
}
