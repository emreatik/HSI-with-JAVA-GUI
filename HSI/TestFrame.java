import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author PC
 */
public class TestFrame extends JFrame{
    
    Display HSI_Gauge; 
    
    public TestFrame(){
        super();       
        
        setLayout(new GridBagLayout());
        setTitle("Flight Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        HSI_Gauge = new Display();
        add(HSI_Gauge); 

        pack();
    }          
    
}
