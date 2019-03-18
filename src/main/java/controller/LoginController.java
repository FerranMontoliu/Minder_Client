package controller;

import model.User;
import view.LoginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginWindow w;
    private User u;

    public LoginController(LoginWindow w) {
        this.w = w;
        this.u = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "SIGN-IN-JRB":
                w.changePanel("SIGN IN");
                break;

            case "SIGN-UP-JRB":
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
                if(w.getSingInUsername().isEmpty()) {
                    w.showWarning("El camp del nom no pot estar buit!");
                    w.getSignInPanel().getJtfUsername().requestFocus();
                    break;
                }
                if(w.getSignInPassword().isEmpty()) {
                    w.showWarning("El camp de password no pot estar buit!");
                    w.getSignInPanel().getJtfPassword().requestFocus();
                    break;
                }
                //Enviar dades al servidor i si aquestes són correctes tancar pestanya.
                break;

            case "SIGN UP":
                u = new User(w.getSingUpUsername(), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), w.getSignUpPasswords()[0], w.getSignUpPasswords()[1]);
                if(w.getSingUpUsername().isEmpty()) {
                    w.showWarning("El camp del nom no pot estar buit!");
                    w.getSignUpPanel().getJtfNewUsername().requestFocus();
                    break;
                }
                if(w.getSignUpPasswords()[0].isEmpty()) {
                    w.showWarning("El camp de password no pot estar buit!");
                    w.getSignUpPanel().getJtfNewPassword().requestFocus();
                    break;
                }
                if(!u.passwordCorrectFormat()) {
                    w.showWarning("La teva password ha de contenir al menys una majúscula, una minúscula, un número i tenir 8 o més caràcters!");
                    w.getSignUpPanel().getJtfNewPassword().requestFocus();
                    break;
                }
                if(w.getSignUpPasswords()[1].isEmpty()) {
                    w.showWarning("El camp de confirmació de password no pot estar buit!");
                    w.getSignUpPanel().getJtfNewPasswordConfirm().requestFocus();
                    break;
                }
                if(!u.passwordConfirm()) {
                    w.showWarning("El camp de confirmació de password no coincideix amb el de password!");
                    w.getSignUpPanel().getJtfNewPasswordConfirm().requestFocus();
                    break;
                }
                if(w.getSignUpEmail().isEmpty()) {
                    w.showWarning("El camp del mail no pot estar buit!");
                    w.getSignUpPanel().getJtfEmail().requestFocus();
                    break;
                }
                if(!u.mailCorrectFormat()) {
                    w.showWarning("El mail ha de tenir un format correcte!");
                    w.getSignUpPanel().getJtfEmail().requestFocus();
                    break;
                }
                if(w.getSignUpAgeField().isEmpty()) {
                    w.showWarning("El camp de l'edat no pot estar buit!");
                    w.getSignUpPanel().getJtfAge().requestFocus();
                    break;
                }
                if(!u.isAdult()) {
                    w.showWarning("L'edat ha de ser un número enter major a 17!");
                    w.getSignUpPanel().getJtfAge().requestFocus();
                    break;
                }
                //Enviar dades al servidor i si aquestes són correctes tancar pestanya.
                break;
        }
    }
}
