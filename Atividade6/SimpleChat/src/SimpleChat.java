// Import do libs do java.
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class SimpleChat implements Receiver {
  JChannel channel;

  String userName = System.getProperty("user.name", "n/a");

  final List<String> state = new LinkedList<String>();

  private void start() throws Exception {
    try {
      channel = new JChannel().setReceiver(this).connect("chat");
      channel.connect("Sala-da-Alegria");
      channel.getState(null, 10000);
      eventLoop();
      channel.close();
    } catch(Exception e) {
      throw e;
    }
  }

  private void eventLoop() {
    System.out.println("EventLoop()");
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while(true) {
      try {
        System.out.print("> ");
        System.out.flush();
        String line = in.readLine().toLowerCase();
        if(line.startsWith("[quit]") || line.startsWith("[exit]"))
          break;
        line = "[ " + userName + " ]: " + line;
        Message msg = new ObjectMessage(null, line);
        channel.send(msg);
      } catch(Exception e) {
        System.out.println("Rapaz um erro! Quem diria...");
      }
    }
  }

  @Override
  public void setState(InputStream input) throws Exception {
    List<String> list;
    list = (List<String>)Util.objectFromStream(new DataInputStream(input));
    synchronized (state) {
      state.clear();
      state.addAll(list);
    }
    System.out.println(list.size() + " messages in chat history):");
    list.forEach(System.out::println);
  }

  public void getState(OutputStream output) throws Exception {
    synchronized (state) {
      Util.objectToStream(state, new DataOutputStream(output));
    }
  }

  @Override
  public void viewAccepted(View newView) {
    System.out.println("viewAccepted()");
    System.out.println("** view: " + newView + " **");
  }

  @Override
  public void receive(Message msg) {
    System.out.println("receive()");
    String line = msg.getSrc() + ": " + msg.getObject();
    System.out.println(line);
    synchronized(state) {
      state.add(line);
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Start()");
    new SimpleChat().start();
  }
}
