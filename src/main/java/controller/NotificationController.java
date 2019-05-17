package controller;


import view.NotificationPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que controla el PopUp que conte la notificacio de "Tens nous matches!"
 */
public class NotificationController implements ActionListener {
    private NotificationPopUp notificationView;
    private MenuController menuController;

    /**
     * Constructor que vincula aquesta classe amb la vista que controla i el controlador de la pantalla principal, per a
     * poder anar a la finestra dels chats en cas que aixi ho vulgui l'usuari.
     *
     * @param notificationView vista del PopUp
     * @param menuController controlador de la pantalla principal de l'aplicacio
     */
    public NotificationController(NotificationPopUp notificationView, MenuController menuController){
        this.notificationView = notificationView;
        this.menuController = menuController;
    }

    /**
     * Metode que gestiona els events generats per les tres fonts de la vista: Els dos botons de "Go to Messages" i "Skip",
     * i la interrupcio generada pel Timer passats 4 segons.
     *
     * @param e accio
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":
                //Vull anar al panell de chats
                notificationView.stopNotification();
                notificationView.closePopUp();
                menuController.goToChatPanel();
                break;
            case "SKIP":
               //Ja he vist la notificacio, vull amagar-la
                notificationView.stopNotification();
                notificationView.closePopUp();
                break;
            case "TIME":
                //Ja han passat els 2 segons, amago la notificaio
                notificationView.closePopUp();
                break;
        }
    }
}

