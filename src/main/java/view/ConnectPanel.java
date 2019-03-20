package view;


import javax.swing.*;
import java.awt.*;

/**
 * Panell que es mostrarà en cas que el mode Connect estigui seleccionat, on l'usuari podra acceptar o refusar usuaris
 * en funcio de la seva imatge de perfil i informacio addicional a la que podra accedir prement el boto de mes informacio
 */
public class ConnectPanel extends JPanel {

    private JLabel lblProfilename;
    private JLabel lblProfilePic;
    private ImageIcon profileIcon;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ConnectPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); //mostrarem els components vesrticalment
        createUserSpace();
        createBottonsOptions();
    }

    /**
     * Metode que inicialitza els camps de l'usuari per a poder mostrar-los a partir de la base de dades
     */
    private void createUserSpace() {
        lblProfilename = new JLabel();
        add(lblProfilename);

        lblProfilePic = new JLabel();
        lblProfilePic.setSize(20, 20);
        add(lblProfilePic);

        showUser();
    }

    /**
     * Metode que crea el panell de botons inferiors per a donar Like, Dislike, o mostrar mes informacio
     */
    private void createBottonsOptions(){
        JPanel jpButtons = new JPanel();
        jpButtons.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon iDislike = new ImageIcon("icons/dislike.png");
        JButton jbDislike = new JButton(iDislike);

        jpButtons.add(jbDislike);

        ImageIcon iMoreInfo = new ImageIcon("icons/information.png");
        JButton jbMoreInfo = new JButton(iMoreInfo);

        jpButtons.add(jbMoreInfo);

        ImageIcon ilike = new ImageIcon("icons/like.png");
        JButton jbLike = new JButton(ilike);

        jpButtons.add(jbLike);

        add(jpButtons);

    }

    /**
     * TEMPORAL: metode que mostra l'usuari desitjat des del controlador per tal de mostrar les seves dades: Nom, edat,
     * i fotografia de perfil
     */
    //public void showUser(User user){
        //lblProfilename.setText(user.getUsername()+ " ," + user.getAge());
        //lblProfilePic.setIcon((Icon)user.getPhoto());
    public void showUser(){
        lblProfilename.setText("Pol Espurnes"+ " ," + "19");
        lblProfilename.setFont(new Font(Font.DIALOG,  Font.ROMAN_BASELINE, 15));
        lblProfilename.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfilename.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0));

        lblProfilePic.setIcon(new ImageIcon("Pictures/images.png"));
        lblProfilePic.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfilePic.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 40));
    }


}
