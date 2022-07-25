/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientcalculadora;

import Calculadora.MySessionRemote;
import ShowCalculadora.ShowCalculadoraRemote;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Magoimortal
 */
public class Main {
    
    @EJB
    private static ShowCalculadoraRemote showCalculadora;
    
    @EJB
    private static MySessionRemote mySession;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Criando objeto Scanner para input
        // Adicionando valores
        System.out.println("Digite o primeiro numero: ");
        String num1 = input.nextLine();
        
        System.out.println("Digite o operador: ");
        String operator = input.nextLine();
        
        System.out.println("Digite o segundo numero: ");
        String num2 = input.nextLine();
        // Chamando o método remoto.
        String Result = mySession.calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
        showCalculadora.ShowCalculadora(Result);
        System.out.println(showCalculadora.getResult(Result));
    }
}
