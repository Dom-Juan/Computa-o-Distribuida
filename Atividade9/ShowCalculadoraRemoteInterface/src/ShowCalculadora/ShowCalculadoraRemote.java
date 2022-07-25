/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ShowCalculadora;

import javax.ejb.Remote;

/**
 *
 * @author Juan
 */
@Remote
public interface ShowCalculadoraRemote {

    void ShowCalculadora(String Result);
    String getResult(String Result);
}
