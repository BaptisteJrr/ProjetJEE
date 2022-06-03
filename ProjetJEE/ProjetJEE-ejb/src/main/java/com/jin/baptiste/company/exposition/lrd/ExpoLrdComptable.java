/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.lrd;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.exposition.ExpoLrdRemoteCompta;
import com.jin.baptiste.company.exposition.leg.ExpoLegPanier;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author Wang
 */
public class ExpoLrdComptable implements ExpoLrdRemoteCompta{
    
    
    @EJB
    private MetierPanierLocal metierPanier;

//    @Override
//    public List<PanierExport> getPanierNonPaye() {
//        List<PanierExport> ListPanierExp = new ArrayList<PanierExport>();
//        Collection<Panier> ListPanier = this.metierPanier.getPanierNonLivre();
//        for (Panier p : ListPanier){
//            if(p != null && !p.isFlagRegle()){
//                Long idP = p.getId();
//                Collection<Long> listeIdProduit = new ArrayList<Long>();
//                Collection<Produit> listeProduit = p.getListeProduit();
//                for( Produit prod : listeProduit){
//                    listeIdProduit.add(prod.getId());
//                }
//                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());            
//                ListPanierExp.add(pe);
//            }
//        }
//        return ListPanierExp;
//    }

    @Override
    public List<PanierExport> getPanierPaye() {
        List<PanierExport> ListPanierExp = new ArrayList<PanierExport>();
        Collection<Panier> ListPanier = this.metierPanier.getPanierNonLivre();
        for (Panier p : ListPanier){
            if(p != null && p.isFlagRegle()){
                Long idP = p.getId();
                Collection<Long> listeIdProduit = new ArrayList<Long>();
                Collection<Produit> listeProduit = p.getListeProduit();
                for( Produit prod : listeProduit){
                    listeIdProduit.add(prod.getId());
                }
                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());            
                ListPanierExp.add(pe);
            }
        }
        return ListPanierExp;
    }

//    @Override
//    public List<PanierExport> getPanierNonLivre() {
//        List<PanierExport> ListPanierExp = new ArrayList<PanierExport>();
//        Collection<Panier> ListPanier = this.metierPanier.getPanierNonLivre();
//        for (Panier p : ListPanier){
//            if(p != null && p.isFlagRegle() && !p.isFlagLivre()){
//                Long idP = p.getId();
//                Collection<Long> listeIdProduit = new ArrayList<Long>();
//                Collection<Produit> listeProduit = p.getListeProduit();
//                for( Produit prod : listeProduit){
//                    listeIdProduit.add(prod.getId());
//                }
//                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());            
//                ListPanierExp.add(pe);
//            }
//        }
//        return ListPanierExp;
//    }

//    @Override
//    public List<PanierExport> getPanierLivre() {
//        List<PanierExport> ListPanierExp = new ArrayList<PanierExport>();
//        Collection<Panier> ListPanier = this.metierPanier.getPanierNonLivre();
//        for (Panier p : ListPanier){
//            if(p != null && p.isFlagRegle() && p.isFlagLivre()){
//                Long idP = p.getId();
//                Collection<Long> listeIdProduit = new ArrayList<Long>();
//                Collection<Produit> listeProduit = p.getListeProduit();
//                for( Produit prod : listeProduit){
//                    listeIdProduit.add(prod.getId());
//                }
//                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());            
//                ListPanierExp.add(pe);
//            }
//        }
//        return ListPanierExp;
//    }

//    @Override
//    public PanierExport getPanierActif(Long idClient) throws ClientInconnuException {
//        try {
//            Panier p = this.metierPanier.getPanierActif(idClient);
//            if(p != null && !p.isFlagRegle() && !p.isFlagLivre()){
//                Long idP = p.getId();
//                Collection<Long> listeIdProduit = new ArrayList<Long>();
//                Collection<Produit> listeProduit = p.getListeProduit();
//                for( Produit prod : listeProduit){
//                    listeIdProduit.add(prod.getId());
//                }
//                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());
//                Long idClientLocal = null;
//                Long idCompte = null;
//                try{
//                    idClientLocal = p.getClient().getId();
//                    pe.setIdClient(idClientLocal);
//                }catch(Exception e){
//                }
//                try{
//                    idCompte = p.getCompte().getId();
//                    pe.setIdCompte(idCompte);
//                }catch(Exception e){
//                    
//                }
//                return pe;
//            }else{
//                return null;
//            }
//        } catch (ClientInconnuException ex) {
//            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//        }

    @Override
    public Collection<PanierExport> getPanierPayebyClient(Long idClient) throws ClientInconnuException {
        try {
        List<PanierExport> ListPanierHistoExp = new ArrayList<PanierExport>();
        Collection<Panier> ListPanierHisto = this.metierPanier.getAllPanierbyClient(idClient);
        for (Panier p : ListPanierHisto){
            if(p != null && p.isFlagRegle()){
                Long idP = p.getId();
                Collection<Long> listeIdProduit = new ArrayList<Long>();
                Collection<Produit> listeProduit = p.getListeProduit();
                for( Produit prod : listeProduit){
                    listeIdProduit.add(prod.getId());
                }
                PanierExport pe = new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());
                Long idClientLocal = null;
                Long idCompte = null;
                try{
                    idClientLocal = p.getClient().getId();
                    pe.setIdClient(idClientLocal);
                }catch(Exception e){
                }
                try{
                    idCompte = p.getCompte().getId();
                    pe.setIdCompte(idCompte);
                }catch(Exception e){

                }
                ListPanierHistoExp.add(pe);
            }
        }
        return ListPanierHistoExp;
    } catch (ClientInconnuException ex) {
        Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);             
    }
    return null;
    }
    
}
