//exemplo de http://www.guj.com.br/articles/37
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class MensageiroClient {

	public static void main( String args[] ) {
		try {
			// Adiciona a interface e pegando ela do rmiregistry.
			Mensageiro mref = (Mensageiro) Naming.lookup( "rmi://localhost:1099/MensageiroService" );

			float termo1 = Integer.parseInt(args[0]);
			String op = args[1];
			float termo2 = Integer.parseInt(args[2]);

			System.out.println( mref.lerMensagem() );

			// Implementação da calculadora.
			mref.setTermo1(termo1);
			mref.setTermo2(termo2);
			mref.setOperador(op);
			mref.enviarResultado();
			mref.enviarMensagem( "Hello World!" );
		}
		catch( MalformedURLException e ) {
			System.out.println();
			System.out.println( "MalformedURLException: " + e.toString() );
		}
		catch( RemoteException e ) {
			System.out.println();
			System.out.println( "RemoteException: " + e.toString() );
		}
		catch( NotBoundException e ) {
			System.out.println();
			System.out.println( "NotBoundException: " + e.toString() );
		}
		catch( Exception e ) {
			System.out.println();
			System.out.println( "Exception: " + e.toString() );
		}
	}
}
