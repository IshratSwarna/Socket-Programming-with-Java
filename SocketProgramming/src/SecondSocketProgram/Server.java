package SecondSocketProgram;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			
			System.out.println("Waiting for Client...");
			ServerSocket ss = new ServerSocket(4996);
			Socket s = ss.accept();
			System.out.println("Client is connected");
			String go = "Enter Your Name : " ;
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			out.println(go);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str1 = in.readLine();
			PrintWriter out1 = new PrintWriter(s.getOutputStream(),true);
			out1.println("Server : Hello! "+str1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
