package controller;

import controller.MenuController;
import view.NotificationPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationController implements ActionListener {
    private NotificationPopUp notificationView;
    //private User[] associatedUsers;
    private MenuController menuController;

    public NotificationController(NotificationPopUp notificationView, MenuController menuController){
        this.notificationView = notificationView;
        /*this.associatedUsers = new User[2];
        this.associatedUsers[0] = associatedUser;
        this.associatedUsers[1] = matchedUser;
        */
        this.menuController = menuController;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":
                System.out.println("vull anar a veure els chats (estic al NotificationController) ");
                notificationView.closeNotification();
                menuController.goToChatPanel();
                break;
            case "SKIP":
                System.out.println("skip popup");
                notificationView.closeNotification();
                break;
        }
    }
}

