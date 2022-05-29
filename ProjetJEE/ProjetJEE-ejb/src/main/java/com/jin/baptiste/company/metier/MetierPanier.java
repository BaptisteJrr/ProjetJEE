/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierPanier implements MetierPanierLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private MetierClientLocal metierClient;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PanierFacadeLocal panierFacade;
    
    
    
    
    

    @Override
    public void payer(long idPanier) {
        
        Panier p = this.panierFacade.find(idPanier);
        p.setFlagRegle(true);
        this.panierFacade.edit(p);
    }

    @Override
    public void livrer(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        if(p.isFlagRegle()){
            p.setFlagLivre(true);
            p.setDate(new Date());
        }
        this.panierFacade.edit(p);
    }

    @Override
    public void ajouterProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.add(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }

    @Override
    public void retirerProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.remove(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        while(liste.contains(pro)){
            liste.remove(pro);
        }
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
        
    }

    @Override
    public void supprimerPanier(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        this.panierFacade.remove(p);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Panier getPanier(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
//        Collection<Long> listeIdProduit = null;
//        for( Produit prod : p.getListeProduit()){
//            listeIdProduit.add(prod.getId());
//        }
//        PanierExport pe = new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(),listeIdProduit,p.getClient().getId(), p.getCompte().getId(), p.getPrixTTC(), p.getDate() );
//        return pe;
        return p;
    }

    @Override
    public List getPanier() {
        return this.panierFacade.findAll();
    }

    @Override
    public List<Panier> getPanierNonPaye() {
        List<Panier> lNP = null;
        for (Panier p : this.panierFacade.findAll()){
            if (!p.isFlagRegle()){
                lNP.add(p);
            }
        }
        return lNP;
    }

    @Override
    public List<Panier> getPanierPaye() {
        List<Panier> lP = null;
        for (Panier p : this.panierFacade.findAll()){
            if (p.isFlagRegle()){
                lP.add(p);
            }
        }
        return lP;    
    }

    @Override
    public List<Panier> getPanierNonLivre() {
        List<Panier> lNL = new ArrayList<Panier>(); 
        List<Panier> allPanier = this.panierFacade.findAll();
        for (Panier p : allPanier){
            if (!p.isFlagLivre() && p.isFlagRegle()){
                lNL.add(p);
            }
        }
        return lNL; 
    }

    @Override
    public List<Panier> getPanierLivre() {
        List<Panier> lL = new ArrayList<Panier>();
        for (Panier p : this.panierFacade.findAll()){
            if (p.isFlagLivre()){
                lL.add(p);
            }
        }
        return lL; 
    }

    @Override
    public void ajouterProduitByClient(long idProduit, Long idClient) {
        Client clt = this.metierClient.getClient(idClient);
        Collection<Panier> listePanier = clt.getListePanier();
        if(listePanier.isEmpty()){
            Panier p = new Panier();
            Collection<Produit> listeProduit = new ArrayList<Produit>();
            Produit produit = this.produitFacade.find(idProduit);
            listeProduit.add(produit);
            p.setListeProduit(listeProduit);
            p.setClient(clt);
            this.panierFacade.create(p);
            listePanier.add(p);
            clt.setListePanier(listePanier);
            this.clientFacade.edit(clt);
        }else{
            //On cherche si il y a un panier actif 
            boolean trouve = false;
            for(Panier p : listePanier){
                if(!p.isFlagLivre() && !p.isFlagRegle() && !trouve){
                    trouve = true;
                    Collection<Produit> listeProduit = p.getListeProduit();
                    listeProduit.add(this.produitFacade.find(idProduit));
                    p.setListeProduit(listeProduit);
                    this.panierFacade.edit(p);
                    
                }
            }
            if(!trouve){
                Panier p = new Panier();
                Collection<Produit> listeProduit = new ArrayList<Produit>();
                Produit produit = this.produitFacade.find(idProduit);
                listeProduit.add(produit);
                p.setListeProduit(listeProduit);
                p.setClient(clt);
                this.panierFacade.create(p);
                listePanier.add(p);
                clt.setListePanier(listePanier);
                this.clientFacade.edit(clt);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Panier getPanierActif(Long idClient) {
        List<Panier> listePanier = this.panierFacade.findAll();
        for(Panier p : listePanier){
            if(p.getClient().getId() == idClient && !p.isFlagLivre() && !p.isFlagRegle()){
                return p;
            }
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
