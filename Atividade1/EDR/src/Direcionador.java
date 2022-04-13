import java.net.*;
import java.io.*;

// TCP Server
public class Direcionador {
  public static void main(String args[]) {
    try {
      int serverPort = 7896; // the server port
      ServerSocket listenSocket1 = new ServerSocket(serverPort);
      ServerSocket listenSocket2 = new ServerSocket(serverPort);
      ServerSocket listenSocket3 = new ServerSocket(serverPort);
      while (true) {
        Socket clientSocket1 = listenSocket1.accept();
        Socket clientSocket2 = listenSocket2.accept();
        Socket clientSocket3 = listenSocket3.accept();

        Connections c1 = new Connections(clientSocket1);
        Connections c2 = new Connections(clientSocket2);
        Connections c3 = new Connections(clientSocket3);
      }
    } catch (IOException e) {
      System.out.println("Listen socket:" + e.getMessage());
    }
  }
}

class Connections extends Thread {
  DataInputStream in;
  DataOutputStream out;
  Socket clientSocket;

  public Connections(Socket aClientSocket) {
    try {
      clientSocket = aClientSocket;
      in = new DataInputStream(clientSocket.getInputStream());
      out = new DataOutputStream(clientSocket.getOutputStream());
      this.start();
    } catch (IOException e) {
      System.out.println("Connection:" + e.getMessage());
    }
  }

  @Override
  public void run() {
    try { // an echo server

      String data = in.readUTF(); // read a line of data from the stream
      out.writeUTF("Dadaos enviados!");
      DatagramSocket aSocket = null;
      try {
        aSocket = new DatagramSocket(6789);
        // create socket at agreed port
        byte[] buffer = new byte[1000];
        buffer = data.getBytes();
        while (true) {
          DatagramPacket request = new DatagramPacket(buffer, buffer.length);
          aSocket.receive(request);
          DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
              request.getAddress(), request.getPort());
          aSocket.send(reply);
        }
      } catch (SocketException e) {
        System.out.println("Socket: " + e.getMessage());
      } catch (IOException e) {
        System.out.println("IO: " + e.getMessage());
      } finally {
        if (aSocket != null)
          aSocket.close();
      }
    } catch (EOFException e) {
      System.out.println("EOF:" + e.getMessage());
    } catch (IOException e) {
      System.out.println("readline:" + e.getMessage());
    } finally {
      try {
        clientSocket.close();
      } catch (IOException e) {
        /* close failed */}
    }
  }
}

/*
 * class UDPConnection extends Thread{
 * public static void main(String args[]) {
 * DatagramSocket aSocket = null;
 * try {
 * aSocket = new DatagramSocket(6789);
 * // create socket at agreed port
 * byte[] buffer = new byte[1000];
 * while (true) {
 * DatagramPacket request = new DatagramPacket(buffer, buffer.length);
 * aSocket.receive(request);
 * DatagramPacket reply = new DatagramPacket(request.getData(),
 * request.getLength(),
 * request.getAddress(), request.getPort());
 * aSocket.send(reply);
 * }
 * } catch (SocketException e) {
 * System.out.println("Socket: " + e.getMessage());
 * } catch (IOException e) {
 * System.out.println("IO: " + e.getMessage());
 * } finally {
 * if (aSocket != null)
 * aSocket.close();
 * }
 * }
 * }
 */