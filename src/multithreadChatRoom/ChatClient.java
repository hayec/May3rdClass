package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient 
{
	BufferedReader in;
	PrintWriter out;
	JFrame frame = new JFrame("Chat Client");
	JTextField textField = new JTextField(40);
	JTextArea textArea = new JTextArea(8, 40);
	public ChatClient()
	{
		textField.setEditable(false);
		textArea.setEditable(false);
		frame.getContentPane().add(textField, "North");
		frame.getContentPane().add(textArea, "South");
		frame.pack();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println(textField.getText());
				textField.setText("");
			}
		});
		
	}
	private String getServerAddress()
	{
		return JOptionPane.showInputDialog(frame, "Enter IP Address of the Server", "Welcome to the Chat Room", JOptionPane.QUESTION_MESSAGE);
	}
	private String getName()
	{
		return JOptionPane.showInputDialog(frame, "Choose a screen name", "Screen name selection", JOptionPane.PLAIN_MESSAGE);
	}
	private void run() throws IOException 
	{
		String serverName = getServerAddress();
		Socket socket = new Socket(serverName, 4000);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		while(true)
		{
			String line = in.readLine();
			if(line.startsWith("SUBMITNAME"))
			{
				out.println(getName());
			}
			else if(line.startsWith("NAMEACCEPTED"))
			{
				textField.setEditable(true);
			}
			else if(line.startsWith("MESSAGE"))
			{
				textArea.setEditable(true);
				textArea.append(line.substring(8) + "\n");
			}
		}
		
	}
	public static void main(String[] args) throws IOException
	{
		ChatClient client = new ChatClient();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setVisible(true);
		client.run();
	}
}

