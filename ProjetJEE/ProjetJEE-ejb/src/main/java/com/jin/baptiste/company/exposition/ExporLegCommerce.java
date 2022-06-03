/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitPrixNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import java.util.ArrayList;
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
public class ExporLegCommerce implements ExporLegCommerceLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private MetierProduitLocal metierProduit;
    
    

    @Override
    public ProduitExport getProduit(Long idProduit) {
        try {
            Produit p = this.metierProduit.getProduit(idProduit);
            ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
            return pe;
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void stockerProduit(Long idProduit, int n) {
        
        try {
            this.metierProduit.stockerProduit(idProduit, n);
            
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitQuantiteNegativeException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) {
        try {
            this.metierProduit.modifierProduit(idProduit, nom, description, prixHT, type);
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void vendreProduit(Long idProduit, int quantite) {
        try {
            this.metierProduit.vendreProduit(idProduit, quantite);
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitStockInsuffisantException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitQuantiteNegativeException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerProduit(Long idProduit) {
        this.metierProduit.supprimerProduit(idProduit);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProduitExport> getListProduit() {
        List<ProduitExport> listProduitExport = new ArrayList<ProduitExport>();
        List<Produit> listProduit = this.produitFacade.findAll();
        for( Produit p : listProduit){
            ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock());
            listProduitExport.add(produitExport);
        }
        
        return listProduitExport;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

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
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) {
        try {
            this.metierProduit.creerProduit(nom, description, prixHT, type, stock);
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (EmptyFieldException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitQuantiteNegativeException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitPrixNegativeException ex) {
            Logger.getLogger(ExporLegCommerce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
