package Modele;

import Controleur.Controleur;
import Vue.*;

public class Singletons {
    public static PanelCentre panelCentre;
    private static MaFenetre maFenetre;
    private static ChatPanel chatPanel;
    private static InscriptionPanel inscriptionPanel;
    private static InputPanel inputPanel;
    private static PanelFond panelFond;
    private static Controleur controleur;
    private static PanelNord panelNord;
    private static PanelDiscussion panelDiscussion;
    private static ConnexionPanel connexionPanel;
    private static PanelOnglet panelOnglet;
    private static PanelError panelError;

    public static MaFenetre getMaFenetre() {
        if (maFenetre == null) {
            return maFenetre = new MaFenetre();
        }
        return maFenetre;
    }

    public static ChatPanel getChatPanel() {
        if (chatPanel == null) {
            chatPanel = new ChatPanel();
        }
        return chatPanel;
    }

    public static PanelOnglet getPanelOnglet(){
        if(panelOnglet==null){
            return panelOnglet= new PanelOnglet();
        }
        return panelOnglet;
    }

    public static InscriptionPanel getInscriptionPanel(){
        if(inscriptionPanel==null){
            return inscriptionPanel= new InscriptionPanel();
        }
        return inscriptionPanel;
    }

    public static ConnexionPanel getConnexionPanel(){
        if(connexionPanel==null){
            return connexionPanel= new ConnexionPanel();
        }
        return connexionPanel;
    }

    public static InputPanel getInputPanel(){
        if(inputPanel==null){
            inputPanel = new InputPanel();
        }
        return inputPanel;
    }

    public static PanelFond getPanelFond() {
        if (panelFond == null) {
            panelFond = new PanelFond();
        }
        return panelFond;
    }

    public static Controleur getControleur() {
        if (controleur == null) {
            controleur = new Controleur();
        }
        return controleur;
    }

    public static PanelNord getPanelNord() {
        if (panelNord == null) {
            return panelNord = new PanelNord();
        }
        return panelNord;
    }

    public static PanelCentre getPanelCentre() {
        if (panelCentre == null) {
            return panelCentre = new PanelCentre();
        }
        return panelCentre;
    }

    public static PanelDiscussion getPanelDiscussion(){
        if (panelDiscussion == null) {
            return panelDiscussion = new PanelDiscussion();
        }
        return panelDiscussion;
    }

    public static PanelError getPanelError(){
        if (panelError == null) {
            return panelError = new PanelError();
        }
        return panelError;
    }
}