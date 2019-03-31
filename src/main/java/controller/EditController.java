package controller;

import model.User;
import view.EditPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditController implements ActionListener, MouseListener {
    private User associatedUser;
    private EditPanel editPanel;
    private MenuController menuController;

    /**
     * Constructor per parametres.
     * @param editPanel Panell d'edicio de perfil associat.
     * @param menuController controlador de Menu associat.
     * @param associatedUser usuari associat a l'edicio de perfil
     */
    public EditController(EditPanel editPanel, MenuController menuController, User associatedUser){
        this.editPanel = editPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch(actionCommand){
            case "SAVE":
                //associatedUser.update()
                break;
            case "CANCEL":
                menuController.cancelEdition();
                break;
        }
    }

    /**
     * Metode que escolta si han clicat a la Label per a canviar la imatge.
     * @param e MouseEvent associat.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("M'has clicat la imatge.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Not implemented.
    }
}
