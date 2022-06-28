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
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitPrixNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExporLegCommerce implements ExporLegCommerceLocal {


    @EJB
    private MetierProduitLocal metierProduit;
    
    /**
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    @Override
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException {
        Produit p = this.metierProduit.getProduit(idProduit);
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
        return pe;

    }

    /**
     *
     * @param idProduit
     * @param n
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    @Override
    public void stockerProduit(Long idProduit, int n) throws ProduitInconnuException, ProduitQuantiteNegativeException {
        this.metierProduit.stockerProduit(idProduit, n);
    }

    /**
     *
     * @param idProduit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @throws ProduitInconnuException
     */
    @Override
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException {
        this.metierProduit.modifierProduit(idProduit, nom, description, prixHT, type);
    }

    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    @Override
    public void vendreProduit(Long idProduit, int quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException {
        this.metierProduit.vendreProduit(idProduit, quantite);

    }

    /**
     *
     * @param idProduit
     */
    @Override
    public void supprimerProduit(Long idProduit) {
        this.metierProduit.supprimerProduit(idProduit);
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProduitExport> getListProduit() {
        List<ProduitExport> listProduitExport = new ArrayList<ProduitExport>();
        List<Produit> listProduit = this.metierProduit.getAllProduit();
        for( Produit p : listProduit){
            ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock());
            listProduitExport.add(produitExport);
        }
        
        return listProduitExport;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public List<ProduitExport> getProduitByType(TypeProduitEnum type) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.metierProduit.getProduitByType(type);
        for(Produit p : allProduit){
            if(p.getType() == type){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), type.name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;
    }

    /**
     *
     * @param nom
     * @return
     */
    @Override
    public List<ProduitExport> searchProduitByName(String nom) {
        
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.metierProduit.searchProduitByName(nom);
        for(Produit p : allProduit){
            if(p.getNom().contains(nom)){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;
    }

    /**
     *
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @param stock
     * @throws EmptyFieldException
     * @throws ProduitQuantiteNegativeException
     * @throws ProduitPrixNegativeException
     */
    @Override
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException, ProduitQuantiteNegativeException, ProduitPrixNegativeException {
        this.metierProduit.creerProduit(nom, description, prixHT, type, stock);
}

    /**
     *
     * @return
     */
    @Override
    public List<TypeProduitEnum> getAllType() {
        return this.metierProduit.getAllType();
    }

}
