/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExporLegLivrerLocal {
    
    /**
     * Permet de passer au stade livré un panier
     * @param idPanier
     */
    public void livrerPanier(Long idPanier);

    /**
     * Permet de lister l'ensemble des paniers pas encore livré
     * @return
     */
    public List<PanierExport> getListPanierNonLivre();

    /**
     * Permet de lister l'ensemble des paniers livrés
     * @return
     */
    public List<PanierExport> getListPanierLivre();
    
}
