/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.me.calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juan
 */
@WebService(serviceName = "CalculatorWS")
public class CalculatorWS {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param num1
     * @param num2
     * @param operator
     * @return 
     */
    @WebMethod(operationName = "calculate")
    public String calculate(@WebParam(name = "num1") int num1, @WebParam(name = "operator") String operator, @WebParam(name = "num2") int num2) {
        //TODO write your implementation code here:
        if(operator.equals("+")) return String.valueOf(num1 + num2);
        if(operator.equals("-")) return String.valueOf(num1 - num2);
        if(operator.equals("/")) return String.valueOf(num1 / num2);
        if(operator.equals("*")) return String.valueOf(num1 * num2);
        if(operator.equals("^")) return String.valueOf(Math.pow(num2, num2));
        return null;
    }
    
}
