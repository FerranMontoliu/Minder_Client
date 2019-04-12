package controller;

import model.entity.User;
import network.ServerComunicationChat;
import view.MainWindow;
import view.NotificationPopUp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

public class MenuController implements ActionListener, WindowListener {
    private static final char USER_MATCH_LIST = 'h';

    private User associatedUser;
    private MainWindow mainWindow;
    private ConnectController connectController;
    private EditController editController;
    private ChatController chatController;
    private MatchController matchController;
    private ProfileController profileController;
    private OtherUserProfileController otherUserProfileController;
    private ServerComunicationChat serverComunicationChat;
    private LinkedList<String> matchedUsernames;


    public MenuController(MainWindow mainWindow, User associatedUser) {
        this.mainWindow = mainWindow;
        this.associatedUser = associatedUser;
        this.matchedUsernames = new LinkedList<>();

        connectController = new ConnectController(mainWindow.getConnect(), this);  //Aixo trenca paradigmes??
        editController = new EditController(mainWindow.getEdit(), this, this.associatedUser);
        chatController = new ChatController(mainWindow.getChat(), associatedUser);
        matchController = new MatchController(mainWindow.getMatch(), this, connectController, this.associatedUser);
        profileController = new ProfileController(mainWindow.getProfile());
        otherUserProfileController = new OtherUserProfileController(mainWindow.getOtherUserProfile(), this);

        mainWindow.registraConnectController(connectController);
        mainWindow.registraChatController(chatController);
        mainWindow.registraEditController(editController);
        mainWindow.registraMatchController(matchController);
        mainWindow.registraOtherProfileController(otherUserProfileController);
        updateNotifications();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {  //TODO:Aqui cal fer la comprovacio del boolean del User isCompleted perque l'opcio de canviar de menu ha d'estar inhabilitada.
            case "CHAT":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("CHAT")) {
                        //TODO: Descomentar la Comunicacio quan tot funcioni
                        //serverComunicationChat = new ServerComunicationChat(this, chatController);
                        //serverComunicationChat.startServerComunication(USER_MATCH_LIST);
                        //serverComunicationChat.join();

                        mainWindow.generateMatchList(matchedUsernames);
                        mainWindow.selectChat();
                        mainWindow.changePanel("CHAT");
                        chatController.runDefaultAppearance();

                    }
                }
                break;

            case "CONNECT":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("CONNECT")) {
                        mainWindow.selectConnect();
                        mainWindow.changePanel("CONNECT");
                    }
                }
                break;

            case "PROFILE":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("PROFILE")) {
                        mainWindow.selectProfile();
                        mainWindow.changePanel("PROFILE");
                        profileController.showUser(associatedUser);
                    }
                }
                break;

            case "LOGOUT":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("LOGOUT")) {
                        mainWindow.selectLogout();
                        mainWindow.changePanel("LOGOUT");

                    }
                }
                break;

            case "EDIT":
                mainWindow.changePanel("EDIT");
                //TODO: Aquestes variables realment les hauria d'agafar del atribut associatedUser, però de moment són proves
                Image profilePicture = null;
                String userDescription = "hola";
                boolean java = true;
                boolean c = false;
                String song = "Frozen";
                String hobbies = null;
                mainWindow.initiateEdit(profilePicture, userDescription, java, c, song, hobbies);
                //mainWindow.setSelectedImage(associatedUser.getImage(), associatedUser.getDescription()...);
                break;
            case "YES LOGOUT":
                //TODO: TANCAR COMUNICACIO DE SERVIDOR I MERDES VARIES
                mainWindow.dispose();
                break;
            case "NO LOGOUT":
                mainWindow.selectConnect();
                mainWindow.changePanel("CONNECT");
                break;

        }
    }

    /**
     * Metode que cancela la edicio de perfil i es retorna al panell Profile.
     */
    public void cancelEdition() {
        mainWindow.changePanel("PROFILE");
    }

    /**
     * Metode que tanca la pestanya informativa de "It's a match" i torna al connect panel
     */
    public void closeMatch() { mainWindow.changePanel("CONNECT");
    }

    /**
     * Metode que permet obrir directament un chat en concret entre dues persones (el primer user de l'array es el que te
     * iniciada la sessio i se li hauria d'obrir un chat amb el user de la segona posicio de l'array)
     * @param usersMatched
     */
    public void goToChatWith(User[] usersMatched) {
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
        //TODO: s'ha d'anar al xat de les dues persones passades per parametre
    }

    /**
     * Metode que canvia el panell Connect a Match en cas de que hi hagi match entre dos usuaris
     */
    public void showMatch() {
        //a la funcio showUsers es passarien els usuaris en questio
        matchController.showUsers();
        mainWindow.changePanel("MATCH");

    }

    /**
     * Metode que es crida quan l'usuari ha iniciat sessio, on es mostra si te algun nou match o no. Tanmateix es crida
     * quan a la meitat de la sessió algú li retorna el match.
     */
    public void updateNotifications(){
        //TODO: bloquejar el frame principal mentres no es faci cap accio amb aquest??
        NotificationPopUp notificationView = new NotificationPopUp(mainWindow.getLocations());
        NotificationController notificationController = new NotificationController(notificationView,this);
        notificationView.registraController(notificationController);
        notificationView.start();
        //TODO: cridar a aquesta funcio sempre que algu li retorni un match i aquest estigui log in
    }

    /**
     * Metode que obre el panell del perfil (sense seleccionar-no, ja que estem al connect panel) mostrar l'usuari del que
     * es vol veure tota la informacio (Es passa com a parametre)
     */
    //public void showUserToConnectProfile(User user)
    public void showUserToConnectProfile() {
        mainWindow.changePanel("OTHER-USER PROFILE");
    }

    /**
     * Metode que retorna a l'usuari al panell connect
     */
    public void goToConnectPanel() {
        mainWindow.changePanel("CONNECT");
    }

    /**
     * Metode que porta a l'usuari al panell de chats, sense anar a cap chat en concret
     */
    public void goToChatPanel() {
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(associatedUser.isCompleted()){
            if(!mainWindow.isSelected("LOGOUT")) {
                mainWindow.selectLogout();
                mainWindow.changePanel("LOGOUT");
            }
        }else{
            mainWindow.showWarning("No pots abandonar fins que el perfil no estigui complert.");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public void loadMatchesList(LinkedList<String> matchedUsernames) {
        this.matchedUsernames = new LinkedList<>();
        this.matchedUsernames = matchedUsernames;
    }
}
