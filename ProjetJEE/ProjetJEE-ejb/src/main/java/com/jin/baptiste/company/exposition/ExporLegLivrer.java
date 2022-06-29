/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNonPayeException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExporLegLivrer implements ExporLegLivrerLocal {

    @EJB
    private MetierPanierLocal metierPanier;
    
    


    /**
     * Permet de passer au stade livré un panier
     * @param idPanier
     */

    @Override
    public void livrerPanier(Long idPanier) {
        try {
            this.metierPanier.livrer(idPanier);
        } catch (PanierNonPayeException ex) {
            Logger.getLogger(ExporLegLivrer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExporLegLivrer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    /**
     * Permet de lister l'ensemble des paniers pas encore livré
     * @return
     */
    @Override
    public List<PanierExport> getListPanierNonLivre() {
        //une list export?
        //algo a faire
        List<Panier> listePanier = this.metierPanier.getPanierNonLivre();
        if(!listePanier.isEmpty()){
            List<PanierExport> listePanierExport = new ArrayList<PanierExport>();
            for( Panier p : listePanier){
                Long idP = p.getId();
                Collection<Long> listeIdProduit = new ArrayList<Long>();
                Collection<Produit> listeProduit = p.getListeProduit();
                for( Produit prod : listeProduit){
                    listeIdProduit.add(prod.getId());
                }
                Map<Produit,Integer> mapProduit = p.getNbProduit();
                Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
                Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
                for(Map.Entry<Produit,Integer> nbP : nbProduit){
                    mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
                }
                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
                Long idClient = null;
                Long idCompte = null;
                try{
                    idClient = p.getClient().getId();
                    pe.setIdClient(idClient);
                }catch(Exception e){    
                }
                try{
                    idCompte = p.getCompte().getId();
                    pe.setIdCompte(idCompte);
                }catch(Exception e){

                }
                listePanierExport.add(pe);
            }

            return listePanierExport;
        }else{
            return new ArrayList<PanierExport>();
        }
        
    }

    /**
     * Permet de lister l'ensemble des paniers livrés
     * @return
     */
    @Override
    public List<PanierExport> getListPanierLivre() {
        List<Panier> listePanier = this.metierPanier.getPanierLivre();
        List<PanierExport> listePanierExport = new ArrayList<PanierExport>();
        for( Panier p : listePanier){
            Long idP = p.getId();
            Collection<Long> listeIdProduit = new ArrayList<Long>();
            Collection<Produit> listeProduit = p.getListeProduit();
            for( Produit prod : listeProduit){
                listeIdProduit.add(prod.getId());
            }
            Map<Produit,Integer> mapProduit = p.getNbProduit();
            Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
            Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
            for(Map.Entry<Produit,Integer> nbP : nbProduit){
                mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
            }
            PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
            Long idClient = null;
            Long idCompte = null;
            try{
                idClient = p.getClient().getId();
                pe.setIdClient(idClient);
            }catch(Exception e){    
            }
            try{
                idCompte = p.getCompte().getId();
                pe.setIdCompte(idCompte);
            }catch(Exception e){

            }
            listePanierExport.add(pe);
        }
        
        return listePanierExport;
    }
}
