//based on example from http://lycog.com/programming/multicast-programming-java/

import java.io.*;
import java.net.*;

public class MulticastIPEnvia {
  public static void main(String[] args) {
    MulticastSocket socket = null; // se DatagramSocket = null; ?
    DatagramPacket outPacket = null;
    String identificador, msg, msg1, msg2, endGrupo;
    long counter = 0;
    int tempo;
    byte[] outBuf = new byte[256];
    final int PORT = 9786;
    String ip;
    if (args.length > 3) {
      ip = args[0];
      identificador = args[1];
      msg = args[2];
      tempo = Integer.parseInt(args[3]);
    } else {
      ip = args[0];
      identificador = "Remetente";
      msg = "mensagem padrao";
      tempo = 1000;
      System.out.println("Quantidade insuficiente de argumentos");
      System.out.println("Valores padrao para identificador, mensagem e tempo atribuidos");
    }

    System.out.println("<IP>"+ ip +"<Identificador>: " + identificador + " <Mensagem>: " + msg + "<tempo>: " + tempo + "\n");

    try {
      socket = new MulticastSocket(); // se new DatagramSocket(); ?

      System.out.println("Time to Live: " + socket.getTimeToLive());
      socket.setTimeToLive(0); // restringindo ao host
      System.out.println("Time to Live: " + socket.getTimeToLive());

      while (true) {
        msg1 = msg + " " + String.format("%010d", counter); // mostrada no emissor
        msg2 = msg1 + " de " + identificador; // enviada ao receptor
        counter++;
        outBuf = msg2.getBytes();

        // Enviado para o endereco IP Multicast e porta
        InetAddress address = InetAddress.getByName(ip);
        outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
        endGrupo = address.toString().substring(1); // removendo o caracter / inicial
        socket.send(outPacket);
        System.out.println(identificador + " enviou: " + msg1 + " para o grupo " + endGrupo);
        System.out.println("Time to Live: " + socket.getTimeToLive());
        try {
          Thread.sleep(tempo);
        } catch (InterruptedException ie) {
          System.out.println(ie);
        }
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}