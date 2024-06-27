import javax.swing.JFrame;
import java.awt.Dimension;
import java.io.IOException;

public class ThreeCardPoker
{
    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame("Three Card Poker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Exits program upon closing the window

        Dimension size = new Dimension(1400, 800);

        //Create ShapesJPanel
        TCPPanel sp = new TCPPanel();
        frame.add(sp);
        frame.setSize(size);
        frame.setMinimumSize(new Dimension(size));
        frame.setMaximumSize(new Dimension(size));
        frame.setVisible( true );
    }   //end of main

}
