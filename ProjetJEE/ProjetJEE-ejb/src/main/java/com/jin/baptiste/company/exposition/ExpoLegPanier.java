/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegPanier implements ExpoLegPanierLocal {

    @EJB
    private MetierClientLocal metierClient;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private MetierProduitLocal metierProduit;

    @EJB
    private MetierPanierLocal metierPanier;
    
    
    

    
    @Override
    public PanierExport getPanier(Long idPanier) {
        Panier p = this.metierPanier.getPanier(idPanier);
        if(p != null){
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
            return pe;
        }else{
            return null;
        }
        
    }

    @Override
    public void payerPanier(Long idPanier) {
        this.metierPanier.payer(idPanier);
    }
    
    @Override
    public void retirerProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerProduit(idProduit, idPanier);
    }

    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerAllProduit(idProduit, idPanier);
    }

    @Override
    public void supprimerPanier(Long idPanier) {
        this.metierPanier.supprimerPanier(idPanier);
    }
    
    @Override
    public ProduitExport getProduit(Long idProduit) {
        Produit p = this.metierProduit.getProduit(idProduit);
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
        return pe;
    }

    @Override
    public List<ProduitExport> getProduitByType(TypeProduitEnum type) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getType() == type){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), type.name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProduitExport> searchProduitByName(String nom) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getNom().contains(nom)){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterProduitToAClient(Long idProduit, Long idClient) {
        this.metierPanier.ajouterProduitByClient(idProduit,idClient);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PanierExport getPanierActif(Long idClient) {
        Panier p = this.metierPanier.getPanierActif(idClient);
        if(p != null){
            Long idP = p.getId();
            Collection<Long> listeIdProduit = new ArrayList<Long>();
            Collection<Produit> listeProduit = p.getListeProduit();
            for( Produit prod : listeProduit){
                listeIdProduit.add(prod.getId());
            }
            PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate());
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
            return pe;
        }else{
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
