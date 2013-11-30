import java.io.*;
import java.util.*;
import java.rmi.*;
import java.net.*;
public class messageClient {
  public static void main(String args[]) throws IOException {
    for (; ; ) {
      BufferedReader br = new BufferedReader (
              new InputStreamReader(System.in));
      String str = br.readLine();
      String lt = null;
      if (str.equals("exit"))
        break;
      
      try {
        Object o = Naming.lookup("connect");
        receiveMessageInterface ts = (receiveMessageInterface) o;
        // ts.receiveMessage(args[0]);
        
        lt = ts.receiveMessage("Client: " + str);
      } catch (MalformedURLException ex) {
        // System.err.println(args[0] + " is not a valid RMI URL");
      } catch (RemoteException ex) {
        System.err.println("Remote object threw exception " + ex);
      } catch (NotBoundException ex) {
        System.err.println("Could not find the requested remote object on the server");
      }
      System.out.println("The other client: " + lt);
    }
    
  }
}