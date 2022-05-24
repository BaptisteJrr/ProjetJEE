/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.facade.ProduitFacade;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierProduit implements MetierProduitLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;
    

    
    
    @Override
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) {
        Produit p = this.produitFacade.find(idProduit);
        if(nom != null){
            p.setNom(nom);
        }
        if(description != null){
            p.setDescription(description);
        }
        p.setPrixHT(prixHT);
        if(type != null){
            p.setType(type);
        }
        this.produitFacade.edit(p);
    }

    @Override
    public void vendreProduit(long idProduit, int quantite) { // Ajouter exception
        Produit p = this.produitFacade.find(idProduit);
        if(p.getStock() - quantite < 0){
            try {
                throw new ProduitStockInsuffisantException();
            } catch (ProduitStockInsuffisantException ex) {
                Logger.getLogger(MetierProduit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            p.setStock(p.getStock() - quantite);
            this.produitFacade.edit(p);
        }
        
    }

    @Override
    public void stockerProduit(long idProduit, int quantite) {
        Produit p = this.produitFacade.find(idProduit);
        if(quantite < 0){
            try {
                throw new ProduitQuantiteNegativeException();
            } catch (ProduitQuantiteNegativeException ex) {
                Logger.getLogger(ProduitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            p.setStock(p.getStock() + quantite);
        }
        this.produitFacade.edit(p);
            
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void supprimerProduit(long idProduit) {
        Produit p = this.produitFacade.find(idProduit);
        this.produitFacade.remove(p);
    }

    @Override
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) {        
        Produit p = new Produit();
        p.setDescription(description);
        p.setNom(nom);
        p.setPrixHT(prixHT);
        p.setType(type);
        p.setStock(stock);
        this.produitFacade.create(p);
    }

    @Override
    public ProduitExport getProduit(Long idProduit) {
        Produit p = this.produitFacade.find(idProduit);
        List<Long> listeIdPanier = null;
        List<Panier> listePanier = p.getListePanier();
        for(Panier pan : listePanier){
              listeIdPanier.add(pan.getId());
        }
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock(), listeIdPanier );
        return pe;
        
    }
}
