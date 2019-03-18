package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JMenuItem menuConnect;
    private JMenuItem menuChat;
    private JMenuItem menuLogout;
    private JMenuItem menuProfile;
    private String selected;

    public MainWindow(){
        createMenu();
        windowPreferences();
    }

    private void windowPreferences() {
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minder");
    }

    /**
     * Metode que serveix per generar la barra de menu superior que sera visible i accessible des de qualsevol finestra
     * de l'aplicacio
     */
    private void createMenu() {
        JMenuBar menuBar;
        menuBar = new JMenuBar();

        menuChat = new JMenuItem("Chat");

        menuConnect = new JMenuItem("Connect");
        selected = "CONNECT";

        menuProfile = new JMenuItem("Profile");

        menuLogout = new JMenuItem("Log Out");

        menuBar.setForeground(new Color(255, 88, 100));

        deselectLogout();
        deselectProfile();
        deselectChat();
        selectConnect();

        menuBar.add(menuChat);
        menuBar.add(menuConnect);
        menuBar.add(menuProfile);
        menuBar.add(menuLogout);

        getContentPane().add(menuBar, BorderLayout.PAGE_START);
    }

    /*private void setMinderBackground() {
        //menuChat.setForeground(Color.WHITE);
        menuChat.setBackground(new Color(255, 88, 100));

        //menuLogout.setForeground(Color.WHITE);
        menuLogout.setBackground(new Color(255, 88, 100));

        //menuConnect.setForeground(Color.WHITE);
        menuConnect.setBackground(new Color(255, 88, 100));

        //menuProfile.setForeground(Color.WHITE);
        menuProfile.setBackground(new Color(255, 88, 100));

        //submenuEditProfile.setForeground(Color.WHITE);

    }*/

    /*public void selectMenuItem(String item){
        switch (item){
            case "Connect":
                menuConnect.setForeground(Color.PINK);
                break;
            case "Messenger":
                menuChat.setForeground(Color.PINK);
                break;
            case "Profile":
                //menuProfile.setForeground(Color.PINK);
        break;
        default:
        menuLogout.setForeground(Color.PINK);
        break;

        }
    }*/

    /**
     * Metode que vincula events de cada un dels JMenuItem al controlador del menu
     * @param controller
     */
    public void registraController(MenuController controller) {
        menuChat.addActionListener(controller);
        menuChat.setActionCommand("CHAT");

        menuConnect.addActionListener(controller);
        menuConnect.setActionCommand("CONNECT");

        menuProfile.addActionListener(controller);
        menuProfile.setActionCommand("PROFILE");

        menuLogout.addActionListener(controller);
        menuLogout.setActionCommand("CONNECT");
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Chat no esta seleccionat
     */
    public void deselectChat(){
        menuChat.setIcon(new ImageIcon("data/chatLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Profile no esta seleccionat
     */
    public void deselectProfile(){
        menuProfile.setIcon(new ImageIcon("data/userLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Connect (la pagina principal) no esta seleccionat
     */
    public void deselectConnect(){
        menuConnect.setIcon(new ImageIcon("data/minderLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Logout no esta seleccionat
     */
    public void deselectLogout(){
        menuLogout.setIcon(new ImageIcon("data/logoutLight.png"));
    }

    /**
     * Es mostra la imatge activa del Chat i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectChat(){
        selected = "CHAT";
        menuChat.setIcon(new ImageIcon("data/chatDark.png"));

        deselectLogout();
        deselectProfile();
        deselectConnect();
    }

    /**
     * Es mostra la imatge activa del Profile i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectProfile(){
        selected = "PROFILE";
        menuProfile.setIcon(new ImageIcon("data/userDark.png"));

        deselectLogout();
        deselectChat();
        deselectConnect();
    }

    /**
     * Es mostra la imatge activa del Connect i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectConnect(){
        selected = "CONNECT";
        menuConnect.setIcon(new ImageIcon("data/minderDark.png"));

        deselectLogout();
        deselectChat();
        deselectProfile();
    }

    /**
     * Es mostra la imatge activa del Logout i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectLogout(){
        selected = "LOGOUT";
        menuLogout.setIcon(new ImageIcon("data/logoutDark.png"));

        deselectProfile();
        deselectChat();
        deselectConnect();
    }


    public boolean isSelected(String menu){
        return menu.equals(selected);
    }
}
