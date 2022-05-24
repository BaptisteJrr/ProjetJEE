/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegPanierLocal {
    // Action du Client sur Panier
    public ProduitExport getProduit(Long idProduit);
    
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    // algo LIKE %%....
    public List<ProduitExport> searchProduitByName(String nom);
        
    public PanierExport getPanier(Long idPanier);
    public void payerPanier(Long idPanier);
   
    
    public void ajouterProduit(Long idProduit, Long idPanier);
    public void retirerProduit(Long idProduit, Long idPanier);
    
    //Nessecaire?
    public void retirerAllProduit(Long idProduit, Long idPanier);
    public void supprimerPanier(Long idPanier);
    

}