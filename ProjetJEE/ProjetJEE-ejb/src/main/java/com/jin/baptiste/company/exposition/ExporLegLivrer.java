/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExporLegLivrer implements ExporLegLivrerLocal {

    @EJB
    private MetierPanierLocal metierPanier;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void livrerPaier(Long idPanier) {
        this.metierPanier.livrer(idPanier);
    }   

    //on doit faire une algotithemique pour lister les panier avec leur adresse ou code postal asc ou decs 
    @Override
    public List<PanierExport> getListPanierNonLivre() {
        //une list export?
        //algo a faire
        return this.metierPanier.getPanierNonLivre();
    }

    @Override
    public List<PanierExport> getListPanierLivre() {
        return this.metierPanier.getPanierLivre();
    }
}