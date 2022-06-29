/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Panier;
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
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierPanierLocal {
    
    /**
     * payer panier
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
    public void payer(long idPanier) throws PanierInconnuException,PanierEmptyException,CompteSoldeNegaException,CompteInconnuException,CompteSommeNegaException,PanierNoAccountLinkedToClientException,ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException ;
        
    /**
     * livrer panier
     * @param idPanier
     * @throws PanierNonPayeException
     * @throws PanierInconnuException
     */
    public void livrer(long idPanier) throws PanierNonPayeException, PanierInconnuException;
        
        //public void ajouterProduit(long idProduit,long idPanier);

    /**
     * ajouter un produit dans le panier actif d'un client, s'il n'y pas de parnier courrant, la creation d'un panier est automatique
     * @param idProduit
     * @param idClient
     * @throws ClientInconnuException
     * @throws ProduitInconnuException
     */
        public void ajouterProduitByClient(long idProduit, Long idClient)throws ClientInconnuException, ProduitInconnuException;

    /**
     * retirer un produit d'un panier
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerProduit(long idProduit, long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException,PanierAlreadyLivreException;
    
    /**
     * retirer tous les produits
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyLivreException
     * @throws PanierAlreadyPayeException
     */
    public void retirerAllProduit(long idProduit, long idPanier)throws PanierInconnuException,ProduitInconnuException, PanierAlreadyLivreException, PanierAlreadyPayeException;
        
    /**
     * supprimer panier
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void supprimerPanier(long idPanier)throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
        
    /**
     * get panier par idPanier
     * @param idPanier
     * @return panier
     * @throws PanierInconnuException
     */
    public Panier getPanier(long idPanier) throws PanierInconnuException;
          

    /**
     * get la list Panier paye
     * @return List<Panier>
     */
        public List<Panier> getPanierPaye();

    /**
     * get la listPanier non livre d'un client
     * @param idClient
     * @return list<Panier>
     * @throws ClientInconnuException
     */
        public List<Panier> getPanierNonLivreByClient(long idClient) throws ClientInconnuException;

    /**
     * get la listPanier non livre
     * @return list<Panier>
     */
    public List<Panier> getPanierNonLivre();

    /**
     * get la listPanier livre
     * @return list<Panier>
     */
    public List<Panier> getPanierLivre();

    /**
     * get panier courrant d'un client
     * @param idClient
     * @return panier
     * @throws ClientInconnuException
     */
    public Panier getPanierActif(Long idClient) throws ClientInconnuException;
        

}
