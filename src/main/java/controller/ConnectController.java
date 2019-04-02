package controller;

import view.ConnectPanel;
import view.MainWindow;
import view.MatchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectController implements ActionListener {
    private ConnectPanel connectPanel;
    //private MatchPanel matchPanel;
    private MatchController matchController;

    public ConnectController(ConnectPanel connectPanel) {
        this.connectPanel = connectPanel;
        //this.matchController = new MatchController();
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
                break;

            case "INFO":
                System.out.println("I like trains");
                connectPanel.matchDesign();//MOSTRAR LA INFO DE L'USUARI!!!!!
                break;

            default:
                break;
        }
    }
}
