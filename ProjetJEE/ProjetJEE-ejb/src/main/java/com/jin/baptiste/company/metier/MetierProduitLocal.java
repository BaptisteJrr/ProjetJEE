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
     *
     * @param idProduit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @throws ProduitInconnuException
     */
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;
    
    /**
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    public Produit getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    public void vendreProduit(long idProduit, int quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException;
    
    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    public void stockerProduit(long idProduit, int quantite) throws ProduitInconnuException,ProduitQuantiteNegativeException;
    
    /**
     *
     * @param idProduit
     */
    public void supprimerProduit(long idProduit);
    
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
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException, ProduitPrixNegativeException;
    
    /**
     *
     * @return
     */
    public List<TypeProduitEnum> getAllType();
    
    /**
     *
     * @param type
     * @return
     */
    public List<Produit> getProduitByType(TypeProduitEnum type);
    
    /**
     *
     * @param nom
     * @return
     */
    public List<Produit> searchProduitByName(String nom);
    
    /**
     *
     * @return
     */
    public List<Produit> getAllProduit();
}
