package view;

import model.User;

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

    public ConnectPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); //mostrarem els components vesrticalment
        createUserSpace();
        createBottonsOptions();
    }

    private void createUserSpace() {
        lblProfilename = new JLabel();
        add(lblProfilename);

        lblProfilePic = new JLabel();
        //profileIcon = new ImageIcon("Pictures/WhatsApp Image 2019-02-25 at 11.20.29.jpeg");
        //setIcon(new ImageIcon("data/chatLight.png"));
        //lblProfilePic.setIcon(profileIcon);

        lblProfilePic.setSize(20, 20);
        add(lblProfilePic);

    }
    private void createBottonsOptions(){
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.LINE_AXIS));


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
        //this.jPanel1.add(label);
        //this.setVisible(true);
        //this.jPanel1.revalidate();
        //this.repaint();
    }

    private void showUser(User user){
        lblProfilename.setText(user.getUsername()+ " ," + user.getAge());
        lblProfilePic.setIcon((Icon)user.getPhoto());
    }


}
