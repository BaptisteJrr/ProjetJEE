/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void livrerPanier(Long idPanier) {
        this.metierPanier.livrer(idPanier);
    }   

    //on doit faire une algotithemique pour lister les panier avec leur adresse ou code postal asc ou decs 
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
                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());
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
            PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());
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
