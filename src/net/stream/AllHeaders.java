package net.stream;

import java.io.*;
import java.net.*;

public class AllHeaders {

	public static void main( String [] args ){
		try{
			URL u = new URL("http://www.oreilly.com");
			URLConnection uc = u.openConnection();
			System.out.println(uc.getURL());
			for( int j = 1; ; ++j ){
				String header = uc.getHeaderField( j );
				if( header == null )
					break;
				System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
			}
		}catch( MalformedURLException ex ){
			System.err.println( " Is not a URL");
		}catch( IOException ex ){
			System.err.println(ex);
		}
		System.out.println();
	}
}
