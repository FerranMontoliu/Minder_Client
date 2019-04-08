package view;

import controller.OtherUserProfileController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OtherUserProfilePanel extends JPanel {
    private JLabel jlName;
    private JLabel jlPhoto;
    private JLabel jlAge;
    private JLabel jlDescription;
    private JLabel jlFavHobbies;
    private JLabel jlFavProgramming;
    private JButton jbBack;

    public OtherUserProfilePanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BorderLayout()); //mostrarem els components vesrticalment
        createBackButton();
        createPhoto();
        createUserInfo();
        this.setBackground(new Color(231, 165, 187));
    }

    /**
     * Metode que crea el i component de la imatge que s'omplira amb la informacio de l'usuari a mostrar
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

        jlName = new JLabel("Pol Rayos"); //omplire amb la informacio de l'usuari
        jlName.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        jlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlName.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));

        jlAge = new JLabel("19");
        jlAge.setFont(new Font(Font.DIALOG,  Font.PLAIN, 10));
        jlAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlAge.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));

        jpUserPhoto.add(jlName);
        jpUserPhoto.add(jlAge);
        jpUserPhoto.setBackground(new Color(202, 123, 148));
        add(jpUserPhoto, BorderLayout.PAGE_START);

    }

    /**
     * Metode que crea els camps i components que posteriorment s'omplirant amb informacio d'usuaris
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
     * Metode que crea el button que permet tornar al connect panel
     */
    private void createBackButton() {
        JPanel jpButton = new JPanel(new BorderLayout());
        jbBack = new JButton("Return", new ImageIcon("icons/back-arrow.png"));
        jpButton.add(jbBack, BorderLayout.WEST);
        jpButton.setBackground(new Color(202, 123, 148));
        add(jpButton, BorderLayout.SOUTH);
    }

    public void registraController(OtherUserProfileController controller){
        jbBack.addActionListener(controller);
        jbBack.setActionCommand("BACK");
    }
}

