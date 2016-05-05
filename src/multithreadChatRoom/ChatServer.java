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
		public Handler(Socket socket)
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
					out.println("SUBMITNAME");//request a screen name
					name = in.readLine();//capture the screen from client
					if(name == null)//test name validity
					{
						return;
					}
					synchronized(names)
					{
						if(!name.contains(name))
						{
							//Name is valid
							names.add(name);
							break;
						}
					}
				}
				out.println("NAMEACCEPTED");
				writers.add(out);
				while(true)
				{
					String line = in.readLine();
					if(input == null) 
						return;
					for(PrintWriter writer : writers)
					{
						writer.println("MESSAGE " + name + ": " + line);
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(name != null)
				{
					names.remove(name);
				}
				if(out != null)
				{
					writers.remove(out);
				}
			}
		}
	}
}