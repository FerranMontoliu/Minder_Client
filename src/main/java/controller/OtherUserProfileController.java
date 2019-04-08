package controller;

import view.OtherUserProfilePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherUserProfileController implements ActionListener {
    private OtherUserProfilePanel otherUserProfilePanel;
    private MenuController menuController;

    //TODO: passar per parametre l'usuari que sesta mostrant actualment al panell i cridar a panel.updateInfo(user) per
    //TODO: a mostrar la info d'aquest
    public OtherUserProfileController(OtherUserProfilePanel otherUserProfilePanel, MenuController menuController) {
        this.otherUserProfilePanel = otherUserProfilePanel;
        this.menuController = menuController;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {  //TODO:Aqui cal fer la comprovacio del boolean del User isCompleted perque l'opcio de canviar de menu ha d'estar inhabilitada.
            case "BACK":
                System.out.println("back");
                menuController.goToConnectPanel();
                break;
            default:
                break;
        }
    }
}
