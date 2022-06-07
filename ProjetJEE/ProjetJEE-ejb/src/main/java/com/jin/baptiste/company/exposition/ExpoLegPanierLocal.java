/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

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
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegPanierLocal {
    // Action du Client sur Panier
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
    
    //Suivie du Panier (tous les Panier)
    public List<PanierExport> getAllPanierHisto (Long idClient) throws ClientInconnuException;
    

}
