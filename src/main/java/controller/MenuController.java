package controller;

import model.DownloadsManager;
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
    private LogoutController logoutController;
    private OtherUserProfileController otherUserProfileController;
    private PreferencesController preferencesController;
    private ServerComunicationChat serverComunicationChat;
    private LinkedList<String> matchedUsernames;
    private int panelReturn;


    public MenuController(MainWindow mainWindow, User associatedUser) {
        this.mainWindow = mainWindow;
        this.associatedUser = associatedUser;
        this.matchedUsernames = new LinkedList<>();

        connectController = new ConnectController(mainWindow.getConnect(), this, this.associatedUser);  //Aixo trenca paradigmes??
        editController = new EditController(mainWindow.getEdit(), this, this.associatedUser);
        chatController = new ChatController(mainWindow.getChat(), associatedUser, this);
        matchController = new MatchController(mainWindow.getMatch(), this, connectController);
        profileController = new ProfileController(mainWindow.getProfile());
        otherUserProfileController = new OtherUserProfileController(mainWindow.getOtherUserProfile(), this);
        logoutController = new LogoutController(mainWindow.getLocations(), this);
        serverComunicationChat = new ServerComunicationChat(this, chatController);
        chatController.setServerComunicationChat(serverComunicationChat);
        preferencesController = new PreferencesController(mainWindow.getPreferences(), this, this.associatedUser);

        mainWindow.registraConnectController(connectController);
        mainWindow.registraChatController(chatController);
        mainWindow.registraEditController(editController);
        mainWindow.registraMatchController(matchController);
        mainWindow.registraOtherProfileController(otherUserProfileController);
        mainWindow.registraPreferencesController(preferencesController);

        //todo: fem l'opcional?
        //updateNotifications();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        chatController.runDefaultAppearance();  //Necessari
        chatController.finishComunications();
        switch (actionCommand) {
            case "CHAT":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("CHAT")) {
                        serverComunicationChat.startServerComunication(USER_MATCH_LIST);

                        mainWindow.generateMatchList(matchedUsernames, chatController);
                        mainWindow.selectChat();
                        mainWindow.changePanel("CHAT");

                    }
                }
                break;

            case "CONNECT":
                if(associatedUser.isCompleted()){
                    if(!mainWindow.isSelected("CONNECT")) {
                        connectController.obtainConnectUser();
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
                        logoutController.showLogout();
                    }
                }
                break;

            case "EDIT":
                mainWindow.changePanel("EDIT");
                associatedUser.base64ToImage(associatedUser.getUsername());
                String userDescription = associatedUser.getDescription();
                boolean java = associatedUser.getLikesJava();
                boolean c = associatedUser.getLikesC();
                String song = associatedUser.getFavSong();
                String hobbies = associatedUser.getHobbies();
                mainWindow.initiateEdit(associatedUser.getUsername(), userDescription, java, c, song, hobbies);
                break;
            case "ACCOUNT PREFERENCES":
                mainWindow.changePanel("ACCOUNT PREFERENCES");
                int minAge = associatedUser.getMinAge();
                int maxAge = associatedUser.getMaxAge();
                String email = associatedUser.getMail();
                mainWindow.initiatePreferences(associatedUser.getUsername(), email, associatedUser.getAge(), associatedUser.isPremium(), minAge, maxAge);
                break;
            case "YES LOGOUT":
                //TODO: TANCAR COMUNICACIO DE SERVIDOR I MERDES VARIES
                DownloadsManager.deleteDirectory();
                chatController.finishComunications();
                logoutController.hideLogout();
                mainWindow.dispose();
                break;
            case "NO LOGOUT":
                logoutController.hideLogout();
                mainWindow.deselectConnect();
                mainWindow.selectProfile();
                mainWindow.changePanel("PROFILE");
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
     * Metode que cancela la edicio de preferencies del compte i retorna al panell Profile
     */
    public void cancelPreferences() {
        mainWindow.changePanel("PROFILE");
    }
    /**
     * Metode que tanca la pestanya informativa de "It's a match" i torna al connect panel
     */
    public void closeMatch() { mainWindow.changePanel("CONNECT");
    }

    /**
     * Metode que permet obrir directament un chat en concret entre dues persones
     * @param userMatched user amb qui s'ha fet match
     */
    public void goToChatWith(User userMatched) {
        //TODO: Descomentar la Comunicacio quan tot funcioni
        //Obtenim la info per carregar el ChatPanel
        serverComunicationChat.startServerComunication(USER_MATCH_LIST);
        mainWindow.generateMatchList(matchedUsernames, chatController);

        //Obrim directament el chat concret
        chatController.loadMatchingChat(userMatched.getUsername());

        //Canvia el panell
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
    }

    /**
     * Metode que canvia el panell Connect a Match en cas de que hi hagi match entre dos usuaris
     */
    public void showMatch(String associatedUsername, String connectedUsername) {
        //a la funcio showUsers es passarien els usuaris en questio
        matchController.showUsers(associatedUsername, connectedUsername);
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
    public void showUserToConnectProfile() {
        mainWindow.changePanel("OTHER-USER PROFILE");
    }

    /**
     * Metode que retorna a l'usuari al panell connect o chat
     */
    public void goToConnectPanel() {
        if(panelReturn == 1){
            mainWindow.changePanel("CONNECT");
            mainWindow.selectConnect();
        }else{
            mainWindow.changePanel("CHAT");
            mainWindow.selectChat();
        }

    }

    public void setPanelReturn(int panelReturn){
        this.panelReturn = panelReturn;
    }

    /**
     * Metode que porta a l'usuari al panell de chats, sense anar a cap chat en concret
     */
    public void goToChatPanel() {
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
        serverComunicationChat = new ServerComunicationChat(this, chatController);
        serverComunicationChat.startServerComunication(USER_MATCH_LIST);
        mainWindow.generateMatchList(matchedUsernames, chatController);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(associatedUser.isCompleted()){
            if(!mainWindow.isSelected("LOGOUT")) {
                logoutController.showLogout();
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
        if(this.matchedUsernames == null){
            this.matchedUsernames = new LinkedList<>();
        }else{
            if(this.matchedUsernames.size() > 0){
                this.matchedUsernames.clear();
            }
        }

        this.matchedUsernames = matchedUsernames;
    }

    /**
     * Metode que carrega la info de perfil del connectUser
     * @param connectUser usuari que s'esta visualitzant
     */
    public void loadConnectUserInfo(User connectUser) {
        String username = connectUser.getUsername();
        connectUser.base64ToImage(username);
        String userDescription = connectUser.getDescription();
        boolean java = connectUser.getLikesJava();
        boolean c = connectUser.getLikesC();
        String song = connectUser.getFavSong();
        String hobbies = connectUser.getHobbies();
        int age = connectUser.getAge();
        mainWindow.loadConnectUserInfo(username, userDescription, age,java, c, song, hobbies);
    }

    /**
     * Metode que es crida un cop l'edicio de perfil ha sigut satisfactoria.
     * @param associatedUser usuari actualitzat
     */
    public void editionCompleted(User associatedUser) {
        this.associatedUser = associatedUser;
        profileController.showUser(associatedUser); //Aquesta funcio actualitza el profile panel amb els nous atributs
        mainWindow.changePanel("PROFILE");
    }

    public void loadProfile() {
        profileController.showUser(associatedUser);
    }


}
