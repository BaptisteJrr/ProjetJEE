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
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     *
     * @param idProduit
     * @param n
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     */
    public void stockerProduit(Long idProduit, int n) throws ProduitInconnuException, ProduitQuantiteNegativeException;

    /**
     *
     * @param idProduit
     * @param nom
     * @param description
     * @param prixHT
     * @param type
     * @throws ProduitInconnuException
     */
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;

    /**
     *
     * @param idProduit
     * @param quantite
     * @throws ProduitInconnuException
     * @throws ProduitStockInsuffisantException
     * @throws ProduitQuantiteNegativeException
     */
    public void vendreProduit(Long idProduit, int quantite)throws ProduitInconnuException,ProduitStockInsuffisantException,ProduitQuantiteNegativeException;

    /**
     *
     * @param idProduit
     */
    public void supprimerProduit(Long idProduit);

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
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException,ProduitPrixNegativeException;
    
    /**
     *
     * @return
     */
    public List<ProduitExport> getListProduit();

    /**
     *
     * @param type
     * @return
     */
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    
    /**
     *
     * @param nom
     * @return
     */
    public List<ProduitExport> searchProduitByName(String nom);
    
    /**
     *
     * @return
     */
    public List<TypeProduitEnum> getAllType();
        
}
