/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
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
public class MetierProduit implements MetierProduitLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;
    
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
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException {
        Produit p = this.produitFacade.find(idProduit);
        if( p == null){
            throw new ProduitInconnuException();
        }
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

    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    @Override
    public void vendreProduit(long idProduit, int quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException { // Ajouter exception
        Produit p = this.produitFacade.find(idProduit);
        if(p == null){
            throw new ProduitInconnuException();
        }
        if(quantite < 0){
            throw new ProduitQuantiteNegativeException();
        }
        if(p.getStock() - quantite < 0){
            throw new ProduitStockInsuffisantException();
        }else{
            p.setStock(p.getStock() - quantite);
            this.produitFacade.edit(p);
        }
        
    }

    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    @Override
    public void stockerProduit(long idProduit, int quantite) throws ProduitInconnuException, ProduitQuantiteNegativeException {
        Produit p = this.produitFacade.find(idProduit);
        if(p == null){
            throw new ProduitInconnuException();
        }
        if(quantite < 0){
            throw new ProduitQuantiteNegativeException();
        }else{
            p.setStock(p.getStock() + quantite);
            this.produitFacade.edit(p);
        }
        
            
    }
    /**
     *
     * @param idProduit
     */

    @Override
    public void supprimerProduit(long idProduit) {
        Produit p = this.produitFacade.find(idProduit);
        this.produitFacade.remove(p);
    }

    /**
     *
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @param stock
     * @throws EmptyFieldException
     * @throws ProduitPrixNegativeException
     * @throws ProduitQuantiteNegativeException
     */
    @Override
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException, ProduitPrixNegativeException, ProduitQuantiteNegativeException {        
        Produit p = new Produit();
        if(nom == null || nom.equals("") || description == null || description.equals("") || type == null || type.toString().equals("")){
            throw new EmptyFieldException();
        }
        if(prixHT < 0){
            throw new ProduitPrixNegativeException();
        }
        if(stock < 0){
            throw new ProduitQuantiteNegativeException();
        }
        p.setDescription(description);
        p.setNom(nom);
        p.setPrixHT(prixHT);
        p.setType(type);
        p.setStock(stock);
        this.produitFacade.create(p);
    }

    /**
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    @Override
    public Produit getProduit(Long idProduit) throws ProduitInconnuException {
        Produit p = this.produitFacade.find(idProduit);
        if(p == null){
            throw new ProduitInconnuException();
        }
          return p;
        
    }

    /**
     *
     * @return
     */
    @Override
    public List<TypeProduitEnum> getAllType() {
        List<TypeProduitEnum> listType = new ArrayList<TypeProduitEnum>();
        for(TypeProduitEnum t : TypeProduitEnum.values())
            listType.add(t);
        return listType;
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public List<Produit> getProduitByType(TypeProduitEnum type) {
        List<Produit> listeProduitRes = new ArrayList<Produit>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getType() == type){
                listeProduitRes.add(p);
            }
        }
        return listeProduitRes;
    }

    /**
     *
     * @param nom
     * @return
     */
    @Override
    public List<Produit> searchProduitByName(String nom) {
        List<Produit> listeProduitRes = new ArrayList<Produit>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getNom().contains(nom)){
                listeProduitRes.add(p);
            }
        }
        return listeProduitRes;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Produit> getAllProduit() {
        return this.produitFacade.findAll();
    }
}
