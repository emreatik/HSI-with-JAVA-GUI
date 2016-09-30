package flightsimulator;        //Package Name

//Libraries
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel {
    
    //Color Constants
    Color color_sky = new Color(60, 143, 232, 255);
    Color color_ground = new Color(122, 59, 59, 255);
    Color pink = new Color(246,146,201,255);
    Color color_speed = new Color(109, 173, 110, 255);
    Color color_compass = new Color(95, 153, 160, 255);
    Color speed_color = new Color(20, 247, 149, 255);
    Color degree_color = new Color(255, 244, 75, 255);
    Color bckgnd;
    
    //Variable Declaration
    int gnd_height,sky_height,pitch,sky_x,speed_lim,speed_scale,altitude_lim,altitude_scale;
    double rotation,rotate,pitch_rotate,flight_speed,flight_limit,directionX
            ,flight_altitude,flight_altitude_lim,compass_direction,compass_direction_limit = 0;
    int roll_time,pitch_time = 1000;
    int x,R,G,B,A=255;    
    String speed_text,altitude_text;
    
    //Constructor
    public Display(){
        super();  
        
        //Default Values
        this.setSize(new Dimension(640,480));//Size of the Panel         
        this.pitch_angle(0);      //0' Pitch Angle
        this.background(3,61,112,255);  //Background Color
        this.speedConfig(1100,100); //Speed Limit and Scale
        this.altitudeConfig(12000,200); //Altitude Limit and Scale
        
        sky_x=240;
        speed_text="";
        altitude_text="";
        
        //Timer for Rotation
        ActionListener rollTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
               rotation += rotate;
               repaint();
            }                                            
        };
        Timer rollTimer = new Timer(roll_time, rollTimerListener);
        rollTimer.start();
        
        //Timer for Pitch
        ActionListener pitchTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                if(pitch_rotate==0){            
                    sky_x=240;
                }else{                                      
                    sky_x -= pitch_rotate*3;                                           
                    sky_height -= pitch_rotate;
                    gnd_height += pitch_rotate;
                }
               repaint();
            }                                            
        };
        Timer pitchTimer = new Timer(pitch_time, pitchTimerListener);
        pitchTimer.start();
        
        //Timer for Speed of Flight
        ActionListener speedTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
               //if(flight_limit!=flight_speed)
               //    ++flight_speed;
               flight_speed=flight_limit;
               speed_text = String.format("%5d",(int) flight_speed);
            }                                            
        };
        Timer speedTimer = new Timer(100, speedTimerListener);
        speedTimer.start();
        
        //Timer for Altitude of Flight
        ActionListener altitudeTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
               //if((flight_altitude_lim)!=flight_altitude)
                //    ++flight_altitude;
               flight_altitude=flight_altitude_lim;
               altitude_text = String.format("%6d",(int) (flight_altitude*10));
            }                                            
        };
        Timer altitudeTimer = new Timer(100, altitudeTimerListener);
        altitudeTimer.start();
        
        ////Timer for Compass
        ActionListener compassTimerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
              //if(compass_direction_limit>compass_direction & compass_direction_limit>=0)
              //    ++compass_direction;
              //else if(compass_direction_limit<compass_direction & compass_direction_limit<0)
              //    --compass_direction;
               compass_direction=compass_direction_limit;
            }                                            
        };        
        Timer compassTimer = new Timer(50, compassTimerListener);
        compassTimer.start();
               
    }  
        
    //Speed Configuration
    public void speedConfig(int lim, int sc){
        speed_lim=lim;
        speed_scale=sc;
    }
    
    //Altitude Configuration
    public void altitudeConfig(int lim, int sc){
        altitude_lim=lim;
        altitude_scale=sc;
    }
    
    //Set Background Color
    public void background(int r,int g, int b, int a){
        R = r;
        G = g;
        B = b;
        A = a;        
        bckgnd = new Color(R,G,B,A);
    }
    
    //Set Direction of Flight
    public void direction(double direction){
        directionX =direction;
        while(direction>359){
            direction = direction-360;
            directionX =direction;
        }
        while(direction<0){
            direction = direction+360;
            directionX =direction;
        }
        
       if(direction>=180){
            compass_direction_limit = -(double)400/36*4*(360-direction)/10;
        }else{
            compass_direction_limit = (double)400/36*4*direction/10;
        }
        
    }
    
    //Set Speed of Flight
    public void speed(int speed){
        flight_limit = speed;
    }
    
    //Set Altitude of Flight
    public void altitude(double altitude){
        flight_altitude_lim = (double)altitude/(double)10;
    }
    
    //Set Pitch Angle of Flight
    public void pitch_angle(double pitch_angle){
        if(pitch_angle<=90 && pitch_angle>=(-90)){            
            pitch = (int) (pitch_angle/90 * 200);
            sky_height = 240 + pitch;
            gnd_height = 240 - pitch;
            sky_x=240;
        }
    }
    
    //Set Angle of Pitch Up
    public void pitch_up(double pitch_up_angle){
        if(pitch_up_angle<=90 && pitch_up_angle>=0){            
            pitch = (int) (pitch_up_angle/90 * 200);
            pitch_rotate = -pitch;              
            sky_height = 240;
            gnd_height = 240; 
        }        
    }
    
    //Set Angle of Pitch Up and Speed
    public void pitch_up(double pitch_up_angle, int pitch_up_time){
        if(pitch_up_angle<=90 && pitch_up_angle>=(0)){            
            pitch = (int) (pitch_up_angle/90 * 200);
            pitch_rotate = -pitch;
            pitch_time=pitch_up_time;                          
            sky_height = 240;
            gnd_height = 240;
        }        
    }
    
    //Set Angle of Pitch Down
    public void pitch_down(double pitch_down_angle){
        if(pitch_down_angle<=90 && pitch_down_angle>=(0)){            
            pitch = (int) (pitch_down_angle/90 * 200);
            pitch_rotate = +pitch;                          
            sky_height = 240;
            gnd_height = 240;
        }        
    }
    
    //Set Angle of Pitch Down and Speed
    public void pitch_down(double pitch_down_angle, int pitch_down_time){
        if(pitch_down_angle<=90 && pitch_down_angle>=(0)){            
            pitch = (int) (pitch_down_angle/90 * 200);
            pitch_rotate = +pitch;
            pitch_time=pitch_down_time;                          
            sky_height = 240;
            gnd_height = 240;
        }        
    }
    
    //Set Rotation of Flight
    public void roll_position(double roll_position){
        rotation = -roll_position;  
    }
    
    //Rotate Flight to Left
    public void roll_left(double roll_left){
        rotate = roll_left;                
        sky_height = 240;
        gnd_height = 240;         
    }
    
    //Rotate Flight to Left in a Specific Time
    public void roll_left(double roll_left, int roll_left_time){
        rotate = roll_left;
        roll_time = roll_left_time;                
        sky_height = 240;
        gnd_height = 240; 
    }
    
    //Rotate Flight to Right
    public void roll_right(double roll_right){
        rotate = -roll_right;                
        sky_height = 240;
        gnd_height = 240; 
    }
    
    //Rotate Flight to Right in a Specific Time
    public void roll_right(double roll_right, int roll_right_time){
        rotate = -roll_right;
        roll_time = roll_right_time;                
        sky_height = 240;
        gnd_height = 240; 
    }
    
    //Create GUI
    @Override
    public void paintComponent (Graphics g ) {
        super.paintComponent(g);
        
        //Set Sky and Ground Proportion in HSI
        
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke basicStroke = new BasicStroke(2f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color_sky);      
        g2d.rotate(Math.toRadians(rotation),320,240);
        g2d.fillRect(0, 0, 640,sky_height);
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color_ground);      
        g2d.fillRect(0, sky_height, 640,gnd_height);
        g2d.setPaint(Color.WHITE);
        g2d.setStroke(basicStroke);
        g2d.drawLine(0, sky_height, 640, sky_height);
        
        basicStroke = new BasicStroke(1.5f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
        g2d.setStroke(basicStroke);                
        for(int i=0; i<=36; i+=1){ 
            if(i%4==0){
                int x=10;
                g2d.drawLine(280,(int) (sky_x+i*((double)200/(double)36)*3+pitch*3),360, (int) (sky_x+i*((double)200/(double)36)*3+pitch*3));
                g2d.drawString(Integer.toString(x*(i/4)), 360,(int) (sky_x+i*((double)200/(double)36)*3+pitch*3+5));
            }else if(i%2==0){
                g2d.drawLine(300,(int) (sky_x+i*((double)200/(double)36)*3+pitch*3),340, (int) (sky_x+i*((double)200/(double)36)*3+pitch*3));
            }else{
                g2d.drawLine(310,(int) (sky_x+i*((double)200/(double)36)*3+pitch*3),330, (int) (sky_x+i*((double)200/(double)36)*3+pitch*3));

            }
        }
        for(int i=0; i<=36; i+=1){ 
            if(i%4==0){
                int x=10;
                g2d.drawLine(280,(int) (sky_x-i*((double)200/(double)36)*3+pitch*3),360, (int) (sky_x-i*((double)200/(double)36)*3+pitch*3));
                g2d.drawString(Integer.toString(x*(i/4)), 360,(int) (sky_x-i*((double)200/(double)36)*3+pitch*3+5));
            }else if(i%2==0){
                g2d.drawLine(300,(int) (sky_x-i*((double)200/(double)36)*3+pitch*3),340, (int) (sky_x-i*((double)200/(double)36)*3+pitch*3));
            }else{
                g2d.drawLine(310,(int) (sky_x-i*((double)200/(double)36)*3+pitch*3),330, (int) (sky_x-i*((double)200/(double)36)*3+pitch*3));

            }
        }
        
        //Set Rotation of Flight in HSI
        
        g2d.rotate(Math.toRadians(-rotation),320,240);        
        basicStroke = new BasicStroke(7f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
        g2d.setStroke(basicStroke);
        g2d.setPaint(Color.YELLOW);
        g2d.drawLine(240, 240, 290, 240);
        g2d.drawLine(350, 240, 400, 240);
        g2d.drawLine(290, 238, 290, 260);
        g2d.drawLine(350, 238, 350, 260);        
        basicStroke = new BasicStroke(2f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
        g2d.setStroke(basicStroke);
        g2d.setPaint(pink);
        g2d.drawLine(140, 240, 500, 240);
        g2d.drawLine(320, 80, 320, 400);
        g2d.fillOval(315, 235, 10, 10);
        g2d.setPaint(pink);
        
        g2d.setPaint(Color.WHITE);
        int[] xPoints ={310,330,320};
        int[] yPoints ={55,55,65};
        g2d.fillPolygon(xPoints, yPoints, 3);
                
        basicStroke = new BasicStroke(200f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
        g2d.setStroke(basicStroke);
        g2d.setPaint(bckgnd);
        g2d.drawOval(20,-60,600,600);      
        
        //Speed of Flight
        
        basicStroke = new BasicStroke(1f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setPaint(color_speed);
        g2d.setStroke(basicStroke);  
        g2d.fillRect(0, 30, 67, 420);
        
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(80-25,30,80-25,440);         
        for(int i=0; i<=speed_lim ; i++){             
            if(i%speed_scale==0){      
                basicStroke = new BasicStroke(1.5f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
                g2d.setStroke(basicStroke); 
                g2d.setFont(new Font("Sans_Serif", Font.PLAIN, 11));
                g2d.drawLine(55-25, (int) (240-i*4*7/10+flight_speed*28/10),80-25, (int) (240-i*4*7/10+flight_speed*28/10));
                g2d.drawString(String.format("%3d", i),30-25, (int) (240-i*4*7/10+flight_speed*28/10+5));                
            }else if(i%2==0){
                basicStroke = new BasicStroke(1f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
                g2d.setStroke(basicStroke); 
                g2d.drawLine(65-25, (int) (240-i*4*7/10+flight_speed*28/10),80-25, (int) (240-i*4*7/10+flight_speed*28/10));
            }
        }     
        int[] xPointspeed ={80-25,90-25,90-25};
        int[] yPointspeed ={440-200,435-200,445-200};
        g2d.drawPolygon(xPointspeed, yPointspeed, 3);
        
        g2d.setPaint(bckgnd);
        g2d.fillRect(25, 0, 70, 30);
        g2d.setPaint(bckgnd);
        g2d.fillRect(25,450, 70, 480);
        
        //Altitude of Flight
        
        basicStroke = new BasicStroke(1.5f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setPaint(color_speed);
        g2d.setStroke(basicStroke);  
        g2d.fillRect(570, 30, 70, 420);
        
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(640-80+20,30,640-80+20,440);         
        for(int i=0; i<=(altitude_lim/10) ; i++){             
            if(i%(altitude_scale/10)==0){              
                basicStroke = new BasicStroke(1.5f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
                g2d.setStroke(basicStroke);
                g2d.setFont(new Font("Sans_Serif", Font.PLAIN, 11));
                g2d.drawLine(640-55+20, (int) (240-i*4*7/10+flight_altitude*28/10),640-80+20, (int) (240-i*4*7/10+flight_altitude*28/10));
                g2d.drawString(String.format("%3d", i*10),640-50+16, (int) (240-i*4*7/10+flight_altitude*28/10+5));                
            }else if(i%2==0){ 
                basicStroke = new BasicStroke(1f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
                g2d.setStroke(basicStroke);
                g2d.drawLine(640-65+20, (int) (240-i*4*7/10+flight_altitude*28/10),640-80+20, (int) (240-i*4*7/10+flight_altitude*28/10));
            }
        }     
        int[] xPointheight ={640-80+20,640-90+20,640-90+20};
        int[] yPointheight ={440-200,435-200,445-200};
        g2d.drawPolygon(xPointheight, yPointheight, 3);        
        
        //Show Direction of Flight on Compass
        
        basicStroke = new BasicStroke(1f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setPaint(color_compass);
        g2d.setStroke(basicStroke);  
        g2d.fillRect(120, 448, 400, 35);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(120,455,520,455); 
        
        for(int i=0; i<360 ; i++){        
            if(i%10==0){
                g2d.drawLine((int) (200+120+i*(double)40/36*4-compass_direction),455, (int) (200+120+i*(double)40/36*4-compass_direction),465);
            switch(i){
                case 0: 
                    g2d.drawString(String.format("N"), (int) (200+120+i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 90:
                    g2d.drawString(String.format("E"), (int) (200+120+i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 180:
                    g2d.drawString(String.format("S"), (int) (200+120+i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 270:
                    g2d.drawString(String.format("W"), (int) (200+120+i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 360:
                    g2d.drawString(String.format("N"), (int) (200+120+i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                default:
                    g2d.drawString(String.format("%3d", i), (int) (200+120+i*(double)40/36*4-compass_direction)-10,477);   
            }
            }else{
                g2d.drawLine((int) (200+120+i*(double)40/36*4-compass_direction),455, (int) (200+120+i*(double)40/36*4-compass_direction),460);
            }
        }  
        
        for(int i=0; i<=360 ; i++){   
            if(i%10==0){
                g2d.drawLine((int) (200+120-i*(double)40/36*4-compass_direction),455, (int) (200+120-i*(double)40/36*4-compass_direction),465);
                switch(i){
                case 0: 
                    g2d.drawString(String.format("N"), (int) (200+120-i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 90:
                    g2d.drawString(String.format("E"), (int) (200+120-i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 180:
                    g2d.drawString(String.format("S"), (int) (200+120-i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 270:
                    g2d.drawString(String.format("W"), (int) (200+120-i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                case 360:
                    g2d.drawString(String.format("N"), (int) (200+120-i*(double)40/36*4-compass_direction)-5,477);  
                    break;
                default:
                    g2d.drawString(String.format("%3d", 360-i), (int) (200+120-i*(double)40/36*4-compass_direction)-10,477);   
            }           
            }else{
                g2d.drawLine((int) (200+120-i*(double)40/36*4-compass_direction),455, (int) (200+120-i*(double)40/36*4-compass_direction),460);
            }                                   
             
        }  
        
        int[] xPointCOMP ={320-5,325-5,330-5};
        int[] yPointCOMP ={450,455,450};
        g2d.drawPolygon(xPointCOMP, yPointCOMP, 3);
        
        g2d.setPaint(bckgnd);
        g2d.fillRect(0,0, 120,30);
        g2d.fillRect(0,450, 120,30);
        g2d.fillRect(520,0, 120,30); 
        g2d.fillRect(520,450, 120,30);        
        g2d.setPaint(Color.WHITE);
        g2d.setPaint(color_speed);
        g2d.setFont(new Font("Sans_Serif", Font.BOLD, 12));
        g2d.drawString("ALTITUDE", 578, 468); 
        g2d.drawString("SPEED", 2, 468); 
        g2d.setFont(new Font("Sans_Serif", Font.PLAIN, 11));
        
        g2d.setFont(new Font("Sans_Serif", Font.BOLD, 13));
        g2d.setPaint(speed_color);
        g2d.drawString(speed_text, 70, 245); 
        g2d.drawString(altitude_text, 525, 245); 
        g2d.setPaint(degree_color);
        g2d.drawString(String.format("%3d",(int) -rotation)+"'", 310, 30);
        
        basicStroke = new BasicStroke(5f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setStroke(basicStroke);
        g2d.setFont(new Font("Sans_Serif", Font.BOLD, 13));
        g2d.setPaint(pink);
        g2d.drawString(String.format("%3d",(int) pitch*9/20), 190, 235);
        
        g2d.setFont(new Font("Sans_Serif", Font.BOLD, 12));
        Color circle = new Color(149, 217, 220, 255);
        g2d.setPaint(circle);
        g2d.drawOval(90, 380, 40, 40);
        g2d.drawString(String.format("%3d",(int) directionX)+"'", 90+10, 380+28-5);
        
        basicStroke = new BasicStroke(2.5f,
        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER); 
        g2d.setStroke(basicStroke);
        g2d.setPaint(speed_color);
        g2d.drawRect(68,230,40,20);
        g2d.drawRect(525,230,45,20);
        
        g2d.setPaint(Color.WHITE);
        g2d.rotate(Math.toRadians(rotation),320,240);  
        for(int i=0; i<360; i+=5){                
            if(i%45==0){
                basicStroke = new BasicStroke(2f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
                g2d.setStroke(basicStroke);   
                g2d.drawLine(320,40,320,60); 
            }else{
                basicStroke = new BasicStroke(1f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);         
                g2d.setStroke(basicStroke);                
                g2d.drawLine(320,40,320,50); 
            }                       
            g2d.rotate(Math.toRadians(5),320,240); 
        }
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640,480);
    }   
    
}
