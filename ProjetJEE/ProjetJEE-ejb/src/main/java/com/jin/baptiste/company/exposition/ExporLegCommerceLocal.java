/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitPrixNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExporLegCommerceLocal {
    
    /**
     * Permet de récupérer un produit à partir de son id
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     * Permet de rajouter des produits au stock.
     * @param idProduit
     * @param n
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    public void stockerProduit(Long idProduit, int n) throws ProduitInconnuException, ProduitQuantiteNegativeException;

    /**
     * Permet de modifier les caractéristiques d'un produit
     * @param idProduit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @throws ProduitInconnuException
     */
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;

    /**
     * Permet de vendre un produit (et de destocker la quantité nécessaire)
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    public void vendreProduit(Long idProduit, int quantite)throws ProduitInconnuException,ProduitStockInsuffisantException,ProduitQuantiteNegativeException;

    /**
     * Permet de supprimer un produit
     * @param idProduit
     */
    public void supprimerProduit(Long idProduit);

    /**
     * Permet de créer un produit (son nom, sa description, son prix, son type et la quantité initiale en stock)
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @param stock
     * @throws EmptyFieldException
     * @throws ProduitQuantiteNegativeException
     * @throws ProduitPrixNegativeException
     */
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException,ProduitPrixNegativeException;
    
    /**
     * Permet de récupérer l'ensemble des produits
     * @return
     */
    public List<ProduitExport> getListProduit();

    /**
     * Permet de trouver tous les produits à partir d'un type
     * @param type
     * @return
     */
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    
    /**
     * Permet de trouver l'ensemble des produits possédant dans leur nom une chaine de caractère
     * @param nom
     * @return
     */
    public List<ProduitExport> searchProduitByName(String nom);
    
    /**
     * Permet de lister l'ensemble des types existant
     * @return
     */
    public List<TypeProduitEnum> getAllType();
        
}
