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
    // Listagem dos serviços.
    public String game(int number) throws RemoteException;
    public String calcTempConversion(double temp, String type, String conversion) throws RemoteException;
    public String conversionMoneyValue(double real, String type, String conversion) throws RemoteException;
    public String calcIncomeTax(float base,int numDp) throws RemoteException;
}
