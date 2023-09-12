package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends GPanel implements ActionListener {
    private final GButton updateCustomer, services, help;
    private final JFrame callerFrame;
    public MainFrame(JFrame callerFrame){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1366, 768));
        this.callerFrame = callerFrame;
        GTextField text = new GTextField();

        CarPanel carPanel = new CarPanel();
        JLabel startRegistration = new JLabel("Start Registration");
        GButton addCustomer = new GButton(10, new Color(0xA62631), new ImageIcon("assets/images/addPerson.png"), new Point(66,9));

        updateCustomer = new GButton("Update Customer\nProfile", new Point(13, 39), 10, new Color(0x262626), new ImageIcon("assets/images/updateCustomer.png"), new Point(181,41));
        services = new GButton("Services", new Point(70, 54), 10, new Color(0x262626), new ImageIcon("assets/images/services.png"), new Point(145,45));
        help =new GButton("Help", new Point(72, 52),10, new Color(0x262626), new ImageIcon("assets/images/help.png"), new Point(149,41));

        services.addActionListener(this);

        carPanel.setBounds(233, 209, 900,252);

        text.setBounds(385, 72, 584,60);

        startRegistration.setBounds(20, 18, 372, 65);
        addCustomer.setBounds(20, 100, 186,52);

        updateCustomer.setBounds(233,495,262,138);
        services.setBounds(551,495,262,138);
        help.setBounds(873, 495, 262,138);

        startRegistration.setFont(new Font("Alexandria", Font.PLAIN, 48));
        startRegistration.setForeground(Color.WHITE);

        this.setLayout(null);
        carPanel.setLayout(null);

        carPanel.add(startRegistration);
        carPanel.add(addCustomer);

        this.add(text);
        this.add(carPanel);

        this.add(updateCustomer);
        this.add(services);
        this.add(help);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == services){
            callerFrame.add(new ServicesFrame(this));
        }
    }
}