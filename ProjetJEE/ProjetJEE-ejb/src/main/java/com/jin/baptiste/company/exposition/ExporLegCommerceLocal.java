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
    
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    public void stockerProduit(Long idProduit, int n) throws ProduitInconnuException, ProduitQuantiteNegativeException;
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;
    public void vendreProduit(Long idProduit, int quantite)throws ProduitInconnuException,ProduitStockInsuffisantException,ProduitQuantiteNegativeException;
    public void supprimerProduit(Long idProduit);
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException,ProduitPrixNegativeException;
    
    public List<ProduitExport> getListProduit();
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    
    public List<ProduitExport> searchProduitByName(String nom);
    
    public List<TypeProduitEnum> getAllType();
        
}
