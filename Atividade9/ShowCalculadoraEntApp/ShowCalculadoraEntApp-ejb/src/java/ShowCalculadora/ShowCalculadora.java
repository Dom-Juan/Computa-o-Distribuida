/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ShowCalculadora;

import javax.ejb.Stateless;

/**
 *
 * @author Juan
 */
@Stateless
public class ShowCalculadora implements ShowCalculadoraRemote {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void ShowCalculadora(String Result) {
        System.out.println("Resultador é: " + Result);
    }
    
    @Override
    public String getResult(String Result) {
        System.out.println("Resultador é: " + Result);
        return Result;
    }
}
