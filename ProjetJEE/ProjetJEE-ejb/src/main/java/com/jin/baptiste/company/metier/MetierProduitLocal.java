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
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierProduitLocal {
    
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) throws ProduitInconnuException;
    
    public Produit getProduit(Long idProduit) throws ProduitInconnuException;
    
    public void vendreProduit(long idProduit, int quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException;
    
    public void stockerProduit(long idProduit, int quantite) throws ProduitInconnuException,ProduitQuantiteNegativeException;
    
    public void supprimerProduit(long idProduit);
    
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock) throws EmptyFieldException,ProduitQuantiteNegativeException, ProduitPrixNegativeException;
    
    public List<TypeProduitEnum> getAllType();
    
    public List<Produit> getProduitByType(TypeProduitEnum type);
    
    public List<Produit> searchProduitByName(String nom);
    
    public List<Produit> getAllProduit();
}
