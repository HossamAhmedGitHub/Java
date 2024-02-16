
package javadrawingapp;
import java.awt.Color;
import javax.swing.JFrame;
public class JavaDrawingApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Panel appPanel = new Panel();
        frame.add(appPanel);
        appPanel.setBackground(Color.WHITE); 
        frame.setTitle("Drawing App");
        frame.setSize(750,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // frame in the middle
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit safely
    }
    
}
