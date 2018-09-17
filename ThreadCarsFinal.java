// Alexander Gonzalez
// Lab 9 - 3/21/17

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ThreadCarsFinal extends JFrame {
   
   private JPanel speeds = new JPanel();

   private JLabel lblCarRedSpeed = new JLabel("Red");
   private JLabel lblCarBlueSpeed = new JLabel("Blue");
   private JLabel lblCarGreenSpeed = new JLabel("Green");
   private JLabel lblCar4Speed = new JLabel("Purple");
   
   private JTextField txtCar1Speed = new JTextField(3);   
   private JTextField txtCar2Speed = new JTextField(3);
   private JTextField txtCar3Speed = new JTextField(3);
   private JTextField txtCar4Speed = new JTextField(3);
   
   private JButton bAdjustSpeeds = new JButton();

   
   public static void main(String[] args) {
       
      ThreadCarsFinal frame = new ThreadCarsFinal();
      frame.setLayout(new GridLayout(5,1));
      frame.setTitle("Cars");
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 250);
      frame.setResizable(false);
      frame.setVisible(true);
      
   } // end main
   
   public ThreadCarsFinal() {
   
      txtCar1Speed.setText("20");
      txtCar2Speed.setText("20");
      txtCar3Speed.setText("20");
      txtCar4Speed.setText("20");
      
      speeds.setLayout(new GridLayout(1,9));
      speeds.add(lblCarRedSpeed);
      speeds.add(txtCar1Speed);
      speeds.add(lblCarBlueSpeed);
      speeds.add(txtCar2Speed);
      speeds.add(lblCarGreenSpeed);
      speeds.add(txtCar3Speed);
      speeds.add(lblCar4Speed);
      speeds.add(txtCar4Speed);
      speeds.add(bAdjustSpeeds);
      add(speeds);
   
      Car redCar = new Car(0,50,500,Color.RED,20);
      add(redCar);
      Thread threadRed = new Thread(redCar); 
         
      Car blueCar = new Car(0,50,500,Color.BLUE,20); //100
      add(blueCar);
      Thread threadBlue = new Thread(blueCar);
      
      Car greenCar = new Car(0,50,500,Color.GREEN,20);
      add(greenCar);
      Thread threadGreen = new Thread(greenCar);

      Car purpleCar = new Car(0,50,500,Color.MAGENTA,20);
      add(purpleCar);
      Thread threadPurple = new Thread(purpleCar);      
      
      
      bAdjustSpeeds.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            redCar.setSleep(Integer.parseInt(txtCar1Speed.getText()));
            blueCar.setSleep(Integer.parseInt(txtCar2Speed.getText()));
            greenCar.setSleep(Integer.parseInt(txtCar3Speed.getText()));
            purpleCar.setSleep(Integer.parseInt(txtCar4Speed.getText()));
          
         }
       });
      
      
      
      threadBlue.start();
      threadGreen.start(); 
      threadRed.start();     
      threadPurple.start();
      
   }
   
   
   
   
class Car extends JPanel implements Runnable
{
   private int yCoord;
   private int xCoord;
   private int width;
   Color carColor;
   private int sleep;
   
   Car(int x, int y, int w, Color c, int slp)
   {
      yCoord = y;
      xCoord = x;
      width = w;
      carColor = c;
      sleep = slp;
      
   }

   public void paintComponent(Graphics g) 
   {
      super.paintComponent(g);
      drawCar(g);
      
     
     
    
   }
   public void drawCar(Graphics g) {
    if (xCoord > width) 
      {
         xCoord = -50;
      }
      xCoord += 10;
     
      g.setColor(carColor);
      int[] xPoly = {xCoord+10,xCoord+20, xCoord+30,xCoord+40};
      int[] yPoly = {yCoord-20,yCoord-30,yCoord-30,yCoord-20};
      g.fillRect(xCoord, yCoord-20, 50, 10);
      g.fillOval(xCoord+10, yCoord-10, 10,10);
      g.fillOval(xCoord+30, yCoord-10, 10,10);
      g.drawPolygon(xPoly,yPoly,4);
      }
   
   public void setSleep(int s) {
      sleep = s;
   }
      
   public int getSleep() {
      return 1000/sleep;
   }

   
   public void run() 
   {  
      while(true)
      {
         repaint();
         try 
         {
            Thread.sleep(getSleep());
         }
         catch (InterruptedException ie)
         {
            System.out.println("interrupted");
         }
      }   
   }
 
}
} // end class
