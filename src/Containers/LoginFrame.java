package Containers;

import UIComponents.GButton;
import UIComponents.GFrame;
import UIComponents.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    JLabel welcome = new JLabel("Welcome!");
    public LoginFrame(){
        this.setSize(1366, 768);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GMS Login");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        welcome.setBounds(797, 383, 200, 36);
        welcome.setFont(new Font("Alexandria", Font.PLAIN, 27));
        welcome.setForeground(Color.WHITE);
        LoginPanel loginPanel = new LoginPanel();
        JTextField username = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        GButton loginButton = new GButton("Login", new Point(20,20), 28, new Color(0xA62631), new Font("Alexandria", Font.PLAIN, 12) );

        username.setPreferredSize(new Dimension(155, 18));
        passwordField.setPreferredSize(new Dimension(155, 18));
        loginButton.setPreferredSize(new Dimension(221, 58));

        loginPanel.setBounds(0,0,1366, 768);
        username.setBounds(721,455,155, 18);
        passwordField.setBounds(721, 509,155,18);
        loginButton.setBounds(753,583,221,58);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.getText().isEmpty() && !passwordField.getPassword().equals("")){
                    if (username.getText().equals("admin") && passwordField.getPassword().equals("admin")){
                        new GFrame();
                        dispose();
                    }
                }
            }
        });
//        loginButton.setVisible(true);

        loginPanel.add(loginButton);
        loginPanel.add(welcome);
        loginPanel.add(username);
        loginPanel.add(passwordField);

        this.add(loginPanel);
        this.setVisible(true);
    }

}
