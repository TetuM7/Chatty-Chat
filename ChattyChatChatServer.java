import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.Date;

public class ChattyChatChatServer {
  public static void main(String[] args) {
    int port = Integer.parseInt(args[0]);
    ServerSocket listener = null;
    Socket client = null;
    int clientNumber = 0;
    Vector<ChattyChatChatRunnable> clientlist = new Vector<ChattyChatChatRunnable>();
    // Establish ServerSocket
    try {
      listener = new ServerSocket(port);
      System.out.println("ServerSocket established on port " + port );
      while (true) {
        try {
          System.out.println("Listening for connection...");
          client = listener.accept();
          System.out.println("Connection established to client " + clientNumber + 
": " + client);
          ChattyChatChatRunnable r = new ChattyChatChatRunnable(clientNumber, 
client, clientlist);
          clientlist.add(r);
          Thread t = new Thread( r );
          t.start();
        } // End try on connection
        catch (IOException e) {
          System.out.println("Error connecting to client " + clientNumber);
          System.out.println("Exception: " + e.toString());
        }
        clientNumber++;
      } // End while(true)
    } // End try on listener
    catch (IOException e) {
      System.out.println("Error with listener");
      System.out.println("Exception: " + e.toString());
    } finally {
      if ( listener != null ) {
        try { listener.close(); }
        catch (Exception e) { }
      }
    }
    return;
  } // End main
}
