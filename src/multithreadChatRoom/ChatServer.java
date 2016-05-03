package multithreadChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Handler;

public class ChatServer 
{
	private static final int PORT = 4000;
	private static HashSet<String> names = new HashSet<String>();
	private static HashSet<PrintWriter> writers = new HashSet<>();
	public static void main(String[] args) throws IOException
	{
		System.out.println("Chat Server is Running");
		ServerSocket listener = new ServerSocket(PORT);
		
		try
		{
			while(true)
			{
				new Handler(listener.accept()).start();
			}
		}
		finally
		{
			listener.close();
		}
		
	}
	private static class Handler extends Thread
	{
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private Handler(Socket socket)
		{
			this.socket = socket;
		}
		private void run()
		{
			try
			{
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				while(true)
				{
					out.println("SUBMITNAME");
					if(name == null)
					{
						return;
					}
					synchronized(names)
					{
						if(!name.contains(name))
						{
							names.add(name);
							break;
						}
					}
				}
			}
			finally {}
		}
	}
}