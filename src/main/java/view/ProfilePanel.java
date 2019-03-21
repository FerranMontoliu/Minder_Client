package view;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JLabel jlName;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ProfilePanel(CardLayout clMainWindow){
        super(clMainWindow);
        includePhoto();
        includeUserInfo();
    }

    private void includeUserInfo() {
        JPanel jpUserInfo = new JPanel();
        JLabel jlNameTag = new JLabel("Name: ");
        jlName = new JLabel();


    }

    private void includePhoto() {

    }





}
