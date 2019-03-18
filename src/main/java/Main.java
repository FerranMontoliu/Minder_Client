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
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
        //Pol soplapolles.
        //Pol tio ets super tonto.
    }
}
