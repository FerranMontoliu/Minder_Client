package model;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

public class User implements Serializable {

    private boolean completed;

    private String username;
    private String age;
    private boolean premium;
    private String mail;
    private String password;
    private String passwordConfirmation;

    private Image photo;
    private String description;
    private boolean likesJava;
    private boolean likesC;
    private String favSong;
    private ArrayList<String> hobbies;

    private ArrayList<User> viewed;
    private ArrayList<User> accepted;
    private ArrayList<User> match;
    private ArrayList<User> acceptedMe;


    public User(boolean completed, String username, String age, boolean premium, String mail, String password, String passwordConfirmation, Image photo, String description, boolean likesJava, boolean likesC, String favSong, ArrayList<String> hobbies, ArrayList<User> viewed, ArrayList<User> accepted, ArrayList<User> match, ArrayList<User> acceptedMe) {
        this.completed = completed;
        this.username = username;
        this.age = age;
        this.premium = premium;
        this.mail = mail;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.photo = photo;
        this.description = description;
        this.likesJava = likesJava;
        this.likesC = likesC;
        this.favSong = favSong;
        this.hobbies = hobbies;
        this.viewed = viewed;
        this.accepted = accepted;
        this.match = match;
        this.acceptedMe = acceptedMe;
    }

    /**
     * Constructor que es crida quan es registra l'usuari.
     *
     **/
    public User(String username, String age, boolean premium, String mail, String password, String passwordConfirmation) {
        this.username = username;
        this.age = age;
        this.premium = premium;
        this.mail = mail;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    /**
     * Constructor que es crida quan l'usuari fa login.
     *
     **/
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Funció que s'encarrega de comprovar si l'usuari és major d'edat o no.
     *
     * @return Retorna true si es major d'edat, false sino.
     */
    public boolean isAdult() throws NumberFormatException {
        return Integer.parseInt(age) > 17;
    }

    /**
     * Funcio que s'encarrega de comprovar si el format del mail es correcte o no.
     *
     * @return Retorna true si el format es correcte, false sino.
     */
    public boolean mailCorrectFormat() {
        EmailValidator v = EmailValidator.getInstance();
        return v.isValid(mail);
    }

    /**
     * Funcio que s'encarrega de comprovar si la password concorda amb el camp de confirmacio de password.
     *
     * @return Retorna true si concorda, false sino.
     */
    public boolean passwordConfirm() {
        return password.equals(passwordConfirmation);
    }

    /**
     * Funció que s'encarrega de comprovar si el format de la password és correcte o no
     *
     * @return Retorna true si el format és correcte, false sinó.
     */
    public boolean passwordCorrectFormat() {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber  = password.matches(".*\\d.*");
        boolean isLongEnough = password.length() > 7;

        return hasUppercase && hasLowercase && hasNumber && isLongEnough;
    }

    /**
     * Funció que s'encarrega de comprovar si la password és correcta o no.
     *
     * @return Retorna true si és correcta, false sinó.
     */
    public boolean passwordIsCorrect() {
        return passwordConfirm() && passwordCorrectFormat();
    }

    /**
     * Getter del username de l'usuari.
     *
     * @return Retorna un String que conté el nom de l'usuari.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter de l'edat de l'usuari.
     *
     * @return Retorna un int que conté l'edat de l'usuari.
     */
    public int getAge() {
        int a = 0;
        try {
            a = Integer.parseInt(age);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * Getter del tipus de compte de l'usuari.
     *
     * @return Retorna true si l'usuari és Premium, false si l'usuari és Normal.
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Getter del mail de l'usuari.
     *
     * @return Retorna un String que conté el mail de l'usuari.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Getter de la password de l'usuari.
     *
     * @return Retorna un String que conté la password de l'usuari.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter de la confirmació de password de l'usuari.
     *
     * @return Retorna un String que conté la confirmació de la password de l'usuari.
     */
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    /**
     * Getter de la foto de perfil de l'usuari.
     *
     * @return Retorna una Image que és la foto de perfil de l'usuari.
     */
    public Image getPhoto() {
        return photo;
    }

    /**
     * Getter de la descripció de l'usuari.
     *
     * @return Retorna un String que conté la descripció del perfil de l'usuari.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter de si a l'usuari li agrada Java.
     *
     * @return Retorna true si a l'usuari li agrada Java, false sinó.
     */
    public boolean isLikesJava() {
        return likesJava;
    }

    /**
     * Getter de si a l'usuari li agrada C.
     *
     * @return Retorna true si a l'usuari li agrada C, false sinó.
     */
    public boolean isLikesC() {
        return likesC;
    }

    /**
     * Getter de la cançó preferida de l'usuari.
     *
     * @return Retorna un String que conté la cançó preferida de l'usuari.
     */
    public String getFavSong() {
        return favSong;
    }

    /**
     * Getter de la llista de hobbies de l'usuari.
     *
     * @return Retorna una llista de Strings que conté els hobbies de l'usuari.
     */
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * Getter de la llista de perfils que ha vist l'usuari.
     *
     * @return Retorna una llista de Users que conté els perfils que ja ha vist l'usuari.
     */
    public ArrayList<User> getViewed() {
        return viewed;
    }

    /**
     * Getter de la llista de perfils que ha acceptat l'usuari.
     *
     * @return Retorna una llista de Users que conté els perfils que ja ha acceptat l'usuari.
     */
    public ArrayList<User> getAccepted() {
        return accepted;
    }

    /**
     * Getter de la llista de perfils amb els quals ha fet match l'usuari.
     *
     * @return Retorna una llista de Users que conté els perfils amb els quals ha fet match l'usuari.
     */
    public ArrayList<User> getMatch() {
        return match;
    }

    public ArrayList<User> getAcceptedMe() {
        return acceptedMe;
    }

    /**
     * Funció encarregada de transformar una imatge a text.
     *
     * @return Retorna el text en Base64 que representa la imatge.
     */
    public String imageToBase64() {
        String s = null;
        try {
            s =  Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File("data/image.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Mètode encarregat de reconstruir una imatge a partir d'un String.
     *
     * @param encodedString String que conté la imatge codificada en Base64.
     */
    public void base64ToImage(String encodedString) {
        try {
            FileUtils.writeByteArrayToFile(new File("data/imageConverted.jpg"), Base64.getDecoder().decode(encodedString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
