package iiitb.org.provider;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
//import javax.imageio.ImageIO;
import java.net.*;

//extends JFrame
public class Gui implements ActionListener{
  private JFrame frame;
  private JPanel panel, headerPanel, lowerPanel, sidePanel;
  private JButton results, quit, help, about, browse;
  private JTextField textField, unedit, unedit2;
  private JLabel label;

  private static boolean netAvailable(){
    try{                                                                            
      final URL url = new URL("http://www.google.com");                       
      final URLConnection conn = url.openConnection();                           
      conn.connect();                                                 
      return true;
    } 
    catch (MalformedURLException e){
      throw new RuntimeException(e);                                         
    } 
    catch (IOException e){                              
      return false;                                                                 
    }  
  } 

 public  Gui(){
    frame=new JFrame("Eat Healthy!");
    frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
    //adding image on the panel
    panel=new JPanel(new BorderLayout());
    headerPanel=new JPanel();
    lowerPanel=new JPanel();
    sidePanel=new JPanel();
    label=new JLabel(new ImageIcon("/home/skirmish/workspace/EatHealthy/src/iiitb/org/provider/food.jpg"));
    frame.add(label);
    frame.pack();
    //adding text fields to the frame
    unedit=new JTextField("Enter Image URL");
    unedit2=new JTextField("Or browse");
    textField=new JTextField(25);
    textField();
    //adding buttons to the frame
    results=new JButton("Get Results");
    quit=new JButton("Quit!");
    help=new JButton("HELP");
    about=new JButton("About");
    browse=new JButton("Browse");
    buttons();

    frame.getRootPane().setDefaultButton(results);//allows enter to submit text
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    //frame.pack();
    frame.repaint();
    frame.setVisible(true);
  }

  private void buttons(){
    quit.addActionListener(this);
    results.addActionListener(this);
    browse.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        //JOptionPane.showMessageDialog(frame.getComponent(0), "SELECT AN IMAGE FILE");
        String name=localFile();
        if(name==null) return;
        //checking the file type
        if(name.endsWith(".jpg") | name.endsWith(".jpeg") | name.endsWith(".png") | name.endsWith(".bmp")){
          FinalDisplay o=new FinalDisplay();
          o.localDisplay(name);
          //System.out.println("File name : "+name );
          frame.setVisible(false);
        }
        else{
          //displaying the browser again if the file selected was not an image
          JOptionPane.showMessageDialog(frame.getComponent(0), "SELECT AN IMAGE FILE!");
          localFile();
        }
      }
    });
    //custom function for help button
    String hlp=helpMe();
    help.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        JOptionPane.showMessageDialog(frame.getComponent(0), hlp);
      }
    });
    //custom function for about button
    String abt=aboutMe();
    about.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        JOptionPane.showMessageDialog(frame.getComponent(0), abt);
      }
    });

    quit.setBackground(Color.RED);
    //adding buttons to the panel
    headerPanel.add(results);
    sidePanel.add(unedit2);
    sidePanel.add(browse);
    lowerPanel.add(about);
    lowerPanel.add(help);
    lowerPanel.add(quit);
    panel.add(sidePanel, BorderLayout.WEST);
    panel.add(headerPanel, BorderLayout.NORTH);
    panel.add(lowerPanel, BorderLayout.SOUTH);
  }

  private void textField(){
    unedit.setEditable(false);
    unedit2.setEditable(false);
    //adding text fields to the panel
    headerPanel.add(unedit);
    headerPanel.add(textField);
    textField.requestFocusInWindow();
  }

  //displaying the message to be displayed after clicking the about button
  private String aboutMe(){
    try{
      Scanner sc=new Scanner(new FileReader("/home/skirmish/workspace/EatHealthy/src/iiitb/org/provider/about.txt"));
      String msg="";
      while(sc.hasNext()) msg=msg+"\n"+sc.nextLine();
      sc.close();
      return msg;
    }
    catch(Exception e){
      return "LMFAO!";
    }
  }

  //displaying the message to be displayed after clicking the help button
  private String helpMe(){
    try{
      Scanner sc=new Scanner(new FileReader("/home/skirmish/workspace/EatHealthy/src/iiitb/org/provider/help.txt"));
      String msg="";
      while(sc.hasNext()) msg=msg+"\n"+sc.nextLine();
      sc.close();
      return msg;
    }
    catch(Exception e){
      return "LOL!";
    }
  }

  //function to open the file browser
  public String localFile(){
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Find Image");
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
      String name=chooser.getSelectedFile().toString();
      return name;
    }
    return null;
  }

  //makes the buttons responsive
  public void actionPerformed(ActionEvent e){
    if(e.getSource()==results){
      String url=textField.getText();
      if(textField.getText().equals("")){
        JOptionPane.showMessageDialog(frame.getComponent(0), "Enter URL!");
        return;
      }
      if(!(url.endsWith(".jpg") | url.endsWith(".jpeg") | url.endsWith(".png") | url.endsWith(".bmp"))){
    	  JOptionPane.showMessageDialog(frame.getComponent(0), "Select JPG/JPEG/PNG/BMP image URL!");
    	  return;
      }
      FinalDisplay o=new FinalDisplay();
      o.urlDisplay(url);
      //System.out.println(url);
      frame.setVisible(false);
    }
    if(e.getSource()==quit) System.exit(1);
  }

  public static void main(String args[]){
    if(!netAvailable()){
      JFrame frame=new JFrame("No Internet Connection");
      frame.setLayout(new BorderLayout());
      JButton ok=new JButton("OK");
      ok.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          System.exit(1);
        }
      });
      JTextField msg=new JTextField("\tConnect to the internet and try again!\t");
      msg.setEditable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(msg, BorderLayout.NORTH);
      frame.add(ok, BorderLayout.SOUTH);
      frame.pack();
      frame.setVisible(true);
    }
    else new Gui();
  }
}
