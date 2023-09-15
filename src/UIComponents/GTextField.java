package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GTextField extends JTextField {
    public GTextField() {
        this.setBackground(new Color(0xA6A3A1));
        this.setForeground(Color.WHITE);
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setFont(new Font("Arial", Font.PLAIN, 13));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (this.getText().equals("")){
            graphics2D.setColor(this.getForeground());
            graphics2D.drawString("Required Field", getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);
        }
        super.paintComponent(graphics);
        graphics2D.dispose();
    }
}
