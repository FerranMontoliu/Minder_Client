import controller.LoginController;
import network.ServerComunicationLogin;
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
            ServerComunicationLogin sc = new ServerComunicationLogin(w);
            LoginController c = new LoginController(w, sc);
            w.registrarControlador(c);
            w.setVisible(true);
        });
    }
}
