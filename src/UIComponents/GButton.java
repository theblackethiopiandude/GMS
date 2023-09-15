package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GButton extends JButton implements MouseListener{
    private final int arc;
    private  Color backgroudColor;
    final private Icon icon;
    final private Point imageStartLocation;
    private boolean hasText = false;
    private  String buttonText;
    public GButton(int arc, Color backgroudColor, ImageIcon icon, Point imageStartLocation){
        this.setOpaque(false);
        this.setFocusable(true);
        this.arc = arc;
        this.backgroudColor = backgroudColor;
        this.icon = icon;
        this.imageStartLocation = imageStartLocation;
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        this.addMouseListener(this);

    }
    public GButton(String buttonText, Point textStartLocation, int arc, Color backgroudColor, ImageIcon icon, Point imageStartLocation){
        this(arc, backgroudColor, icon, imageStartLocation);
        this.buttonText = buttonText;
        this.hasText = true;
        this.setBorder(new EmptyBorder(textStartLocation.y , textStartLocation.x,10,10));
    }
    public GButton(String buttonText, Point textStartLocation, int arc, Color backgroudColor, Font font){
        this.buttonText = buttonText;
        this.setOpaque(false);
        this.setFocusable(true);
        this.arc = arc;
        this.backgroudColor = backgroudColor;
        this.imageStartLocation = null;
        this.icon = null;
        this.hasText = true;
        this.setFont(font);
        this.setBorder(new EmptyBorder(textStartLocation.y , textStartLocation.x,10,10));
        this.addMouseListener(this);
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


        graphics2D.setPaint(backgroudColor);
        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), arc, arc);

        if((icon != null) && (imageStartLocation != null))
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        backgroudColor = backgroudColor.darker();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        backgroudColor = backgroudColor.brighter();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        backgroudColor = backgroudColor.brighter();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backgroudColor = backgroudColor.darker();
    }
}
