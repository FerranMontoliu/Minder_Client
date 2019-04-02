package controller;

import model.EmptyTextFieldException;
import model.User;
import model.UserManager;
import network.ServerComunication;
import view.LoginWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginController implements ActionListener, WindowListener {

    private LoginWindow w;
    private User u;
    private ServerComunication sc;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
     */
    public LoginController(LoginWindow w, ServerComunication sc) {
        this.w = w;
        this.u = null;
        this.sc = sc;
        sc.startServerComunication();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "SIGN-IN-JRB":
                w.cleanSignInPanel();
                w.changePanel("SIGN IN");
                break;

            case "SIGN-UP-JRB":
                w.cleanSignUpPanel();
                w.changePanel("SIGN UP");
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
                    u = new User(w.getSignInUsername(), w.getSignInPassword());
                    //Enviar dades al servidor i si aquestes són correctes tancar pestanya.
                    //El servidor retorna un usuari amb totes les dades completes tal que el codi a partir d'aquí seria així:
                    User user = new User(false, "Polete", "19", true, "polete@polete.polete", "Polete777", "Polete777", null, "", true, true, "Church Of Hell", null, null, null, null, null);
                    w.dispose();
                    if(user.isCompleted()) {
                        MainWindow mw = new MainWindow("CONNECT");
                        MenuController mc = new MenuController(mw, user);
                        mw.registraController(mc);
                        mw.setVisible(true);
                    } else {
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
                    u = new User(w.getSignUpUsername(), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), w.getSignUpPasswords()[0], w.getSignUpPasswords()[1]);

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
