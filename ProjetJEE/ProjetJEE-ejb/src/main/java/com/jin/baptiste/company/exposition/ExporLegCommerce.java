/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExporLegCommerce implements ExporLegCommerceLocal {

    @Override
    public ProduitExport getProduit(Long idProduit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stockerProduit(Long idProduit, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vendreProduit(long idProduit, int quantite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerProduit(long idProduit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
