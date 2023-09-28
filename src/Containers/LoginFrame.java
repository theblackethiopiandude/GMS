package Containers;

import UIComponents.GButton;
import UIComponents.GFrame;
import UIComponents.LoginPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DatabaseComponents.Connection.getConnection;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {
    JLabel welcome = new JLabel("Welcome!");
    GButton loginButton;
    JPasswordField passwordField;
    JTextField usernameField;
    public LoginFrame(){
        this.setSize(1366, 768);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GMS Login");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("assets/images/car-service.png").getImage());
        welcome.setBounds(750, 383, 200, 36);
        welcome.setFont(new Font("Arial", Font.PLAIN, 27));
        welcome.setForeground(Color.WHITE);
        LoginPanel loginPanel = new LoginPanel();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new GButton("Login", new Point(92,21), 28, new Color(0xA62631), new Font("Arial", Font.PLAIN, 15) );

//        username.setBackground(new Color(0x262626));
//        username.setBorder(new EmptyBorder(0, 0, 0,0));
//        passwordField.setBackground(new Color(0x262626));
//        passwordField.setBorder(new EmptyBorder(0, 0, 0,0));
        JTextField fields[] = {usernameField, passwordField};
        for (JTextField field:fields) {
            field.setBackground(new Color(0x262626));
            field.setBorder(new EmptyBorder(0, 0, 0,0));
            field.setFont(new Font("Arial", Font.BOLD, 15));
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
        }
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        JLabel labels[] = {usernameLabel, passwordLabel};
        for (JLabel field:labels) {
            field.setFont(new Font("Arial", Font.BOLD, 13));
            field.setForeground(Color.WHITE);
        }

        usernameField.setPreferredSize(new Dimension(155, 18));
        passwordField.setPreferredSize(new Dimension(155, 18));
        loginButton.setPreferredSize(new Dimension(221, 58));

        loginPanel.setBounds(0,0,1366, 768);
        usernameLabel.setBounds(723,435,155, 18);
        usernameField.setBounds(725,455,155, 18);
        passwordLabel.setBounds(723, 489,155,18);
        passwordField.setBounds(725, 509,155,18);
        loginButton.setBounds(753,583,221,58);

        loginButton.addActionListener(this);
        usernameField.addKeyListener(this);
        passwordField.addKeyListener(this);
//        loginButton.setVisible(true);

        loginPanel.add(usernameLabel);
        loginPanel.add(passwordLabel);

        loginPanel.add(loginButton);
        loginPanel.add(welcome);
        loginPanel.add(usernameField);
        loginPanel.add(passwordField);

        this.add(loginPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            if (!usernameField.getText().isEmpty()){
                MessageDigest sha256 = null;
                try {
                    sha256 = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println(ex.getMessage());
                }
                byte[] hashedBytes = sha256.digest(new String(passwordField.getPassword()).getBytes(StandardCharsets.UTF_8));
                StringBuilder hexStringBuilder = new StringBuilder();
                for (byte b : hashedBytes) {
                    hexStringBuilder.append(String.format("%02x", b));
                }
                boolean valid = false;
                try (PreparedStatement pst = getConnection().prepareStatement("SELECT Password FROM LoginInfo WHERE Username = ?")){

                    pst.setString(1, usernameField.getText());

                    ResultSet rs = pst.executeQuery();
                    while(rs.next()) {
                        if (rs.getString("Password").equals(hexStringBuilder.toString())){
                            new GFrame();
                            this.dispose();
                            valid = true;
                        }
                    }
                } catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
                if (!valid){
                    welcome.setFont(new Font("Arial", Font.BOLD, 20));
                    welcome.setText("Incorrect Credential !");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == usernameField){
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                passwordField.requestFocus();
        }else if (e.getSource() == passwordField){
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                loginButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
