/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyLivreException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierEmptyException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNoAccountLinkedToClientException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegPanier implements ExpoLegPanierLocal {

    @EJB
    private MetierClientLocal metierClient;

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private MetierProduitLocal metierProduit;

    @EJB
    private MetierPanierLocal metierPanier;
    
    
    

    
    @Override
    public PanierExport getPanier(Long idPanier) {
        try {
            Panier p = this.metierPanier.getPanier(idPanier);
            if(p != null){
                Long idP = p.getId();
                Collection<Long> listeIdProduit = new ArrayList<Long>();
                Collection<Produit> listeProduit = p.getListeProduit();
                for( Produit prod : listeProduit){
                    listeIdProduit.add(prod.getId());
                }
                Map<Produit,Integer> mapProduit = p.getNbProduit();
                Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
                Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
                for(Map.Entry<Produit,Integer> nbP : nbProduit){
                    mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
                }
                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
                Long idClient = null;
                Long idCompte = null;
                try{
                    idClient = p.getClient().getId();
                    pe.setIdClient(idClient);
                }catch(Exception e){
                }
                try{
                    idCompte = p.getCompte().getId();
                    pe.setIdCompte(idCompte);
                }catch(Exception e){
                    
                }
                return pe;
            }else{
                return null;
            }
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public void payerPanier(Long idPanier) {
        try {
            this.metierPanier.payer(idPanier);
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierEmptyException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSoldeNegaException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSommeNegaException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierNoAccountLinkedToClientException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void retirerProduit(Long idProduit, Long idPanier) {
        try {
            this.metierPanier.retirerProduit(idProduit, idPanier);
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyPayeException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyLivreException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) {
        try {
            this.metierPanier.retirerAllProduit(idProduit, idPanier);
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyLivreException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyPayeException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerPanier(Long idPanier) {
        try {
            this.metierPanier.supprimerPanier(idPanier);
        } catch (PanierInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyPayeException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PanierAlreadyLivreException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ProduitExport getProduit(Long idProduit) {
        try {
            Produit p = this.metierProduit.getProduit(idProduit);
            ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
            return pe;
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ProduitExport> getProduitByType(TypeProduitEnum type) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getType() == type){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), type.name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProduitExport> searchProduitByName(String nom) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.produitFacade.findAll();
        for(Produit p : allProduit){
            if(p.getNom().contains(nom)){
                ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock() );
                resList.add(produitExport);
            }
        }
        return resList;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterProduitToClient(Long idProduit, Long idClient) {
        try {
            this.metierPanier.ajouterProduitByClient(idProduit,idClient);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (ClientInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProduitInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PanierExport getPanierActif(Long idClient) {
        try {
            Panier p = this.metierPanier.getPanierActif(idClient);
            if(p != null){
                Long idP = p.getId();
                Collection<Long> listeIdProduit = new ArrayList<Long>();
                Collection<Produit> listeProduit = p.getListeProduit();
                for( Produit prod : listeProduit){
                    listeIdProduit.add(prod.getId());
                }
                Map<Produit,Integer> mapProduit = p.getNbProduit();
                Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
                Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
                for(Map.Entry<Produit,Integer> nbP : nbProduit){
                    mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
                }
                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
                Long idClientLocal = null;
                Long idCompte = null;
                try{
                    idClientLocal = p.getClient().getId();
                    pe.setIdClient(idClientLocal);
                }catch(Exception e){
                }
                try{
                    idCompte = p.getCompte().getId();
                    pe.setIdCompte(idCompte);
                }catch(Exception e){
                    
                }
                return pe;
            }else{
                return null;
            }
        } catch (ClientInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PanierExport> getAllPanierHisto(Long idClient) {
        try {
            List<PanierExport> ListPanierHistoExp = new ArrayList<PanierExport>();
            Collection<Panier> ListPanierHisto = this.metierPanier.getAllPanierbyClient(idClient);
            for (Panier p : ListPanierHisto){
                if(p != null){
                    Long idP = p.getId();
                    Collection<Long> listeIdProduit = new ArrayList<Long>();
                    Collection<Produit> listeProduit = p.getListeProduit();
                    for( Produit prod : listeProduit){
                        listeIdProduit.add(prod.getId());
                    }
                    Map<Produit,Integer> mapProduit = p.getNbProduit();
                    Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
                    Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
                    for(Map.Entry<Produit,Integer> nbP : nbProduit){
                        mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
                    }
                    PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
                    Long idClientLocal = null;
                    Long idCompte = null;
                    try{
                        idClientLocal = p.getClient().getId();
                        pe.setIdClient(idClientLocal);
                    }catch(Exception e){
                    }
                    try{
                        idCompte = p.getCompte().getId();
                        pe.setIdCompte(idCompte);
                    }catch(Exception e){
                        
                    }
                    ListPanierHistoExp.add(pe);
                }
            }
            return ListPanierHistoExp;
        } catch (ClientInconnuException ex) {
            Logger.getLogger(ExpoLegPanier.class.getName()).log(Level.SEVERE, null, ex);             
        }
        return null;
    }
}
