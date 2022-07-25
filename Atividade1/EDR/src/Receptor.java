package exercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receptor{
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
		try{
            int serverPort = Integer.parseInt(args[0]);
	    	aSocket = new DatagramSocket(serverPort); // create socket at agreed port
			byte[] buffer = new byte[1000];
 			while(true){
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request); 
				String endereco = request.getAddress().toString();
				System.out.println("Connection received from: " + endereco);
    			DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
    				request.getAddress(), request.getPort());
				System.out.println("Message received from client: " + new String(request.getData()));
    			aSocket.send(reply);
    		}
		} catch (SocketException e){ 
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) { 
			System.out.println("IO: " + e.getMessage());
		} finally { 
			if(aSocket != null) aSocket.close();
		}
    }
}
