import java.rmi.*;
import java.util.Date;
public interface receiveMessageInterface extends Remote {
  public String receiveMessage(String x) throws RemoteException;
}