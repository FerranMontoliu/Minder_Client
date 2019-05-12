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
    private String sender;
    private boolean isOn;
    private Socket socketToServer;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;


    public ServerComunicationMessage(ChatController chatController, String sender){
        try {
            this.isOn = false;
            this.chatController = chatController;
            this.sender = sender;

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
        try {
            dataOut.close();
        } catch (IOException e) {}
        try {
            objectOut.close();
        } catch (IOException e) {}
        try {
            dataIn.close();
        } catch (IOException e) {}
        try {
            objectIn.close();
        } catch (IOException e) {}
        try {
            socketToServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run () {
        try {
            dataOut.writeChar(SEND_MESSAGE);
            dataOut.writeUTF(sender);
            while (isOn) {
                boolean stillMatch = dataIn.readBoolean();
                if(stillMatch){
                    Chat receivedChat = (Chat) objectIn.readObject();
                    chatController.setReceivedChat(receivedChat);
                    chatController.loadChat();
                }else{
                    chatController.informUnmatch();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Received chat failed");
        }
    }

    public void sendMessage (Message message) throws IOException {
        objectOut.writeObject(message);
    }
}
