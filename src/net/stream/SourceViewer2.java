package net.stream;

import java.io.*;
import java.net.*;

public class SourceViewer2 {

	public static void main( String[] args ){
		try{
			URL u = new URL( "http://www.baidu.com");
			URLConnection uc = u.openConnection();
			try( InputStream raw = uc.getInputStream()) {
				InputStream buffer = new BufferedInputStream( raw );
				Reader reader = new InputStreamReader( buffer );
				int c;
				while((c = reader.read()) != -1 ){
					System.out.print((char)c) ;
				}
			}	
		}catch(MalformedURLException ex ){
			System.err.println(args[0] + " is not a parseable URL");
		}catch( IOException ex ){
			System.err.println(ex);
		}
	
	}
}
