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
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierPanierLocal {
    
        public void payer(long idPanier) throws PanierInconnuException,PanierEmptyException,CompteSoldeNegaException,CompteInconnuException,CompteSommeNegaException,PanierNoAccountLinkedToClientException,ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException ;
        
        public void livrer(long idPanier) throws PanierNonPayeException, PanierInconnuException;
        
        //public void ajouterProduit(long idProduit,long idPanier);
        public void ajouterProduitByClient(long idProduit, Long idClient)throws ClientInconnuException, ProduitInconnuException;
        public void retirerProduit(long idProduit, long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException,PanierAlreadyLivreException;
    
        public void retirerAllProduit(long idProduit, long idPanier)throws PanierInconnuException,ProduitInconnuException, PanierAlreadyLivreException, PanierAlreadyPayeException;
        
        public void supprimerPanier(long idPanier)throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
        
        public Panier getPanier(long idPanier) throws PanierInconnuException;
        
        //public List<Panier> getPanier();    
        public List<Panier> getPanierNonPaye();       
        public List<Panier> getPanierPaye();
        public List<Panier> getPanierNonLivre();
        public List<Panier> getPanierLivre();
        public Panier getPanierActif(Long idClient) throws ClientInconnuException;
        
        public Collection<Panier> getAllPanierbyClient(Long idClient)throws ClientInconnuException;

}
