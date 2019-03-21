package controller;

import view.ConnectPanel;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectController implements ActionListener {
    private ConnectPanel connectPanel;
    public ConnectController(ConnectPanel connectPanel) {
        this.connectPanel = connectPanel;
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
                //MOSTRAR LA INFO DE L'USUARI!!!!!
                break;

            default:
                break;
        }
    }
}
