
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.lang.String;

public class ChattyChatChatServerRunnable implements Runnable {
  String nicknames;
  int clientNumber;
  Socket client;
  PrintWriter out;
 String message;
  BufferedReader in;
  Vector<ChattyChatChatServerRunnable> clientlist;
  public ChattyChatChatServerRunnable( int n, Socket s,Vector<ChattyChatChatServerRunnable> l ) throws IOException {
	nicknames="";
	clientNumber = n;
    client = s;
    clientlist= l;
    out = new PrintWriter(s.getOutputStream(), true);
    in = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
    message=" ";
  }
  public void run() {
    try {
      out.println("Type \".\" to disconnect");
      boolean done = false;
      while (!done) {
        String input = in.readLine();
        if ( input == null ||input.substring(0,5).equals("/quit")) 
          {done = true;
          }
        if (input != null )
        {
        	if (input.substring(0,5).contains("/nick"))
         {nicknames = input.substring(6, input.length());}
         
        if(input.substring(0,3).equals("/dm"))
        		 { int index =input.indexOf(" ",4);
        		 String nick=input.substring(4,input.indexOf(" ",4));
        		  message = input.substring(input.indexOf(" ",4),input.length());
        		
        		 
        		 for (int l=0;l<clientlist.size();l++) {
        			 
        			 if (clientlist.get(l)!= this && clientlist.get(l).nicknames.equals(nick))
        			 {
        				 clientlist.get(l).out.println(message);
        				 
        		          System.out.println("Sent \"" + message + "\" to client " + clientlist.get(l).clientNumber);
        		          }
                        }                                                
        		       }
         else
         { for(int i=0; i < clientlist.size(); i++)
        	 {clientlist.get(i).out.println(input);
        	 System.out.println("Sent \"" + input + "\" to client ");
        	 }
        	} 
        }
        }
      
      // End while (!done)
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