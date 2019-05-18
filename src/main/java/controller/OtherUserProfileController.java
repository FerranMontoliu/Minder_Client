package controller;

import view.ProfilePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del panell de perfil d'un altre usuari que no es el client.
 */
public class OtherUserProfileController implements ActionListener {
    private ProfilePanel otherUserProfilePanel;
    private MenuController menuController;

    /**
     * Constructor amb paramtres que permet controlar el panell de perfil d'altres usuaris des del compte de l'associat
     *
     * @param otherUserProfilePanel Panell d'informacio de l'usuari del que vull saber informacio com a associat
     * @param menuController controlador de l'aplicacio principal
     */
    public OtherUserProfileController(ProfilePanel otherUserProfilePanel, MenuController menuController) {
        this.otherUserProfilePanel = otherUserProfilePanel;
        this.menuController = menuController;
    }


    /**
     * Metode que permet reaccionar als esdeveniments provocats per els botons del panell de preferencies
     * @param e Accio que ve dels botons del panell
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals("BACK")){
            menuController.goToConnectPanel();
        }
    }
}
