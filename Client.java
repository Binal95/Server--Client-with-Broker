import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class Client {
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		try{
		
		//Socket socket = new Socket("172.29.76.39",4000);
		Socket socket = new Socket("localhost",4000);
		//BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
		Scanner scan = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Which number of service you want to access ?");
		int service = scan.nextInt();
		
		//send requested service number to broker
		pw.println(service);
		
		String s = null;
		
		//get result from broker
		s = br.readLine();
		
		while(s.equals("I do not have this service"))
		{
			System.out.println("Broker does not have service please request for another service number");
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Which number of service you want to access ?");
			int service2 = scan.nextInt();
			
			//send new request service number to broker
			pw.println(service2);
			
			//read result from broker
			s = br.readLine();
		}
		
		
		
		//System.out.println(s);
		String ser = s;
		String[] num = new String[20];
		num = ser.split(",");
		int got_service =  Integer.parseInt(num[0]);
		
		System.out.println("Result from broker");
		System.out.println("----------------------------------------------------------------------");
		//System.out.printf("service number: %d \n",got_service);
		
		int port = Integer.parseInt(num[1]);
	
		
		System.out.printf("Service :%d is available on port number: %d \n",got_service, port);
		
		//sleep so get a time to modify port number on server
		Thread.sleep(25000);
		
		try
		{
	    pw.close();
	    br.close();
		socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		socket = new Socket("localhost",port);
		//socket = new Socket("172.29.76.39",port);
		PrintWriter pw2 = new PrintWriter(socket.getOutputStream(),true);
		String send = "Can you provide service number:" + got_service;
		pw2.println(send);
		//pw2.printf("%d",got_service);
		
		BufferedReader br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println();
		
		//print result yes or no from broker
		System.out.println(br2.readLine());
		
		try
		{
		    pw.close();
		    br2.close();
		    socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
