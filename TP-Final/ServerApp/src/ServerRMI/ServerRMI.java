/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author Juan
 */
public class ServerRMI {
    public ServerRMI() throws RemoteException{
        int port = 9090;
        InterfaceForServices i = new Services();
        Registry r = LocateRegistry.createRegistry(port);
        r.rebind("server-rmi", i);
        System.out.println("port: " + port);
        System.out.println("Address:" + "localhost:"+ port + "/" + (r.list()[0]));
    }
    
    public static void main(String [] args) throws RemoteException {
        new ServerRMI();
    }
}
