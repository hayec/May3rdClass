package objectSending;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public static void main(String[] args) throws IOException
	{
		ObjectOutputStream outputToClient;
		ObjectInputStream inputFromClient;
		ServerSocket listener = new ServerSocket(9000);
		while(true)
		{
			System.out.println("Waiting for connection : ");
			Socket socket = listener.accept();
			outputToClient = new ObjectOutputStream(socket.getOutputStream());
			Message m = new Message(0, "John Doe", "Hello");
			outputToClient.writeObject(m);
			outputToClient.flush();//Send the message even if buffer is not full
			System.out.println("Message is sent.");
		}
		
			
		
	}
}
