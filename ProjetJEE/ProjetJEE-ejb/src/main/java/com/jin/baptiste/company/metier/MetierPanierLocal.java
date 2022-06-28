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
    public void payer(long idPanier) throws PanierInconnuException,PanierEmptyException,CompteSoldeNegaException,CompteInconnuException,CompteSommeNegaException,PanierNoAccountLinkedToClientException,ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException ;
        
    /**
     *
     * @param idPanier
     * @throws PanierNonPayeException
     * @throws PanierInconnuException
     */
    public void livrer(long idPanier) throws PanierNonPayeException, PanierInconnuException;
        
        //public void ajouterProduit(long idProduit,long idPanier);

    /**
     *
     * @param idProduit
     * @param idClient
     * @throws ClientInconnuException
     * @throws ProduitInconnuException
     */
        public void ajouterProduitByClient(long idProduit, Long idClient)throws ClientInconnuException, ProduitInconnuException;

    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerProduit(long idProduit, long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException,PanierAlreadyLivreException;
    
    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyLivreException
     * @throws PanierAlreadyPayeException
     */
    public void retirerAllProduit(long idProduit, long idPanier)throws PanierInconnuException,ProduitInconnuException, PanierAlreadyLivreException, PanierAlreadyPayeException;
        
    /**
     *
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void supprimerPanier(long idPanier)throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
        
    /**
     *
     * @param idPanier
     * @return
     * @throws PanierInconnuException
     */
    public Panier getPanier(long idPanier) throws PanierInconnuException;
        
        //public List<Panier> getPanier();    
//        public List<Panier> getPanierNonPaye();       

    /**
     *
     * @return
     */
        public List<Panier> getPanierPaye();
        //suivre la livraison par client?   public List<Panier> getPanierNonLivreByClient(long idClient);

    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
        public List<Panier> getPanierNonLivreByClient(long idClient) throws ClientInconnuException;

    /**
     *
     * @return
     */
    public List<Panier> getPanierNonLivre();

    /**
     *
     * @return
     */
    public List<Panier> getPanierLivre();

    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public Panier getPanierActif(Long idClient) throws ClientInconnuException;
        

}
