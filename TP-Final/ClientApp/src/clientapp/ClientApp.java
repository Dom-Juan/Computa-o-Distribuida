/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientapp;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Juan
 */
public class ClientApp {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket s = null; // Criando o socket com null.
        int serverPort = 9090;
        try {
            Scanner input = new Scanner(System.in); // Criado Input de leitura.
            s = new Socket("localhost", serverPort); // Socket TCP instanciado.
            DataInputStream in = new DataInputStream(s.getInputStream()); // "Input" do TCP.
            DataOutputStream out = new DataOutputStream(s.getOutputStream()); // "Output" do TCP.
            String data = null; // String para enviar e receber dados.
            if (args.length > 0) { // Apenas se executar com argumentos o programa é aberto.
                while (!"[quit]".equals(data)) {
                    data = "";
                    StringBuilder builder = new StringBuilder(); // construtor de strings.
                    if (args[0].equals("1")) {  // Serviço do jogo.
                        System.out.println("Digite um número de 1 até 6");
                        String number = input.nextLine();   // número de entrada do usuario pra jogar o jogo.
                        builder.append("1 ");
                        builder.append(number);
                        data = builder.toString();

                        out.writeUTF(data);
                        data = in.readUTF();
                        System.out.println("Received: " + data);
                    } else if (args[0].equals("2")) {   // Serviço de conversão de temperatura.
                        builder.append("2 ");
                        System.out.println("Digite a temperatura atual: ");
                        String number = input.nextLine();   // valor da temperatura escolhida.
                        builder.append(number);
                        builder.append(" ");

                        System.out.println("C (Celsius)");
                        System.out.println("K (Kelvin)");
                        System.out.println("F (Fahrenheit)");
                        System.out.println("RA (Rankine)");
                        System.out.println("Digite a letra da temperatura atual:");
                        String temp = input.nextLine(); // Criando e pegando a temperatura.
                        builder.append(temp);
                        builder.append(" ");

                        System.out.println("C (Celsius)");
                        System.out.println("K (Kelvin)");
                        System.out.println("F (Fahrenheit)");
                        System.out.println("RA (Rankine)");
                        System.out.println("Digite a letra da temperatura de conversão:");
                        temp = input.nextLine();    // Pegando a temperatura para qual será convertida.
                        builder.append(temp);

                        data = builder.toString();
                        out.writeUTF(data);

                        data = in.readUTF();
                        System.out.println("Received: " + data);
                    } else if (args[0].equals("3")) {   // Serviço de converção de moedas.
                        builder.append("3 ");

                        System.out.println("Quer converter uma moeda para reais?");
                        System.out.println("0 -> Não");
                        System.out.println("1 -> Sim");
                        String op = input.nextLine();

                        boolean opMade = op.equals("1") ? true : false;
                        if(opMade) {
                            System.out.println("Escolha a moeda abaixo:");
                            System.out.println("dollar");
                            System.out.println("euro");
                            System.out.println("iene");
                            System.out.println("peso");
                            System.out.println("Escreve exatamente qual moeda você quer:");
                            String currency = input.nextLine();
                            
                            System.out.println("Digite a quantidade: ");
                            String value = input.nextLine();


                            builder.append(value);
                            builder.append(" ");
                            builder.append(currency);
                            builder.append(" ");
                            builder.append("real");
                            
                            data = builder.toString();
                        } else {
                            System.out.println("Digite a quantidade de reais: ");
                            String value = input.nextLine();
    
                            System.out.println("Escolha a moeda abaixo:");
                            System.out.println("dollar");
                            System.out.println("euro");
                            System.out.println("iene");
                            System.out.println("peso");
                            System.out.println("Escreve exatamente qual moeda você quer:");
                            String currency = input.nextLine();

                            builder.append(value);
                            builder.append(" ");
                            builder.append("real");
                            builder.append(" ");
                            builder.append(currency);
    
                            data = builder.toString();
                        }

                        out.writeUTF(data);
                        data = in.readUTF();
                        System.out.println("Received: " + data);
                    } else if (args[0].equals("4")) {   // Serviço de calculo de imposto de renda.
                        builder.append("4 ");
                        System.out.println("Digite seu salário atual: ");
                        String income = input.nextLine();
                        builder.append(income);         // Salário.

                        System.out.println("Digite a quantidade de dependentes: ");
                        String numDependentsOfTaxPayer = input.nextLine();    // Dependentes.
                        builder.append(numDependentsOfTaxPayer);

                        data = builder.toString();
                        out.writeUTF(data);
                        data = in.readUTF();
                        System.out.println("Received: " + data);
                    } else if (args[0].equals("[quit]")) {  // Envia um comando de disconnect para o server.
                        builder.append("5");

                        data = builder.toString();
                        out.writeUTF(data);
                        break;
                    } else {
                        System.out.println("ERROR -> entrada errada");
                    }

                    System.out.println("Escolha uma das opções: ");
                    System.out.println("1 -> Roleta russa");
                    System.out.println("2 -> Conversão de temperatura");
                    System.out.println("3 -> Conversão de moeda");
                    System.out.println("4 -> Calcular imposto de renda");
                    System.out.println("[quit] -> sair do programa");
                    String op = input.nextLine();
                    args[0] = op;
                }
            }
            s.close();          // Fechando o socket TCP.
            input.close();      // Fechando o Input stream.
            System.exit(0);     // Fechando a aplicação.
        // Tratamento de erros abaixo.
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally { // fechar o socket caso um erro ocorra.
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }
}
