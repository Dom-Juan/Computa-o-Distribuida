/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalculadoraRI;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Juan
 */
public interface CalculadoraRemoteInterface extends Remote {
    String calculate(double num1, double num2, String operator) throws RemoteException;
}
