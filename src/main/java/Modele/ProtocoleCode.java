package Modele;

public enum ProtocoleCode {
    CREATION_COMPTE(0), CONNEXION(1), CREATION_CHAT_SIMPLE(2),CREATION_CHAT_GROUPE(3), MESSAGE(4), DECONNEXION(5);


    private int code;

    ProtocoleCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return Integer.toString(code);
    }
}
