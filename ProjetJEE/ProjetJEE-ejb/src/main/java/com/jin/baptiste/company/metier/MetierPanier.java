/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.CompteFacadeLocal;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyLivreException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierEmptyException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNoAccountLinkedToClientException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNonPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierPanier implements MetierPanierLocal {

    @EJB
    private CompteFacadeLocal compteFacade;

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private MetierClientLocal metierClient;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PanierFacadeLocal panierFacade;
    
    
    
    
    
    
    
    

    @Override
    public void payer(long idPanier) throws PanierInconnuException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, PanierEmptyException {
        
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }else{
            Collection<Produit> listeProduit = p.getListeProduit();

            if(listeProduit != null){
                if(listeProduit.isEmpty()){
                    throw new PanierEmptyException();
                }
            }
            Compte cpt = p.getCompte();
            if(cpt != null ){
                p.setFlagRegle(true);
                this.metierCompte.debiter(cpt.getId(), p.getPrixTTC());
                this.panierFacade.edit(p);
                this.compteFacade.edit(cpt);
            }else{
                throw new PanierNoAccountLinkedToClientException();
            }
        }
        
        
    }

    @Override
    public void livrer(long idPanier) throws PanierInconnuException, PanierNonPayeException {
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }
        else{
            if(p.isFlagRegle()){
                p.setFlagLivre(true);
                p.setDate(new Date());
            }else{
                throw new PanierNonPayeException();
            }
            this.panierFacade.edit(p);
        }
        
    }

    /*@Override
    public void ajouterProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.add(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }
*/
    @Override
    public void retirerProduit(long idProduit, long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }
        if(p.isFlagLivre()){
            throw new PanierAlreadyLivreException();
        }
        if(p.isFlagRegle()){
            throw new PanierAlreadyPayeException();
        }
        Produit pro = this.produitFacade.find(idProduit);
        if(pro == null){
            throw new ProduitInconnuException();
        }
        Collection<Produit> liste = p.getListeProduit();
        liste.remove(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) throws PanierAlreadyPayeException, PanierAlreadyLivreException, PanierInconnuException, ProduitInconnuException {
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }
        if(p.isFlagLivre()){
            throw new PanierAlreadyLivreException();
        }
        if(p.isFlagRegle()){
            throw new PanierAlreadyPayeException();
        }
        Produit pro = this.produitFacade.find(idProduit);
        if(pro == null){
            throw new ProduitInconnuException();
        }
        Collection<Produit> liste = p.getListeProduit();
        while(liste.contains(pro)){
            liste.remove(pro);
        }
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
        
    }

    @Override
    public void supprimerPanier(long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }
        if(p.isFlagLivre()){
            throw new PanierAlreadyLivreException();
        }
        if(p.isFlagRegle()){
            throw new PanierAlreadyPayeException();
        }
        this.panierFacade.remove(p);
    }


    @Override
    public Panier getPanier(long idPanier) throws PanierInconnuException {
        Panier p = this.panierFacade.find(idPanier);
        if(p == null){
            throw new PanierInconnuException();
        }
        return p;
    }
/*
    @Override
    public List getPanier() {
        return this.panierFacade.findAll();
    }*/

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
    public void ajouterProduitByClient(long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException {
        Client clt;
        try {
            clt = this.metierClient.getClient(idClient);
        } catch (ClientInconnuException ex) {
            throw ex;
        }

        Collection<Panier> listePanier = clt.getListePanier();
        if(listePanier.isEmpty()){
            Panier p = new Panier();
            Collection<Produit> listeProduit = new ArrayList<Produit>();
            Produit produit = this.produitFacade.find(idProduit);
            if(produit == null){
                throw new ProduitInconnuException();
            }
            listeProduit.add(produit);
            p.setListeProduit(listeProduit);
            p.setClient(clt);
            try{
               p.setCompte(clt.getCompte()); 
            }catch(Exception e){
                
            }
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
                    Produit produit = this.produitFacade.find(idProduit);
                    if(produit == null){
                        throw new ProduitInconnuException();
                    }
                    
                    listeProduit.add(produit);
                    p.setListeProduit(listeProduit);
                    try{
                        p.setCompte(clt.getCompte()); 
                    }catch(Exception e){
                    }
                    this.panierFacade.edit(p);
                    
                }
            }
            if(!trouve){
                Panier p = new Panier();
                Collection<Produit> listeProduit = new ArrayList<Produit>();
                Produit produit = this.produitFacade.find(idProduit);
                if(produit == null){
                    throw new ProduitInconnuException();
                }
                listeProduit.add(produit);
                p.setListeProduit(listeProduit);
                p.setClient(clt);
                try{
                    p.setCompte(clt.getCompte()); 
                }catch(Exception e){
                }
                this.panierFacade.create(p);
                listePanier.add(p);
                clt.setListePanier(listePanier);
                this.clientFacade.edit(clt);
            }
        }
    }

    @Override
    public Panier getPanierActif(Long idClient) throws ClientInconnuException {
        Client clt = this.clientFacade.find(idClient);
        if(clt == null){
            throw new ClientInconnuException();
        }
        Collection<Panier> listePanier = clt.getListePanier();
        if(!listePanier.isEmpty()){
            for(Panier p : listePanier){
                if(!p.isFlagLivre() && !p.isFlagRegle()){
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Panier> getAllPanierbyClient(Long idClient) throws ClientInconnuException {
        Client clt = this.clientFacade.find(idClient);
        if(clt != null){
            return clt.getListePanier();
            
        }else{
            throw new ClientInconnuException();
        }    
    }
    
}
