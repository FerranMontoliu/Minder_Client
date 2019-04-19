import controller.LoginController;
import network.ServerComunicationLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import view.LoginWindow;

import javax.swing.*;

public class Main {

    /**
     * Mètode principal del programa
     *
     * @param args Paràmetres d'entrada del programa.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Crea la vista i el controlador de la pestanya de login/registre i els vincula:
            LoginWindow w = new LoginWindow();
            LoginController c = new LoginController(w);
            w.registrarControlador(c);
            w.setVisible(true);
            //TODO ALBA: Comprovacio hash contrassenya mal funcionament
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("Minder19"));
        });
    }
}
