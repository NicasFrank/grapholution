import javax.swing.*;
import java.awt.*;

public class Minesweeper_GUI extends JFrame {

    private int boardX  = 15;
    private int boardY  = 15;
    private int bombs   = 30;

    public static void main( String args[] ) throws Exception
    {
        //Set Gameboard
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout( new GridLayout(10, 10));

        //Set Buttons
        for ( int x = 0; x < 10; x++ )
        {
            for ( int y = 0; y < 10; y++ )
            {
                JButton button = new JButton();
                //Size Buttons
                button.setPreferredSize(new Dimension(20, 20) );
                frame.add( button );
            }
        }
        // Size Frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
