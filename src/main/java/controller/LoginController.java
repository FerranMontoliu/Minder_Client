package controller;

import model.EmptyTextFieldException;
import model.User;
import model.UserManager;
import network.ServerComunicationLogin;
import view.LoginWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginController implements ActionListener, WindowListener {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private User u;
    private ServerComunicationLogin sc;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
     */
    public LoginController(LoginWindow w, ServerComunicationLogin sc) {
        this.w = w;
        this.u = null;
        this.sc = sc;
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
                    if(UserManager.mailInSignIn(w.getSignInUsername())){
                        //constructor amb el mail en comptes de username
                    }else{
                        //constructor amb el username com a username
                    }
                    u = new User(w.getSignInUsername(), w.getSignInPassword());
                    sc.startServerComunication(LOGIN_USER);
                    //Enviar dades al servidor i si aquestes són correctes tancar pestanya.

                    //El servidor retorna un usuari amb totes les dades completes tal que el codi a partir d'aquí seria així:
                    User user = new User(true, "Polete", "19", true, "polete@polete.polete", "Polete777", null, null, "", true, true, "Church Of Hell", null, null, null, null, null);

                    w.dispose();
                    if(user.isCompleted()) {
                        MainWindow mw = new MainWindow("CONNECT");
                        MenuController mc = new MenuController(mw, user);
                        mw.registraController(mc);
                        mw.setVisible(true);
                    } else {  //TODO: En principi no et fara SIGN IN un no completed perque sóbliga a completar quan es fa SIGN UP
                        MainWindow mw = new MainWindow("PROFILE"); //Si, es mostra el perfil, pero pq s'ha de completar.
                        MenuController mc = new MenuController(mw, user); //Potser estaria millor escriure EDIT i no PROFILE no?
                        mw.registraController(mc);
                        mw.setVisible(true);
                    }

                } catch (EmptyTextFieldException e1) {  //Focus??
                    w.showWarning(e1.getMessage());
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
                    u = new User(w.getSignUpUsername(), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), w.getSignUpPasswords()[0], null);

                    sc.startServerComunication(REGISTER_USER);
                    //Enviar dades al servidor i si aquestes són correctes tancar pestanya.

                    w.dispose();
                    MainWindow mw = new MainWindow("EDIT"); //Si, es mostra el perfil, pero pq s'ha de completar.
                    MenuController mc = new MenuController(mw, u); //Potser estaria millor escriure EDIT i no PROFILE no?
                    mw.firstEdition();
                    mw.registraController(mc);
                    mw.setVisible(true);
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
}
