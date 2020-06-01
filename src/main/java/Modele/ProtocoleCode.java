package Modele;

/**
 * Cette enum attribut une valeur à chaque protocole
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public enum ProtocoleCode {
    CREATION_COMPTE(0), CONNEXION(1), CREATION_CHAT_SIMPLE(2),CREATION_CHAT_GROUPE(3), MESSAGE(4), DECONNEXION(5);

    /**
     * code de l'état du protocole
     */
    private int code;

    /**
     * Constructeur de l'énum ProtocoleCode
     * @param code code de l'état du protocole
     */
    ProtocoleCode(int code) {
        this.code = code;
    }

    /**
     * Retourne l'état du protocole en string
     * @return nom du protocole
     */
    @Override
    public String toString() {
        return Integer.toString(code);
    }
}
