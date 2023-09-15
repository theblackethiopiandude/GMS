package Containers;

import UIComponents.GButton;
import UIComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryFrame extends GPanel {
    public HistoryFrame(JPanel callerPanel){
        this.setLayout(null);
        this.setBounds(0,0, 1366, 768);
        this.setPreferredSize(new Dimension(1366, 768));

        GButton backButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/backArrow.png"), new Point(21, 15));
        GButton addButton = new GButton(29, new Color(0x262626), new ImageIcon("assets/images/addIcon.png"), new Point(27, 14));

        backButton.setBounds(271,26, 71, 43);
        addButton.setBounds(970, 26, 71, 43);



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callerPanel.setVisible(true);
            }
        });

        this.add(backButton);
        this.add(addButton);

        this.setVisible(true);
    }
}
