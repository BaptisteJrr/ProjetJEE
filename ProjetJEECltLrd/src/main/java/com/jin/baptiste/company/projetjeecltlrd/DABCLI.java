/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import com.jin.baptiste.company.exception.CompteClotureException;
import com.jin.baptiste.company.exception.CompteInconnuException;
import com.jin.baptiste.company.exception.SoldeInsufisantException;
import com.jin.baptiste.company.exposition.ExpoLrdRemote;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import javax.ejb.EJBException;

/**
 *
 * @author LeNonGrillePain
 */
public class DABCLI {
    private final ExpoLrdRemote services;
    private final Scanner scanner = new Scanner(System.in);
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY' à 'HH:mm");
    private final NumberFormat soldeFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    private long idCompte = -1;

    public DABCLI(ExpoLrdRemote services) {
        this.services = services;
    }

    public void run() throws com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException, CompteSoldeNegaException, CompteSommeNegaException {
        int choix = -1;
        do {
            this.authentifier();
            do {
                try {
                    showMenu();
                    choix = (int) CLIUtils.saisirEntier(scanner, "Votre choix : ", 0, 3);
                    switch (choix) {
                        case 1:
                            this.consulterCompte();
                            choix = this.askNext();
                            break;
                        case 2:
                            this.debiterCompte();
                            choix = this.askNext();
                            break;
                        case 3:
                            this.crediterCompte();
                            choix = this.askNext();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Erreur de choix");
                    }
                } catch(CompteInconnuException ex){
                    System.out.println("Votre compte est inconnu.");
                    choix = 0;
                } catch (EJBException ex) {
                    System.out.println("Erreur non gérée (" + ex.getClass().getName() + " : " + ex.getMessage() + ")");
                    Throwable cause = ex.getCause();
                    while (cause != null) {
                        System.out.println("\tCause " + cause.getClass().getName() + " : " + cause.getMessage());
                        cause = cause.getCause();
                    }
                }
            } while (choix != 0);
            this.quitter();
        } while (true);
    }

    private void authentifier() {
        CLIUtils.afficherTitreSection("Bonjour");
        this.idCompte = CLIUtils.saisirEntier(scanner, "Identifiant de compte : ");
    }

    private void showMenu() {
        CLIUtils.afficherTitreSection("Menu de séléction");
        System.out.println("\t1. Consulter votre solde");
        System.out.println("\t2. Retirer");
        System.out.println("\t3. Déposer");
        System.out.println("\t0. Quitter");
    }

    private int askNext() {
        return CLIUtils.yesNoQuestion(scanner, "Souhaitez-vous effectuer une autre operation (y|n) ?") ? 1 : 0;
    }

    private void consulterCompte() throws CompteInconnuException, com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException{
        CLIUtils.afficherTitreSection("Consultation de solde");
//        try {
            Position p = this.services.getCompte(this.idCompte);
            System.out.println("Solde le " + this.dateFormat.format(p.getDate().getTime()) + " : " + this.soldeFormat.format(p.getSolde()) + ".");
//        } catch (CompteClotureException ex) {
//            System.out.println("Le compte est clôturé.");
//        }
    }

    private void debiterCompte() throws CompteInconnuException, CompteSoldeNegaException, com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException, CompteSommeNegaException{
        CLIUtils.afficherTitreSection("Opération de Debit");
        final double montant = CLIUtils.saisirDouble(scanner, "Montant à débiter : ", 0, Double.POSITIVE_INFINITY);
//        try {
            this.services.debiter(idCompte, montant);
            System.out.println("Compte " + idCompte + " a été débité de " + this.soldeFormat.format(montant) + ".");
//        } catch (CompteClotureException | MontantInvalidException | SoldeInsufisantException ex) {
//            System.out.println("Erreur : " + ex.getMessage());
//        }
    }

    private void crediterCompte() throws CompteInconnuException, com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException, CompteSommeNegaException{
        CLIUtils.afficherTitreSection("Opération de Crédit");
        final double montant = CLIUtils.saisirDouble(scanner, "Montant à créditer : ", 0, Double.POSITIVE_INFINITY);
//        try {
            this.services.crediter(idCompte, montant);
            System.out.println("Compte " + idCompte + " a été crédité de " + this.soldeFormat.format(montant) + ".");
//        } catch (CompteClotureException | MontantInvalidException ex) {
//            System.out.println("Erreur : " + ex.getMessage());
//        }
    }

    private void quitter() {
        CLIUtils.afficherTitreSection("Au revoir");
        this.idCompte = -1;
    }

}
