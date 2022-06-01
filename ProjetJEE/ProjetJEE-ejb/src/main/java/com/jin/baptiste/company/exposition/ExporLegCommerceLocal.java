/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

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
    
    public ProduitExport getProduit(Long idProduit);
    
    public void stockerProduit(Long idProduit, int n);
    public void modifierProduit(Long idProduit, String nom, String description, double prixHT, TypeProduitEnum type);
    public void vendreProduit(Long idProduit, int quantite);
    public void supprimerProduit(Long idProduit);
    public void creerProduit(String nom, String description, double prixHT, TypeProduitEnum type, int stock);
    
    public List<ProduitExport> getListProduit();
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    
    public List<ProduitExport> searchProduitByName(String nom);
        
}
