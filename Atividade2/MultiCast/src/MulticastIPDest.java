// based on example from http://lycog.com/programming/multicast-programming-java/

import java.io.*;
import java.net.*;

public class MulticastIPDest {
  public static void main(String[] args) {
    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    String endRem, endGrupo;
    byte[] inBuf = new byte[256];
    final int PORT = 9786;

    try {
      // Juntando-se se o grupo Multicast IP
      String ip = args[0];
      socket = new MulticastSocket(PORT);
      System.out.println("Time to Live: " + socket.getTimeToLive());
      socket.setTimeToLive(0); // restringindo ao host
      System.out.println("Time to Live: " + socket.getTimeToLive());
      InetAddress address = InetAddress.getByName(ip);
      socket.joinGroup(address);
      endGrupo = address.toString().substring(1);

      while (true) {
        inPacket = new DatagramPacket(inBuf, inBuf.length);
        socket.receive(inPacket);
        String msg = new String(inBuf, 0, inPacket.getLength());
        endRem = inPacket.getAddress().toString().substring(1);
        System.out.println("Recebido: " + msg + " Remetente: " + endRem + " Grupo: " + endGrupo);
        System.out.println("Time to Live: " + socket.getTimeToLive());
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}