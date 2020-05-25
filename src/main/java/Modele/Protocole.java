package Modele;

public enum Protocole {
    CREATION_COMPTE(0), CONNEXION(1), CREATION_CONTACT(2), MESSAGE(3), DECONNEXION(4);


    private int code;

    Protocole(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return Integer.toString(code);
    }
}
