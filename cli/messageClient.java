import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.net.*;

import javax.imageio.ImageIO;
public class messageClient {
  public static void main(String args[]) {
    (new Thread(new Runner())).start();
  }
}

class Runner implements Runnable {
  public void run () {
    System.out.println("mymessageClient go.");
    BufferedReader br = new BufferedReader (
      new InputStreamReader(System.in));
    String str = null;
//    try {
//      str = br.readLine();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    byte[] lt = null;

    try {
      Object o = Naming.lookup("connect");
      receiveMessageInterface ts = (receiveMessageInterface) o;
      // ts.receiveMessage(args[0]);
      
      lt = ts.receiveMessage(str);
      ByteArrayInputStream bin = new ByteArrayInputStream(lt);  
      BufferedImage image = ImageIO.read(bin);
      
      File file = new File("image.jpg");
      ImageIO.write(image, "jpg", file);
      
    } catch (MalformedURLException ex) {
      // System.err.println(args[0] + " is not a valid RMI URL");
    } catch (RemoteException ex) {
      System.err.println("\nRemote object threw exception " + ex);
    } catch (NotBoundException ex) {
      System.err.println("\nCould not find the requested remote object on the server");
    } catch (AWTException e) {
      // TODO Auto-generated catch block
      System.err.println("\nAWTException threw exception " + e);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.err.println("\nIOException threw exception " + e);
    } 
  }
}