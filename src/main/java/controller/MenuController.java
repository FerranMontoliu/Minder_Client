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


    public MenuController(MainWindow mainWindow, User associatedUser) {
        this.mainWindow = mainWindow;
        this.associatedUser = associatedUser;
        this.matchedUsernames = new LinkedList<>();

        connectController = new ConnectController(mainWindow.getConnect(), this, this.associatedUser);  //Aixo trenca paradigmes??
        editController = new EditController(mainWindow.getEdit(), this, this.associatedUser);
        chatController = new ChatController(mainWindow.getChat(), associatedUser);
        matchController = new MatchController(mainWindow.getMatch(), this, connectController, this.associatedUser);
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
                        //System.out.println("obtain connect user. Estic a gotoCOnnect panel del menu controller");
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
                        //mainWindow.selectLogout();
                        logoutController.showLogout();
                        //mainWindow.changePanel("LOGOUT");
                    }
                }
                break;

            case "EDIT":
                mainWindow.changePanel("EDIT");
                //TODO: Aquestes variables realment les hauria d'agafar del atribut associatedUser, però de moment són proves
                associatedUser.base64ToImage(associatedUser.getUsername());  //Aixo et descarrega la img a la carpeta data amb el nom del User com a titol
                String userDescription = associatedUser.getDescription();
                boolean java = associatedUser.getLikesJava();
                boolean c = associatedUser.getLikesC();
                String song = associatedUser.getFavSong();
                String[] hobbies = associatedUser.getHobbies();
                mainWindow.initiateEdit(associatedUser.getUsername(), userDescription, java, c, song, hobbies);
                //mainWindow.setSelectedImage(associatedUser.getImage(), associatedUser.getDescription()...);
                break;
            case "ACCOUNT PREFERENCES":
                //TODO: descomentar associatedUser.getMinAge() i getMaxAge()
                mainWindow.changePanel("ACCOUNT PREFERENCES");
                int minAge = 24;
                int maxAge = 35;
                //int minAge = associatedUser.getMinAge();
                //int maxAge = associatedUser.getMaxAge();
                String email = associatedUser.getMail();
                mainWindow.initiatePreferences(associatedUser.getUsername(), email, associatedUser.getAge(), associatedUser.isPremium(), minAge, maxAge);
                break;
            case "YES LOGOUT":
                //TODO: TANCAR COMUNICACIO DE SERVIDOR I MERDES VARIES
                DownloadsManager.deleteDirectory();
                logoutController.hideLogout();
                mainWindow.dispose();
                break;
            case "NO LOGOUT":
                logoutController.hideLogout();
                mainWindow.deselectConnect();

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
        //serverComunicationChat.startServerComunication(USER_MATCH_LIST);
        //serverComunicationChat.join();
        mainWindow.generateMatchList(matchedUsernames);

        //Obrim directament el chat concret
        chatController.loadMatchingChat(userMatched.getUsername());
        //serverComunicationChat.join();


        //Canvia el panell
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
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
        mainWindow.selectConnect();

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
                logoutController.showLogout();
                //mainWindow.selectLogout();
                //mainWindow.changePanel("LOGOUT");
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

    /**
     * Metode que carrega la info de perfil del connectUser
     * @param connectUser usuari que s'esta visualitzant
     */
    public void loadConnectUserInfo(User connectUser) {
        mainWindow.loadConnectUserInfo(connectUser);
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
