package network;

import controller.ChatController;
import controller.MenuController;
import model.ClientConfig;
import model.Json;
import model.entity.Chat;
import model.entity.Message;

import java.io.*;
import java.net.Socket;

public class ServerComunicationMessage extends Thread {
    private static final char SEND_MESSAGE = 'g';

    private ChatController chatController;
    private boolean isOn;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;


    public ServerComunicationMessage(ChatController chatController){
        try {
            this.isOn = false;
            this.chatController = chatController;

            //Configuració inicial del client:
            ClientConfig cc = Json.parseJson();

            this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());

            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'establir la comunicacio client-servidor.
     */
    public void startServerComunication() {
        isOn = true;
        this.start();
    }

    /**
     * Metode encarregat de tancar la comunicacio client-servidor.
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    @Override
    public void run () {
        try {
            while (isOn) {
                Chat receivedChat = (Chat) objectIn.readObject();
                chatController.loadChat(receivedChat);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage (Message message) throws IOException {
        objectOut.writeObject(message);
    }
}
