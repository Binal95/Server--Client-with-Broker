import java.util.*;
import java.io.*;
import java.net.*;
	
	
class Handle extends Thread 
{
	int a = 0;
	static ArrayList<Integer> l2 = new ArrayList<Integer>(100);
	static int counter_first  =0;
	int port =8000; 
	
	    Handle(int a)
		{
			this.a = a;
		}
	    
	    //add method to add in a Arraylist
		synchronized public void addition(int k)
		{
			l2.add(k);
			System.out.printf("Service %d : %d \n", l2.get(l2.size()-1), (l2.get(l2.size()-1)+port));
		}
		
		//remove method to add in a Arraylist
		synchronized public void remove()
		{
			int rm = l2.get(0);
			l2.remove(0);
			System.out.printf("Removed service number: %d \n",rm);
			
		}
		
		@Override
		public void run()
		{
			int service = 0;
			
			try {
				if(a == 1)
				{
					ServerSocket server = new ServerSocket(4000);
					System.out.println("Broker Started");
					System.out.println("Waiting for a client to give a service request");
					
				//If want more than one client then uncomment below line	
				/*while(true)
				{*/
				int c=0;
				
				Socket client= server.accept();
				System.out.println("Client connected");
				
				//BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				//DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				PrintWriter pw = new PrintWriter(client.getOutputStream(),true);
				Scanner scan = new Scanner(System.in);
				String check = " ";
				
				while(check != "success")
				{
				service = Integer.parseInt(br.readLine());
				
				if(service != 0)
				{
					System.out.printf("Request Service number by client : %d \n", service);
				}
				
				
				for(int i=0;i<l2.size();i++)
				{
					if(l2.get(i) == service)
					{
						
						if(l2.get(i) != 0){
						int pass_port = l2.get(i) +port;	
						String pass =  l2.get(i) + "," +  pass_port;
						pw.println(pass);
						c++;
						check = "success";
						//client.close();
						break;
						}
						
					}
					
					
				}
				if(c == 0)
				{
				   pw.println("I do not have this service");
				}
				
				}
				
				//}
				
				
				}
				else if( a==2 )
				{
					Random num = new Random();
					
					
					while(true)
					{
					if(counter_first != 0)
					{
						int c =0;
						int r = num.nextInt(100) + 1;
						
						if(!(l2.contains(r)))
						{
							addition(r);
							//l2.add(r);
							//System.out.printf("Service %d : %d \n", l2.get(l2.size()-1), (l2.get(l2.size()-1)+port));
						    //c++;
								
						}
					}
					if(counter_first == 0)
					{int c =0;
					for(int i=0;i<15;i++)
					{
						
						int r = num.nextInt(101) + 1;
						if(!(l2.contains(r)))
						{
							    addition(r);  
                        	    //l2.add(r);
								//System.out.printf("Service %d : %d \n", l2.get(l2.size()-1), (l2.get(l2.size()-1)+port));
						
								counter_first++;
							}
                        
						}
					}
					
					Thread.sleep(15000);
					}
						
					}
				
				else
				{
					
					
					while(true)
					{
						//l2.remove(0);
						//System.out.println()
						remove();
						Thread.sleep(30000);
					}	
				}
					
				
			}
				
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
}

public class Broker{
	
		
	public static void main(String[] args) throws InterruptedException
	{
	 
		Thread h1 = new Thread(new Handle(2));
		h1.start();
	
		Thread h2 = new Thread(new Handle(1));
		h2.start();
		
		Thread.sleep(1000);
		Thread h3 = new Thread(new Handle(3));
		h3.start();
		
		
		
	}


}
