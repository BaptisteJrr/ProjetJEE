/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Wang
 */
public interface ExpoLrdRemoteCompta {
//        public List<PanierExport> getPanierNonPaye();       
        public List<PanierExport> getPanierPaye();
//        public List<PanierExport> getPanierNonLivre();
//        public List<PanierExport> getPanierLivre();
//        public PanierExport getPanierActif(Long idClient) throws ClientInconnuException;
        
        public Collection<PanierExport> getPanierPayebyClient(Long idClient)throws ClientInconnuException;
}
