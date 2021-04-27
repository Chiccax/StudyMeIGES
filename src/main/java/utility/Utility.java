package utility;

import java.util.UUID;

public class Utility {

    /**
     * Genera una nuova password
     * @return nuova password
     */
    public static String generatePassword(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String[] random = randomUUIDString.split("-");
        return random[0];
    }

    /**
     * Genera un numero intero compreso fra un min e un max
     * @param min numero minimo da generare
     * @param max numero massimo da generare
     * @return codicce intero
     */
    public static int generateCode(int min, int max){
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }


}
