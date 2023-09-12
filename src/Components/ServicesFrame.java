package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicesFrame extends GPanel implements ActionListener {
    private final JPanel callerPanel;
    GButton backButton, addButton;
    public ServicesFrame(JPanel callerPanel){
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));

        this.callerPanel = callerPanel;
        this.callerPanel.setVisible(false);

        this.backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));
        this.addButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/addIcon.png"), new Point(27, 14));

        ServicePanel itemPanel = new ServicePanel("Oil Change",new ImageIcon("assets/images/oil.png"));

        backButton.setBounds(271,26, 71, 43);
        addButton.setBounds(970, 26, 71, 43);

        itemPanel.setBounds(134, 97, 262, 299);


        backButton.addActionListener(this);

        this.add(backButton);
        this.add(addButton);
        this.add(itemPanel);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == backButton){
            this.setVisible(false);
            callerPanel.setVisible(true);
        }
    }
}
