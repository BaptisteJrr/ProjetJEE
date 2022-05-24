/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegPanier implements ExpoLegPanierLocal {

    @EJB
    private MetierProduitLocal metierProduit;

    @EJB
    private MetierPanierLocal metierPanier;

    
    @Override
    public PanierExport getPanier(Long idPanier) {
        Panier p = this.metierPanier.getPanier(idPanier);
        Collection<Long> listeIdProduit = null;
        for( Produit prod : p.getListeProduit()){
            listeIdProduit.add(prod.getId());
        }
        PanierExport pe = new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(),listeIdProduit,p.getClient().getId(), p.getCompte().getId(), p.getPrixTTC(), p.getDate() );
        return pe;
    }

    @Override
    public void payerPanier(Long idPanier) {
        this.metierPanier.payer(idPanier);
    }


    @Override
    public void ajouterProduit(Long idProduit, Long idPanier) {
       
        /*
         1.autehtification
        --> oui : 1. verifier panier existe 
        
        
        --> non : 
        
        
        */
        this.metierPanier.ajouterProduit(idProduit, idPanier);
    }

    @Override
    public void retirerProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerProduit(idProduit, idPanier);
    }

    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerAllProduit(idProduit, idPanier);
    }

    @Override
    public void supprimerPanier(Long idPanier) {
        this.metierPanier.supprimerPanier(idPanier);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ProduitExport getProduit(Long idProduit) {
        Produit p = this.metierProduit.getProduit(idProduit);
        List<Long> listeIdPanier = null;
        List<Panier> listePanier = p.getListePanier();
        for(Panier pan : listePanier){
              listeIdPanier.add(pan.getId());
        }
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock(), listeIdPanier );
        return pe;
    }

    @Override
    public List<ProduitExport> getProduitByType(TypeProduitEnum type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProduitExport> searchProduitByName(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
