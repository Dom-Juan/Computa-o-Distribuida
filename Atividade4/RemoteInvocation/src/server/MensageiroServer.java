//exemplo de http://www.guj.com.br/articles/37
import java.rmi.Naming;

public class MensageiroServer {
	
	public MensageiroServer() {
		try {
			Mensageiro ms = new MensageiroImpl();
			Naming.rebind("rmi://localhost:1099/MensageiroService", ms);
		}
		catch( Exception e ) {
			System.out.println( "Trouble: " + e );
		}
	}

	public static void main(String[] args) {
		new MensageiroServer();
	}
}