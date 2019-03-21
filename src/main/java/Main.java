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
        //Configuració inicial del client:
        ClientConfig cc = Json.parseJson();

        //Crea la vista i el controlador de la pestanya de login/registre i els vincula:
        LoginWindow w = new LoginWindow();
        LoginController c = new LoginController(w);
        w.registrarControlador(c);
        w.setVisible(true);
    }
}
