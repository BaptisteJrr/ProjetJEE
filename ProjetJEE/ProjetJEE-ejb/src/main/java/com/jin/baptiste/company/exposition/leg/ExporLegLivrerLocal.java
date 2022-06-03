/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.leg;

import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExporLegLivrerLocal {
    
    public void livrerPanier(Long idPanier);
    public List<PanierExport> getListPanierNonLivre();
    public List<PanierExport> getListPanierLivre();
    
}
