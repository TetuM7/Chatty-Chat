
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class ChattyChatChatClient {
	
	public static void main(String[] args) {
	    
	    String hostname = args[0];
	    int port = Integer.parseInt(args[1]);
	    Socket server = null;
	    try {
	      server = new Socket(hostname, port);
	      
	      BufferedReader in = new BufferedReader( new InputStreamReader(
	                                              server.getInputStream() 
	                                                                   )
	                                            );
	      PrintWriter out = new PrintWriter( server.getOutputStream(), true );
	      
	      BufferedReader userIn = new BufferedReader( new InputStreamReader(
	                                                  System.in ) );
	      System.out.println( in.readLine() );
	      boolean done = false;
	      
	      ChattyChatChatClientRunnable r = new ChattyChatChatClientRunnable(in);
	      
	      Thread t = new Thread( r );
          t.start();
	      
	      
	      while (!done) {
	        String output = userIn.readLine();
	        if ( output.contains("/quit") ) {
	          done = true;
	        }
	        out.println(output);
	        String response = in.readLine();
	        System.out.println(response);
	      }
	      
	    } // End try on server
	    catch (IOException e) {
	      System.out.println("Error with socket");
	    } finally {
	      if (server != null ) {
	        try { server.close(); }
	        catch (IOException e) { }
	      }
	    }
	    return;
	  }
	}


