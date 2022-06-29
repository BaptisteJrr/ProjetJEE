/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitPrixNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierProduitLocal {
    
    /**
     * modifier les attributs d'un produit
     * @param idProduit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @throws ProduitInconnuException
     */
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;
    
    /**
     * get produit par idProduit
     * @param idProduit
     * @return produit
     * @throws ProduitInconnuException
     */
    public Produit getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     * vendre un produit avec une quantite parametre
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    public void vendreProduit(long idProduit, int quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException;
    
    /**
     * stocker produit avec une quantite parametre
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    public void stockerProduit(long idProduit, int quantite) throws ProduitInconnuException,ProduitQuantiteNegativeException;
    
    /**
     * supprimer produit
     * @param idProduit
     */
    public void supprimerProduit(long idProduit);
    
    /**
     * creation d'un produit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @param stock
     * @throws EmptyFieldException
     * @throws ProduitQuantiteNegativeException
     * @throws ProduitPrixNegativeException
     */
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException, ProduitPrixNegativeException;
    
    /**
     * get tous les types des produits
     * @return List<TypeProduitEnum>
     */
    public List<TypeProduitEnum> getAllType();
    
    /**
     * get produit par type
     * @param type
     * @return List<Produit>
     */
    public List<Produit> getProduitByType(TypeProduitEnum type);
    
    /**
     * searche produit par le nom
     * @param nom
     * @return List<Produit>
     */
    public List<Produit> searchProduitByName(String nom);
    
    /**
     * get tous les produits
     * @return List<TypeProduitEnum>
     */
    public List<Produit> getAllProduit();
}
