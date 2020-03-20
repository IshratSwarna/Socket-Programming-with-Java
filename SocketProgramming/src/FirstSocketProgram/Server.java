package FirstSocketProgram;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Waiting for clients..");
			ServerSocket ss = new ServerSocket(9845);
			Socket s = ss.accept();
			System.out.println("Connection established");
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = in.readLine();
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			out.println("Server says : "+str);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
