package net.udp;

import java.io.*;
import java.net.*;

public class UDPPoke {
	private int bufferSize;
	private int timeout;
	private InetAddress host;
	private int port;
	
	public UDPPoke( InetAddress host, int port, int bufferSize, int timeout ){
		this.bufferSize = bufferSize;
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}
	
	public UDPPoke( InetAddress host, int port ){
		this( host, port, 8192, 30000 );
	}
	
	public byte[] poke(){
		try(DatagramSocket socket = new DatagramSocket( 0)){
			DatagramPacket outgoing = new DatagramPacket( new byte[1], 1, host, port);
			socket.connect(host, port );
			socket.setSoTimeout(timeout);
			socket.send(outgoing);
			DatagramPacket incoming = new DatagramPacket( new byte[bufferSize], bufferSize);
			socket.receive(incoming);
			
			int numBytes = incoming.getLength();
			byte[] response = new byte[numBytes ];
			System.arraycopy(incoming.getData(), 0, response, 0, numBytes );
			return response;
		}catch(IOException ex ){
			return null;
		}
	}
	
	public static void main(String []args ){
		InetAddress host;
		int port = 0;
		try{
			host = InetAddress.getByName("rama.poly.edu");
			port = Integer.parseInt("19");
		}catch( RuntimeException | UnknownHostException ex ){
			System.out.println("Usage: java UDPPoke host port");
			return;
		}
		
		try{
			UDPPoke poker = new UDPPoke( host, port );
			byte[] response = poker.poke();
			if( response == null ){
				System.out.println("No response within allotted time");
				return;
			}
			String result = new String( response, "US-ASCII");
			System.out.println(result);
		}catch( UnsupportedEncodingException ex ){
			ex.printStackTrace();
		}
	}
	
}
