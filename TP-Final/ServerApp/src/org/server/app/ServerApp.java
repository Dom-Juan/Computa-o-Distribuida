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
        if(args.length > 1) serverPort = Integer.parseInt(args[2]);
        try{
            s = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = s.accept();
                System.out.println("Received: "+ clientSocket);
                MakeConnection connection = new MakeConnection(clientSocket);
            }
        } catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        } catch (EOFException e){System.out.println("EOF:"+e.getMessage());
	} catch (IOException e){System.out.println("readline:"+e.getMessage());
	} finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}

class MakeConnection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket client;
    InterfaceForServices services;
    public MakeConnection(Socket s) throws NotBoundException, IOException {
        try {
            client = s;
            System.out.println("Conexão em: " + client);
            System.out.println("IP: " + client.getLocalAddress());
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            services = (InterfaceForServices) Naming.lookup("rmi://localhost:1099/server-rmi");
            this.start();
        } catch(IOException e) {
            System.out.println("Connection error -> " + e.getMessage());
        }
    }
    
    public void run() {
        try {
            while(true) {
                String data = in.readUTF();
                String [] args = data.split(" ");
                String response = "";
                if(String.valueOf(args[0]).equals("1")) {
                    response = services.game(Integer.valueOf(args[1]));
                } else if(String.valueOf(args[0]).equals("2")) {
                    response = services.verifyPalindrome(String.valueOf(args[1]));
                } else if(String.valueOf(args[0]).equals("3")) {
                    response = services.calcTriangleArea(Double.valueOf(args[1]), Double.valueOf(args[2]));
                } else if(String.valueOf(args[0]).equals("4")) {
                    response = services.calcTrapezeArea(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]));
                } else if(String.valueOf(args[0]).equals("[quit]")) {
                    out.writeUTF("[quit]");
                    client.close();
                } else {
                    response = "ERROR -> Service not found!";
                }
                out.writeUTF("Response:\n >: " + response);
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
