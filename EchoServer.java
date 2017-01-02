import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer {

  //magic begins
  public static void main(String[] args) throws Exception {

	 //assign first command line argument as port number
    int port = Integer.parseInt(args[0]);

			//Create server socket and use the first argument as the port number
    		ServerSocket echoServer = new ServerSocket(port);
	    	System.out.println("Server is up!");

	  //wrap the socket in try catch block because alot can go wrong here
     try {

			//connect client socket to server socket
			Socket echoClient = echoServer.accept();
         System.out.println("Client Connected!");

	 //keep running the connection until at least one of them is closed
    while ((echoServer.isClosed() != true) || (echoClient.isClosed() != true)) {

	 //read the input from the client
    BufferedReader reader = new BufferedReader(new InputStreamReader(echoClient.getInputStream()));

	 //store the message that was read from the client 
    String message = reader.readLine();

	 //to be able to writer back responses to client
    PrintWriter writer = new PrintWriter(echoClient.getOutputStream(), true); 
	
	 //keep reading clients responses until one of the following characters are read
    while(message.contains("#") != true || message.contains("$") != true){

	 //reverse the string that client "wrote" and store it
    String builder = new StringBuilder(message).reverse().toString();

			//respond back with reveresed string
    		writer.println(builder);
			
		   //if this wasn't here it wouldn't read any more than one string from client 
	 		message = reader.readLine();

   		} 
			
			//close the writer
			writer.close();

			//close the connection between client and server
			echoClient.close();
			echoServer.close();

    	}
	
	//catch anything that went wrong and print it.
   }catch(IOException ex){
    ex.printStackTrace();
  }
 }
}
