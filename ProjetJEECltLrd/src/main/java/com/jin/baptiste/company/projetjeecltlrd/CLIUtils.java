/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import java.util.Scanner;

/**
 *
 * @author LeNonGrillePain
 */
class CLIUtils {
    private CLIUtils() {
    }
    
    public static void afficherTitreSection(String titre){
        System.out.println();
        System.out.println("***** " + titre.toUpperCase() + "*****");
        System.out.println();
    }
    
    public static double saisirDouble(Scanner sc, String label, double limiteInf, double limiteMax){
        do{
            try{
                System.out.print(label);
                double d = Double.parseDouble(sc.next());
                if(d < limiteInf || d > limiteMax){
                    throw new NumberFormatException("nombre incorrect");
                }
                return d;
            }catch(NumberFormatException ex){
                System.out.println("Erreur de saisie : " + ex.getMessage());
            }
        }while(true);
    }
    
    public static double saisirDouble(Scanner sc, String label){
        return saisirDouble(sc, label, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
    
    public static long saisirEntier(Scanner sc, String label, long limiteInf, long limiteMax){
        do{
            try{
                System.out.print(label);
                long d = Long.parseLong(sc.next());
                if(d < limiteInf || d > limiteMax){
                    throw new NumberFormatException("nombre incorrect");
                }
                return d;
            }catch(NumberFormatException ex){
                System.out.println("Erreur de saisie : " + ex.getMessage());
            }
        }while(true);
    }
    
    public static long saisirEntier(Scanner sc, String label){
        return saisirEntier(sc, label, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public static String saisirChaine(Scanner sc, String label){
        do{
            System.out.print(label);
            String c = sc.next();
            c = c == null ? null : c.trim();
            if(c == null || c.isEmpty()){
                System.out.println("Erreur de sasisie : veuillez saisir au moins un caractère.");
            }else{
                return c;
            }
        }while(true);
    }
    
    public static boolean yesNoQuestion(Scanner sc, String label){
        do{
            String s = CLIUtils.saisirChaine(sc, label).toLowerCase();
            switch(s){
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Erreur de saisie : veuillez saisir 'y' ou 'n'.");
            }
        }while(true);
    }
}
