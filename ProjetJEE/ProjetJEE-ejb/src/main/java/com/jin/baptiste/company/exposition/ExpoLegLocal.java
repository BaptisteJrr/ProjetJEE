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

    /**
     * Création du client ainsi que son compte
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

    /**
     * Récupération d'un client à partir de son mail
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     */
    public ClientExport getClientByMail(String mail) throws FormatInvalideException, ClientInconnuException;

    /**
     * Création du compte du client
     * @param solde
     * @param mail
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException;
    
    /**
     * Récupération des factures d'un client a partir de son mail
     * @param email
     * @return
     */
    public List<FactureExport> getFacture(String email);
    
    /**
     * Permet de récuperer toutes les informations d'un produit à partir de son id
     * @param idProduit
     * @return
     * @throws ProduitInconnuException
     */
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    /**
     * Récupère la liste des produits du type saisie
     * @param type
     * @return
     */
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);

    /**
     * Permet de trouver tous les produits contenant la chaine de caractère saisie
     * @param nom
     * @return
     */
    public List<ProduitExport> searchProduitByName(String nom);
        
    /**
     * Récupérer un panier à partir de son id
     * @param idPanier
     * @return
     * @throws PanierInconnuException
     */
    public PanierExport getPanier(Long idPanier) throws PanierInconnuException;

    /**
     * payer le panier en fonction de son id, se base sur le client, son compte et le(s) produit(s) présent(s) dans le panier
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
     * permet d'ajouter un produit donnée au panier actif d'un client (et le créer si nécessaire)
     * @param idProduit
     * @param idClient
     * @throws ClientInconnuException
     * @throws ProduitInconnuException
     */
    public void ajouterProduitToClient(Long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException;

    /**
     * Permet de retirer un exemplaire d'un produit dans un panier
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;
    

    /**
     * Permet de retirer tous les exemplaires d'un produit dans un panier
     * @param idProduit
     * @param idPanier
     * @throws PanierInconnuException
     * @throws ProduitInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void retirerAllProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;

    /**
     * Permet de supprimer un panier à partir de son id
     * @param idPanier
     * @throws PanierInconnuException
     * @throws PanierAlreadyPayeException
     * @throws PanierAlreadyLivreException
     */
    public void supprimerPanier(Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
    
    /**
     * Permet de récupérer le panier actif d'un client à partir de son id.
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public PanierExport getPanierActif(Long idClient) throws ClientInconnuException;

    /**
     * Permet de récupérer l'ensemble des paniers d'un client en attente de livraison
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public List<PanierExport> getPanierNonLivreByClient(Long idClient) throws ClientInconnuException;

    /**
     * Permet de récupérer l'ensemble des produits existants
     * @return
     */
    public List<ProduitExport> getListProduit();
    
    
    
}
