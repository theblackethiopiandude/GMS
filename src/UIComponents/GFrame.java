package UIComponents;

import Containers.MainFrame;

import javax.swing.*;

public class GFrame extends JFrame {
    public GFrame(){
        this.setSize(1366, 768);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GMS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("assets/images/car-service.png").getImage());
        MainFrame mainFrame= new MainFrame(this);

        mainFrame.setBounds(0,0, 1366, 768);

        this.add(mainFrame);
        this.setVisible(true);

    }
}
