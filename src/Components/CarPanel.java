package Components;

import javax.swing.*;
import java.awt.*;

public class CarPanel extends JPanel {
    private final int width,height;
    private final ImageIcon carImage = new ImageIcon("assets/images/carPanel.png");

    public CarPanel(){
        this.width = 900;
        this.height = 252;
    }

   /* if Layout Manager used
   public CarPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }*/

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(carImage.getImage(),0,0, width, height,null);
        //        super.paintComponent(graphics);
    }
}