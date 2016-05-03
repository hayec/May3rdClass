package objectSending;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException 
	{
		ObjectInputStream inputFromServer = null;
		Socket s = new Socket("172.29.6.20", 9000);//This computer is local host, 127.0.0.1 is loop back, or use own IP address
		Message m;
		inputFromServer = new ObjectInputStream(s.getInputStream());
		while(true)
		{
			m = (Message) inputFromServer.readObject();
			if(m == null)
			{
				return;
			}
			System.out.println("Processing the message...");
			System.out.println(m);
			if(m.getType() == 0)
			{
				System.out.println(m.getName() + " : " + m.getMessage());
			}
			else if(m.getType() == 1)
			{
				System.out.println(m.getName() + " left the chat.");
			}
			else
			{
				System.out.println(m.getName() + " entered the chat.");
			}
		}
	}
}
