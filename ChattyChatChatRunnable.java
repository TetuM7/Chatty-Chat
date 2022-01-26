import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;
import java.net.Socket;
public class ChattyChatChatRunnable implements Runnable {
  
  int clientNumber;
  Socket client;
  PrintWriter out;
  BufferedReader in;
  String nickname;
  Vector<ChattyChatChatRunnable> clients;
  
  public ChattyChatChatRunnable( int n, Socket s, Vector<ChattyChatChatRunnable> c ) throws IOException {
    
	clients = c;
	clientNumber = n;
    client = s;
    out = new PrintWriter( s.getOutputStream(), true);
    in = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
  }
  public void  printToScreen(String msg)
  {
	  System.out.println(msg);
  }
  public void run() {
    try {
      out.println("Type \".\" to disconnect");
      boolean done = false;
      while ( !done ) {
        String input = in.readLine();
        if ( input == null || input.equals(".") ) {
          done = true;
        }
        if ( input != null ) {
          System.out.println(input);
          for(ChattyChatChatRunnable c : clients)
          {
        	  c.printToScreen(input);
          }
          //out.println(output);
          //System.out.println("Sent \"" + output + "\" to client " + clientNumber);
        }
      } // End while (!done)
    } // End try
    catch (IOException e) {
      System.out.println("Error with client " + clientNumber);
      System.out.println("Exception: " + e.toString() );
    } finally {
      System.out.println("Closing connection to client " + clientNumber);
      try { client.close(); }
      catch (Exception e) { }
    }
  } // End run 
}