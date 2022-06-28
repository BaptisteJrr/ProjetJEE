/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import com.jin.baptiste.company.exposition.ExpoLrdRemote;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
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

    public void run(){
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

    private void consulterCompte(){
        CLIUtils.afficherTitreSection("Consultation de solde");
        Position p;
        try {
            p = this.services.getCompte(this.idCompte);
            System.out.println("Solde le " + this.dateFormat.format(p.getDate().getTime()) + " : " + this.soldeFormat.format(p.getSolde()) + ".");
        } catch (CompteInconnuException ex) {
            System.out.println("Le compte n'a pas été trouvé");
        }
        
    }

    private void debiterCompte(){
        try {
            CLIUtils.afficherTitreSection("Opération de Debit");
            final double montant = CLIUtils.saisirDouble(scanner, "Montant à débiter : ", 0, Double.POSITIVE_INFINITY);
            this.services.debiter(idCompte, montant);
            System.out.println("Compte " + idCompte + " a été débité de " + this.soldeFormat.format(montant) + ".");
        } catch (CompteSoldeNegaException ex) {
            System.out.println("Le solde du compte après débit est négatif. Débit annulé");
        } catch (CompteInconnuException ex) {
            System.out.println("Le compte n'a pas été trouvé");
        } catch (CompteSommeNegaException ex) {
            System.out.println("Erreur: la somme entrée est négative");
        }
    }

    private void crediterCompte() {
        try {
            CLIUtils.afficherTitreSection("Opération de Crédit");
            final double montant = CLIUtils.saisirDouble(scanner, "Montant à créditer : ", 0, Double.POSITIVE_INFINITY);
            this.services.crediter(idCompte, montant);
            System.out.println("Compte " + idCompte + " a été crédité de " + this.soldeFormat.format(montant) + ".");
        } catch (CompteInconnuException ex) {
            System.out.println("Le compte n'a pas été trouvé");
        } catch (CompteSommeNegaException ex) {
            System.out.println("Erreur: la somme entrée est négative");
        }
    }

    private void quitter() {
        CLIUtils.afficherTitreSection("Au revoir");
        this.idCompte = -1;
    }

}
