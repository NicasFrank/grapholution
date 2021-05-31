public class MweeperField implements mousePerformer {
    private position    pos;
    private FieldStatus status          = FieldStatus.HIDE_UNMARKED;
    private boolean     isBomb          = false;
    private int         bombsAroundMe   = 0;
    private JButton     but;
    private boolean     isTriggered     = false;

    public mweeperField( int x, int y ) {
        this.pos = new position(x, y);
        init();
    }
    public mweeperField( position p ) {
        this.pos = p;
        init();
    }
    public void resetField() {
        status          = FieldStatus.HIDE_UNMARKED;
        isBomb          = false;
        bombsAroundMe   = 0;
        isTriggered     = false;
        but.setFont(new Font("Arial", Font.BOLD, 13));
        but.setBackground(Color.LIGHT_GRAY);
        but.setText(" ");
        but.setEnabled(true);
    }
    public void setBomb() {
        this.isBomb = true;
    }
    public boolean isBomb() {
        return isBomb;
    }