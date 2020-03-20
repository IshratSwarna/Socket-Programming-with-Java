package SecondSocketProgram;

import java.io.*;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			System.out.println("Client is Requesting");
			Socket s = new Socket("localhost",4996);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			String str1 = userInput.readLine();
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			out.println(str1);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in1.readLine());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
