package net.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class NetStream {
	
	public static String getMacCyrillicString(InputStream in ) throws IOException{
		InputStreamReader r = new InputStreamReader( in, "MacCyrillic");
		StringBuilder sb = new StringBuilder();
		int c;
		while( (c = r.read()) != -1)
			sb.append((char) c);
		return sb.toString();
	}
	
	
	public static void generateCharacters(OutputStream out ) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		
		byte [] line = new byte[numberOfCharactersPerLine + 2 ];
		
		while( true ){
			for( int i = start; i < start + numberOfCharactersPerLine; ++i ){
				line[i - start] = (byte)((i - firstPrintableCharacter )
						% numberOfPrintableCharacters + firstPrintableCharacter );
			}
			line[72] = (byte)'\r';
			line[73] = (byte)'\n';
			out.write( line );
			start = ((start + 1) - firstPrintableCharacter ) 
					% numberOfPrintableCharacters + firstPrintableCharacter;
		}
		
	}

	public static void main( String[] args ){
		OutputStream out = null;
		
		try{
			out = new FileOutputStream("/tmp/data.txt");
		}catch( IOException ex ){
			System.out.println( ex.getMessage());
		}finally{
			if( out != null )
			try{
				out.close();
			}catch( IOException ex ){
				
			}
		}
	}
	
}
