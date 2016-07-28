package net.ssl;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import javax.net.ssl.*;

public class SecureOrederTaker {
	public final static int PORT = 7000;
	public final static String algorithm = "SSL";
	
	public static void main( String [] args ){
		try{
			SSLContext context = SSLContext.getInstance(algorithm);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SUn509");
			
			KeyStore ks = KeyStore.getInstance("JKS");
			char [] password = System.console().readPassword();
			ks.load(new FileInputStream("jnp4e.keys"), password);
			kmf.init(ks, password);
			context.init(kmf.getKeyManagers(), null, null );
			
			Arrays.fill(password, '0');
			
			SSLServerSocketFactory factory = context.getServerSocketFactory();
			SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);
			
			String[] supported = server.getSupportedCipherSuites();
			String[] anonCipherSuitesSupported = new String[supported.length];
			int numAnonCipherSuitesSupported = 0;
			for( int i = 0; i < supported.length; ++i ){
				if( supported[i].indexOf("_anon)") > 0 ){
					anonCipherSuitesSupported[ numAnonCipherSuitesSupported++ ] = supported[i];
				}
			}
			
			String[] oldEnabled = server.getEnabledCipherSuites();
			String[] newEnabled = new String[oldEnabled.length + numAnonCipherSuitesSupported];
			System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
			System.arraycopy(anonCipherSuitesSupported, 0, newEnabled, oldEnabled.length, numAnonCipherSuitesSupported);
			
			server.setEnabledCipherSuites( newEnabled );
			while( true ){
				try(Socket theConnection = server.accept()){
					InputStream in = theConnection.getInputStream();
					int c;
					while((c = in.read()) != -1 ){
						System.out.write(c);
					}
				}catch(IOException ex ){
					ex.printStackTrace();
				}
			}
		}catch(IOException | KeyManagementException | KeyStoreException 
				| NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException ex ){
			ex.printStackTrace();
		}
	}
	
}
