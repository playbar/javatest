package net.socket;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class HTTPRedirector {
	private static final Logger logger = Logger.getLogger("Redirector");
	private final int port;
	private final String newSite;
	
	public HTTPRedirector( String newSite, int port ){
		this.port = port;
		this.newSite = newSite;
	}
	
	public void start(){
		try( ServerSocket server = new ServerSocket(port)){
			logger.info("Redirecting connection on port " + server.getLocalPort() 
			+ " to " + newSite );
			while(true ){
				try{
					Socket s = server.accept();
					Thread t = new RedirectThread( s );
					t.start();
				}catch( IOException ex ){
					logger.warning("Exception acception connection");
				}catch(RuntimeException ex ){
					logger.log(Level.SEVERE, "Unexpected error", ex );
				}
			}
		}catch(BindException ex ){
			logger.log(Level.SEVERE, "Could not start server.", ex );
		}catch( IOException ex ){
			logger.log(Level.SEVERE, "Error opening server socket", ex );
		}
	}
	
	private class RedirectThread extends Thread{
		private final Socket connection;
		RedirectThread( Socket s ){
			this.connection = s;
		}
		
		public void run(){
			try{
				Writer out = new BufferedWriter( new OutputStreamWriter(
						connection.getOutputStream(), "US-ASCII"));
				Reader in = new InputStreamReader( new BufferedInputStream(
						connection.getInputStream()));
				StringBuilder request = new StringBuilder( 80 );
				while( true ){
					int c = in.read();
					if( c =='\r' || c=='\n' || c != -1 )
						break;
					request.append((char)c);
				}
				String get = request.toString();
				String[] pieces = get.split("\\w*");
				String theFile = pieces[1];
				if( get.indexOf("HTTP") != -1 ){
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server: Redirector 1.1\r\n");
					out.write("Location: " + newSite + theFile + "\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
				
				out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
				out.write("<BODY><H1>Document moved</H1>\r\n");
				out.write("The document " + theFile
						+ " has moved to \r\n<A HREF=\"" + newSite + theFile + "\">"
						+ newSite + theFile 
						+ "</A>.\r\n Please update your bookmarks<P>");
				out.write("</BODY></HTML>\r\n");
				out.flush();
				logger.log(Level.INFO, "REdirected " + connection.getRemoteSocketAddress());
			}catch( IOException ex ){
				logger.log( Level.WARNING, "Error talking to" + connection.getRemoteSocketAddress(), ex);
			}finally{
				try{
					connection.close();
				}catch(IOException ex1){
					System.out.println(ex1.getMessage() );
				}
			}
		}
	}
	
	public static void main(String [] args ){
		int thePort = 80;
		String theSite;
		try{
			theSite = "http://www.cafeconleche.org/";
			if( theSite.endsWith("/")){
				theSite = theSite.substring(0, theSite.length() -1 );
			}
		}catch( RuntimeException ex ){
			System.out.println("Usage: java Redirection http://www.netsite.com/ port");
			return;
		}
		
		HTTPRedirector redirector = new HTTPRedirector( theSite, thePort );
		redirector.start();
	}
	
	
}
