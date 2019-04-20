import controller.LoginController;
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

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String existingPassword = passwordEncoder.encode("Minder19"); // Password entered by user
            String dbPassword       =  "$2a$10$Rbmxa1Y2Z7eZ07qAcgt84edrIpBxULv6emOxcbQV7MjzMCDMRVYWq";// Load hashed DB password

            if (passwordEncoder.matches(existingPassword, dbPassword)) {
                // Encode new password and store it
                System.out.println("equals");
            } else {
                System.out.println("error");
            }

            //altre metode que es pot/hauria de fer des del servidor

        });
    }
    /**
     * Check if the old password match the registered password
     *
     * @param id            id of the user with the password
     * @param oldPassword   The password given to the system by a user
     * @return              True if the password is incorrect, else it's false
     * @throws Exception    Database errors
     */
    private void incorrectOldPassword(String id, String oldPassword) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //User user = dao.getUserById(id);
        //return !encoder.matches(oldPassword, user.getPassword());
    }

    /**
     * Takes the id of a potential User and returns the User object. If user does not exist null
     * is returned.
     *
     * @param id    ObjectId of the user in the database
     * @return      A User object with matching id as argument
     */
    //public User getUserById(String id) {
      //  MongoCollection collection = db.getClient().getCollection("users");
        //return collection.findOne(new ObjectId(id)).as(User.class);
    //}

}
