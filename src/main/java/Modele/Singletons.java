package Modele;

import Controleur.Controleur;
import Vue.*;

public class Singletons {
    private static MaFenetre maFenetre;
    private static ChatPanel chatPanel;
    private static InscriptionPanel inscriptionPanel;
    private static InputPanel inputPanel;
    private static PanelFond panelFond;
    private static Controleur controleur;

    public static MaFenetre getMaFenetre(){
        if(maFenetre==null){
            return maFenetre = new MaFenetre();
        }
        return maFenetre;
    }

    public static ChatPanel getChatPanel(){
        if(chatPanel==null){
            chatPanel = new ChatPanel();
        }
        return chatPanel;
    }

    public static InscriptionPanel getInscriptionPanel(){
        if(inscriptionPanel==null){
            return inscriptionPanel= new InscriptionPanel();
        }
        return inscriptionPanel;
    }

    public static InputPanel getInputPanel(){
        if(inputPanel==null){
            inputPanel = new InputPanel();
        }
        return inputPanel;
    }

    public static PanelFond getPanelFond(){
        if(panelFond==null){
            panelFond = new PanelFond();
        }
        return panelFond;
    }

    public static Controleur getControleur(){
        if(controleur==null){
            controleur = new Controleur();
        }
        return controleur;
    }
}
