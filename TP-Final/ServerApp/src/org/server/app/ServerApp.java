/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.server.app;

import ServerRMI.InterfaceForServices;
import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;

/**
 *
 * @author Juan
 */
public class ServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException {
        ServerSocket s = null;
        int serverPort = 9090;
        if(args.length > 1) serverPort = Integer.parseInt(args[0]);
        try{
            System.out.println("Iniciando o servidor...");
            s = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = s.accept();                               // Aceitando a conexao do cliente.
                System.out.println("Received: "+ clientSocket);                 // Mensagem monstrando a conexao
                MakeConnection connection = new MakeConnection(clientSocket);   // Criando e instanciando conexão,
                                                                                //para conectalo ao ServerRMI.
            }
        } catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        } catch (EOFException e){System.out.println("EOF:"+e.getMessage());
	} catch (IOException e){System.out.println("readline:"+e.getMessage());
	} finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}

class MakeConnection extends Thread {
    DataInputStream in;     // Entrada de dados do server TCP.
    DataOutputStream out;   // Saida de dados do server TCP.
    Socket client;          // Criando o server cliente.
    InterfaceForServices services;  // Criando a interface de serviços.
    // Função que realiza a conexão com o RMI server.
    public MakeConnection(Socket s) throws NotBoundException, IOException {
        try {
            client = s; // adiciona o cliente a esse conexão do server, para depois se comunicar com o RMI.
            System.out.println("Conexão em: " + client);
            System.out.println("IP: " + client.getLocalAddress());
            in = new DataInputStream(client.getInputStream());      // Recebendo dados do Cliente.
            out = new DataOutputStream(client.getOutputStream());   // Enviando dados de volta ao cliente.
            System.out.println("Endereço: " + "rmi://localhost:7070/serverRMI");
            // Varaiavel service recebe os serviços após o lookup.
            services = (InterfaceForServices) Naming.lookup("rmi://localhost:7070/serverRMI");
            System.out.println("Serviço: " + services);
            this.start();   // iniciando a thread.
        } catch(IOException e) {
            System.out.println("Connection error -> " + e.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                String data = in.readUTF();                 // recebendo as entradas do Cliente.
                String [] requestData = data.split(" ");    // Fazendo o split de string de dados para vetor de strings com string.
                System.out.println(requestData[0] + " " + requestData[1]);
                // requestData é dividio em partes, na posição 0 fica qual serviço o cliente quer, as outras partes são reservadas para os dados.
                String response = "";                       // Criando a resposta do servidor.
                if(String.valueOf(requestData[0]).equals("1")) {    // Caso do cliente pedir pro service retornar o jogo para ele.
                    System.out.println("Número escolhido: " + requestData[1]);
                    response = services.game(Integer.valueOf(requestData[1]));
                } else if(String.valueOf(requestData[0]).equals("2")) {     // Caso do cliente pedir para converter temperatura.
                    System.out.println("Temperatura atual: " + requestData[1]+"\n Conversão para: " + requestData[2]);
                    response = services.calcTempConversion(Double.valueOf(requestData[1]), String.valueOf(requestData[2]), String.valueOf(requestData[3]));
                } else if(String.valueOf(requestData[0]).equals("3")) {     // Caso do cliente queira converter a moeda real.
                    System.out.println("Valor em Real: " + requestData[1]+"\n moeda a ser convertida: " + requestData[2]);
                    response = services.conversionMoneyValue(Double.valueOf(requestData[1]), String.valueOf(requestData[2]), String.valueOf(requestData[3]));
                } else if(String.valueOf(requestData[0]).equals("4")) {     // Caso do cliente pedir para calcular o imposto de renda.
                    System.out.println("Valor em Real do salário base: " + requestData[1]);
                    response = services.calcIncomeTax(Float.valueOf(requestData[1]), Integer.valueOf(requestData[2]));
                } else if(String.valueOf(requestData[0]).equals("5")) {     // Enviar o dado para desconexão do cliente e desconecta-lo do server.
                    out.writeUTF("[quit]");
                    client.close();
                } else {
                    response = "ERROR -> Service not found!";
                }
                out.writeUTF(response);
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {/* close failed */
            }
        }
    }
}
