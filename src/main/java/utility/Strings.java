package utility;

public class Strings {
    //RecensioneServlet.java (+ NO_ARGUMENT, INVALID_TITLE)
    public static final String INVALID_TEXT = "Il testo deve essere tra i 10 e i 30 caratteri";
    //ModificaAreaUtenteServlet.java (+ INVALID_USER)
    public static final String NO_EMAIL = "Campo email vuoto";
    public static final String NO_USERNAME = "Campo nome utente vuoto";
    public static final String UDED_USERNAME = "Ops... nome utente già in uso, provane un altro";
    public static final String WRONG_EMAIL_FORMAT=  "Formato email non valido";
    public static final String NO_USER = "Utente non esistente";
    public static final String EMAIL_ESISTENTE= "L'email è già presente nel sistema";
    public static final String NO_VALIDEPASSWORD = "La password deve contenere almeno un carattere numerico, una maiuscola, una minuscola, un carattere speciale e almeno 8 caratteri.";
    public static final String NO_PASSWORD = "Le password non coincidono";
    public static final String EMPTY_PASSWORD = "Campo password vuoto";
    //InsegnanteServlet.java
    public static final String INVALID_TITLE = "Inserire un titolo compreso tra i 5 e 35 caratteri";
    public static final String INVALID_TITLE2 = "Titolo gi&agrave esistente";
    public static final String INVALID_DES = "Inserire una descrizione compresa tra i 10 e i 30 caratteri";
    public static final String INVALID_URL = "Url gi&agrave esistente";
    public static final String NO_URL = "Url non valido!";
    public static final String NO_INSERT = "Inserimento non riuscito!";
    public static final String COMPLETE = "Pacchetto inserito con successo!";
    public static final String INVALID_PRICE = "Prezzo non valido";
    public static final String INVALID_CODE_LENGTH = "Inserire un codice pacchetto di massimo 6 caretteri";
    public static final String INVALID_CODE = "Codice pacchetto gi&agrave; in uso";
    public static final String NO_CODE = "Inserire codice per proseguire!";
    public static final String NO_ARGUMENT = "Tutti i parametri devono essere compilati";
    public static final String NO_SOTTOCATEGORY = "Codice sottocategoria non valido";
    //LoginServlet.java (+ NO_ARGUMENT)
    public static final String NO_USER_PASSWORD = "Username o password errati";
    //RecuperoPasswordServlet.java (+ NO_EMAIL)
    public static final String NO_ESIXT_EMAIL = "Email non esistente";
    //RegistrazioneServlet.java (+ NO_ARGUMENT, NO_PASSWORD, WRONG_EMAIL_FORMAT)
    public static final String INVALID_PASSWORD = "La password deve contenere almeno un carattere numerico, una maiuscola, una minuscola, un carattere speciale e almeno 8 caratteri.";
    public static final String INVALID_USER = "Inserire un nome utente da almeno 4 caratteri";
    public static final String EXISTS_USER = "Utente gi&agrave esistente ";

    //invio email
    public static final String GENERATE_PASSWORD = Utility.generatePassword();
    public static final int GENERATE_CODE = Utility.generateCode(1111,9999);
    public static final String SUBJECT_RECUPERA_PASSWORD = "Recupero password";
    public static final String MESSAGE_RECUPERA_PASSWORD = "Ecco la tua nuova password: " + GENERATE_PASSWORD + ". Con questa potrai accedere nuovamente al sito StudyMe." +
            "Per una questione di sicurezza è raccomandabile modificare la ";
    public static final String SUBJECT_CONFERMA_EMAIL = "StudyMe: conferma la tua email";
    public static final String MESSAGE_CONFERMA_EMAIL = "Inserisci il codice " + GENERATE_CODE + " per confermare la veridicità della tua email";


}
