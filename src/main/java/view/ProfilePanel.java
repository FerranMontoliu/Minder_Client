package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JLabel jlName;
    private JLabel jlPhoto;
    private JLabel jlAge;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ProfilePanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BorderLayout());
        createPhoto();
        createUserInfo();
    }

    /**
     * Metode que es crida des del constructor per a crear els jlabels fixats ("Name: ", "Age: ", "Hobbies: ")
     * i crear (Sense fixar cap valor) per la informacio de l'usuari que acompanyara els jLabels anteriors. Aquesta info
     * s'incloura al metode includeUserInfo on se li passara la informacio de l'usuari en concret
     */
    private void createUserInfo() {

        TitledBorder border = new TitledBorder("Basic Information");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpUserInfo = new JPanel();
        jpUserInfo.setLayout(new BoxLayout(jpUserInfo, BoxLayout.PAGE_AXIS));
        jpUserInfo.setBorder(border);

        jlName = new JLabel("Name: Alba"); //omplire amb la informacio de l'usuari
        jpUserInfo.add (jlName);

        jlAge = new JLabel("Age: 19");
        jpUserInfo.add(jlAge);
        jpUserInfo.setSize(jpUserInfo.getPreferredSize());
        add(jpUserInfo, BorderLayout.CENTER);


    }

    /**
     * Metode que es crida des del constructor per a crear el jlabel que contindra la foto principal, sense incloure cap
     * imatge, ja que aquesta nomes es posara quan es cridi a la funcio IncludePhoto amb la info de l'usuari
     */
    private void createPhoto() {
        JPanel jpUserPhoto = new JPanel();
        jpUserPhoto.setLayout(new BoxLayout(jpUserPhoto, BoxLayout.PAGE_AXIS));
        jlPhoto = new JLabel(new ImageIcon("Pictures/espies.jpg"));
        jlPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        jlPhoto.setVerticalAlignment(SwingConstants.CENTER);
        jlPhoto.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserPhoto.add(jlPhoto);

        jlName = new JLabel("Pol Rayos"); //omplire amb la informacio de l'usuari
        //jlName.setHorizontalTextPosition(SwingConstants.CENTER);
        jlName.setHorizontalAlignment(SwingConstants.CENTER);
        //jlName.setVerticalAlignment(SwingConstants.CENTER);
        jlName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        jpUserPhoto.setAlignmentX(CENTER_ALIGNMENT);
        jpUserPhoto.add(jlName);
        add(jpUserPhoto, BorderLayout.NORTH);


    }





}
