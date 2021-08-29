package com.carbonzero.gui.impl;

import com.carbonzero.gui.services.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class LoginScreen extends JFrame implements ActionListener, IFrame {

    private GridBagConstraints gbc;
    private JButton logoButton, logInButton, registerButton;
    private JPanel buttonPanel;
    private final String konsorsiyumLink = "https://www.konsorsiyum.io/";
    private JTextField idTextField;
    private JPasswordField passwordField;

    public LoginScreen(){
        initFrame();
        initLogo();
        initIDRequest();
        initPasswordRequest();
        initRegisterButton();
        initLogInButton();
        initButtonPanel();
        setVisible(true);
    }

    @Override
    public void initFrame(){
        setTitle("Carbon Zero");
        setSize(350,450);
        //setMinimumSize(getSize());
        setResizable(false);
        getContentPane().setBackground(IColors.specialGreen);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        gbc = new GridBagConstraints();
    }

    private void initLogo() {
        ImageIcon logo = new ImageIcon("src/images/application.images/carbon_zero_logo.png");
        Image resized = logo.getImage().getScaledInstance(300,150, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resized);
        logoButton = new JButton(logo);
        logoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoButton.setOpaque(false);
        logoButton.setContentAreaFilled(false);
        logoButton.setBorderPainted(false);
        logoButton.setFocusPainted(false);
        logoButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        logoButton.addActionListener(this);
        gbc = GBCAdjuster.gbcAdjuster(2,1,0,0, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        add(logoButton,gbc);
    }

    private void initIDRequest() {
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,1,GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(IFont.labelFont);
        idLabel.setForeground(Color.WHITE);
        add(idLabel,gbc);
        gbc = GBCAdjuster.gbcAdjuster(1,1,1,1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        idTextField = new JTextField();
        idTextField.setFont(IFont.textFont);
        add(idTextField, gbc);
    }

    private void initPasswordRequest() {
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,2,GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(IFont.labelFont);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel,gbc);
        gbc = GBCAdjuster.gbcAdjuster(1,1,1,2,GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        passwordField = new JPasswordField();
        passwordField.setFont(IFont.textFont);
        add(passwordField,gbc);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");
        IButtonAdjuster.initButton(registerButton,IFont.buttonFont,Color.BLACK,Color.WHITE);
        registerButton.addActionListener(this);
    }

    private void initLogInButton() {
        logInButton = new JButton("Log In");
        IButtonAdjuster.initButton(logInButton,IFont.buttonFont,Color.BLACK,Color.WHITE);
        logInButton.addActionListener(this);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,0));
        buttonPanel.add(registerButton);
        buttonPanel.add(logInButton);
        buttonPanel.setBackground(IColors.specialGreen);
        gbc = GBCAdjuster.gbcAdjuster(2,1,0,3, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        add(buttonPanel,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == logoButton){
            try {
                Desktop.getDesktop().browse(new URI(konsorsiyumLink));
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        else if(e.getSource() == logInButton){
            if(!idTextField.getText().equals("") & !passwordField.getText().equals("")) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String password = passwordField.getText();
                    String requestURL = String.format("http://localhost:8080/logincheck?id=%d&password=%s", id, password);

                    // TODO https://stackoverflow.com/questions/43330607/how-i-get-json-as-string-from-url-java
                    RestTemplate restTemplate = new RestTemplate();
                    Boolean response = restTemplate.getForObject(requestURL, Boolean.class);

                    if (response == true) {
                        dispose();
                        ApplicationMainScreen applicationMainScreen = new ApplicationMainScreen(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "ID or password is incorrect.");
                    }
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "ID can contain only numbers.");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"ID or password is empty.");
            }
        }
        else if(e.getSource() == registerButton){
            dispose();
            RegisterScreen registerScreen = new RegisterScreen();
        }
    }
}
