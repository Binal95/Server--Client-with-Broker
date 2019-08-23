import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {
	public static void main(String[] args) throws IOException 
	{
		
		ServerSocket server = new ServerSocket(8098);
		System.out.println("Server Started");
		System.out.println("Waiting for a client to access a service request");
		Socket client= server.accept();
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Client connected");
		int service =0;
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		PrintWriter pw = new PrintWriter(client.getOutputStream(),true);
		Scanner scan = new Scanner(System.in);
		
		try
		{
		String s = 	br.readLine();
		System.out.println(s);
		System.out.println("----------------------------------------------------------------------");
		String[] pass = s.split(":");
		service = Integer.parseInt(pass[1]);
		if(service != 0)
		{
			pw.println("Yes");
			System.out.println("I provided");
		}
		
		
		}
		catch(Exception e)
		{
			pw.println("No");
		}
		
		//server.close();
		
		
	}

}
