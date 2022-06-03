/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.miage.company.projetjeecomptable;

import com.jin.baptiste.company.exposition.ExpoLrdRemoteCompta;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Wang
 */
public class CMPTACLI {
    private final ExpoLrdRemoteCompta services;
    private final Scanner scanner = new Scanner(System.in);
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY' à 'HH:mm");
//    private final NumberFormat soldeFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    private long idCompte = -1;

    public CMPTACLI(ExpoLrdRemoteCompta services) {
        this.services = services;
    }

    public void run() throws ClientInconnuException {
        int choix = -1;
        do {
            this.authentifier();
            do {
//                try {
                    showMenu();
                    choix = (int) CMPTACLIUtils.saisirEntier(scanner, "Votre choix : ", 0, 3);
                    switch (choix) {
                        case 1:
                            this.consulterPanierPayebyClient();
                            choix = this.askNext();
                            break;
                        case 2:
                            this.consulterPanierPaye();
                            choix = this.askNext();
                            break;
//                        case 3:
//                            this.crediterCompte();
//                            choix = this.askNext();
//                            break;
//                        case 0:
//                            break;
                        default:
                            System.out.println("Erreur de choix");
//                    }
//                } catch(CompteInconnuException ex){
//                    System.out.println("Votre compte est inconnu.");
//                    choix = 0;
//                } catch (EJBException ex) {
//                    System.out.println("Erreur non gérée (" + ex.getClass().getName() + " : " + ex.getMessage() + ")");
//                    Throwable cause = ex.getCause();
//                    while (cause != null) {
//                        System.out.println("\tCause " + cause.getClass().getName() + " : " + cause.getMessage());
//                        cause = cause.getCause();
//                    }
                }
            } while (choix != 0);
            this.quitter();
        } while (true);
    }

    private void authentifier() {
        CMPTACLIUtils.afficherTitreSection("Bonjour et Bienvenue au System Comptable");
        this.idCompte = CMPTACLIUtils.saisirEntier(scanner, "Identifiant de compte : ");
    }

    private void showMenu() {
        CMPTACLIUtils.afficherTitreSection("Menu de séléction");
        System.out.println("\t1. Consulter ses paniers payés");
        System.out.println("\t0. Consulter tous les paniers payés");
    }
    
    //demander ID Client pour consulter ses listPanier
//    private void askIdClient(){
//        this.idCompte = CMPTACLIUtils.saisirEntier(scanner, "Identifiant de compte : ");
//    }

    private int askNext() {
        return CMPTACLIUtils.yesNoQuestion(scanner, "Souhaitez-vous effectuer une autre operation (y|n) ?") ? 1 : 0;
    }

//    private void consulterCompte() {
//        CMPTACLIUtils.afficherTitreSection("Consultation de solde");
//
//            Position p = this.services.getCompte(this.idCompte);
//            System.out.println("Solde le " + this.dateFormat.format(p.getDate().getTime()) + " : " + this.soldeFormat.format(p.getSolde()) + ".");
//
//    }
//
//    private void debiterCompte() {
//        CMPTACLIUtils.afficherTitreSection("Opération de Debit");
//        final double montant = CMPTACLIUtils.saisirDouble(scanner, "Montant à débiter : ", 0, Double.POSITIVE_INFINITY);
//            this.services.debiter(idCompte, montant);
//            System.out.println("Compte " + idCompte + " a été débité de " + this.soldeFormat.format(montant) + ".");
//
//    }
//
//    private void crediterCompte() {
//        CMPTACLIUtils.afficherTitreSection("Opération de Crédit");
//        final double montant = CMPTACLIUtils.saisirDouble(scanner, "Montant à créditer : ", 0, Double.POSITIVE_INFINITY);
//            this.services.crediter(idCompte, montant);
//            System.out.println("Compte " + idCompte + " a été crédité de " + this.soldeFormat.format(montant) + ".");
//
//    }
    
    private void consulterPanierPayebyClient() throws ClientInconnuException{
        CMPTACLIUtils.afficherTitreSection("Consultation de List des Panier Payés");
        Collection<PanierExport> listPanierExpo = this.services.getPanierPayebyClient(idCompte);
        System.out.println(this.dateFormat.format(new Date()) + "List des Panier Payés " + listPanierExpo);
    }
    
    private void consulterPanierPaye() {
        CMPTACLIUtils.afficherTitreSection("Consultation de List des Panier Payés");
        Collection<PanierExport> listPanierExpo = this.services.getPanierPaye();
        System.out.println(this.dateFormat.format(new Date()) + "List des Panier Payés " + listPanierExpo);
    }

    private void quitter() {
        CMPTACLIUtils.afficherTitreSection("Au revoir");
        this.idCompte = -1;
    }
}
