package controller;

import model.DownloadsManager;
import model.EmptyTextFieldException;
import model.UserManager;
import model.entity.User;
import network.ServerComunicationLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import view.LoginWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Controlador de la vista de Login i Sign Up
 */
public class LoginController implements ActionListener, WindowListener {
    //Communication commands
    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private User associatedUser;
    private ServerComunicationLogin sc;
    private boolean correctLogin;
    private boolean correctRegister;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     * @param w vista de login
     */
    public LoginController(LoginWindow w) {
        this.w = w;
        this.associatedUser = null;
        try {
            this.sc =  new ServerComunicationLogin(this);
        } catch (IOException e) {
            w.showWarning("The server communication is not working.");
        }
    }

    /**
     * Metode que implementa el ActionPerformed dels ActionListeners associats al LoginWindow
     * @param e esdeveniment
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
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
                    UserManager.isEmpty(w.getSignInUsername(), "username");
                    UserManager.isEmpty(w.getSignInPassword(), "password");

                    associatedUser = new User(UserManager.fixSQLInjections(w.getSignInUsername()), w.getSignInPassword());  //Constructor que ja comprova si es mail o Username

                    sc = new ServerComunicationLogin(this);
                    sc.startServerComunication(LOGIN_USER);

                     if(correctLogin){
                        w.dispose();
                        DownloadsManager.createDirectory(associatedUser.getUsername());
                        if(associatedUser.isCompleted()) {
                            MainWindow mw = new MainWindow("PROFILE");
                            associatedUser.setCompleted(true);
                            MenuController mc = new MenuController(mw, associatedUser);
                            associatedUser.base64ToImage(associatedUser.getUsername(), associatedUser.getUsername());
                            mc.loadProfile();
                            mw.registraController(mc);
                            mw.setVisible(true);
                        } else {
                            MainWindow mw = new MainWindow("EDIT");
                            MenuController mc = new MenuController(mw, associatedUser);
                            mw.firstEdition();
                            mw.registraController(mc);
                            mw.setVisible(true);
                        }
                    }else{
                        w.showWarning("User or password not correct!");
                    }

                } catch (EmptyTextFieldException e1) {
                    w.showWarning(e1.getMessage());
                } catch (IOException e1) {
                    w.showWarning("Error with server communication.");
                }
                break;

            case "SIGN UP":
                try {
                    UserManager.isEmpty(w.getSignUpUsername(), "username");
                    String[] passwords = w.getSignUpPasswords();
                    UserManager.isEmpty(passwords[0], "password");
                    UserManager.isEmpty(passwords[1], "password confirm");
                    UserManager.isEmpty(w.getSignUpEmail(), "mail");
                    UserManager.isEmpty(w.getSignUpAgeField(), "age");
                    UserManager.signUpPasswordIsCorrect(passwords[0], passwords[1]);
                    UserManager.mailCorrectFormat(w.getSignUpEmail());
                    UserManager.isAdult(w.getSignUpAgeField());
                    UserManager.isAgeFilterCorrect(w.getMinAge(), w.getMaxAge(), w.getNoFilter());
                    String hashedPassword = encoder.encode(passwords[0]);
                    associatedUser = new User(UserManager.fixSQLInjections(w.getSignUpUsername()), Integer.parseInt(w.getSignUpAgeField()), w.isPremiumSignUp(), w.getSignUpEmail(), hashedPassword, Integer.parseInt(w.getMinAge()), Integer.parseInt(w.getMaxAge()));

                    sc.startServerComunication(REGISTER_USER);

                    if(correctRegister){
                        DownloadsManager.createDirectory(associatedUser.getUsername());
                        w.dispose();
                        MainWindow mw = new MainWindow("EDIT");
                        MenuController mc = new MenuController(mw, associatedUser);
                        mw.firstEdition();
                        mw.registraController(mc);
                        mw.setVisible(true);
                    }else{
                        w.showWarning("The sign up process was not successful");
                    }

                } catch (Exception e1) {
                    w.showWarning(e1.getMessage());
                }
                break;
        }
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowOpened(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowClosing(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowClosed(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowIconified(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowActivated(WindowEvent e) {
        //Not used.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
        //Not used.
    }


    /**
     * Getter del atribut associatedUser
     * @return usuari associat
     */
    public User getRegisteredUser() {
        return associatedUser;
    }

    /**
     * Metode que crea l'usuari amb les dades provinents de la BBDD sabent que existeix
     * @param u usuari rebut
     */
    public void setSignInUser(User u) {
        this.associatedUser = u;
    }

    /**
     * Getter de l'usuari que ha iniciat sessio amb el compte actual
     * @return Usuari associat a la compta
     */
    public User getLoginUser() {
        return associatedUser;
    }

    /**
     * Setter que indica si el la peticio d'inici de sessio es correcta o no
     * @param b boolea que indica si el login es correcte o no
     */
    public void setCorrectLogin(boolean b) {
        correctLogin = b;
    }

    /**
     * Setter que indica si el registre s'ha pogut completar amb exit o no
     * @param existsR boolea que indica si es correcte
     */
    public void setCorrectRegister(boolean existsR) {
        correctRegister = existsR;
    }
}
