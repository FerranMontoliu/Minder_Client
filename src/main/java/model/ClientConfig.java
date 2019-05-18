package model;

/**
 * Classe encarregada de la configuracio del client.
 */
public class ClientConfig {

    private String serverIP;
    private int serverPort;

    /**
     * Constructor del ClientConfig.
     *
     * @param serverIP IP del servidor central.
     * @param serverPort Port de connexió al servidor central.
     */
    public ClientConfig(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    /**
     * Getter de la IP del servidor central.
     *
     * @return Retorna un String que conte l'adreça IP del servidor.
     */
    public String getServerIP() {
        return serverIP;
    }

    /**
     * Getter del port de connexio amb el servidor.
     *
     * @return Retorna un int que conté el port de connexio amb el servidor.
     */
    public int getServerPort() {
        return serverPort;
    }
}
