import controller.LoginController;
import view.LoginWindow;
import model.ClientConfig;
import model.Json;

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
        w.setVisible(true);
    }
}
