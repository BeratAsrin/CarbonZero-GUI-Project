package com.carbonzero.gui.impl;

import com.carbonzero.gui.services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ApplicationMainScreen extends JFrame implements IFrame, ActionListener {

    private JButton konsorsiyumLogoButton, userProfileImageButton;
    private ArrayList <JButton> menuOptions;
    private final String konsorsiyumLink = "https://www.konsorsiyum.io/";
    private JPanel logoPanel, menuPanel, centerPanel, homePagePanel, profilePanel, bankAccountPanel, assetPanel,
            organizedMarketPanel, priceHistoryPanel, objectionPanel ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel welcomeLabel, timeStamp;

    int id;
    String name, surname, company, password, email;

    public ApplicationMainScreen(int id){
        initFrame();
        initHeadingPanel();
        initWelcomeLabel();
        initLogo();
        initMenuPanel();
        initMenu();
        initHomePagePanel();
        initProfilePanel();
        initBankAccountPanel();
        initAssetPanel();
        initOrganizedMarketPanel();
        initPriceHistoryPanel();
        initObjectionPanel();
        initCenterPanel();
        setVisible(true);
    }

    @Override
    public void initFrame() {
        setTitle("CarbonZero Application");
        setMinimumSize(new Dimension(800,650));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void initHeadingPanel() {
        logoPanel = new JPanel();
        logoPanel.setLayout(new GridBagLayout());
        logoPanel.setBackground(IColors.specialGreen);
        add(logoPanel, BorderLayout.NORTH);
    }

    private void initWelcomeLabel() {
        // TODO USERNAME DATABASEDEN ÇEKİLECEK
        // TODO userProfile Database bağlantısından sonra kontrol edilecek
        // TODO https://stackoverflow.com/questions/9963750/image-resize-maintain-aspect-ratio

        ImageIcon userProfile = new ImageIcon("src/images/vesikalık.jpeg");
        int ratio = userProfile.getIconHeight() /  userProfile.getIconWidth();
        Image resize = userProfile.getImage().getScaledInstance(90*ratio,90/ratio,Image.SCALE_SMOOTH);
        userProfile = new ImageIcon(resize);

        userProfileImageButton = new JButton(userProfile);
        userProfileImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userProfileImageButton.setOpaque(false);
        userProfileImageButton.setContentAreaFilled(false);
        userProfileImageButton.setBorderPainted(false);
        userProfileImageButton.setFocusPainted(false);
        userProfileImageButton.addActionListener(this);
        userProfileImageButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        gbc = GBCAdjuster.gbcAdjuster(1,1,2, 0,
                GridBagConstraints.VERTICAL, GridBagConstraints.EAST);
        gbc.weightx = 1;
        logoPanel.add(userProfileImageButton, gbc);

        String toWelcome ="<html>Welcome,<br/>Berat Asrın CAFEROĞLU</html>";
        welcomeLabel = new JLabel(toWelcome);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(IFont.labelFont);
        welcomeLabel.setForeground(Color.WHITE);
        gbc = GBCAdjuster.gbcAdjuster(1,1,1, 0,
                GridBagConstraints.VERTICAL, GridBagConstraints.EAST);
        gbc.weightx = 98;
        logoPanel.add(welcomeLabel,gbc);
    }

    private void initLogo() {
        ImageIcon konsorsiyumLogo = new ImageIcon("src/images/application.images/konsorsiyum_logo.jpg");
        Image resize = konsorsiyumLogo.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        konsorsiyumLogo = new ImageIcon(resize);
        konsorsiyumLogoButton = new JButton(konsorsiyumLogo);
        konsorsiyumLogoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        konsorsiyumLogoButton.setOpaque(false);
        konsorsiyumLogoButton.setContentAreaFilled(false);
        konsorsiyumLogoButton.setBorderPainted(false);
        konsorsiyumLogoButton.setFocusPainted(false);
        konsorsiyumLogoButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        konsorsiyumLogoButton.addActionListener(this);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0, 0,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        gbc.weightx = 1;
        logoPanel.add(konsorsiyumLogoButton, gbc);
    }

    private void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);
        add(menuPanel, BorderLayout.WEST);
    }

    private void initMenu() {
        menuOptions = new ArrayList();
        menuOptions.add(new JButton("Home Page"));
        menuOptions.add(new JButton("Profile"));
        menuOptions.add(new JButton("Bank Account"));
        menuOptions.add(new JButton("Assets"));
        menuOptions.add(new JButton("Organized Market"));
        menuOptions.add(new JButton("Price History"));
        menuOptions.add(new JButton("Objection"));
        menuOptions.add(new JButton(("Exit")));
        JLabel blank = new JLabel();
        gbc = GBCAdjuster.gbcAdjuster(1,1,0, 0,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        gbc.weighty = 0.2;
        menuPanel.add(blank, gbc);

        for (JButton temp : menuOptions){
            temp.setBorderPainted(false);
            temp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            temp.setForeground(Color.WHITE);
            temp.setBackground(IColors.specialGreen);
            gbc = GBCAdjuster.gbcAdjuster(1,1,0, menuOptions.indexOf(temp)+1,
                    GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
            temp.setFont(IFont.applicationMainScreenButtonFont);
            temp.addActionListener(this);
            menuPanel.add(temp, gbc);
        }

        timeStamp = new JLabel("Welcome!");
        timeStamp.setFont(IFont.applicationMainScreenButtonFont);
        timeStamp.setForeground(Color.WHITE);
        timeStamp.setBackground(IColors.specialGreen);
        timeStamp.setOpaque(true);
        timeStamp.setVerticalAlignment(JLabel.CENTER);
        timeStamp.setHorizontalAlignment(JLabel.CENTER);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0, menuOptions.size()+1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        gbc.weighty = 0.4;
        menuPanel.add(timeStamp,gbc);

        // Timer
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(date);
                timeStamp.setText(time);
            }
        });
        timer.start();

    }

    private void initHomePagePanel() {
        // TODO GRAFİK EKLE
        ComponentAdder componentAdder = new ComponentAdder();

        homePagePanel = new JPanel();
        homePagePanel.setLayout(new GridLayout(2,1,10,10));
        homePagePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        homePagePanel.setBackground(Color.WHITE);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,2,10,10));

        String[] tableTwoHeading = {"Market Type","Total Certificates","Certificates for Sale","Minimum Price","Maximum Price"};
        String[][] tableTwoData = {
                {"Agricultural CarbonZero", "52", "30", "5 TL","15 TL"},
                {"Industrial CarbonZero", "100", "70", "50 TL","100 TL"},
                {"Home CarbonZero", "532", "300", "1 TL","5 TL"},
                {"Energy CarbonZero", "200", "100", "20 TL","50 TL"}

        };

        JTable table2 = new JTable(tableTwoData, tableTwoHeading);
        componentAdder.tableAdder(table2, tablePanel);

        String[] tableThreeHeading = {"Last Transactions", "Base Price","Number of Certificate", "Buyer", "Seller"};
        String[][] tableThreeData = {
            {"1","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"2","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"3","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"4","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"5","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"6","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"7","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"8","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"9","5 TL","10","Berat A.Ş.", "Asrın A.Ş."},
            {"10","5 TL","10","Berat A.Ş.", "Asrın A.Ş."}
        };

        JTable table3 = new JTable(tableThreeData, tableThreeHeading);
        componentAdder.tableAdder(table3, tablePanel);

        JPanel graphicPanel = new JPanel();
        graphicPanel.setLayout(new BorderLayout());
        graphicPanel.setBackground(Color.WHITE);
        GraphDrawer graphDrawer = new GraphDrawer(graphicPanel);

        homePagePanel.add(tablePanel);
        homePagePanel.add(graphicPanel);


    }

    private void initCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new CardLayout());
        centerPanel.add(homePagePanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void initProfilePanel(){

        profilePanel = new JPanel();
        profilePanel.setLayout(new GridBagLayout());
        profilePanel.setBackground(Color.WHITE);
        ComponentAdder componentAdder = new ComponentAdder();


        JLabel nameLabel = new JLabel("Name:");
        componentAdder.labelAdder(nameLabel,profilePanel,0,0,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.EAST);

        JTextField nameTextField = new JTextField("Berat Asrın",15);
        componentAdder.textFieldAdder(nameTextField,false, 1, 0, 1,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JLabel surnameLabel = new JLabel("Surname:");
        componentAdder.labelAdder(surnameLabel,profilePanel,0,1,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.EAST);

        JTextField surnameTextField = new JTextField("CAFEROĞLU",15);
        componentAdder.textFieldAdder(surnameTextField,false, 1, 1, 1,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JLabel companyLabel = new JLabel("Company:");
        componentAdder.labelAdder(companyLabel,profilePanel,0,2,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.EAST);

        JTextField companyTextField = new JTextField("Konsorsiyum",15);
        componentAdder.textFieldAdder(companyTextField,false, 1, 2, 1,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JLabel mailLabel = new JLabel("E-Mail:");
        componentAdder.labelAdder(mailLabel,profilePanel,0,3,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.EAST);

        JTextField mailTextField = new JTextField("beratasrin01@gmail.com",15);
        componentAdder.textFieldAdder(mailTextField,false, 1, 3, 1,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JLabel passwordLabel = new JLabel("Password:");
        componentAdder.labelAdder(passwordLabel,profilePanel,0,4,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.EAST);

        JTextField passwordTextField = new JTextField("123",15);
        componentAdder.textFieldAdder(passwordTextField,false, 1, 4, 1,
                GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JButton passwordUpdate = new JButton("Update Password");
        componentAdder.passwordUpdateButtonAdder(passwordUpdate, 1,5,1,GridBagConstraints.VERTICAL,GridBagConstraints.WEST);


    }

    private void initBankAccountPanel(){
        bankAccountPanel = new JPanel();
        bankAccountPanel.setLayout(new GridBagLayout());
        bankAccountPanel.setBackground(Color.WHITE);

        JLabel currentBankAccountLabel = new JLabel("Current Bank Account:");
        currentBankAccountLabel.setFont(IFont.labelFont);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,0,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(currentBankAccountLabel, gbc);

        JTextField currentBankAccountTextField = new JTextField("BANK_ACCOUNT_NUMBER");
        currentBankAccountTextField.setEditable(false);
        currentBankAccountTextField.setFont(IFont.textFont);
        gbc = GBCAdjuster.gbcAdjuster(1,1,1,0,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(currentBankAccountTextField, gbc);

        JLabel currencyLabel = new JLabel("Currency on CarbonZero System:");
        currencyLabel.setFont(IFont.labelFont);
        gbc = GBCAdjuster.gbcAdjuster(1,1,0,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(currencyLabel, gbc);

        JTextField currencyTextField = new JTextField("1000 TL");
        currencyTextField.setEditable(false);
        currencyTextField.setFont(IFont.textFont);
        gbc = GBCAdjuster.gbcAdjuster(1,1,1,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(currencyTextField, gbc);

        JButton changeBankAccountButton = new JButton("Change Bank Account");
        IButtonAdjuster.initButton(changeBankAccountButton,IFont.applicationMainScreenButtonFont, Color.WHITE, IColors.specialGreen);
        changeBankAccountButton.addActionListener(e -> {
            // TODO Banka değiştirme sekmesi POP UP
        });
        gbc = GBCAdjuster.gbcAdjuster(2,1,0,2,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(changeBankAccountButton,gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        gbc = GBCAdjuster.gbcAdjuster(2,1,0,3,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
        bankAccountPanel.add(buttonPanel,gbc);

        JButton drawMoneyButton = new JButton("Draw Money");
        IButtonAdjuster.initButton(drawMoneyButton,IFont.applicationMainScreenButtonFont,Color.WHITE,IColors.specialGreen);
        buttonPanel.add(drawMoneyButton);

        JButton loadMoneyButton = new JButton("Load Money");
        IButtonAdjuster.initButton(loadMoneyButton,IFont.applicationMainScreenButtonFont,Color.WHITE,IColors.specialGreen);
        buttonPanel.add(loadMoneyButton);


    }

    private void initAssetPanel(){

        ComponentAdder componentAdder = new ComponentAdder();

        assetPanel = new JPanel();
        assetPanel.setBackground(Color.WHITE);
        assetPanel.setLayout(new GridBagLayout());

        JLabel depositLabel = new JLabel(String.format("Deposit on CarbonZero System: %s", "1000 TL"));
        componentAdder.labelAdder(depositLabel,assetPanel,0,0,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER);

        JLabel carbonCertificatesLabel = new JLabel(String.format("Total Owned CarbonZero Certificates: %s", "52"));
        componentAdder.labelAdder(carbonCertificatesLabel,assetPanel,0,1,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER);

        JLabel listedCarbonCertificatesLabel = new JLabel(String.format("CarbonZero Certificates Listed On Organized Market: %s", "20"));
        componentAdder.labelAdder(listedCarbonCertificatesLabel,assetPanel,0,2,1,1,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER);


    }

    private void initOrganizedMarketPanel(){
        organizedMarketPanel = new JPanel();
        organizedMarketPanel.setBackground(Color.cyan);
    }

    private void initPriceHistoryPanel(){
        priceHistoryPanel = new JPanel();
        priceHistoryPanel.setBackground(Color.green);
    }

    private void initObjectionPanel(){
        objectionPanel = new JPanel();
        objectionPanel.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == konsorsiyumLogoButton){
            try {
                Desktop.getDesktop().browse(new URI(konsorsiyumLink));
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        else if(e.getSource() == menuOptions.get(menuOptions.size()-1)){
            dispose();
            LoginScreen loginScreen = new LoginScreen();
        }
        else if(e.getSource() == menuOptions.get(0)){
            centerPanel.removeAll();
            centerPanel.add(homePagePanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(1)){
            centerPanel.removeAll();
            centerPanel.add(profilePanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(2)){
            centerPanel.removeAll();
            centerPanel.add(bankAccountPanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(3)){
            centerPanel.removeAll();
            centerPanel.add(assetPanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(4)){
            centerPanel.removeAll();
            centerPanel.add(organizedMarketPanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(5)){
            centerPanel.removeAll();
            centerPanel.add(priceHistoryPanel);
            IFrameRefresher.refreshFrame(this);
        }
        else if(e.getSource() == menuOptions.get(6)){
            centerPanel.removeAll();
            centerPanel.add(objectionPanel);
            IFrameRefresher.refreshFrame(this);
        }
    }

    class ComponentAdder{

        public void passwordUpdateButtonAdder(JButton buttonToAdd, int x, int y, int weightx, int fill, int anchor) {
            buttonToAdd.addActionListener(e -> {
                JFrame passwordFrame = new JFrame("Update Password");
                passwordFrame.setSize(500,300);
                passwordFrame.setResizable(false);
                passwordFrame.getContentPane().setBackground(IColors.specialGreen);
                passwordFrame.setLocationRelativeTo(null);
                passwordFrame.setLayout(new GridBagLayout());
                passwordFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                JLabel currentPasswordLabel = new JLabel("Current Password");
                currentPasswordLabel.setHorizontalAlignment(JLabel.CENTER);
                currentPasswordLabel.setFont(IFont.labelFont);
                currentPasswordLabel.setForeground(Color.WHITE);
                gbc = GBCAdjuster.gbcAdjuster(1,1   ,0,0,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
                passwordFrame.add(currentPasswordLabel, gbc);

                JTextField currentPasswordTextField = new JTextField();
                currentPasswordTextField.setFont(IFont.textFont);
                gbc = GBCAdjuster.gbcAdjuster(1,1,0,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
                passwordFrame.add(currentPasswordTextField, gbc);
                passwordFrame.setVisible(true);

                JLabel newPasswordLabel = new JLabel("New Password");
                newPasswordLabel.setHorizontalAlignment(JLabel.CENTER);
                newPasswordLabel.setFont(IFont.labelFont);
                newPasswordLabel.setForeground(Color.WHITE);
                gbc = GBCAdjuster.gbcAdjuster(1,1,0,2,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
                passwordFrame.add(newPasswordLabel, gbc);

                JTextField newPasswordTextField = new JTextField();
                newPasswordTextField.setFont(IFont.textFont);
                gbc = GBCAdjuster.gbcAdjuster(1,1,0,4,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
                passwordFrame.add(newPasswordTextField, gbc);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(IColors.specialGreen);
                buttonPanel.setLayout(new GridLayout(1,2,10,10));
                gbc = GBCAdjuster.gbcAdjuster(1,1,0,5,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
                passwordFrame.add(buttonPanel, gbc);

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setFont(IFont.buttonFont);
                cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cancelButton.setBorderPainted(false);
                cancelButton.setForeground(Color.BLACK);
                cancelButton.setBackground(Color.WHITE);
                cancelButton.addActionListener(e1 -> passwordFrame.dispose());
                buttonPanel.add(cancelButton);

                JButton confirmButton = new JButton("Confirm");
                confirmButton.setFont(IFont.buttonFont);
                confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                confirmButton.setBorderPainted(false);
                confirmButton.setForeground(Color.BLACK);
                confirmButton.setBackground(Color.WHITE);
                confirmButton.addActionListener(e12 -> {
                    // TODO PASSWORD DEĞİŞİKLİĞİNİ VERİ TABANINA KAYDET
                });
                buttonPanel.add(confirmButton);

                passwordFrame.setVisible(true);
            });
            IButtonAdjuster.initButton(buttonToAdd, IFont.applicationMainScreenButtonFont, Color.WHITE, IColors.specialGreen);
            gbc =  GBCAdjuster.gbcAdjuster(1,1,x, y,
                    fill, anchor);
            gbc.weightx = weightx;
            profilePanel.add(buttonToAdd, gbc);
        }

        public void labelAdder(JLabel labelToAdd,JPanel panel,int x, int y,int gridWidth, int weightx, int fill, int anchor ){
            labelToAdd.setFont(IFont.labelFont);
            gbc =  GBCAdjuster.gbcAdjuster(1,1,x, y,
                    fill, anchor);
            gbc.weightx = weightx;
            gbc.gridwidth = gridWidth;
            panel.add(labelToAdd, gbc);
        }

        public void textFieldAdder(JTextField textFieldToAdd, boolean editable, int x, int y, int weightx, int fill,
                                   int anchor){
            textFieldToAdd.setEditable(editable);
            textFieldToAdd.setFont(IFont.textFont);
            gbc =  GBCAdjuster.gbcAdjuster(1,1,x, y,
                    fill, anchor);
            gbc.weightx = weightx;
            profilePanel.add(textFieldToAdd, gbc);
        }

        public void tableAdder(JTable table, JPanel panel) {
            table.setFillsViewportHeight(true);
            table.setFont(IFont.tableRowFont);
            table.setRowHeight(25);
            // TODO DUZELT
            table.getTableHeader().setFont(IFont.tableHeaderFont);
            table.getTableHeader().setOpaque(false);
            table.getTableHeader().setBackground(IColors.specialGreen);
            table.getTableHeader().setForeground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
        }
    }
}
