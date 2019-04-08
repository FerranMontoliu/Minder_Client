package view;

import controller.MenuController;
import model.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JLabel jlName;
    private JLabel jlPhoto;
    private JLabel jlAge;
    private JLabel jlDescription;
    private JLabel jlFavHobbies;
    private JLabel jlFavProgramming;
    private JButton jbEditProfile;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ProfilePanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BorderLayout());
        createPhoto();
        createUserInfo();
        createEditButton();
    }

    /**
     * Metode que crea i col·loca el JButton d'edicio de perfil.
     */
    private void createEditButton() {
        JPanel jpButton = new JPanel(new FlowLayout());
        jbEditProfile = new JButton("Edit Profile", new ImageIcon("icons/edit_profile_icon.png"));
        jbEditProfile.setBackground(Color.WHITE);
        jpButton.add(jbEditProfile);

        add(jpButton, BorderLayout.SOUTH);
    }

    /**
     * Metode que es crida des del constructor per a crear els jlabels fixats ("Name: ", "Age: ", "Hobbies: ")
     * i crear (Sense fixar cap valor) per la informacio de l'usuari que acompanyara els jLabels anteriors. Aquesta info
     * s'incloura al metode includeUserInfo on se li passara la informacio de l'usuari en concret
     */
    private void createUserInfo() {
        //TODO: omplir els camps amb la informacio de l'usuari en questio

        TitledBorder border = new TitledBorder("Basic Information");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel jpUserInfo = new JPanel();
        jpUserInfo.setLayout(new BoxLayout(jpUserInfo, BoxLayout.PAGE_AXIS));
        jpUserInfo.setBorder(border);

        jlDescription = new JLabel("Description:"); //omplire amb la informacio de l'usuari
        jlDescription.setIcon(new ImageIcon("icons/DescriptionIcon.png"));
        jlDescription.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlDescription);

        jlFavHobbies = new JLabel("Favorite Hobbies:"); //omplire amb la informacio de l'usuari
        jlFavHobbies.setIcon(new ImageIcon("icons/HobbiesIcon.png"));
        jlFavHobbies.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlFavHobbies);

        jlFavProgramming = new JLabel("Favorite programming language:"); //omplire amb la informacio de l'usuari
        jlFavProgramming.setIcon(new ImageIcon("icons/programming-code.png"));
        jlFavProgramming.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlFavProgramming);


        add(jpUserInfo, BorderLayout.CENTER);


    }

    /**
     * Metode que es crida des del constructor per a crear el jlabel que contindra la foto principal, sense incloure cap
     * imatge, ja que aquesta nomes es posara quan es cridi a la funcio IncludePhoto amb la info de l'usuari
     */
    private void createPhoto() {
        //TODO: aquest metode nomes crea els jlabels per a que a la funcio de AddUserInfo s'atualitzi la informacio de les
        //TODO: etiquetes
        JPanel jpUserPhoto = new JPanel();
        jpUserPhoto.setLayout(new BoxLayout(jpUserPhoto, BoxLayout.PAGE_AXIS));
        jlPhoto = new JLabel(new ImageIcon("Pictures/user-64px.png"));
        jlPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlPhoto.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserPhoto.add(jlPhoto);

        jlName = new JLabel(); //omplire amb la informacio de l'usuari
        jlName.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        jlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlName.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));

        jlAge = new JLabel();
        jlAge.setFont(new Font(Font.DIALOG,  Font.PLAIN, 10));
        jlAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlAge.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));

        jpUserPhoto.add(jlName);
        jpUserPhoto.add(jlAge);
        jpUserPhoto.setBackground(new Color(202, 123, 148));
        add(jpUserPhoto, BorderLayout.NORTH);

    }

    /**
     * Metode que registra un ActionListener al JButton d'edicio de perfil
     */
    public void registraController(MenuController mc){
        jbEditProfile.addActionListener(mc);
        jbEditProfile.setActionCommand("EDIT");
    }

    /**
     * Metode que actualitza la informacio de l'usuari segons la informacio que se li passa com a parametre, de tal
     * manera que omple els camps del perfil amb aquesta informacio
     * @param user: usuari que ha iniciat sessio i que vol accedir a la seva informacio
     */
    public void updateInfo(User user){
        //TODO: cridar la funcio des del controller amb l'usuari per paramtre
        jlName.setText("Pol Rayos");
        jlAge.setText("19");
        jlFavProgramming.setText ("Java");
        jlFavHobbies.setText("Destrossar el github, Fer cafès");
        jlDescription.setText("M'agrada passar hores al lab de compus");
    }

}
