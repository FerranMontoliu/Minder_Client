package view;


import controller.ConnectController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Panell que es mostrarà en cas que el mode Connect estigui seleccionat, on l'usuari podra acceptar o refusar usuaris
 * en funcio de la seva imatge de perfil i informacio addicional a la que podra accedir prement el boto de mes informacio
 */
public class ConnectPanel extends JPanel {

    private JLabel lblProfilename;
    private JLabel lblProfilePic;
    private JButton jbDislike;
    private JButton jbMoreInfo;
    private JButton jbLike;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ConnectPanel(CardLayout clMainWindow){
        super(clMainWindow);
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); //mostrarem els components vesrticalment
        createUserSpace();
        createBottonsOptions();

        this.setBackground(new Color (173, 105, 127));
    }

    /**
     * Metode que inicialitza els camps de l'usuari per a poder mostrar-los a partir de la base de dades
     */
    private void createUserSpace() {
        JPanel jpUserSpace = new JPanel();
        lblProfilename = new JLabel();
        jpUserSpace.add(lblProfilename);

        lblProfilePic = new JLabel();
        lblProfilePic.setSize(20, 20);
        jpUserSpace.add(lblProfilePic);
        jpUserSpace.setBackground(new Color(231, 165, 187));

        add(jpUserSpace, BorderLayout.PAGE_START);
        showUser();
    }

    /**
     * Metode que crea el panell de botons inferiors per a donar Like, Dislike, o mostrar mes informacio
     */
    private void createBottonsOptions(){
        JPanel jpButtons = new JPanel();
        jpButtons.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon iDislike = new ImageIcon("icons/cancel.png");
        jbDislike = new JButton(iDislike);

        jpButtons.add(jbDislike);

        ImageIcon iMoreInfo = new ImageIcon("icons/infoBlue.png");
        jbMoreInfo = new JButton(iMoreInfo);

        jpButtons.add(jbMoreInfo);

        ImageIcon ilike = new ImageIcon("icons/checked.png");
        jbLike = new JButton(ilike);

        jpButtons.add(jbLike);

        add(jpButtons, BorderLayout.SOUTH);

        jpButtons.setBackground(new Color(202, 123, 148));

    }

    /**
     * TEMPORAL: metode que mostra l'usuari desitjat des del controlador per tal de mostrar les seves dades: Nom, edat,
     * i fotografia de perfil
     */
    //public void showUser(User user){
        //lblProfilename.setText(user.getUsername()+ " ," + user.getAge());
        //lblProfilePic.setIcon((Icon)user.getPhoto());
    public void showUser(){
        JPanel jpUserImage = new JPanel();
        jpUserImage.setLayout(new BoxLayout(jpUserImage, BoxLayout.PAGE_AXIS));
        lblProfilename.setText("Pol Espurnes"+ " ," + "19");
        lblProfilename.setFont(new Font(Font.DIALOG,  Font.ROMAN_BASELINE, 15));
        lblProfilename.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProfilename.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfilename.setVerticalAlignment(SwingConstants.CENTER);
        lblProfilename.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 100));
        jpUserImage.add(lblProfilename);
        lblProfilePic.setIcon(new ImageIcon("Pictures/images.png"));
        lblProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProfilePic.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfilePic.setVerticalAlignment(SwingConstants.CENTER);
        lblProfilePic.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 130));
        add(jpUserImage, BorderLayout.CENTER);
    }

    public void registraController(ConnectController controller) {
        jbDislike.addActionListener(controller);
        jbDislike.setActionCommand("DISLIKE");

        jbLike.addActionListener(controller);
        jbLike.setActionCommand("LIKE");

        jbMoreInfo.addActionListener(controller);
        jbMoreInfo.setActionCommand("INFO");
    }


}
