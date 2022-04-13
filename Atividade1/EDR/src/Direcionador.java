import java.net.*;
import java.io.*;

// TCP Server
public class Direcionador {
  public static final int serverPort = 7896; // the server port
  public static void main(String args[]) {
    //int index = 0;
    try {
      ServerSocket listenSocket = new ServerSocket(serverPort);
      while (true) {
        Socket clientSocket = listenSocket.accept();

        // Criando e executando as Threads.
        Connections  connection = new Connections(clientSocket); 
        System.out.println("Thread Criada!!");
        //index++;
      }
    } catch (IOException e) {
      System.out.println("Listen socket:" + e.getMessage());
    }
  }
}

class Connections extends Thread {
  // Parte de TCP.
  DataInputStream in;
  DataOutputStream out;
  Socket clientSocket;

  // Parte de UDP.
  private DatagramSocket aSocket;

  public Connections(Socket aClientSocket) {
    try {
      clientSocket = aClientSocket;
      in = new DataInputStream(clientSocket.getInputStream());
      out = new DataOutputStream(clientSocket.getOutputStream());
      this.aSocket = null;
      this.start();
    } catch (IOException e) {
      System.out.println("Connection:" + e.getMessage());
    }
  }

  @Override
  public void run() {
    try {
      String data = in.readUTF(); // recebe o input do Stream do TCPClient.
      out.writeUTF("Dados enviados com sucesso! " + data); // envia a resposta para o TCPClient.
      int serverPort = 6789;
      this.aSocket = new DatagramSocket(serverPort); // Cria o socket UDP.
      byte[] m = data.getBytes(); // Pega a mensagem em bytes.
      InetAddress aHost = InetAddress.getByName("localhost"); // Cria o host para o UDP.
      byte[] buffer = new byte[1000];
      while(true) {
        // Aceita o request do UDPClient.
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(request);
        System.out.println("Pacote recebido do UDP!");
        // Distribuindo o reply
        DatagramPacket reply = new DatagramPacket(m, data.length(), request.getAddress(), request.getPort());
        aSocket.send(reply);
        System.out.println("Reply enviada!");
      }
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    } finally {
      if (this.aSocket != null)
        this.aSocket.close();
        System.out.println("Socket fechado. . .");
    }
  }
}