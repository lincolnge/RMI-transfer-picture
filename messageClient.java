import java.io.*;
import java.util.*;
import java.rmi.*;
import java.net.*;
public class messageClient {
  public static void main(String args[]) {
    (new Thread(new Runner())).start();
  }
}

class Runner implements Runnable {
  public void run () {
    BufferedReader br = new BufferedReader (
            new InputStreamReader(System.in));
    String str = null;
    try {
      str = br.readLine();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String lt = null;

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
  }
}