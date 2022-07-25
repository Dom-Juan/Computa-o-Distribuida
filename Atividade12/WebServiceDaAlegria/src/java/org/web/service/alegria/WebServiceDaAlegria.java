/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.web.service.alegria;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juan
 */
@WebService(serviceName = "WebServiceDaAlegria")
public class WebServiceDaAlegria {

    /**
     * This is a sample web service operation
     * @param x
     * @return 
     */
    @WebMethod(operationName = "calcModule")
    public int calcModule(@WebParam(name = "x") int x) {
        return (x % 2);
    }
    
    /**
     * This is a sample web service operation
     * @param x
     * @return 
     */
    @WebMethod(operationName = "calcSeno")
    public double calcSeno(@WebParam(name = "x") double x) {
        return (Math.sin(Math.toRadians(x)));
    }
    
    /**
     * This is a sample web service operation
     * @param x
     * @param base
     * @return 
     */
    @WebMethod(operationName = "calcLog")
    public double calcLog(@WebParam(name = "x") double x, @WebParam(name = "base") double base) {
        if(base == 0) base = 10;
        double result = ((Math.log(x)/Math.log(base)));
        return result;
    }
}
