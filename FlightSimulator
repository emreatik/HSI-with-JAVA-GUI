import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class FlightSimulator{    
    
   static TestFrame newFrame;
    
    public FlightSimulator(){        
               
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
        
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_0);
        I2CDevice device = bus.getDevice(0x18);
        device.write(0x20, (byte)0x27);
        device.write(0x23, (byte)0x00);
        Thread.sleep(500);

        newFrame = new TestFrame();
        newFrame.setVisible(true);

        newFrame.HSI_Gauge.background(3,61,112,255);
        newFrame.HSI_Gauge.speedConfig(1100,30); //Speed Limit and Scale
        newFrame.HSI_Gauge.altitudeConfig(12000,200); //Altitude Limit and Scale
        newFrame.HSI_Gauge.speed(360);
        newFrame.HSI_Gauge.altitude(7000);
        newFrame.HSI_Gauge.direction(0);
        
        Boolean ready = false;
        byte[] data = new byte[6];           

        int[] xAccl = new int[10];        
        int[] yAccl = new int[10];  
        int[] zAccl = new int[10]; 

        int x = 0,y = 0,z = 0;
        int x_av = 0,y_av = 0,z_av = 0;        

        while(true){
            
           for(int i=0; i<10; i++){
               
                data[0] = (byte)device.read(0x28);
                data[1] = (byte)device.read(0x29);
                data[2] = (byte)device.read(0x2A);
                data[3] = (byte)device.read(0x2B);
                data[4] = (byte)device.read(0x2C);
                data[5] = (byte)device.read(0x2D);               
               
               Thread.sleep(100);
               
               if(ready){
                   x -= xAccl[i]; 
                   y -= yAccl[i];
                   z -= zAccl[i];
               }
               
               xAccl[i] = ((data[1] & 0xFF) * 256 + (data[0] & 0xFF));
               yAccl[i] = ((data[3] & 0xFF) * 256 + (data[2] & 0xFF));
               zAccl[i] = ((data[5] & 0xFF) * 256 + (data[4] & 0xFF));
               
               if(xAccl[i] > 32767){
                    xAccl[i] -= 65536;      
               } 
               if(yAccl[i] > 32767){
                    yAccl[i] -= 65536;
               }
               if(zAccl[i] > 32767){
                    zAccl[i] -= 65536;
               }             
               
               x += xAccl[i]; 
               y += yAccl[i];
               z += zAccl[i];   
               
               //System.out.printf("Average Acceleration in X-Axis : %d %n", xAccl[i]);
               //System.out.printf("Average Acceleration in Y-Axis : %d %n", yAccl[i]);
               //System.out.printf("Average Acceleration in Z-Axis : %d %n", zAccl[i]);
               //System.out.println("..................................");
               
                if(i==9 && !ready){
                   ready = true;
                }
               
               if(ready){
                    x_av = x/10;//-505 to 150
                    y_av = y/10; //-699 to -41
                    z_av = z/10;
                    
                    x_av = (int) (((double)(x_av+177.5))/((double)327.5)*90);
                    y_av = (int) (((double)(y_av+370))/((double)329)*90);
                    
                    newFrame.HSI_Gauge.pitch_angle(x_av);
                    newFrame.HSI_Gauge.roll_position(y_av);
                    //System.out.printf("Average Acceleration in X-Axis : %d %n", x_av);
                    //System.out.printf("Average Acceleration in Y-Axis : %d %n", y_av);
                    //System.out.printf("Average Acceleration in Z-Axis : %d %n", z_av);
                    //System.out.println("..................................
                    
               }
           }   

           
            
        }
 
       
       /* 
        int y=1;
        for(int x=0; x<=15;x=x+y){            
        
            newFrame.HSI_Gauge.pitch_angle(x*2+20);
            newFrame.HSI_Gauge.roll_position(x);
            newFrame.HSI_Gauge.speed(x+360);
            newFrame.HSI_Gauge.altitude(5*x+7000);
            newFrame.HSI_Gauge.direction(x);
            
            //newFrame.HSI_Gauge.roll_right(1); //TURN RIGHT (degree per second)
            //newFrame.HSI_Gauge.roll_right(0.01,10); //TURN RIGHT (degree per x miliseconds)
            //newFrame.HSI_Gauge.roll_left(45); //TURN LEFT (degree per second)
            //newFrame.HSI_Gauge.roll_left(0.5,1000); //TURN LEFT (degree per x miliseconds)
            //newFrame.HSI_Gauge.roll_position(-25); // ROLL to X degree (RANGE 0-360)
            //newFrame.HSI_Gauge.pitch_angle(30); //SET UP-DOWN POSITION [ANGLE (RANGE 0-90 FOR UP ; -90-0 FOR DOWN)]
            //newFrame.HSI_Gauge.pitch_up(0.5,100); //UP MOVEMENT [ANGLE (RANGE 0-90) DEGREE/SECOND)]        
            //newFrame.HSI_Gauge.pitch_up(1,50);//UP MOVEMENT [ANGLE (RANGE 0-90) DEGREE/X ms)]   
            //newFrame.HSI_Gauge.pitch_down(0.5,50);//DOWN MOVEMENT [ANGLE (RANGE 0-90) DEGREE/SECOND)]
            //newFrame.HSI_Gauge.pitch_down(1,x);//DOWN MOVEMENT [ANGLE (RANGE 0-90) DEGREE/X ms)]
            //newFrame.HSI_Gauge.pitch_angle(0);
            //newFrame.HSI_Gauge.speed(120);
            //newFrame.HSI_Gauge.pitch_angle(-30);
            //newFrame.HSI_Gauge.direction(15);
            
            Thread.sleep(500);
            if(x==15){
                y=-1;
            }
            if(x==-15){
               y=+1; 
            }
        }
        */
    }
    
}
