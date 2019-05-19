package network;

import controller.ChatController;
import controller.MenuController;
import model.entity.Chat;
import model.ClientConfig;
import model.Json;
import model.entity.MatchLoader;
import model.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Classe encarregada de les comunicacions per a carregar els elements necessaris del ChatPanel. No gestiona la missatgeria.
 */
public class ServerComunicationChat {
    private static final char USER_UNMATCHED = 'e';
    private static final char LOAD_CHAT = 'f';
    private static final char USER_MATCH_LIST = 'h';
    private static final char USER_INFO = 'm';

    private ChatController chatController;
    private MenuController menuController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

    /**
     * Constructor per parametres.
     * @param menuController controlador de menu associat.
     * @param chatController controlador de chat associat.
     */
    public ServerComunicationChat(MenuController menuController, ChatController chatController){
        try {
            this.menuController = menuController;
            this.chatController = chatController;

            //Configuració inicial del client:
            ClientConfig cc = Json.parseJson();

            this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());

            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.dataIn = new DataInputStream(socketToServer.getInputStream());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'establir la comunicacio client-servidor.
     *
     * @param command Indica quina accio fer.
     */
    public void startServerComunication(char command) {
        this.command = command;
        try {
            dataOut.writeChar(command);
            switch (command){
                case USER_MATCH_LIST: //Obte i carrega la llista de matches del user associat
                    getUserMatchList();
                    break;
                case LOAD_CHAT:
                    loadChat();
                    break;
                case USER_UNMATCHED:
                    userUnmatched();
                    break;
                case USER_INFO:
                    getUserInfo();
                    break;
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'obtenir la info d'un usuari.
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams.
     * @throws ClassNotFoundException Es tira si hi ha algun problema rebent les dades.
     */
    private void getUserInfo() throws IOException, ClassNotFoundException {
        dataOut.writeUTF(chatController.getDestinationUsername());
        User userInfo = (User) objectIn.readObject();
        chatController.setUserInfo(userInfo);
    }

    /**
     * Metode encarregat d'enrregistrar un unmatch.
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams.
     */
    private void userUnmatched() throws IOException {
        dataOut.writeUTF(chatController.getSourceUsername());
        dataOut.writeUTF(chatController.getUnmatchingUsername());
    }

    /**
     * Metode encarregat de carregar el xat entre dos usuaris.
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams.
     * @throws ClassNotFoundException Es tira si hi ha algun problema rebent les dades.
     */
    private void loadChat() throws IOException, ClassNotFoundException {
        dataOut.writeUTF(chatController.getSourceUsername());
        dataOut.writeUTF(chatController.getDestinationUsername());
        boolean chatExists = dataIn.readBoolean();
        if(chatExists) {
            Chat receivedChat = (Chat) objectIn.readObject();
            chatController.setReceivedChat(receivedChat);
        } else {
            chatController.setReceivedChat(null);
        }
    }

    /**
     * Metode encarregat d'obtenir i carregar la llista de matchs de l'usuari.
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams.
     * @throws ClassNotFoundException Es tira si hi ha algun problema rebent les dades.
     */
    private void getUserMatchList() throws IOException, ClassNotFoundException {
        dataOut.writeUTF(chatController.getAssociatedUser().getUsername());
        MatchLoader matchLoader = (MatchLoader) objectIn.readObject();
        menuController.loadMatchesList(matchLoader.getMatchedUsernames());
        chatController.loadMatchesList(matchLoader.getMatchedUsernames());
    }
}
