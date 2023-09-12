package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GButton extends JButton {
    private final int cornerRadius;
    private final Color backgroudColor;
    final private Icon icon;
    final private Point imageStartLocation;
    private boolean hasText = false;
    private  String buttonText;
    public GButton(int arc, Color backgroudColor, ImageIcon icon, Point imageStartLocation){
        this.setOpaque(false);
        this.setFocusable(false);
        this.cornerRadius = arc;
        this.backgroudColor = backgroudColor;
        this.icon = icon;
        this.imageStartLocation = imageStartLocation;
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    }
    public GButton(String buttonText, Point textStartLocation, int arc, Color backgroudColor, ImageIcon icon, Point imageStartLocation){
        this(arc, backgroudColor, icon, imageStartLocation);
        this.buttonText = buttonText;
        this.hasText = true;
        this.setBorder(new EmptyBorder(textStartLocation.y , textStartLocation.x,10,10));
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(backgroudColor);
        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), cornerRadius, cornerRadius);

        graphics2D.drawImage(((ImageIcon) icon).getImage(), imageStartLocation.x, imageStartLocation.y, null);

        if (hasText){
            graphics2D.setColor(Color.WHITE);
            String[] word = buttonText.split("\n");

            graphics2D.drawString(word[0], getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);

            if (word.length > 1) {
                for (int i = 1, marign = 27; i < word.length; i++, marign += 27)
                    graphics2D.drawString(word[i], getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top + marign);
            }
        }

//        super.paintComponent(graphics);

    }
    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to prevent painting the default border
    }
}
