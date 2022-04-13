import java.net.*;
import java.io.*;

public class Emissor{
  
  public static void main(String args[]) {
    // arguments supply message and hostname
    String ip = "localhost";

    // Cria as interfaces de conexôes que funcionam por Runnable/Threads
    TCPConnectionClient tcp1 = new TCPConnectionClient(ip, "a");
    TCPConnectionClient tcp2 = new TCPConnectionClient(ip, "b");
    TCPConnectionClient tcp3 = new TCPConnectionClient(ip, "c");

    // implementação das Threads.
    Thread t1 = new Thread(tcp1);
    Thread t2 = new Thread(tcp2);
    Thread t3 = new Thread(tcp3);

    // incio das Threads.
    t1.start();
    t2.start();
    t3.start();

    System.out.println(t1.getName());
    System.out.println(t2.getName());
    System.out.println(t3.getName());
  }
}

class TCPConnectionClient implements Runnable {
  private String ip;
  private String info;

  public TCPConnectionClient(String ip, String info) {
    this.ip = ip;
    this.info = info;
  }

  @Override
  public void run() {
    Socket socket = null;
    try {
      int serverPort = 7896;
      socket = new Socket(this.ip, serverPort);
      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      out.writeUTF(this.info); // UTF is a string encoding see Sn. 4.4
      String data = in.readUTF(); // read a line of data from the stream
      System.out.println("Received: " + data);
    } catch (UnknownHostException e) {
      System.out.println("Socket:" + e.getMessage());
    } catch (EOFException e) {
      System.out.println("EOF:" + e.getMessage());
    } catch (IOException e) {
      System.out.println("readline:" + e.getMessage());
    } finally {
      if (socket != null)
        try {
          socket.close();
        } catch (IOException e) {
          System.out.println("close:" + e.getMessage());
        }
    }
  }
}
