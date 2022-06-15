/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Facture;
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
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
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
    public void creerClient(String nom, String prenom, String mail, String adresse)throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientInconnuException, ClientCompteAlreadyLinkedException;
//    public ClientExport getClient(Long id);
    public ClientExport getClientByMail(String mail) throws FormatInvalideException, ClientInconnuException;
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException;
    //public void crediter(Long id, Double somme) throws CompteInconnuException, CompteSommeNegaException;
    //public void debiter(Long id, Double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException;
    //public Position getCompte(Long idCompte) throws CompteInconnuException;
    public List<FactureExport> getFacture(String email);
    
    public ProduitExport getProduit(Long idProduit) throws ProduitInconnuException;
    
    public List<ProduitExport> getProduitByType(TypeProduitEnum type);
    // algo LIKE %%....
    public List<ProduitExport> searchProduitByName(String nom);
        
    public PanierExport getPanier(Long idPanier) throws PanierInconnuException;
    public void payerPanier(Long idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException;
   
    public void ajouterProduitToClient(Long idProduit, Long idClient) throws ClientInconnuException, ProduitInconnuException;
    public void retirerProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;
    
    //Nessecaire?
    public void retirerAllProduit(Long idProduit, Long idPanier) throws PanierInconnuException,ProduitInconnuException,PanierAlreadyPayeException,PanierAlreadyLivreException;
    public void supprimerPanier(Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException;
    
    public PanierExport getPanierActif(Long idClient) throws ClientInconnuException;
    public List<PanierExport> getPanierNonLivreByClient(Long idClient) throws ClientInconnuException;
    public List<ProduitExport> getListProduit();
    
    
    
}
