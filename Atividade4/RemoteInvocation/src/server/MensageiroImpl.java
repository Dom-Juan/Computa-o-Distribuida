//exemplo de http://www.guj.com.br/articles/37
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {
	float termo1;
	float termo2;
	float resultado;

	String operador;
	public MensageiroImpl() throws RemoteException {
		super();
	}

	public void setTermo1(float termo1) throws RemoteException {
		this.termo1 = termo1;
	}

	public void setTermo2(float termo2) throws RemoteException {
		this.termo2 = termo2;
	}

	public void setOperador(String op) throws RemoteException {
		this.operador = op;
	}

	public float operacao() {
		if(this.operador.equals("+")) {
			return (this.termo1 + this.termo2);
		} else if (this.operador.equals("-")) {
			return (this.termo1 - this.termo2);
		} else if (this.operador.equals("*")) {
			return (this.termo1 * this.termo2);
		} else if (this.operador.equals("/")) {
			return (this.termo1 / this.termo2);
		} else {
			return -9999;
		}
	}

	public float enviarResultado() throws RemoteException {
		float resultado = (this.operacao());
		System.out.println( resultado );
		return resultado;
	}

	public void enviarMensagem( String msg ) throws RemoteException {
		System.out.println( msg );
	}

	public String lerMensagem() throws RemoteException {
		return "This is not a Hello World! message";
	}
}
