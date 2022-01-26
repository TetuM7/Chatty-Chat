import java.io.IOException;
import java.io.BufferedReader;


	
public class ChattyChatChatClientRunnable implements Runnable {
	  BufferedReader in;
	
	  public ChattyChatChatClientRunnable( BufferedReader l ) throws IOException {
		    
		    in = l;
		    
		  }
	  public void run() {
		    try {
		    	boolean done = false;
		    	while ( !done ) {
		            String input = in.readLine();
		            
		        if ( input == null) {
		          done = true;
		        }
		        if ( input != null ) {
		        	if ( input.equals("/quit"))
		        	{done =true;
		        	}
		        	else
		        	
		          System.out.println(input);
		        }
		      
		    	} 
		    } 		    catch (IOException e) {
		      System.out.println("Error with client " );
		      System.out.println("Exception: " + e.toString() );
		    } 
		    }
		   
		}
	
	


