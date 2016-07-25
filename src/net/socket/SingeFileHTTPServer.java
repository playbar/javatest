package net.socket;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.logging.*;


public class SingeFileHTTPServer {
	private static final Logger logger = Logger.getLogger("SingleFileHTTPServer");
	private final byte []content;
	private final byte [] header;
	private final int port;
	private final String encoding;
	
	public SingeFileHTTPServer( String data, String encoding, String mimeType,
			int port) throws UnsupportedEncodingException{
		this(data.getBytes(encoding), encoding, mimeType, port );
	}
	
	public SingeFileHTTPServer( byte [] data, String encoding, String mimeType, int port ){
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n"
			+ "Server: OneFile 2.0\r\n"
			+ "Content-length: " + this.content.length + "\r\n"
			+ "Content-type: " + mimeType + "; charset=" + encoding + "\r\n";
		this.header = header.getBytes(Charset.forName("US-ASCII"));
		return;
	}
	
	public void start(){
		ExecutorService pool = Executors.newFixedThreadPool( 100);
		try(ServerSocket server = new ServerSocket(this.port)){
			logger.info("Accepting connections on port " + server.getLocalPort() );
			logger.info("Data to be sent:");
			logger.info( new String(this.content, encoding ));
			while( true ){
				try{
					Socket connection = server.accept();
					pool.submit( new HTTPHandler(connection ));
				}catch(IOException ex){
					logger.log(Level.WARNING, "Exception accepting connection", ex);
				}catch( RuntimeException ex ){
					logger.log(Level.SEVERE, "Unecpected error", ex);
				}
			}
		}catch(IOException ex ){
			logger.log(Level.SEVERE, "Could not start server", ex);
		}
	}
	
	private class HTTPHandler implements Callable<Void>{
		private final Socket connection;
		
		HTTPHandler(Socket connection ){
			this.connection = connection;
		}
		
		@Override
		public Void call() throws IOException{
			try{
				OutputStream out = new BufferedOutputStream(connection.getOutputStream());
				InputStream in = new BufferedInputStream( connection.getInputStream() );
				StringBuilder request = new StringBuilder( 80);
				while( true ){
					int c = in.read();
					if( c == '\r' || c == '\n' || c== -1)
						break;
					request.append((char)c);
				}
				
				if( request.toString().indexOf("HTTP/") != -1 ){
					out.write(header);
				}
				out.write(content);
				out.flush();
			}catch(IOException ex ){
				logger.log(Level.WARNING, "Error writing to client", ex);
			}finally{
				connection.close();
			}
			return null;
		}
	}
	
	public static void main( String [] args ){
		int port;
		try{
			port = Integer.parseInt("304");
			if( port < 1 || port > 65535 )
				port = 80;
		}catch(RuntimeException ex ){
			port = 80;
		}
		
		String encoding = "UTF-8";
		try{
			String strPath = "/temp/error.txt";
			Path path = Paths.get(strPath);
			byte [] data = Files.readAllBytes(path);
			
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(strPath);
			SingleFileHTTPServer server = new SingleFileHTTPServer( data, encoding, 
					contentType, port );
			server.start();
		}catch( ArrayIndexOutOfBoundsException ex ){
			System.out.println("Usage: java SingleFileHTTPServer filename port encoding");
		}catch(IOException ex ){
			logger.severe(ex.getMessage());
		}
	}
	
	
}
