package gui.signIn;

import config.Constants;
import data.Data;
import gui.MainFrame;
import gui.imageButton.ShiftingImageButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends JPanel {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel retypePass;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ShiftingImageButton registerButton;
    private ShiftingImageButton exitButton;
    private JLabel newAccountLabel;
    private JPasswordField retypePassField;


    public RegisterPanel(){
        setSize(1200,700);
        setPreferredSize(new Dimension(1200,700));
        setBounds(0,0,1200,700);
        setLayout(null);
        initGoToRegisterLabel();
        initRegisterButton();
        initCancelButton();
        initPasswordLabel();
        initPasswordTextField();
        initUsernameLabel();
        initUsernameTextField();
        initRetypePassLabel();
        initRetypePassField();
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(retypePass);
        add(retypePassField);
        add(registerButton);
        add(exitButton);
        add(newAccountLabel);
        setVisible(true);
    }

    private void initUsernameLabel(){
        usernameLabel = new JLabel("Username ");
        usernameLabel.setBounds(290,200,200,20);
    }

    private void initPasswordLabel(){
        passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(290,300,200,20);
    }
    private void initRetypePassLabel(){
        retypePass = new JLabel("Retype Password ");
        retypePass.setBounds(290,400,200,20);
    }
    private void initUsernameTextField(){
        usernameField = new JTextField();
        usernameField.setBounds(290,230,250,20);
        usernameField.setOpaque(false);
        usernameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.GRAY));
    }

    private void initPasswordTextField(){
        passwordField = new JPasswordField();
        passwordField.setBounds(290,330,250,20);
        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.GRAY));

    }

    private void initRetypePassField(){
        retypePassField = new JPasswordField();
        retypePassField.setBounds(290,430,250,20);
        retypePassField.setOpaque(false);
        retypePassField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.GRAY));
    }

    public void initRegisterButton(){
        registerButton = new ShiftingImageButton("register");
        registerButton.setBounds(300, 500, 100,30);
        registerButton.setBackground(Color.cyan);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String passWord = String.valueOf(passwordField.getPassword());
                String retypePassword = String.valueOf(retypePassField.getPassword());
                if(username.equals("") || passWord.equals("") || retypePassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "one or more empty fields", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!passWord.equals(retypePassword)) {
                    JOptionPane.showMessageDialog(null, "password not match", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String registerRes = Data.register(username,passWord);
                if(registerRes.equals("this username used before")) {
                    JOptionPane.showMessageDialog(null, "this username used before", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                MainFrame.cl.show(MainFrame.panelCont, "3");
            }
        });
    }

    public void initCancelButton(){
        exitButton = new ShiftingImageButton("loginExit");
        exitButton.setBounds(420, 500, 100,30);
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void initGoToRegisterLabel(){
        newAccountLabel = new JLabel("already have an account?");
        newAccountLabel.setBounds(310, 550, 200,20);
        newAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.panelCont, "1");
            }
        });

    }



    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("loginImage"), 0, 0, 1200, 700, null);
    }
}
