import controller.LoginController;
import controller.MenuController;
import view.LoginWindow;
import model.ClientConfig;
import model.Json;
import view.MainWindow;

import java.awt.*;

public class Main {

    /**
     * Mètode principal del programa
     *
     * @param args Paràmetres d'entrada del programa.
     */
    public static void main(String[] args) {
        ClientConfig cc = Json.parseJson();
        LoginWindow w = new LoginWindow();
        LoginController c = new LoginController(w);
        w.registrarControlador(c);
        MainWindow mw = new MainWindow();
        MenuController mc = new MenuController(mw);
        mw.registraController(mc);
        mw.setVisible(true);
        //w.setVisible(true);
    }
}
