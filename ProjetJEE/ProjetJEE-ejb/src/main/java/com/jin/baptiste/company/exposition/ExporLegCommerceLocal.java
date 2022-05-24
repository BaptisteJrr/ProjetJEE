/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExporLegCommerceLocal {
    
    public ProduitExport getProduit(Long idProduit);
    public void stockerProduit(Long idProduit, int n);
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type);
    public void vendreProduit(long idProduit, int quantite);
    public void supprimerProduit(long idProduit);
        
}
