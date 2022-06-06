/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Facture;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.facade.FactureFacadeLocal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Baptiste
 */
@Stateless
public class MetierFacture implements MetierFactureLocal {

    @EJB
    private FactureFacadeLocal factureFacade;

    
    
    @Override
    public void CreerFacture(Panier p) {
        Client clt = p.getClient();
        Compte cpt = p.getCompte();
        Map<Produit,Integer> mapProduit = p.getNbProduit();
        Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
        Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
        for(Map.Entry<Produit,Integer> nbP : nbProduit){
            mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
        }

        Facture facture = new Facture(clt.getNom(), clt.getPrenom(), clt.getEmail(),clt.getAdresse(), p.totalHT(), new Date(), mapIdProduit);
        
        this.factureFacade.create(facture);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Facture> getFactureByClient(String mail) {
        return this.factureFacade.findbyEmail(mail);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
