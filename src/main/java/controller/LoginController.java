package controller;

import model.EmptyTextFieldException;
import model.entity.User;
import model.UserManager;
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

public class LoginController implements ActionListener, WindowListener {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private User associatedUser;
    private ServerComunicationLogin sc;
    private boolean correctLogin;
    private boolean correctRegister;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
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
                    sc.join();

                    //El servidor retorna un usuari amb totes les dades completes tal que el codi a partir d'aquí seria així:
                    User user = new User(true, "Polete", "19", true, "polete@polete.polete", "password", null,"M'agraden els croissants", false, true, "Frozen", null, null, null, null, null);
                    if(correctLogin){
                        w.dispose();
                        if(user.isCompleted()) { //TODO ALBA: Canviar user pel atribut associatedUser. La variable user és de Test.
                            MainWindow mw = new MainWindow("PROFILE");
                            //TODO ALBA: a la BBDD teoricament esta complet (true) pero no ho llegeix be!!
                            //System.out.println(associatedUser.getUsername());
                            //System.out.println(associatedUser.isCompleted());
                            associatedUser.setCompleted(true);
                            //System.out.println(u.isCompleted());
                            //System.out.println(u.getAge());
                            //TODO: associatedUser es l'usuari de la bbdd, user es el de test
                            MenuController mc = new MenuController(mw, associatedUser);
                            mw.registraController(mc);
                            mw.setVisible(true);
                        } else {
                            MainWindow mw = new MainWindow("EDIT");
                            MenuController mc = new MenuController(mw, associatedUser);
                            mw.registraController(mc);
                            mw.setVisible(true);
                        }
                    }else{
                        w.showWarning("User or password not correct!");
                    }

                } catch (EmptyTextFieldException e1) {
                    w.showWarning(e1.getMessage());
                } catch (InterruptedException | IOException e1) {
                    w.showWarning("There has been a problem with the server communication.");
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
                    String hashedPassword = encoder.encode(passwords[0]);
                    associatedUser = new User(UserManager.fixSQLInjections(w.getSignUpUsername()), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), hashedPassword);

                    sc.startServerComunication(REGISTER_USER);
                    sc.join();

                    if(true/*correctRegister*/){ //TODO: Descomentar-ho si volem deixar de fer proves.
                        w.dispose();
                        MainWindow mw = new MainWindow("EDIT"); //Si, es mostra el perfil, pero pq s'ha de completar.
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

    @Override
    public void windowOpened(WindowEvent e) {
        //Not used.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //w.quitWindow(); //TODO: fer un optionPane que mostri un missatge de sortida
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
        return associatedUser;
    }

    /**
     * Metode que crea l'usuari amb les dades provinents de la BBDD sabent que existeix
     * @param u
     */
    public void setSignInUser(User u) {
        this.associatedUser = u;
    }

    public User getLoginUser() {
        return associatedUser;
    }

    public void setCorrectLogin(boolean b) {
        correctLogin = b;
    }

    public void setCorrectRegister(boolean existsR) {
        correctRegister = existsR;
    }
}
