//exemplo de http://www.guj.com.br/articles/37
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensageiro extends Remote {

	public void enviarMensagem( String msg ) throws RemoteException;
	public String lerMensagem() throws RemoteException;

	public void setTermo1(float termo1) throws RemoteException;
	public void setTermo2(float termo2) throws RemoteException;
	public void setOperador(String op) throws RemoteException;
	public float enviarResultado() throws RemoteException;
}