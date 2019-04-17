package network;

import controller.ChatController;
import controller.ConnectController;
import controller.MenuController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

public class ServerComunicationConnect extends Thread {
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
            this.isOn = false;
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
    public void run() {
        switch (command){
            case CONNECT_USER: //TODO: Solicita un USER a visualitzar pel connect panel
                try{
                    User connectUser = (User) objectIn.readObject();
                    connectController.loadNewUser(connectUser);
                } catch (ClassNotFoundException | IOException e) {
                    connectController.showWarning("Error loading User from Server.");
                }

                break;
            case CONNECT_LIKE: //TODO: Fas LIKE en el connect panel.
                try{
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
                    dataOut.writeUTF(connectController.getSourceUsername());
                    dataOut.writeUTF(connectController.getConnectUsername());
                }catch (IOException e){
                    connectController.showWarning("Error communicating with Server.");
                }
                break;
        }
    }
}
