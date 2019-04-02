package controller;

import view.ConnectPanel;
import view.MainWindow;
import view.MatchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectController implements ActionListener {
    private ConnectPanel connectPanel;
    //private MatchPanel matchPanel;
    private MenuController menuController;


    public ConnectController(ConnectPanel connectPanel, MenuController menuController) {
        this.connectPanel = connectPanel;
        this.menuController = menuController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "DISLIKE":
                System.out.println("I don't like you..");
                break;

            case "LIKE":
                System.out.println("I like you!");
                //si hi ha match
                menuController.showMatch();
                break;

            case "INFO":
                System.out.println("I like trains");
                //mostra la info de l'usuari
                break;

            default:
                break;
        }
    }
}
