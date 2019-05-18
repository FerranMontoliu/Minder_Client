package network;

import controller.ConnectController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Comunicacio client-servidor per gestionar el connect panel.
 */
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

    /**
     * Constrcutor per parametres.
     * @param connectController controlador de connect associat.
     */
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
                        //Enviem l'usuari associat al compte
                        objectOut.writeObject(connectController.getAssociatedUser());
                        //Llegim el seguent usuari a mostrar per el connectPanel
                        User connectUser = (User) objectIn.readObject();
                        connectController.loadNewUser(connectUser);
                    } catch (ClassNotFoundException | IOException e) {
                        connectController.showWarning("Error loading User from Server.");
                    }

                    break;
                case CONNECT_LIKE:
                    try{
                        //Enviem el nom de l'usuari associat i del que volem connectar-nos
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                        //Llegim si aquests dos tenen una relacio i es un match
                        boolean isMatch = dataIn.readBoolean();
                        //Fem certes accions en funcio de si es match o no
                        connectController.matchActions(isMatch);

                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
                case CONNECT_DISLIKE:
                    try{
                        //Enviem el nom de l'usuari associat i del que no ens agrada
                        dataOut.writeUTF(connectController.getSourceUsername());
                        dataOut.writeUTF(connectController.getConnectUsername());
                    }catch (IOException e){
                        connectController.showWarning("Error communicating with Server.");
                    }
                    break;
                case USER_MATCHED:
                    //Enviem els dos usuaris amb Match: l'associat i el del connect panel
                    objectOut.writeObject(connectController.getAssociatedUser());
                    objectOut.writeObject(connectController.getConnectUser());
                    break;
            }
        } catch (IOException e) {
            connectController.showWarning("Error communicating with server.");
        }
    }

}
