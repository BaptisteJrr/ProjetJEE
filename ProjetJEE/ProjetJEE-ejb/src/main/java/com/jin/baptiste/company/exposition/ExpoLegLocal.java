/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegLocal {
    // Action du Client sur le Compte 

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
    public void creerClient(String nom, String prenom, String mail, String adresse)throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientInconnuException, ClientCompteAlreadyLinkedException;
//    public ClientExport getClient(Long id);

    /**
     *
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     */
    public ClientExport getClientByMail(String mail) throws FormatInvalideException, ClientInconnuException;

    /**
     *
     * @param solde
     * @param mail
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException;
    //public void crediter(Long id, Double somme) throws CompteInconnuException, CompteSommeNegaException;
    //public void debiter(Long id, Double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException;
    //public Position getCompte(Long idCompte) throws CompteInconnuException;

    /**
     *
     * @param email
     * @return
     */
    public List<FactureExport> getFacture(String email);
    
    /**
     *
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     *
     * @param type
     * @return
     */
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    // algo LIKE %%....

    /**
     *
     * @param nom
     * @return
     */
    public List<ProduitExport> searchProduitByName(String nom);
        
    /**
     *
     * @param idPanier
     * @return
     * @throws PanierInconnuException
     */
    public PanierExport getPanier(Long idPanier) throws PanierInconnuException;

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
    public void payerPanier(Long idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException;
   
    /**
     *
     * @param idProduit
     * @param idClient
     * @throws ClientInconnuException
     * @throws ProduitInconnuException
     */
    public void ajouterProduitToClient(Long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException;

    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;
    
    //Nessecaire?

    /**
     *
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerAllProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;

    /**
     *
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void supprimerPanier(Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
    
    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public PanierExport getPanierActif(Long idClient) throws ClientInconnuException;

    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public List<PanierExport> getPanierNonLivreByClient(Long idClient) throws ClientInconnuException;

    /**
     *
     * @return
     */
    public List<ProduitExport> getListProduit();
    
    
    
}
