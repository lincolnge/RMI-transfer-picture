import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.*;
import java.util.Date;
public interface receiveMessageInterface extends Remote {
  public byte[] receiveMessage(String x) throws RemoteException, AWTException, IOException;
}