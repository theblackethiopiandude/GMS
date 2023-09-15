package UIComponents;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        this.setPreferredSize(new Dimension(1366, 768));
        this.setLayout(null);
    }
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        ImageIcon image = new ImageIcon("assets/images/loginBackground.png");
        graphics2D.drawImage(image.getImage(),0,0, 1366, 768,null);
        //        super.paintComponent(graphics);
    }
}
