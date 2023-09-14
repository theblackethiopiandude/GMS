package UIComponents;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private final int width,height;
    private final ImageIcon image;

    public ImagePanel(ImageIcon image, Dimension size){
        this.width = size.width;
        this.height = size.height;
        this.image = image;
    }


    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(image.getImage(),0,0, width, height,null);
        //        super.paintComponent(graphics);
    }
}