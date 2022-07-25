/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalculadoraRI;

/**
 *
 * @author Juan
 */     
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements CalculadoraRemoteInterface {
        
    public Server() {}
    
    @Override
    public String calculate(double num1, double num2, String operator) {
        String Result = null;
        if (operator.equals("+")) {
            //System.out.println("Result " + (num1 + num2));
            Result = String.valueOf((num1 + num2));
            return Result;
        }
        if (operator.equals("-")) {
            //System.out.println("Result " + (num1 - num2));
            Result = String.valueOf((num1 - num2));
            return Result;
        }
        if (operator.equals("/")) {
            //System.out.println("Result " + (num1 / num2));
            Result = String.valueOf((num1 / num2));
            return Result;
        }
        if (operator .equals( "*")) {
            //System.out.println("Result " + (num1 * num2));
            Result = String.valueOf((num1 * num2));
            return Result;
        }
        return Result;
    }
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            CalculadoraRemoteInterface stub = (CalculadoraRemoteInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("calculate", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
