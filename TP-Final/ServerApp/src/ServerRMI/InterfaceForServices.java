/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Juan
 */
// Interface de serviços
public interface InterfaceForServices extends Remote {
    public String verifyPalindrome(String p) throws RemoteException;
    public String game(int number) throws RemoteException;
    public String calcTriangleArea(double B, double H) throws RemoteException;
    public String calcTrapezeArea(double B, double b, double H) throws RemoteException;
}
