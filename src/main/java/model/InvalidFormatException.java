package model;

/**
 * Exception llencada en camps de text de format no valid.
 */
public class InvalidFormatException extends Exception{

    /**
     * Constructor per parametres.
     * @param message missatge d'error.
     */
    public InvalidFormatException(String message){
        super(message);
    }
}
