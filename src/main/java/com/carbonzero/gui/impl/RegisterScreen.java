package com.carbonzero.gui.impl;

import com.carbonzero.databaseconnection.impl.users.DatabaseConnectionClass;
import com.carbonzero.gui.services.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterScreen extends JFrame implements IFrame, ActionListener {

    private GridBagConstraints gbc;
    private JLabel imageLabel, idLabel, passwordLabel, passwordConfirmLabel, mailLabel, nameLabel, surnameLabel, companyLabel;
    private JTextField idTextField, mailTextField, nameTextField, surnameTextField, companyTextField;
    private JPasswordField passwordField, passwordConfirmField;
    private JPanel inputPanel, buttonPanel;
    private JButton cancelButton, registerButton;

    public RegisterScreen(){
        //TODO BANKA BAĞLANTISI EKLENECEK
        //TODO PROFİL RESMİ EKLEME GELECEK https://mkyong.com/swing/java-swing-jfilechooser-example/
        initFrame();
        initHeadImage();
        initInputPanel();
        initIDRequest();
        initNameRequest();
        initSurnameRequest();
        initCompanyRequest();
        initPasswordRequest();
        initMailRequest();
        initCancelButton();
        initRegisterButton();
        initButtonPanel();
        setVisible(true);
    }

    @Override
    public void initFrame() {
        setTitle("Register");
        setSize(500,600);
        //setMinimumSize(getSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(IColors.specialGreen);
        gbc = new GridBagConstraints();
    }

    private void initHeadImage() {
        ImageIcon image = new ImageIcon("src/images/application.images/registerlogo.png");
        Image resize = image.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        image = new ImageIcon(resize);
        imageLabel = new JLabel();
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setIcon(image);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,0,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        add(imageLabel, gbc);
    }

    private void initInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBackground(IColors.specialGreen);
        inputPanel.setLayout(new GridLayout(7,7,10,10));
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,1,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        gbc.weightx = 1;
        add(inputPanel, gbc);
    }

    private void initIDRequest() {
        idLabel = new JLabel("ID:");
        idLabel.setFont(IFont.labelFont);
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel);

        idTextField = new JTextField();
        idTextField.setFont(IFont.textFont);
        inputPanel.add(idTextField);
    }

    private void initNameRequest() {
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(IFont.labelFont);
        nameLabel.setForeground(Color.WHITE);
        inputPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setFont(IFont.textFont);
        inputPanel.add(nameTextField);
    }

    private void initSurnameRequest() {
        surnameLabel = new JLabel("Surname:");
        surnameLabel.setFont(IFont.labelFont);
        surnameLabel.setForeground(Color.WHITE);
        inputPanel.add(surnameLabel);

        surnameTextField = new JTextField();
        surnameTextField.setFont(IFont.textFont);
        inputPanel.add(surnameTextField);
    }

    private void initCompanyRequest() {
        companyLabel = new JLabel("Company:");
        companyLabel.setFont(IFont.labelFont);
        companyLabel.setForeground(Color.WHITE);
        inputPanel.add(companyLabel);

        companyTextField = new JTextField();
        companyTextField.setFont(IFont.textFont);
        inputPanel.add(companyTextField);
    }

    private void initPasswordRequest() {
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(IFont.labelFont);
        passwordLabel.setForeground(Color.WHITE);
        inputPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(IFont.textFont);
        inputPanel.add(passwordField);

        passwordConfirmLabel = new JLabel("Confirm Password:");
        passwordConfirmLabel.setFont(IFont.labelFont);
        passwordConfirmLabel.setForeground(Color.WHITE);
        inputPanel.add(passwordConfirmLabel);

        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setFont(IFont.textFont);
        inputPanel.add(passwordConfirmField);
    }

    private void initMailRequest() {
        mailLabel = new JLabel("E-Mail:");
        mailLabel.setFont(IFont.labelFont);
        mailLabel.setForeground(Color.WHITE);
        inputPanel.add(mailLabel);

        mailTextField = new JTextField();
        mailTextField.setFont(IFont.textFont);
        inputPanel.add(mailTextField);
    }

    private void initCancelButton() {
        cancelButton = new JButton("Cancel");
        IButtonAdjuster.initButton(cancelButton,IFont.buttonFont,Color.BLACK,Color.WHITE);
        cancelButton.addActionListener(this);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");
        IButtonAdjuster.initButton(registerButton,IFont.buttonFont,Color.BLACK,Color.WHITE);
        registerButton.addActionListener(this);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,0));
        buttonPanel.add(cancelButton);
        buttonPanel.add(registerButton);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,2,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        buttonPanel.setBackground(IColors.specialGreen);
        add(buttonPanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){
            dispose();
            LoginScreen loginScreen = new LoginScreen();
        }
        else if(e.getSource() == registerButton){
            int option;
            if( idTextField.getText().equals("") | nameTextField.getText().equals("") |
                    surnameTextField.getText().equals("") | companyTextField.getText().equals("") |
                    passwordField.getText().equals("") | passwordConfirmField.getText().equals("") |
                    mailTextField.getText().equals("")
            ){
                JOptionPane.showMessageDialog(null, "At least one field is empty.");
            }
            else if(!passwordField.getText().equals(passwordConfirmField.getText())){
                JOptionPane.showMessageDialog(null, "Passwords are not matching.");
            }
            else{
                option = JOptionPane.showConfirmDialog(null,"The given information about myself is totally true.",
                        "Register Confirm",JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    int id = 0;
                    String name = null;
                    String surname = null;
                    String company = null;
                    String password = null;
                    String email = null;

                    try {
                        id = Integer.parseInt(idTextField.getText());
                        name = nameTextField.getText();
                        surname = surnameTextField.getText();
                        company = companyTextField.getText();
                        password = passwordField.getText();
                        email = mailTextField.getText();

                        DatabaseConnectionClass databaseConnectionClass = new DatabaseConnectionClass();
                        databaseConnectionClass.createConnection();
                        PreparedStatement preparedStatement = databaseConnectionClass.con.prepareStatement("SELECT id FROM users WHERE id = ?");
                        preparedStatement.setInt(1,id);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()){
                            databaseConnectionClass.closeConnection();
                            throw new SQLIntegrityConstraintViolationException();
                        }
                        else{
                            databaseConnectionClass.closeConnection();
                        }

                        // TODO https://www.youtube.com/watch?v=tQ8BozAhis4
                        String url = String.format("http://localhost:8080/postuser", id, name, surname,
                                company, password, email);
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                        map.add("id", String.valueOf(id));
                        map.add("name", name);
                        map.add("surname", surname);
                        map.add("company", company);
                        map.add("password", password);
                        map.add("email", email);
                        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.postForEntity(url, request, String.class);
                        dispose();
                        LoginScreen loginScreen = new LoginScreen();
                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(null, "Id should contain only number.");
                    } catch (SQLIntegrityConstraintViolationException error){
                        JOptionPane.showMessageDialog(null, "This id has been taken.");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                // NO_OPTION
                else{
                    JOptionPane.showMessageDialog(null,"Please correct your personal information.");
                }
            }
        }
    }
}
