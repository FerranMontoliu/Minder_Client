package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que genera un JFrame per a poder fer logout de la compta de l'usuari associat (tant des de la opcio de Logout
 * com amb l'accio de tancar la finestra
 */
public class LogoutFrame extends JFrame {
    private JButton jbLogOut;
    private JButton jbCancel;
    private final Color BG_COLOR = new Color(173, 105, 127);
    private GridBagConstraints constraints;

    /**
     * Constructor sense parametres que inicialitza els components, layout i panells intermitjos del jframe
     */
    public LogoutFrame(){
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addHeader();
        addLogOutButton();
        addCancelButton();
        setBackground(new Color(94, 94, 94));
    }

    /**
     * Metode que crea la capcalera del JFrame amb una icone i un text en forma de titol interrogatiu
     */
    private void addHeader() {
        //Header: icone amb titol
        String header = "   Are you sure you want to log out of Minder?";
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 2, 5);
        constraints.fill = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(new ImageIcon("icons/exit.png"));
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);
    }

    /**
     * Metode que afegeix el boto de confirmacio de LogOut amb la seva icona
     */
    private void addLogOutButton() {
        jbLogOut = new JButton("LogOut", new ImageIcon("icons/logout_button.png"));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.SOUTHEAST;
        constraints.gridwidth = 2;
        constraints.ipady = 12;
        constraints.ipadx = 12;
        add(jbLogOut, constraints);


    }

    /**
     * Metode que afegeix el boto de cancel.lacio de Logout amb la seva icona
     */
    private void addCancelButton() {
        jbCancel = new JButton("Cancel", new ImageIcon("icons/cancel_16px.png"));
        constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.SOUTHWEST;
        constraints.gridwidth = 1;
        constraints.ipady = 12;
        constraints.ipadx = 12;
        add(jbCancel, constraints);
    }

    /**
     * Metode que vincula les actionsEvents que generen aquests dos botons del JFrame amb el controlador d'aquest panell
     *
     * @param menuController Controlador principal de la finestra sobre la qual actuen aquests botons
     */
    public void registerController(MenuController menuController){
        jbLogOut.addActionListener(menuController);
        jbLogOut.setActionCommand("YES LOGOUT");
        jbCancel.addActionListener(menuController);
        jbCancel.setActionCommand("NO LOGOUT");
    }

    /**
     * Metode que permet mostrar el Frame per sobre de les altres finestres
     *
     * @param location zona de la pantalla de l'usuari a la que te la finestra per a que se li obri al mateix lloc
     */
    public void showFrame(Point location){
            setSize(370,170);
            setAlwaysOnTop(true);
            setUndecorated(true);
            setLocation(location.x+25, location.y);
            setVisible(true);
    }

    /**
     * Metode que permet tancar aquest frame
     */
    public void hideFrame() {
        dispose();
    }
}