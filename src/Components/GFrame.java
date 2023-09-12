package Components;

import javax.swing.*;
import java.awt.*;
public class GFrame extends JFrame {
    public GFrame(){
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sept 2023 Final Exam");
        this.setResizable(false);
        GPanel main = new GPanel();
        GTextField text = new GTextField();

        CarPanel carPanel = new CarPanel();
        JLabel startRegistration = new JLabel("Start Registration");
        GButton addCustomer = new GButton(10, new Color(0xA62631), new ImageIcon("assets/images/addPerson.png"), new Point(66,9));

        GButton updateCustomer = new GButton("Update Customer\nProfile", new Point(13, 39), 10, new Color(0x262626), new ImageIcon("assets/images/updateCustomer.png"), new Point(181,41));
        GButton services = new GButton("Services", new Point(70, 54), 10, new Color(0x262626), new ImageIcon("assets/images/services.png"), new Point(145,45));
        GButton help = new GButton("Help", new Point(72, 52),10, new Color(0x262626), new ImageIcon("assets/images/help.png"), new Point(149,41));

        carPanel.setBounds(233, 209, 900,252);

        text.setBounds(385, 72, 584,60);

        startRegistration.setBounds(20, 18, 372, 65);
        addCustomer.setBounds(20, 100, 186,52);

        updateCustomer.setBounds(233,495,262,138);
        services.setBounds(551,495,262,138);
        help.setBounds(873, 495, 262,138);

        startRegistration.setFont(new Font("Alexandria", Font.PLAIN, 48));
        startRegistration.setForeground(Color.WHITE);

        main.setLayout(null);
        carPanel.setLayout(null);

        carPanel.add(startRegistration);
        carPanel.add(addCustomer);

        main.add(text);
        main.add(carPanel);

        main.add(updateCustomer);
        main.add(services);
        main.add(help);

        this.add(main);
        this.setVisible(true);
    }
}