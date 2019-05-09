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
    private static final char CONNECT_LIKE = 'i';
    private static final char CONNECT_DISLIKE = 'j';
    private static final char CONNECT_USER = 'k';

    private ConnectController connectController;
    private boolean isOn;
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
                case CONNECT_USER: //TODO: Solicita un USER a visualitzar pel connect panel
                    try{
                        //System.out.println(connectController.getAssociatedUser().getUsername());
                        objectOut.writeObject(connectController.getAssociatedUser());
                        User connectUser = (User) objectIn.readObject();
                        connectController.loadNewUser(connectUser);
                    } catch (ClassNotFoundException | IOException e) {
                        connectController.showWarning("Error loading User from Server.");
                    }

                    break;
                case CONNECT_LIKE: //TODO: Fas LIKE en el connect panel.
                    try{
                        //TODO: marcar viewed; marcar liked
                        //primer envio el que ha fet login, despres l'usuari que li agrada
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                        boolean isMatch = dataIn.readBoolean();
                        connectController.setMatch(isMatch);
                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
                case CONNECT_DISLIKE: //TODO: Fas DISLIKE en el connect panel.
                    try{
                        //TODO: marcar viewed, marcar disliked
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
            }
        } catch (IOException e) {
            connectController.showWarning("Error communicating with server.");
        }

    }

}
