package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServicePanel extends JPanel {
    private final ImageIcon image;
    private final String word;

    private final Color backgroundColor = new Color(0x262626);

    public ServicePanel(String word, ImageIcon image) {
        this.image = image;
        this.word = word;
        this.setBorder(new EmptyBorder(25, 81, 10, 10));
        this.setFont(new Font("Arial", Font.PLAIN, 19));
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(backgroundColor);
        graphics2D.fillRect(0,0, this.getWidth(), this.getHeight() - 71);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(word, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);

        graphics2D.drawImage(image.getImage(),0,71, null);
        //        super.paintComponent(graphics);
    }
}
