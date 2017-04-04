package iiitb.org.provider;

import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import iiitb.org.model.ResourceModel;

import java.io.*;
import java.util.*;
//import javax.imageio.ImageIO;
//package handling database operations
//import org.iiitb.ResourceProvider;
//import jdbc;
//import org.iiitb.model;

public class FinalDisplay{
  private JFrame frame;
  private JLabel label;
  private JPanel displayPanel;
  private JButton ok;
  private JTextField display1, display2, display3, display4, display5, display6;
  ResourceModel obj;

  FinalDisplay(){
    frame=new JFrame("Eat Healthy! - Results");
    frame.setLayout(new BorderLayout());
    displayPanel=new JPanel();
    ok=new JButton("OK");
    ok.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        System.exit(1);
      }
    });
  }
/*
  private String getDisplayText(StringBuilder s){
    ArrayList<String> array=o.findTags(s);
    obj=DisplayResource.providerMethod(array);
    String text=obj.getName()+"\n";
    text=text+"Calories: "+obj.getCalorie()+"\n";
    text=text+"Carbohydrates: "+obj.getCarbohydrate()+" grams\n";
    text=text+"Protein: "+obj.getProtein()+" grams\n";
    text=text+"Cholestrol: "+obj.getSodium()+" miligrams\n";
    text=text+"Total Fat: "+obj.getTotalFat()+" grams\n";
    return text;
  }
*/
  //displays image from the url
  public void urlDisplay(String imageURL){
	  Image image=null;
    try{
      URL url = new URL(imageURL);
      //image = ImageIO.read(url);
     
      image = Toolkit.getDefaultToolkit().createImage(url);
    }catch(IOException e){
      e.printStackTrace();
    }

    //calling urlClient class object
    UrlClient o=new UrlClient();
    StringBuilder s=o.getData(imageURL);
    ArrayList<String> array=o.findTags(s);
    obj=DisplayResource.providerMethod(array);
    
    //for(int i=0; i<array.size(); i++) text+=array.get(i)+"\n";
    /*
    String text=obj.getName().toUpperCase()+"\n";
    text=text+"Calories: "+obj.getCalorie()+"\n";
    text=text+"Carbohydrates: "+obj.getCarbohydrate()+" grams\n";
    text=text+"Protein: "+obj.getProtein()+" grams\n";
    text=text+"Cholestrol: "+obj.getSodium()+" miligrams\n";
    text=text+"Total Fat: "+obj.getTotalFat()+" grams\n";
	*/
    //displaying results on the screen
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //display=new JTextField(text);
    //if(obj.getName().length()==0) display1=new JTextField("DATA NOT AVAILABLE!");
    display1=new JTextField(obj.getName());//toUpperCase());
    display1.setEditable(false);
    displayPanel.add(display1);
    
    display2=new JTextField("Calories: "+obj.getCalorie());
    display2.setEditable(false);
    displayPanel.add(display2);
    
    display3=new JTextField("Carbohydrates: "+obj.getCarbohydrate()+" grams");
    display3.setEditable(false);
    displayPanel.add(display3);
    
    display4=new JTextField("Protein: "+obj.getProtein()+" grams");
    display4.setEditable(false);
    displayPanel.add(display4);
    
    display5=new JTextField("Cholestrol: "+obj.getSodium()+" miligrams");
    display5.setEditable(false);
    displayPanel.add(display5);
    
    display6=new JTextField("Total Fat: "+obj.getTotalFat()+" grams\n");
    display6.setEditable(false);
    displayPanel.add(display6);
    
    label=new JLabel(new ImageIcon(image));
    frame.add(label);
    frame.add(displayPanel, BorderLayout.NORTH);
    frame.pack();
    frame.add(ok, BorderLayout.SOUTH);
    frame.setVisible(true);
  }

  //displays image from local storage
  public void localDisplay(String loc){
	  Image image = null;
    try{
      File sourceimage = new File(loc);
      image = ImageIO.read(sourceimage);
    }catch(IOException e){
      e.printStackTrace();
    }

    //calling localClient class object
    LocalClient o=new LocalClient();
    StringBuilder s=o.getData(loc);
    ArrayList<String> array=o.findTags(s);
    obj=DisplayResource.providerMethod(array);
    
    //for(int i=0; i<array.size(); i++) text+=array.get(i)+"\n";
    /*
    String text=obj.getName()+"\n";
    text=text+"Calories: "+obj.getCalorie()+"\n";
    text=text+"Carbohydrates: "+obj.getCarbohydrate()+" grams\n";
    text=text+"Protein: "+obj.getProtein()+" grams\n";
    text=text+"Cholestrol: "+obj.getSodium()+" miligrams\n";
    text=text+"Total Fat: "+obj.getTotalFat()+" grams\n";
    //for(int i=0; i<array.size(); i++) text+=array.get(i)+"\n";
	*/
    //displaying results on the screen
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //if(obj.getName()=="") display1=new JTextField("DATA NOT AVAILABLE!");
    display1=new JTextField(obj.getName());
    display1.setEditable(false);
    displayPanel.add(display1);
    
    display2=new JTextField("Calories: "+obj.getCalorie());
    display2.setEditable(false);
    displayPanel.add(display2);
    
    display3=new JTextField("Carbohydrates: "+obj.getCarbohydrate()+" grams");
    display3.setEditable(false);
    displayPanel.add(display3);
    
    display4=new JTextField("Protein: "+obj.getProtein()+" grams");
    display4.setEditable(false);
    displayPanel.add(display4);
    
    display5=new JTextField("Cholestrol: "+obj.getSodium()+" miligrams");
    display5.setEditable(false);
    displayPanel.add(display5);
    
    display6=new JTextField("Total Fat: "+obj.getTotalFat()+" grams\n");
    display6.setEditable(false);
    displayPanel.add(display6);
    
    label=new JLabel(new ImageIcon(image));
    frame.add(label);
    frame.add(displayPanel, BorderLayout.NORTH);
    frame.pack();
    frame.add(ok, BorderLayout.SOUTH);
    frame.setVisible(true);
  }
}
