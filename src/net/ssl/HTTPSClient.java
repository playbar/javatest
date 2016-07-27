package net.ssl;

import java.io.*;
import javax.net.ssl.*;

public class HTTPSClient {
	public static void main( String[] args ){
		int port = 443;
		String host = "www.usps.com";
		SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		SSLSocket socket = null;
		try{
			socket = (SSLSocket)factory.createSocket(host, port);
			String [] supported = socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported);
			Writer out = new OutputStreamWriter( socket.getOutputStream(), "UTF-8");
			
			out.write("Get http://" + host + "/ HTTP/1.1 \r\n");
			out.write("Host: " + host + "\r\n");
			out.write("\r\n");
			out.flush();
			
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			
			String s;
			while( !(s = in.readLine()).equals("")){
				System.out.print(s);
			}
			System.out.print(s);
			String contentLength = in.readLine();
			int length = Integer.MAX_VALUE;
			try{
				length = Integer.parseInt(contentLength.trim(), 16 );
			}catch( NumberFormatException ex ){
				
			}
			System.out.println( contentLength );
			
			int c;
			int i = 0;
			while((c = in.read()) != -1 && i++ < length ){
				System.out.write(c);
			}
			System.out.println();
		}catch( IOException ex ){
			System.out.println(ex);
		}finally{
			try{
				if( socket != null )
					socket.close();
			}catch(IOException e){}
		}
	}
}
