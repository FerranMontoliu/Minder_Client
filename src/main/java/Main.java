import controller.LoginController;
import view.LoginWindow;

import javax.swing.*;

public class Main {

    /**
     * Metode principal del programa
     *
     * @param args Parametres d'entrada del programa.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Crea la vista i el controlador de la pestanya de login/registre i els vincula:
            LoginWindow w = new LoginWindow();
            LoginController c = new LoginController(w);
            w.registrarControlador(c);
            w.setVisible(true);
        });
    }

}
