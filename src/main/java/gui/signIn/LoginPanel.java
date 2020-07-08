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

public class LoginPanel extends JPanel{
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ShiftingImageButton LoginButton;
    private ShiftingImageButton exitButton;
    private JLabel newAccountLabel;

    public LoginPanel(){
        setSize(1200,700);
        setPreferredSize(new Dimension(1200,700));
        setBounds(0,0,1200,700);
        setLayout(null);
        initGoToRegisterLabel();
        initLoginButton();
        initCancelButton();
        initPasswordLabel();
        initPasswordTextField();
        initUsernameLabel();
        initUsernameTextField();
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(LoginButton);
        add(exitButton);
        add(newAccountLabel);
        setVisible(true);
    }

    private void initUsernameLabel(){
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(290,200,200,20);
    }

    private void initPasswordLabel(){
        passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(290,300,200,20);
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


    public void initLoginButton(){
        LoginButton = new ShiftingImageButton("login");
        LoginButton.setBounds(300, 500, 100,30);
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String passWord = String.valueOf(passwordField.getPassword());
                if(username.equals("") || passWord.equals("")) {
                    JOptionPane.showMessageDialog(null, "one or more empty fields", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String loginRes = Data.login(username,passWord);
                if(loginRes.equals("username not valid")) {
                    JOptionPane.showMessageDialog(null, "username not valid", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(loginRes.equals("wrong password")) {
                    JOptionPane.showMessageDialog(null, "wrong password", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                MainFrame.cl.show(MainFrame.panelCont, "3");
            }
        });
    }

    public void initCancelButton(){
        exitButton = new ShiftingImageButton("loginExit");
        exitButton.setBounds(420, 500, 100,30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void initGoToRegisterLabel(){
        newAccountLabel = new JLabel("click here to create a new account");
        newAccountLabel.setBounds(310, 550, 200,30);
        newAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.panelCont, "2");
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("loginImage"), 0, 0, 1200, 700, null);
    }
}