package Modele;

import Controleur.Controleur;
import Vue.*;

/**
 * La classe Singletons permet de créer différentes instances uniques
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class Singletons {

    /**
     * Instance de ListeDiscussion
     * @see ListeDiscussion
     */
    public static ListeDiscussion listeDiscussion;

    /**
     * Instance
     */
    private static MaFenetre maFenetre;

    /**
     * Instance de ChatPanel
     * @see ChatPanel
     */
    private static ChatPanel chatPanel;

    /**
     * Instance de InscriptionPanel
     * @see InscriptionPanel
     */
    private static InscriptionPanel inscriptionPanel;

    /**
     * Instance de InputPanel
     * @see InputPanel
     */
    private static InputPanel inputPanel;

    /**
     * Instance de PanelFond
     * @see PanelFond
     */
    private static PanelFond panelFond;

    /**
     * Instance de Controleur
     * @see Controleur
     */
    private static Controleur controleur;

    /**
     * Instance de PanelNord
     * @see PanelNord
     */
    private static PanelNord panelNord;
    private static PanelDiscussion panelDiscussion;


    /**
     * Instance de ConnexionPanel
     * @see ConnexionPanel
     */
    private static ConnexionPanel connexionPanel;

    /**
     * Instance de PanelOnglet
     * @see PanelOnglet
     */
    private static PanelOnglet panelOnglet;

    /**
     * Instance de PanelError
     * @see PanelError
     */
    private static PanelError panelError;

    /**
     * Instance de ComboChoix
     * @see ComboChoix
     */
    private static ComboChoix comboChoix;

    /**
     * Méthode permettant de récupérer l'instance de MaFenetre ou de la créer
     * @return l'instance de MaFenetre
     */
    public static MaFenetre getMaFenetre() {
        if (maFenetre == null) {
            return maFenetre = new MaFenetre();
        }
        return maFenetre;
    }

    /**
     * Méthode permettant de récupérer l'instance de ChatPanel ou de la créer
     * @return l'instance de ChatPanel
     */
    public static ChatPanel getChatPanel() {
        if (chatPanel == null) {
            chatPanel = new ChatPanel();
        }
        return chatPanel;
    }

    /**
     * Méthode permettant de récupérer l'instance de PanelOnglet ou de la créer
     * @return l'instance de PanelOnglet
     */
    public static PanelOnglet getPanelOnglet(){
        if(panelOnglet==null){
            return panelOnglet= new PanelOnglet();
        }
        return panelOnglet;
    }

    /**
     * Méthode permettant de récupérer l'instance de InscriptionPanel ou de la créer
     * @return l'instance de InscriptionPanel
     */
    public static InscriptionPanel getInscriptionPanel(){
        if(inscriptionPanel==null){
            return inscriptionPanel= new InscriptionPanel();
        }
        return inscriptionPanel;
    }

    /**
     * Méthode permettant de récupérer l'instance de InscriptionPanel ou de la créer
     * @return l'instance de ConnexionPanel
     */
    public static ConnexionPanel getConnexionPanel(){
        if(connexionPanel==null){
            return connexionPanel= new ConnexionPanel();
        }
        return connexionPanel;
    }

    /**
     * Méthode permettant de récupérer l'instance de InputPanel ou de la créer
     * @return l'instance de InputPanel
     */
    public static InputPanel getInputPanel(){
        if(inputPanel==null){
            inputPanel = new InputPanel();
        }
        return inputPanel;
    }

    /**
     * Méthode permettant de récupérer l'instance de PanelFond ou de la créer
     * @return l'instance de PanelFond
     */
    public static PanelFond getPanelFond() {
        if (panelFond == null) {
            panelFond = new PanelFond();
        }
        return panelFond;
    }

    /**
     * Méthode permettant de récupérer l'instance de Controleur ou de la créer
     * @return l'instance de Controleur
     */
    public static Controleur getControleur() {
        if (controleur == null) {
            controleur = new Controleur();
        }
        return controleur;
    }

    /**
     * Méthode permettant de récupérer l'instance de PanelNord ou de la créer
     * @return l'instance de PanelNord
     */
    public static PanelNord getPanelNord() {
        if (panelNord == null) {
            return panelNord = new PanelNord();
        }
        return panelNord;
    }

    /**
     * Méthode permettant de récupérer l'instance de ListeDiscussion ou de la créer
     * @return l'instance de ListeDiscussion
     */
    public static ListeDiscussion getListeDiscussion() {
        if (listeDiscussion == null) {
            return listeDiscussion = new ListeDiscussion();
        }
        return listeDiscussion;
    }

    /**
     * Méthode permettant de récupérer l'instance de PanelDiscussion ou de la créer
     * @return l'instance de PanelDiscussion
     */
    public static PanelDiscussion getPanelDiscussion(){
        if (panelDiscussion == null) {
            return panelDiscussion = new PanelDiscussion();
        }
        return panelDiscussion;
    }

    /**
     * Méthode permettant de récupérer l'instance de PanelError ou de la créer
     * @return l'instance de PanelError
     */
    public static PanelError getPanelError(){
        if (panelError == null) {
            return panelError = new PanelError();
        }
        return panelError;
    }

    /**
     * Méthode permettant de récupérer l'instance de ComboChoix ou de la créer
     * @return l'instance de ComboChoix
     */
    public static ComboChoix getComboChoix(){
        if (comboChoix == null) {
            return comboChoix = new ComboChoix();
        }
        return comboChoix;
    }
}