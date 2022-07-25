/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Calculadora;

import javax.ejb.Stateless;

/**
 *
 * @author Magoimortal
 */
@Stateless
public class MySession implements MySessionRemote {

    @Override
    public String calculate(double num1, double num2, String operator) {
        String Result = null;
        if (operator.equals("+")) {
            System.out.println("Result" + (num1 + num2));
            Result = String.valueOf((num1 + num2));
            return Result;
        }
        if (operator.equals("-")) {
            System.out.println("Result" + (num1 - num2));
            Result = String.valueOf((num1 - num2));
            return Result;
        }
        if (operator.equals("/")) {
            System.out.println("Result" + (num1 / num2));
            Result = String.valueOf((num1 / num2));
            return Result;
        }
        if (operator .equals( "*")) {
            System.out.println("Result" + (num1 * num2));
            Result = String.valueOf((num1 * num2));
            return Result;
        }
        return Result;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
