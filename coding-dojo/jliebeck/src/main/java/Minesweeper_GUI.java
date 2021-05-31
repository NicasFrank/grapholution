import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Minesweeper_GUI extends JFrame {

    private int x  = 15;
    private int y  = 15;
    private int bombs  = 30;
    private boolean gameOver = false;

    private Map<Integer, Map<Integer, MweeperField>> boardMap;
    private Map<Integer, position> bombMap;
    private JPanel boardPanel;
    private JPanel headPanel;
    private JTextField bombsField;

    private int bombsMarked;
    private int cleanFields;
    private int seconds;



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
    private boolean placeBombs() {

        Random pX = new Random();
        Random pY = new Random();
        int bombCount = 0;
        //while( bombMap.size() < bombs ) {
        while(bombCount < bombs) {
            int x = (1 + pX.nextInt( X ) );
            int y = (1 + pY.nextInt( Y ) );
            if(!boardMap.get(x).get(y).isBomb() ) {
                boardMap.get(x).get(y).setBomb();
                bombCount++;
                bombMap.put(bombCount, new position(x, y));
            }
        }
        return true;
    }
    public void init(){
        bombMap = new HashMap<Integer, MMWeeper.position>();
        boardMap = new HashMap<Integer, Map<Integer,MweeperField>>();
        bombsMarked = 0;
        cleanFields = (X * Y) - bombs;
        seconds = 0;

        for(int i = 1; i<= X; i++) {
            boardMap.put(i, new HashMap<Integer, MweeperField>());
            for(int j = 1; j <= boardY; j++) {
                boardMap.get(i).put(j, new MweeperField(i, j ));
            }
        }
        placeBombs();
    }
    }
}

