package network;

import controller.ChatController;
import controller.ConnectController;
import controller.MenuController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

public class ServerComunicationConnect  {
    private static final char USER_MATCHED = 'd';
    private static final char CONNECT_LIKE = 'i';
    private static final char CONNECT_DISLIKE = 'j';
    private static final char CONNECT_USER = 'k';

    private ConnectController connectController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

    public ServerComunicationConnect(ConnectController connectController){
        try {
            this.connectController = connectController;

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
                case CONNECT_USER:
                    try{
                        objectOut.writeObject(connectController.getAssociatedUser());
                        User connectUser = (User) objectIn.readObject();
                        connectController.loadNewUser(connectUser);
                    } catch (ClassNotFoundException | IOException e) {
                        connectController.showWarning("Error loading User from Server.");
                    }

                    break;
                case CONNECT_LIKE:
                    try{
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                        boolean isMatch = dataIn.readBoolean();
                        System.out.println(isMatch);
                        connectController.matchActions(isMatch);

                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
                case CONNECT_DISLIKE:
                    try{
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
                case USER_MATCHED:
                    objectOut.writeObject(connectController.getAssociatedUser());
                    objectOut.writeObject(connectController.getConnectUser());
                    break;
            }
        } catch (IOException e) {
            connectController.showWarning("Error communicating with server.");
        }
        stopServerComunication();
    }

    /**
     * Metode encarregat de tancar la comunicacio client-servidor.
     */
    public void stopServerComunication() {
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
}
