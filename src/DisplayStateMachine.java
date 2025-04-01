
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//CLASS: provides a basic method to display the current state machine. 
public class DisplayStateMachine {


    public DisplayStateMachine() 
    {	

    	try {
    	BufferedImage img;
        img=ImageIO.read(new File(".\\An.PNG"));	
    	if (Equation.A1 == true) { img=ImageIO.read(new File(".\\A1.PNG"));	}
    	if (Equation.A2 == true) { img=ImageIO.read(new File(".\\A2.PNG"));	}
    	if (Equation.A3 == true) {img=ImageIO.read(new File(".\\A3.PNG"));	}

        ImageIcon imgi=new ImageIcon(img);
        JFrame frametest=new JFrame();
        frametest.setSize(750,600);
        JLabel label =new JLabel();
        label.setIcon(imgi);
        frametest.add(label);
        frametest.setVisible(true);
    } catch (IOException error) {
    	System.out.println("--------------------------------------------------------");
    	System.out.println("ERROR: image couldn't get loaded");
    }
    	
    	
}}

