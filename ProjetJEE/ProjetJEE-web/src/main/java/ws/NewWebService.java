/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.jin.baptiste.company.NewSessionBean;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Baptiste
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    @EJB
    private NewSessionBean ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "businessMethod")
    @Oneway
    public void businessMethod() {
        ejbRef.businessMethod();
    }
    
}
