import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
public class messageServer extends UnicastRemoteObject implements receiveMessageInterface {
  public messageServer ( ) throws RemoteException {
    super( );
  }

  public String receiveMessage(String x) throws RemoteException
  {
    System.out.println(x);
    return x;
  }

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      messageServer f = new messageServer( );
      Naming.rebind("connect", f);
      System.out.println("mymessageServer ready.");
    } catch (RemoteException rex) {
      System.out.println("Exception in messageServer.main: " + rex);
    } catch (MalformedURLException ex) {
      System.out.println("MalformedURLException " + ex);
    }
  }
}