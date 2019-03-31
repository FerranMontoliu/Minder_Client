package controller;

import model.User;
import view.LoginWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginWindow w;
    private User u;

    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
     */
    public LoginController(LoginWindow w) {
        this.w = w;
        this.u = null;
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
                u = new User(w.getSingInUsername(), w.getSignInPassword());
                if(u.getUsername().isEmpty()) {
                    w.showWarning("El camp del nom no pot estar buit!");
                    w.focusUserIn();
                    break;
                }
                if(u.getPassword().isEmpty()) {
                    w.showWarning("El camp de password no pot estar buit!");
                    w.focusPasswordIn();
                    break;
                }
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
                    MenuController mc = new MenuController(mw, user);
                    mw.registraController(mc);
                    mw.setVisible(true);
                }
                break;

            case "SIGN UP":
                u = new User(w.getSingUpUsername(), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), w.getSignUpPasswords()[0], w.getSignUpPasswords()[1]);
                if(u.getUsername().isEmpty()) {
                    w.showWarning("El camp del nom no pot estar buit!");
                    w.focusUserUp();
                    break;
                }
                if(u.getPassword().isEmpty()) {
                    w.showWarning("El camp de password no pot estar buit!");
                    w.focusPasswordUp();
                    break;
                }
                if(!u.passwordCorrectFormat()) {
                    w.showWarning("La teva password ha de contenir al menys una majúscula, una minúscula, un número i tenir 8 o més caràcters!");
                    w.focusPasswordUp();
                    break;
                }
                if(u.getPasswordConfirmation().isEmpty()) {
                    w.showWarning("El camp de confirmació de password no pot estar buit!");
                    w.focusPasswordConfirmUp();
                    break;
                }
                if(!u.passwordConfirm()) {
                    w.showWarning("El camp de confirmació de password no coincideix amb el de password!");
                    w.focusPasswordConfirmUp();
                    break;
                }
                if(u.getMail().isEmpty()) {
                    w.showWarning("El camp del mail no pot estar buit!");
                    w.focusMailUp();
                    break;
                }
                if(!u.mailCorrectFormat()) {
                    w.showWarning("El mail ha de tenir un format correcte!");
                    w.focusMailUp();
                    break;
                }
                if(w.getSignUpAgeField().isEmpty()) {
                    w.showWarning("El camp de l'edat no pot estar buit!");
                    w.focusAgeUp();
                    break;
                }
                try {
                    boolean a = u.isAdult();
                    if(!a) {
                        w.showWarning("L'edat ha de ser un número enter major a 17!");
                        w.focusAgeUp();
                        break;
                    }
                } catch(NumberFormatException e1) {
                    w.showWarning("L'edat ha de ser un número enter!");
                    w.focusAgeUp();
                    break;
                }
                //Enviar dades al servidor i si aquestes són correctes tancar pestanya.
                w.cleanSignInPanel();
                w.changePanel("SIGN IN");
                break;
        }
    }
}
