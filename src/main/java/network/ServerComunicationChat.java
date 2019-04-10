package network;

import controller.ChatController;
import model.ClientConfig;
import model.Json;

import java.io.*;
import java.net.Socket;

public class ServerComunicationChat extends Thread {
    private static final char LOAD_CHAT = 'f';
    private static final char SEND_MESSAGE = 'g';
    private static final char USER_MATCH_LIST = 'h';

    private ChatController chatController;
    private boolean isOn;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

    public ServerComunicationChat(ChatController chatController){
        try {
            this.isOn = false;
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
        isOn = true;
        this.start();
    }

    /**
     * Metode encarregat de tancar la comunicació client-servidor.
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            dataOut.writeChar(command);
            switch (command){
                case USER_MATCH_LIST: //Obte i carrega la llista de matches del user
                    objectOut.writeObject(chatController.getAssociatedUser());
                    //TODO: Rebre la llista
                    //chatController.loadMatchesList();
                    break;
                case LOAD_CHAT:
                    break;
                case SEND_MESSAGE:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
