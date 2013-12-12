import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
public class messageServer extends UnicastRemoteObject implements receiveMessageInterface {
  public messageServer ( ) throws RemoteException {
    super( );
  }

  public byte[] receiveMessage(String x) throws AWTException, IOException
  {
    // screen capture
//    BufferedImage screencapture = new Robot().createScreenCapture(
//        new Rectangle(100,100,100,100));
    BufferedImage screencapture = new Robot().createScreenCapture(
        new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
    
    // save in local
//    File file = new File("screencapture.jpg");
//    ImageIO.write(screencapture, "jpg", file);
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(screencapture, "jpg", out);
    byte[] b = out.toByteArray();  
    return b;
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