import java.net.*;
import java.io.*;

public class Receptor {
  public static void main(String args[]) {
    // args give message contents and destination hostname
    String ip = "localhost";

    // Cria as interfaces de conexôes que funcionam por Runnable/Threads
    UDPConnectionClient upd1 = new UDPConnectionClient(ip, "a");
    UDPConnectionClient upd2 = new UDPConnectionClient(ip, "b");
    UDPConnectionClient upd3 = new UDPConnectionClient(ip, "c");

    // implementação das Threads.
    Thread t1 = new Thread(upd1);
    Thread t2 = new Thread(upd2);
    Thread t3 = new Thread(upd3);

    // incio das Threads.
    t1.start();
    t2.start();
    t3.start();

    System.out.println(t1.getName());
    System.out.println(t2.getName());
    System.out.println(t3.getName());
  }
}

class UDPConnectionClient implements Runnable {
  private DatagramSocket aSocket;
  private String ip;
  private String info;

  public UDPConnectionClient(String ip, String info) {
    this.aSocket = null;
    this.ip = ip;
    this.info = info;
  }

  @Override
  public void run() {
    try {
      aSocket = new DatagramSocket();
      byte[] m = this.info.getBytes();
      InetAddress aHost = InetAddress.getByName(this.ip);
      int serverPort = 6789;
      DatagramPacket request = new DatagramPacket(m, info.length(), aHost, serverPort);
      aSocket.send(request);
      byte[] buffer = new byte[1000];
      DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
      aSocket.receive(reply);
      System.out.println("Reply: " + new String(reply.getData()));
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    } finally {
      if (aSocket != null)
        aSocket.close();
    }
  }
}
