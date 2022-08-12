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
        System.out.println("ServerRMI.java\nExecução: ServerRMI();");
        String serverRMIName = "serverRMI";                 // Local do RMI.
        int port = 7070;                                    // porta do RMI.
        InterfaceForServices i = new Services();            // Instanciando e criando interfaces.
        Registry r = LocateRegistry.createRegistry(port);   // Criando o registro no RMI.
        r.rebind(serverRMIName, i);                         // Realizando o bind do RMI.
        System.out.println("port: " + port);
        System.out.println("Address:" + "localhost:"+ port + "/" + (r.list()[0]));
    }
    
    public static void main(String [] args) throws RemoteException {
        System.out.println("ServerRMI.java\nExecução: main();");
        new ServerRMI();
    }
}
