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
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
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
    public PanierExport getPanier(Long idPanier) throws PanierInconnuException {
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
    }

    @Override
    public void payerPanier(Long idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException {
        this.metierPanier.payer(idPanier);
    }
    
    @Override
    public void retirerProduit(Long idProduit, Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        this.metierPanier.retirerProduit(idProduit, idPanier);
    }

    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyLivreException, PanierAlreadyPayeException {
        this.metierPanier.retirerAllProduit(idProduit, idPanier);
    }

    @Override
    public void supprimerPanier(Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        this.metierPanier.supprimerPanier(idPanier);

    }
    
    @Override
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException {
        
        Produit p = this.metierProduit.getProduit(idProduit);
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
        return pe;
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
    public void ajouterProduitToClient(Long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException {
        this.metierPanier.ajouterProduitByClient(idProduit,idClient);
    }

    @Override
    public PanierExport getPanierActif(Long idClient) throws ClientInconnuException {
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
    }

//    @Override
//    public List<PanierExport> getAllPanierHisto(Long idClient) throws ClientInconnuException {
//        List<PanierExport> listePanierExport = new ArrayList<PanierExport>();
//        Collection<Panier> listePanier = this.metierPanier.getAllPanierbyClient(idClient);
//        for (Panier p : listePanier){
//            if(p != null){
//                Long idP = p.getId();
//                Collection<Long> listeIdProduit = new ArrayList<Long>();
//                Collection<Produit> listeProduit = p.getListeProduit();
//                for( Produit prod : listeProduit){
//                    listeIdProduit.add(prod.getId());
//                }
//                Map<Produit,Integer> mapProduit = p.getNbProduit();
//                Map<String,Integer> mapIdProduit = new HashMap<String,Integer>();
//                Set<Map.Entry<Produit,Integer>> nbProduit = mapProduit.entrySet();
//                for(Map.Entry<Produit,Integer> nbP : nbProduit){
//                    mapIdProduit.put(nbP.getKey().getNom(),nbP.getValue());
//                }
//                PanierExport pe =new PanierExport(p.getId(), p.isFlagLivre(), p.isFlagRegle(), listeIdProduit, p.getPrixTTC() , p.getDate(),mapIdProduit);
//                Long idClientLocal = null;
//                Long idCompte = null;
//                try{
//                    idClientLocal = p.getClient().getId();
//                    pe.setIdClient(idClientLocal);
//                }catch(Exception e){
//                }
//                try{
//                    idCompte = p.getCompte().getId();
//                    pe.setIdCompte(idCompte);
//                }catch(Exception e){
//
//                }
//                listePanierExport.add(pe);
//            }
//        }
//        return listePanierExport;
//    }

    @Override
    public List<PanierExport> getPanierNonLivreByClient(Long idClient) throws ClientInconnuException {
        List<Panier> listePanier = this.metierPanier.getPanierNonLivreByClient(idClient);
        if(!listePanier.isEmpty()){
            List<PanierExport> listePanierExport = new ArrayList<PanierExport>();
            for( Panier p : listePanier){
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
                Long idCompte = null;
                try{
                    pe.setIdClient(idClient);
                }catch(Exception e){    
                }
                try{
                    idCompte = p.getCompte().getId();
                    pe.setIdCompte(idCompte);
                }catch(Exception e){

                }
                listePanierExport.add(pe);
            }

            return listePanierExport;
        }else{
            return new ArrayList<PanierExport>();
        }
    }
}
