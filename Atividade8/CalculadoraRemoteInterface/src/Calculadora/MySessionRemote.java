/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package Calculadora;

import javax.ejb.Remote;

/**
 *
 * @author Magoimortal
 */
@Remote
public interface MySessionRemote {

    String calculate(double num1, double num2, String operator);
    
}
