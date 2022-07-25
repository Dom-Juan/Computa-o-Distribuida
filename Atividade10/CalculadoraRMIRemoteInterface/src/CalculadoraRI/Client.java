/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalculadoraRI;

/**
 *
 * @author Juan
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Scanner input = new Scanner(System.in); // Criando objeto Scanner para input
            // Adicionando valores
            System.out.println("Digite o primeiro numero: ");
            String num1 = input.nextLine();

            System.out.println("Digite o operador: ");
            String operator = input.nextLine();

            System.out.println("Digite o segundo numero: ");
            String num2 = input.nextLine();
            
            // Chamando o método remoto.
            Registry registry = LocateRegistry.getRegistry(host);
            CalculadoraRemoteInterface stub = (CalculadoraRemoteInterface) registry.lookup("calculate");
            String response = stub.calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
            
            //System.out.println(showCalculadora.getResult(response));
            System.out.println("response: " + response);
        } catch (NumberFormatException | NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
