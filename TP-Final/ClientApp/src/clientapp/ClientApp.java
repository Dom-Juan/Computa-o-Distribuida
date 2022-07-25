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
        Socket s = null;
        int serverPort = 9090;
        try{
            Scanner input = new Scanner(System.in);
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());
            s = new Socket("localhost", serverPort);
            String data = "";
            if(args.length > 1)
                while(!"[quit]".equals(data)) {
                    if(args[0].equals("1")) {
                        System.out.println("Digite um número de 1 até 6");
                        String number = input.nextLine();
                        data ="1 ";
                        data += number;
                        out.writeUTF(data);
                    } else if(args[0].equals("2")) {
                        data ="2 ";
                        System.out.println("Digite uma palavra para verificar se é palindroma!");
                        String word = input.nextLine();
                        data += word;
                        
                        out.writeUTF(data);
                    } else if(args[0].equals("3")) {
                        data ="3 ";
                        System.out.println("Digite a base");
                        String number = input.nextLine();
                        data += number;
                        data += " ";
                        System.out.println("Digite a altura");
                        number = input.nextLine();
                        data += number;
                        
                        out.writeUTF(data);
                    } else if(args[0].equals("4")) {
                        data ="4 ";
                        System.out.println("Digite a base maior");
                        String number = input.nextLine();
                        data += number;
                        data += " ";
                        
                        System.out.println("Digite a base menor");
                        number = input.nextLine();
                        data += number;
                        data += " ";
                        
                        System.out.println("Digite a altura");
                        number = input.nextLine();
                        data += number;

                        out.writeUTF(data);
                    } else if(args[0].equals("[quit]")) {
                        System.out.println();
                        out.writeUTF(data);
                    } else {
                        System.out.println("ERROR -> Wrong input");
                    }
                    out.writeUTF("aaa");
                    data = in.readUTF();
                    System.out.println("Received: "+ data) ; 
                }
        } catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        } catch (EOFException e){System.out.println("EOF:"+e.getMessage());
	} catch (IOException e){System.out.println("readline:"+e.getMessage());
	} finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
    
}
