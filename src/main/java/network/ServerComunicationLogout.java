package network;

import model.ClientConfig;
import model.Json;

import java.io.*;
import java.net.Socket;

/**
 * Comunicacio client-servidor per gestionar el logout.
 */
public class ServerComunicationLogout {
    private static final char USER_DISCONNECTS = 'n';

    private Socket socketToServer;
    private DataOutputStream dataOut;
    private ObjectOutputStream objectOut;

    /**
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     *
     * @throws IOException Es tira si hi ha problema amb els streams.
     */
    public ServerComunicationLogout() throws IOException {
        //Configuració inicial del client:
        ClientConfig cc = Json.parseJson();

        this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());

        this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
        this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
    }

    /**
     * Metode encarregat d'establir la comunicacio client-servidor.
     *
     * @param username Usuari que esta utilitzant el programa.
     *
     * @throws IOException Es tira si hi ha problema amb els streams.
     */
    public void startServerComunication(String username) throws IOException {
        dataOut.writeChar(USER_DISCONNECTS);
        objectOut.writeObject(username);
    }

}
