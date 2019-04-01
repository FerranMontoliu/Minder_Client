package network;

import model.ClientConfig;
import model.Json;
import view.LoginWindow;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerComunication extends Thread {

    private LoginWindow w;
    private boolean isOn;
    private Socket socketToServer;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;

    /**
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     *
     * @param w Vista associada on es veuran reflexats els canvis.
     */
    public ServerComunication(LoginWindow w) {
        try {
            this.isOn = false;
            this.w = w;

            //Configuració inicial del client:
            ClientConfig cc = Json.parseJson();

            this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());
            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'establir la comunicació client-servidor.
     *
     */
    public void startServerComunication() {
        isOn = true;
        this.start();
    }

    /**
     * Mètode encarregat de tancar la comunicació client-servidor.
     *
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Mètode que s'executa quan es crea el fil d'execució.
     *
     */
    public void run() {
        while(isOn) {
            try {
                UserState p = (UserState) objectIn.readObject();
                //Actualitzar dades i vista.
            } catch(ClassNotFoundException | IOException e) {
                e.printStackTrace();
                stopServerComunication();
            }
        }
        stopServerComunication();
    }
}

