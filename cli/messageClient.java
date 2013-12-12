import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.rmi.*;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class messageClient  extends JFrame implements ActionListener{
  JPanel panel = new JPanel(new BorderLayout());
  JPanel panel_iner = new JPanel(new BorderLayout());

  String urlString = "";
  JLabel label = new JLabel(new ImageIcon(urlString));
  TextField tf = new TextField(); 
  // Create a button 
  JButton enterButton = new JButton("Screenshot");
  JButton endButton = new JButton("Exit");
  
  public static void main(String args[]) {
    new messageClient();
  }
  
  public messageClient() {
    super();

    // Have the button add this window as an event listener
    enterButton.addActionListener(this);
    endButton.addActionListener(this);
    // Set the button's action command 
    // (used in the event the button will generate)
    enterButton.setActionCommand("show");
    endButton.setActionCommand("end");
    
    panel.add(label, BorderLayout.CENTER);
    panel.add(panel_iner, BorderLayout.SOUTH);
    panel_iner.setLayout(new GridLayout(1, 3));
    // panel_iner.add(new JLabel("Hello"));
    panel_iner.add(tf);
    panel_iner.add(enterButton);
    panel_iner.add(endButton);


    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(panel, BorderLayout.CENTER);
    
    this.setSize(1440, 900);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Show Picture");
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent evt) {
    // Get the action command from the event
    String actionCommand = evt.getActionCommand();
    if (actionCommand.equals("end")) {
      // If we click the button, exit the program
      System.exit(0);
    }
    
    panel.remove(label);

    System.out.println("mymessageClient go.");
    BufferedReader br = new BufferedReader (
      new InputStreamReader(System.in));
    String str = null;
    byte[] lt = null;

    try {
      Object o = Naming.lookup(tf.getText());
      // Object o = Naming.lookup("rmi://127.0.0.1/connect");
      // Object o = Naming.lookup("rmi://localhost:1099/connect");

      receiveMessageInterface ts = (receiveMessageInterface) o;
      
      lt = ts.receiveMessage(str);
      ByteArrayInputStream bin = new ByteArrayInputStream(lt);  
      BufferedImage image = ImageIO.read(bin);
      
      label = new JLabel(new ImageIcon(image));     

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
    
    // The only event we are interested in as that from 
    if (actionCommand.equals("show")) {
      panel.add(label, BorderLayout.CENTER);
      this.getContentPane().add(panel, BorderLayout.CENTER);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Show Picture");
      this.setVisible(true);
    }
  }
}