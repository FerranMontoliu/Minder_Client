package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "SIGN-IN-JRB":
                break;

            case "SIGN-UP-JRB":
                break;

            case "SHOW-PW":
                break;

            case "LOG-IN":
                break;

            default:
                break;
        }
    }
}
