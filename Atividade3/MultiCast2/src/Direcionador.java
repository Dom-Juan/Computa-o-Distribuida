import java.net.*;
import java.io.*;

//
public class Direcionador {
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
			Socket s = new Socket(ip, 7890);
			tcpConfirm(address, 0, s);
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

	public int tcpConfirm(String ip, int port, Socket s) {
		String data = "";
		try {
			s = new Socket(ip, port);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			out.writeUTF(ip+":"+port); // Envia msg para o server.
			data = in.readUTF(); // Resposta do Server.
			
			System.out.println("Received: " + data);

		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}

		if(data.equals(ip+":"+port)) {
			return 1;
		} else {
			return 0;
		}
	}
}