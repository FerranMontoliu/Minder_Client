package model;

import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.*;

/**
 * Classe encarregada de comprovar formats de camps d'usuari.
 */
public class UserManager {

    /**
     * Funcio que s'encarrega de comprovar si l'usuari es major d'edat o no.
     * @throws InvalidFormatException excepcio de format invalid.
     * @param age edat en format string.
     */
    public static void isAdult(String age) throws InvalidFormatException {
        try{
            int a = getAge(age);
            if(a < 18){
                throw new InvalidFormatException("You must be at least 18 in order to register");
            }
        }catch (NumberFormatException e1){
            throw new InvalidFormatException("Age field must be a number");
        }

    }

    /**
     * Funcio que s'encarrega de comprovar si les edats minimes i maximes seleccionades al desplegable son coherents.
     * @param minAge edat minima en string.
     * @param maxAge edat maxima en string.
     * @param noFilter boolea que indica si l'usuari vol filtre o no.
     * @throws InvalidFormatException excepcio que indica un format no valid.
     */
    public static void isAgeFilterCorrect(String minAge, String maxAge, boolean noFilter) throws InvalidFormatException {
        int am = getAge(minAge);
        //Si l'usuari no vol cap filtre per edat, definim la maxima edat com un 0
        int aM = noFilter? 0: getAge(maxAge);

        if((am > aM) && !noFilter){
            throw new InvalidFormatException("Maximum age should be higher or equal to minimum age");
        }

    }

    /**
     * Funcio que s'encarrega de comprovar si el format del mail es correcte o no.
     * @param mail correu electronic introduit per l'usuari.
     */
    public static void mailCorrectFormat(String mail) throws InvalidFormatException {
        EmailValidator v = EmailValidator.getInstance();
        if(!v.isValid(mail)){
            throw new InvalidFormatException("Email field is not correct");
        }
    }

    /**
     * Funcio que gestiona si el format del correu es correcte o no.
     *
     * @param usernameField email de l'usuari introduit.
     *
     * @return Retorna true si el format es correcte, false sino.
     */
    public static boolean mailInSignIn(String usernameField){
        try{
            mailCorrectFormat(usernameField);
            return true;
        } catch (InvalidFormatException e) {
            return false;
        }
    }

    /**
     * Funcio que s'encarrega de comprovar si la password concorda amb el camp de confirmacio de password.
     * @param password contrasenya introduida.
     * @param passwordConfirmation contrasenya de confirmacio introduida.
     * @throws InvalidFormatException excepcio de format invalid.
     */
    public static void passwordConfirm(String password, String passwordConfirmation) throws InvalidFormatException {
        System.out.println(password);
        System.out.println(passwordConfirmation);
        if(!password.equals(passwordConfirmation)){
            throw new InvalidFormatException("Password field and password do not match");
        }
    }

    /**
     * Funcio que s'encarrega de comprovar si el format de la password es correcte o no.
     * @param password contrasenya introduida.
     * @throws InvalidFormatException excepcio de format invalid.
     */
    public static void passwordCorrectFormat(String password) throws InvalidFormatException{
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber  = password.matches(".*\\d.*");
        boolean isLongEnough = password.length() > 7;

        if(!(hasUppercase && hasLowercase && hasNumber && isLongEnough)){
            throw new InvalidFormatException("Invalid password: It must be 8 characters long, contain an uppercase letter, a lowercase letter and a number");
        }
    }

    /**
     * Funcio que s'encarrega de comprovar si la password es correcta o no quan un es registra.
     * @param password contrasenya introduida.
     * @param passwordConfirmation contrasenya de confiramcio introduida.
     * @throws InvalidFormatException excepcio de format invalid.
     */
    public static void signUpPasswordIsCorrect(String password,String passwordConfirmation) throws InvalidFormatException {
        passwordConfirm(password, passwordConfirmation);
        passwordCorrectFormat(password);
    }

    /**
     * Getter de l'edat de l'usuari.
     * @return Retorna un int que conte l'edat de l'usuari.
     */
    public static int getAge(String age) throws NumberFormatException{
        int a = Integer.parseInt(age);
        return a;
    }

    /**
     * Metode que permet mirar si un camp esta buit.
     *
     * @param fieldContent Contingut del camp a analitzar en format String.
     * @param fieldName Nom del camp a omplir..
     *
     * @throws EmptyTextFieldException excepcio de textfield buit.
     */
    public static void isEmpty(String fieldContent, String fieldName) throws EmptyTextFieldException {
        if(fieldContent.isEmpty()){
            throw new EmptyTextFieldException("The field "+fieldName+" cannot be empty!");
        }
    }

    /**
     * Metode que permet comprovar si s'han seleccionat els camps obligatoris per a la creacio d'un nou perfil.
     * @param img imatge seleccionada per l'usuari del FileChooser.
     * @param description descripcio de l'usuari.
     * @param java boolean que indica si a l'usuari li agrada aquest llenguatge de programacio.
     * @param c boolean que infica si a l'usuari li agrada aquest llenguatge de programacio.
     * @throws InvalidFormatException excepcio de format invalid.
     */
    public static void checkEditProfileNewData(ImageIcon img, String description, boolean java, boolean c) throws InvalidFormatException {
        if(img == null){
            throw new InvalidFormatException("An image must be selected");
        }
        if(description.isEmpty()){
            throw new InvalidFormatException("Description must be added.");
        }
        if((!java)&&(!c)){
            throw new InvalidFormatException("A favourite programming language must be added!");
        }
    }

    /**
     * Metode que substitueix possibles caracters erronis en el recull i processament de dades.
     *
     * @param fixThis string que conte el caracter amb possible error.
     *
     * @return String amb el caracter reemplacat per un de correcte.
     */
    public static String fixSQLInjections(String fixThis){
        return fixThis.replace("'","`");
    }

    /**
     * Metode que comprova quina es la maxima edat a enviar a la database en funcio de si l'usuari vol filtre o no.
     *
     * @param maxAge String que conte l'edat seleccionada del desplegable de max age.
     * @param noFilter boolea que indica si l'usuari vol filtre (false) o si no el vol (true).
     *
     * @return edat maxima a carregar a la base de dades.
     */
    public static int checkMaxAge(String maxAge, boolean noFilter) {
        int age = noFilter? 0: Integer.parseInt(maxAge);
        return age;
    }
}
