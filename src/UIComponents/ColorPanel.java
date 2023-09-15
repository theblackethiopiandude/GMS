package UIComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class ColorPanel extends JPanel {
    private Color color = new Color(0x262626);
    private ImageIcon image, line;
    private boolean hasImage = false, hasLine;
    private Point imageStartLocation, l1, l2, l3;
    private int arc;

    public ColorPanel(int arc, ImageIcon image, Point imageStartLocation, ImageIcon line, Point l1, Point l2, Point l3){
        this(arc, image, imageStartLocation);
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.line = line;
        this.hasLine = true;
    }
    public ColorPanel(int arc, ImageIcon image, Point imageStartLocation){
        this(arc);
        this.image = image;
        this.imageStartLocation = imageStartLocation;
        this.hasImage = true;
    }
    public ColorPanel(int arc){
        this.arc = arc;
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setImage(ImageIcon image, Point imageStartLocation) {
        this.image = image;
        this.imageStartLocation = imageStartLocation;
        this.hasImage = true;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setPaint(color);
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        if (hasImage)
            graphics2D.drawImage(image.getImage(), imageStartLocation.x, imageStartLocation.y,null);

        if (hasLine){
            graphics2D.drawImage(line.getImage(), l1.x, l1.y,null);
            graphics2D.drawImage(line.getImage(), l2.x, l2.y,null);
            graphics2D.drawImage(line.getImage(), l3.x, l3.y,null);
        }
//        super.paintComponent(graphics);
    }
}
