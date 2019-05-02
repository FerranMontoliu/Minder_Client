package network;

import controller.ChatController;
import controller.MenuController;
import model.entity.Chat;
import model.ClientConfig;
import model.Json;
import model.entity.MatchLoader;
import model.entity.Message;

import java.io.*;
import java.net.Socket;

/**
 * Classe encarregada de les comunicacions per a carregar els elements necessaris del ChatPanel. No gestiona la missatgeria.
 */
public class ServerComunicationChat {
    private static final char USER_UNMATCHED = 'e';
    private static final char LOAD_CHAT = 'f';
    private static final char USER_MATCH_LIST = 'h';

    private ChatController chatController;
    private MenuController menuController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

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
     */
    public void startServerComunication(char command) {
        this.command = command;
        try {
            dataOut.writeChar(command);
            switch (command){
                case USER_MATCH_LIST: //Obte i carrega la llista de matches del user associat
                    dataOut.writeUTF(chatController.getAssociatedUser().getUsername());
                    MatchLoader matchLoader = (MatchLoader) objectIn.readObject();
                    menuController.loadMatchesList(matchLoader.getMatchedUsernames());
                    chatController.loadMatchesList(matchLoader.getMatchedUsernames());
                    break;
                case LOAD_CHAT:
                    dataOut.writeUTF(chatController.getSourceUsername());
                    dataOut.writeUTF(chatController.getDestinationUsername());
                    boolean chatExists = dataIn.readBoolean();
                    if(chatExists){
                        Chat receivedChat = (Chat) objectIn.readObject();
                        chatController.setReceivedChat(receivedChat);
                    }else{
                        chatController.setReceivedChat(null);
                    }

                    break;
                case USER_UNMATCHED:
                    dataOut.writeUTF(chatController.getSourceUsername());
                    dataOut.writeUTF(chatController.getUnmatchingUsername());
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
