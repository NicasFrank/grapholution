import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class MMWeeper<position> extends JFrame implements Runnable {

    private JFrame  mainFrame ;
    private JPanel  mainPanel;

    private int boardX  = 15;
    private int boardY  = 15;
    private int bombs   = 35;

    private int bombsMarked;
    private int cleanFields;
    private int seconds;

    private boolean gameOver = false;

    private Map<Integer, Map<Integer, mweeperField>> boardMap;
    private Map<Integer,position> bombMap;
    private JPanel boardPanel;
    private JPanel headPanel;
    private JTextField bombsField;

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new JFrame("MMWEEEEEEPER");
                int w = Toolkit.getDefaultToolkit().getScreenSize().width;
                int h = Toolkit.getDefaultToolkit().getScreenSize().height;
                mainFrame.setPreferredSize(new Dimension(350,390));
                mainFrame.setResizable(true);
                mainPanel = new JPanel(new BorderLayout());
                init();

                setPanel();
                mainFrame.add(mainPanel);
                mainFrame.setContentPane(mainFrame.getContentPane());
                mainFrame.pack();
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });
    }


    public static void main( String args[] ) throws Exception
    {

    }

    private void init() {
        bombMap     = new HashMap<Integer, MMWeeper.position>();
        boardMap    = new HashMap<Integer, Map<Integer,mweeperField>>();
        bombsMarked = 0;
        cleanFields = (boardX * boardY) - bombs;
        seconds     = 0;

        for(int i = 1; i<= boardX; i++) {
            boardMap.put(i, new HashMap<Integer, mweeperField>());
            for(int j = 1; j <= boardY; j++) {
                boardMap.get(i).put(j, new mweeperField(i, j ));
            }
        }
        placeBombs();
    }

    private boolean placeBombs() {

        Random pX = new Random();
        Random pY = new Random();
        int bombCount = 0;
        //while( bombMap.size() < bombs ) {
        while(bombCount < bombs) {
            int x = (1 + pX.nextInt( boardX ) );
            int y = (1 + pY.nextInt( boardY ) );
            //if(!boardMap.get(x).get(y).isBomb() ) {
             //   boardMap.get(x).get(y).setBomb();
                bombCount++;
                bombMap.put(bombCount, new position(x, y));
            }
        }
        return true;
    }

    private JPanel head() {
        headPanel = new JPanel(new BorderLayout());

        bombsField = new JTextField(6);
        bombsField.setEditable(true);
        bombsField.setText( String.valueOf(bombs));

        JButton start = new JButton("Start");
        start.addActionListener( new mweeperAction(GameActions.START) );

        headPanel.add(bombsField, BorderLayout.LINE_START);

        headPanel.add(start, BorderLayout.LINE_END);
        return headPanel;
    }

    private JPanel board() {
        boardPanel = new JPanel();
        GridLayout gLayout = new GridLayout(15, 15, 0, 0 );
        boardPanel.setLayout(gLayout);

        for( Integer x : boardMap.keySet()) {
            for(Integer y : boardMap.get(x).keySet()) {
                boardPanel.add( boardMap.get(x).get(y).getButton() );
            }
        }

        return boardPanel;
    }

    private void gameOver() {
        this.gameOver = true;
        for( Integer x : boardMap.keySet()) {
            for(Integer y : boardMap.get(x).keySet()) {
                boardMap.get(x).get(y).trigger();
            }
        }
    }

    private void setPanel() {

        mainPanel.add(head(), BorderLayout.PAGE_START);

        mainPanel.add(board(), BorderLayout.CENTER);
    }
}


