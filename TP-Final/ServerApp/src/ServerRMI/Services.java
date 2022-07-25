/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author Juan
 */
public class Services extends UnicastRemoteObject implements InterfaceForServices{
    public Services () throws RemoteException{
        super();
    }
    
    
    // Verifica se a string é palindroma.
    @Override
    public String verifyPalindrome(String p) {
        String o = "", r = "";
        o = p;
        for(int i = o.length(); i >= 0; i --) {
            r = r + o.charAt(i);
        }
        String result = (o.equals(r)) ? "Não é palindromo!\n" : "É palindromo!\n";
        return (result);
    }
    
    // Joga jogo da roleta russa.
    @Override
    public String game(int number) {
        Random rand = new Random();
        int last = 5;
        int deathNumber = rand.nextInt(last);
        deathNumber = deathNumber + 1;
        String result = (deathNumber == number) ? "You died" : "You survived";
        return (result);
    }
    
    // Calcula área de um triangulo.
    @Override
    public String calcTriangleArea(double B, double H) {
        return String.valueOf(((B * H )/ 2));
    }
    
    // Calcula área de um trapézio.
    @Override
    public String calcTrapezeArea(double B, double b, double H) {
        return String.valueOf((((B + b )/ 2)*H));
    }
}
