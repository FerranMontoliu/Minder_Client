package controller;

import model.EmptyTextFieldException;
import model.InvalidFormatException;
import model.UserManager;
import model.entity.User;
import network.ServerCommunicationPreferences;
import network.ServerComunicationEdit;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import view.EditPanel;
import view.PreferencesPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controlador encarregat del panell d'edicio de preferecies d'usuari.
 */
public class PreferencesController implements ActionListener {
    private static final char EDIT_PREFERENCES = 'l';
    private static final char CHECK_USER = 'a'; //IMPLEMENTACIO IGUAL A LOGIN_USER

    private PreferencesPanel preferencesPanel;
    private MenuController menuController;
    private User associatedUser;
    private boolean editResult;
    private boolean correctLogin;
    private ServerCommunicationPreferences sc;

    /**
     * Constructor per parametres.
     *
     * @param preferencesPanel Panell d'edicio de preferencies del compte
     * @param menuController controlador de Menu associat.
     * @param associatedUser usuari associat al compte iniciat
     */
    public PreferencesController(PreferencesPanel preferencesPanel, MenuController menuController, User associatedUser){
        this.preferencesPanel = preferencesPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
        this.editResult = false;
        sc = new ServerCommunicationPreferences(this);
    }

    /**
     * Metode que permet reaccionar als events provoctats per els botons del panell de preferencies
     *
     * @param e Accio que ve dels botons del panell
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean editingPassword;

        switch (actionCommand){
            case "NO FILTER":
                //En cas de que l'usuari no vulgui filtre, deshabilitare els desplegables i actualitzare l'edat maxima a 0
                if (preferencesPanel.noFilterChecked()){
                    preferencesPanel.disableFilter();
                    updateMaxAgeNoFilter();
                }else{
                    preferencesPanel.enableFilter();
                }

                break;
            case "SAVE":

                try {
                    //Potser l'usuari no vol canviar la contrassenya i vol editar algun altre camp. En aquest cas, mirem
                    //si la current password esta buida i una de les altres esta plena, ja que voldra dir que vol canviar
                    //la contrassenya pero la current no estara be (esta buida). En canvi, si les tres estan buides vol dir
                    //que no la vol canviar i per tant no hem de comprovar si es la seva contrassenya actual

                    if(preferencesPanel.getCurrentPassword().isEmpty() && preferencesPanel.getNewPassword().isEmpty()
                            && preferencesPanel.getNewPasswordConfirm().isEmpty()){
                        editingPassword = false;
                    }else{
                        UserManager.isEmpty(preferencesPanel.getCurrentPassword(), "password");
                        sc.startServerComunication(CHECK_USER); //aixo em diu si correct login o no (mira la contrassenya
                        editingPassword = true;
                    }
                    //Si estic editant modificant la contrassenya i la he escrit be, llavors actualitzare la nova contrassenya
                    //a la database (en cas que coincideixi amb le confirm passwors)
                    if (correctLogin && editingPassword) {
                        String newPassword = preferencesPanel.getNewPassword();
                        String newConfirmPassword = preferencesPanel.getNewPasswordConfirm();
                        UserManager.isEmpty(newPassword, "password");
                        UserManager.signUpPasswordIsCorrect(newPassword, newConfirmPassword);
                        String hashedPassword = encoder.encode(newPassword);

                        associatedUser.savePreferencesUpdate(hashedPassword, preferencesPanel.getIsPremium(), preferencesPanel.getMinAge(), preferencesPanel.getMaxAge(), preferencesPanel.noFilterChecked());
                    }

                    //Si no ha escrit be la contrassenya actual, mostrare un avis
                    if(!correctLogin && editingPassword){
                        preferencesPanel.showWarning("Current Password incorrect. Please, try again");
                        return;
                    }

                    //Si no vull editar la contrassenya, actualitzare els altres camps, que no necessiten autentificacio previa
                    if(!editingPassword){
                        associatedUser.savePreferencesUpdate(associatedUser.getPassword(), preferencesPanel.getIsPremium(), preferencesPanel.getMinAge(), preferencesPanel.getMaxAge(), preferencesPanel.noFilterChecked());
                    }
                        //Aqui ja tinc a l'associatedUser amb els parametres a canviar
                        sc.startServerComunication(EDIT_PREFERENCES);
                        associatedUser.setCompleted(true);

                        if(editResult){
                            //nomes s'ha d'actualitzar el nou usuari associat
                            //no tindrem problema amb el connect user ja que es prepara el seguent en el moment en que demana, un altre usuari
                            //per tant, ja s'agafara el correcte
                            menuController.editionCompleted(associatedUser);
                        }else{
                            preferencesPanel.showWarning("There has been a problem with the server communication.");
                        }

                } catch (EmptyTextFieldException e1) {
                    preferencesPanel.showWarning(e1.getMessage());
                } catch (InvalidFormatException e2) {
                    preferencesPanel.showWarning("New Password and New Password Confirm do not match");
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                break;

            case "CANCEL":
                menuController.cancelPreferences();
                break;
        }
    }

    /**
     * Metode que actualitza el camp.
     */
    private void updateMaxAgeNoFilter() {
        associatedUser.setMaxAge(0);
    }

    /**
     * Getter de l'usuari associat a la compta iniciada a la finestra actual.
     * @return usuari associat.
     */
    public User getAssociatedUser(){
        return associatedUser;
    }

    /**
     * Setter del parametre editResult.
     * @param editResult que ens indica si l'usuari s'ha pogut editar correctament o no.
     */
    public void setEditResult(boolean editResult){
        this.editResult = editResult;
    }

    /**
     * Setter del parametre correctLogin.
     * @param b que indica si el login es correcte o no.
     */
    public void setCorrectLogin(boolean b) {
        this.correctLogin = b;
    }

    /**
     * Setter de l'usuari associat a aquest compte.
     * @param u sign in user.
     */
    public void setSignInUser(User u) {
        this.associatedUser = u;
    }

    /**
     * Metode que genera un nou usuari amb els camps minims completats que son el seu nom i la seva contrassenya, rebuts del
     * panell de log in.
     * @return Usuari amb usuari i contrassenya
     */
    public User getChekingUser() {
        return new User(associatedUser.getUsername(), preferencesPanel.getCurrentPassword());
    }

    /**
     * Metode que fa mostrar un missatge per pantalla indicant el correcte edit
     */
    public void showEditOk() {
        preferencesPanel.showSuccess("Account preferences updated");
    }
}
