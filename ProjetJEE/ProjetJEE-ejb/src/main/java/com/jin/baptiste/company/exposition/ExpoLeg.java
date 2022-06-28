/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Facture;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.metier.MetierFactureLocal;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyLivreException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierEmptyException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNoAccountLinkedToClientException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.FactureExport;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLeg implements ExpoLegLocal {

    @EJB
    private MetierProduitLocal metierProduit;

    @EJB
    private MetierFactureLocal metierFacture;

    @EJB
    private MetierPanierLocal metierPanier;

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private MetierClientLocal metierClient;
    
    
    
    
    
    
    //les autentifications c'est a ce niveau-la a faire pas dans le metier

    /**
     *
     * @param nom
     * @param prenom
     * @param mail
     * @param adresse
     * @throws FormatInvalideException
     * @throws EmptyFieldException
     * @throws ClientAlreadyExistException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */

    @Override
    public void creerClient(String nom, String prenom, String mail, String adresse) throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientInconnuException, ClientCompteAlreadyLinkedException {
            this.metierClient.creerClient(nom, prenom, mail, adresse);
       
    }

    /**
     *
     * @param solde
     * @param mail
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */

    @Override
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException {
            this.metierCompte.creerCompte(solde, mail);

    }


    /**
     *
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     */

    @Override
    public ClientExport getClientByMail(String mail) throws FormatInvalideException, ClientInconnuException {
        Client clt = null;
        clt = this.metierClient.getClientparMail(mail);
        List<Long> listeIdPanier = new ArrayList<Long>();
        for(Panier p : clt.getListePanier()){
            listeIdPanier.add(p.getId());
        }
        Long idCompte = null;
        try{
            idCompte = clt.getCompte().getId();
        }catch(Exception e){
            
        }
        ClientExport clte = new ClientExport(clt.getId(),clt.getNom(),clt.getPrenom(),clt.getEmail(),idCompte,clt.getAdresse(),listeIdPanier);
        return clte;
    }    

    /**
     *
     * @param email
     * @return
     */

    @Override
    public List<FactureExport> getFacture(String email) {
        List<FactureExport> listeFactureExport = new ArrayList<FactureExport>();
        List<Facture> listeFacture = this.metierFacture.getFactureByClient(email);
        for(Facture f : listeFacture){
            listeFactureExport.add(new FactureExport(f.getNom(), f.getPrenom(), f.getMail(), f.getAdresse(), f.getPrixHT(), f.getDate(), f.getNbProduit()));
        }
        
        return listeFactureExport;
    }
    
    /**
     *
     * @param idPanier
     * @return
     * @throws PanierInconnuException
     */
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

    /**
     *
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierEmptyException
     * @throws CompteSoldeNegaException
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     * @throws PanierNoAccountLinkedToClientException
     * @throws ProduitInconnuException
     * @throws ProduitQuantiteNegativeException
     * @throws ProduitStockInsuffisantException
     */
    @Override
    public void payerPanier(Long idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException {
        this.metierPanier.payer(idPanier);
    }
    
    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    @Override
    public void retirerProduit(Long idProduit, Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        this.metierPanier.retirerProduit(idProduit, idPanier);
    }

    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyLivreException
     * @throws PanierAlreadyPayeException
     */
    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyLivreException, PanierAlreadyPayeException {
        this.metierPanier.retirerAllProduit(idProduit, idPanier);
    }

    /**
     *
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    @Override
    public void supprimerPanier(Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        this.metierPanier.supprimerPanier(idPanier);

    }
    
    /**
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    @Override
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException {
        
        Produit p = this.metierProduit.getProduit(idProduit);
        ProduitExport pe = new ProduitExport(p.getId(), p.getNom(),p.getType().toString(), p.getPrixHT(),p.getDescription(), p.getStock() );
        return pe;
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public List<ProduitExport> getProduitByType(TypeProduitEnum type) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.metierProduit.getProduitByType(type);
        for(Produit p : allProduit){
            ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), type.name(), p.getPrixHT(), p.getDescription(), p.getStock() );
            resList.add(produitExport);
        }
        return resList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param nom
     * @return
     */
    @Override
    public List<ProduitExport> searchProduitByName(String nom) {
        List<ProduitExport> resList = new ArrayList<ProduitExport>();
        List<Produit> allProduit = this.metierProduit.searchProduitByName(nom);
        for(Produit p : allProduit){
            ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock() );
            resList.add(produitExport);
        }
        return resList;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param idProduit
     * @param idClient
     * @throws ClientInconnuException
     * @throws ProduitInconnuException
     */
    @Override
    public void ajouterProduitToClient(Long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException {
        this.metierPanier.ajouterProduitByClient(idProduit,idClient);
    }

    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
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


    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */

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

    /**
     *
     * @return
     */
    @Override
    public List<ProduitExport> getListProduit() {
        List<ProduitExport> listProduitExport = new ArrayList<ProduitExport>();
        List<Produit> listProduit = this.metierProduit.getAllProduit();
        for( Produit p : listProduit){
            ProduitExport produitExport = new ProduitExport(p.getId(), p.getNom(), p.getType().name(), p.getPrixHT(), p.getDescription(), p.getStock());
            listProduitExport.add(produitExport);
        }
        
        return listProduitExport;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
