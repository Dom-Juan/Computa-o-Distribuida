/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * Bibliografias:
 * -> https://valorinveste.globo.com/objetivo/organize-as-contas/imposto-de-renda-2022/noticia/2022/05/31/ultimo-dia-para-entrega-do-imposto-de-renda-2022.ghtml
 * -> https://pt.wikipedia.org/wiki/Escalas_termom%C3%A9tricas
 * -> https://wise.com/br/currency-converter/brl-to-jpy-rate
 * 
*/

/**
 *
 * @author Juan
 */
public class Services extends UnicastRemoteObject implements InterfaceForServices {
    public Services() throws RemoteException {
        super();
    }

    // Joga jogo da roleta russa.
    @Override
    public String game(int number) throws RemoteException {
        Random rand = new Random();
        int last = 5;
        int deathNumber = rand.nextInt(last);
        deathNumber = deathNumber + 1;
        String result = (deathNumber == number) ? "Você perdeu!" : "Você venceu!";
        return (result);
    }

    @Override
    public String calcTempConversion(double temp, String type, String conversion) throws RemoteException {
        if (type.equals("C")) { // Contas com Celsius.
            double C = temp, F = 0.0;
            if (conversion.equals("F")) {
                // Conversão para achar Fahrenheit.
                F = (C - 32) / 1.8;
                return String.valueOf(("O valor da conversão Celsius -> Fahrenheit " + (F)));
            } else if (conversion.equals("K")) {
                // Conversão para achar o Kelvin.
                double K = C + 273.15;
                return String.valueOf(("O valor da conversão Celsius -> Kelvin " + (K)));
            } else if (conversion.equals("RA")) {
                // Conversão para achar o Rankine.
                double RA = (C + 273.15) * 1.8;
                return String.valueOf(("O valor da conversão Celsius -> Rankine " + (RA)));
            } else if (conversion.equals(type)) {
                return String.valueOf("O valor da conversão "+conversion+" -> "+type+"  "+temp);
            }
        } else if (type.equals("F")) { // Contas com Fahrenheit.
            // Achando Celsius de Fahrenheit.
            double F = temp;
            double C = (F * 1.8) + 32;
            return String.valueOf("O valor da conversão Fahrenheit -> Celsius " + (C));
        } else if (type.equals("K")) { // Contas com Kelvin.
            if(conversion.equals("C")) { // Converte para Celsius.
                double K = temp;
                double C = K - 273.15;
                return String.valueOf("O valor da conversão Kelvin -> Celsius " + (C));
            } else if(conversion.equals("F")) { // Converte para Fahrenheit.
                double K = temp;
                double F = K * 1.8 - 459.889;
                return String.valueOf("O valor da conversão Kelvin -> Fahrenheit " + (F));
            } else if(conversion.equals("RA")) { // Converte para Rankine.
                double K = temp;
                double RA = (K*1.8);
                return String.valueOf("O valor da conversão Kelvin -> Rankine " + (RA));
            } else if (conversion.equals(type)) {
                return String.valueOf("O valor da conversão "+conversion+" -> "+type+"  "+temp);
            }
        } else if(type.equals("RA")) { // Contas com Rankine.
            if(conversion.equals("C")) { // Converte para Celsius.
                double RA = temp;
                double C = (RA/1.8) - 273.15;
                return String.valueOf("O valor da conversão Rankine -> Celsius " + (C));
            } else if(conversion.equals("F")) { // Converte para Fahrenheit.
                double RA = temp;
                double F = (RA - 459.67);
                return String.valueOf("O valor da conversão Rankine -> Fahrenheit " + (F));
            } else if(conversion.equals("K")) { // Converte para Kelvin.
                double RA = temp;
                double K = (RA/1.8);
                return String.valueOf("O valor da conversão Rankine -> Kelvin " + (K));
            } else if (conversion.equals(type)) {
                return String.valueOf("O valor da conversão "+conversion+" -> "+type+"  "+temp);
            }
        }else {
            return "Tipo de temperatura inválida";
        }
        return null;
    }

    @Override
    public String conversionMoneyValue(double real, String type, String conversion) throws RemoteException {   // Conversão de valores de moedas.
        if(conversion.equals("real")) {
            if(type.equals("dollar")) {
                return String.valueOf("O valor em real de " + real + " dollar(es) " + " é: " + (real*5.16));
            } else if(type.equals("euro")) {
                return String.valueOf("O valor em real de " + real + " euro(s) " +  " é: " + (real*5.33));
            } else if(type.equals("iene")) {
                return String.valueOf("O valor em real de " + real + " inene(s) " +  " é: " + (real/25.78));
            } else if(type.equals("peso argentino")) {
                return String.valueOf("O valor em real de " + real + " peso(s) argentino(s) " +  " é: " + (real/25.72));
            }
        } else {
            if(conversion.equals("dollar")) {
                return String.valueOf("O valor em dollar(es) de " + real + " é: " + (real*0.19));
            } else if(conversion.equals("euro")) {
                return String.valueOf("O valor em euro(s) de " + real + " é: " + (real*0.19));
            } else if(conversion.equals("iene")) {
                return String.valueOf("O valor em iene(s) de " + real + " é: " + (real*25.78));
            } else if(conversion.equals("peso argentino")) {
                return String.valueOf("O valor em peso(s) argentino(s) de " + real + " é: " + (real*26.02));
            }
        }
        
        return "ERRO AO CONVERTER!";
    }

    @Override
    public String calcIncomeTax(float income, int numDependentsOfTaxPayer) throws RemoteException {     // salario base | numero de dependentes.
        float incomeTax;
        if (income < 1903.98f) { // Caso o salário seja menor q o esperado para calculo do imposto.
            return "Isento de imposto";
        } else {
            float aliquot = 1f;   // aliquota
            float deduction = 0f; // dedução

            if (income >= 1903.99f && income <= 2826.65f) { // Casos aonde paga o INSS.
                aliquot = 0.075f;
                deduction = 142.80f;
            } else if (income > 2826.65f && income <= 3751.05f) {
                aliquot = 0.15f;
                deduction = 354.80f;
            } else if (income > 3751.05f && income <= 4664.68) {
                aliquot = 0.225f;
                deduction = 636.13f;
            } else if (income > 4664.68f) {
                aliquot = 0.275f;
                deduction = 869.36f;
            }
            incomeTax = ((income - (numDependentsOfTaxPayer * 2275.08f)) * aliquot) - deduction;  // imposto de renda retido na fonte
        }
        if (incomeTax >= 0)
            return String.valueOf("Imposto de renda retido na fonte : R$ " + incomeTax);
        return "Isento de imposto";
    }
}
