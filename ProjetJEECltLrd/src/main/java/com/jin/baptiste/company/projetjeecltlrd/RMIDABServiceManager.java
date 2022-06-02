/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import com.jin.baptiste.company.exposition.ExpoLrdRemote;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author LeNonGrillePain
 */
public class RMIDABServiceManager {
    private final static String GLASSFISH_ORB_HOST = "localhost";
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String SERVICES_DAB_EJB_URI = "com.jin.baptiste.company.exposition.ExpoLrdRemote";

    private InitialContext namingContext;
    private ExpoLrdRemote dabRemoteSvc;

    public RMIDABServiceManager() throws NamingException {
        this.initJndi();
        this.retrieveRemoteServicesDAB();
    }

    private void initJndi() throws NamingException {
        Properties jNDIProperties = new Properties();
        jNDIProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", GLASSFISH_ORB_HOST);
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", GLASSFISH_ORB_PORT);
        this.namingContext = new InitialContext(jNDIProperties);
    }

    private void retrieveRemoteServicesDAB() throws NamingException {
        this.dabRemoteSvc = (ExpoLrdRemote) this.namingContext.lookup(SERVICES_DAB_EJB_URI);
    }

    public ExpoLrdRemote getdabRemoteSvc() {
        return dabRemoteSvc;
    }
}
