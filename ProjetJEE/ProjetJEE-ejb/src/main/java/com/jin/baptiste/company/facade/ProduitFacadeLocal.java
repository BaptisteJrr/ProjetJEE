/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.entities.TypeProduitEnum;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface ProduitFacadeLocal {

    void create(Produit produit);

    void edit(Produit produit);

    void remove(Produit produit);

    Produit find(Object id);

    List<Produit> findAll();

    List<Produit> findRange(int[] range);

    int count();
    
    public void creerProduit(String nom, TypeProduitEnum type, double prixHT, String description, int stock);
    
    public void vendreProduit(long idProduit, int quantite);
    
    public void stockerProduit(long idProduit, int quantite);
    
    
}
