package exercicio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Direcionador {
	public static void main (String args[]) {
		try{
			int serverPort = Integer.parseInt(args[0]);
			try (ServerSocket listenSocket = new ServerSocket(serverPort)) {
                while(true) {
                	Socket clientSocket = listenSocket.accept();
                	new Connection(clientSocket);
                }
            }
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	DatagramSocket aSocket;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			aSocket = new DatagramSocket();    //UDP Socket	
			clientSocket = aClientSocket;		// TCP Socket
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort0 = 6789;
			int serverPort1 = 6790;
			int serverPort2 = 6791;
			
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());

			String data = in.readUTF();	
			out.writeUTF("Mensage Received");                  // read a line of data from the stream
			byte [] m = data.getBytes();
            DatagramPacket request0 = new DatagramPacket(m,  data.length(), aHost, serverPort0);
			DatagramPacket request1 = new DatagramPacket(m,  data.length(), aHost, serverPort1);
			DatagramPacket request2 = new DatagramPacket(m,  data.length(), aHost, serverPort2);

			aSocket.send(request0);
			aSocket.send(request1);
			aSocket.send(request2);
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
}