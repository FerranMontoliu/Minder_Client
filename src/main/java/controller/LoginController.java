package controller;

import model.EmptyTextFieldException;
import model.PasswordHasher;
import model.User;
import model.UserManager;
import network.ServerComunicationLogin;
import view.LoginWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class LoginController implements ActionListener, WindowListener {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private User u;
    private ServerComunicationLogin sc;
    private boolean correctLogin;
    private boolean correctRegister;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
     */
    public LoginController(LoginWindow w) {
        this.w = w;
        this.u = null;
        this.sc =  new ServerComunicationLogin(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "SIGN-IN-JRB":
                w.cleanSignInPanel();
                w.changePanel("SIGN IN");
                w.focusUserIn();
                break;

            case "SIGN-UP-JRB":
                w.cleanSignUpPanel();
                w.changePanel("SIGN UP");
                w.focusUserUp();
                break;

            case "SHOW-UP":
                w.getSignUpPanel().showPassword();
                break;

            case "SHOW-IN":
                w.getSignInPanel().showPassword();
                break;

            case "SIGN IN":
                try {
                    UserManager.isEmpty(w.getSignInUsername(), "nom");
                    UserManager.isEmpty(w.getSignInPassword(), "password");
                    u = new User(UserManager.fixSQLBugs(w.getSignInUsername()), w.getSignInPassword());  //Constructor que ja comprova si es mail o Username

                    sc.startServerComunication(LOGIN_USER);
                    sc.join();
                    //El servidor retorna un usuari amb totes les dades completes tal que el codi a partir d'aquí seria així:
                    User user = new User(true, "Polete", "19", true, "polete@polete.polete", "password", null,"M'agraden els croissants", false, true, "Frozen", null, null, null, null, null);
                    if(true/*correctLogin*/){
                        w.dispose();
                        if(user.isCompleted()) { //TODO: Canviar user pel atribut u
                            MainWindow mw = new MainWindow("CONNECT");
                            MenuController mc = new MenuController(mw, user);
                            mw.registraController(mc);
                            mw.setVisible(true);
                        } else {
                            MainWindow mw = new MainWindow("EDIT");
                            MenuController mc = new MenuController(mw, user);
                            mw.registraController(mc);
                            mw.setVisible(true);
                        }
                    }else{
                        w.showWarning("L'usuari o contrasenya no són correctes!");
                    }

                } catch (EmptyTextFieldException e1) {
                    w.showWarning(e1.getMessage());
                } catch (InterruptedException e1) {
                    w.showWarning("Hi ha hagut un problema amb la connexió al servidor.");
                }
                break;

            case "SIGN UP":
                try {
                    UserManager.isEmpty(w.getSignUpUsername(), "nom");
                    String[] passwords = w.getSignUpPasswords();
                    UserManager.isEmpty(passwords[0], "password");
                    UserManager.isEmpty(passwords[1], "password confirm");
                    UserManager.isEmpty(w.getSignUpEmail(), "mail");
                    UserManager.isEmpty(w.getSignUpAgeField(), "age");
                    UserManager.signUpPasswordIsCorrect(passwords[0], passwords[1]);
                    UserManager.mailCorrectFormat(w.getSignUpEmail());
                    UserManager.isAdult(w.getSignUpAgeField());
                    //PasswordHasher ph = new PasswordHasher(passwords[0]);
                   // u = new User(UserManager.fixSQLBugs(w.getSignUpUsername()), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), ph.getSecurePassword(), ph.getSalt());

                    sc.startServerComunication(REGISTER_USER);
                    sc.join();

                    if(correctRegister){
                        w.dispose();
                        MainWindow mw = new MainWindow("EDIT"); //Si, es mostra el perfil, pero pq s'ha de completar.
                        MenuController mc = new MenuController(mw, u); //Potser estaria millor escriure EDIT i no PROFILE no?
                        mw.firstEdition();
                        mw.registraController(mc);
                        mw.setVisible(true);
                    }else{
                        w.showWarning("El registre no ha sigut satisfactori.");
                    }

                } catch (Exception e1) {
                    w.showWarning(e1.getMessage());
                }
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        sc.stopServerComunication();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //Not used.
    }

    public User getRegisteredUser() {
        return u;
    }

    public void setSignInUser(User u) {
        this.u = u;
    }

    public User getLoginUser() {
        return u;
    }

    public User loginWithHashedPassword() {
        /*
        try {
            PasswordHasher ph = new PasswordHasher(w.getSignInPassword());
            ph.setSalt(u.getSalt());
            u.setPassword(ph.getSecurePassword());
            return u;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        return u;
    }

    public void setCorrectLogin(boolean b) {
        correctLogin = b;
    }

    public void setCorrectRegister(boolean existsR) {
        correctRegister = existsR;
    }
}
