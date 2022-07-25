
// codigo adaptado
import java.net.*;
import java.io.*;

public class MulticastPeer2 {
  public static void main(String args[]) {
    // args give message contents & destination multicast group (e.g. "228.5.6.7")
    MulticastSocket s = null;
    try {
      InetAddress group = InetAddress.getByName(args[1]);
      s = new MulticastSocket(6790);
      System.out.println("Time to Live: " + s.getTimeToLive()); // TTL
      s.setTimeToLive(0); // restringindo ao host
      System.out.println("TTL: " + s.getTimeToLive()); // TTL
      s.joinGroup(group);
      byte[] m = args[0].getBytes(); // mensagem passada em linha de comando
      DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6790);
      s.send(messageOut);

      // get messages from others in group
      byte[] buffer = new byte[1000];
      for (int i = 0; i < 3; i++) {
        DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
        s.receive(messageIn);
        String mensagem = new String(messageIn.getData());
        mensagem = mensagem.trim();
        System.out.println("Received:" + mensagem + " " + mensagem.length());
        System.out.println("TTL: " + s.getTimeToLive()); // TTL
      }
      s.leaveGroup(group);
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    } finally {
      if (s != null)
        s.close();
    }
  }
}