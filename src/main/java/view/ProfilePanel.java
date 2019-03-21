package view;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ProfilePanel(CardLayout clMainWindow){
        super(clMainWindow);
        //vull que sigui BorderLayout
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); //mostrarem els components vesrticalment
        includePhoto();
        includeUserInfo();
    }

    private void includeUserInfo() {
        JPanel jpUserInfo = new JPanel();

    }

    private void includePhoto() {

    }


}
