package minesweeper;

public class TBoard extends Board{
    private int colLength, rowLength;
    private String[] colNums, rowNums;
    private String spacer;

    public TBoard(int width, int height, int numMines) {
        super(width, height, numMines);
        colLength = Integer.toString(width-1).length();
        rowLength = Integer.toString(height-1).length();
        colNums = new String[width];
        rowNums = new String[height];

        for (int i = 0; i < width; i++) {
            StringBuffer col = new StringBuffer(Integer.toString(i));
            while (col.length() < colLength) {
                col.insert(0, ' ');
            }
            colNums[i] = col.toString();
        }
        StringBuffer spaces = new StringBuffer();
        for (int i = 0; i < rowLength + 2; i++) {
            spaces.append(' ');
        }
        spacer = spaces.toString();
        for (int i = 0; i < height; i++) {
            StringBuffer row = new StringBuffer(Integer.toString(i));
            while (row.length() <= rowLength) {
                row.insert(0, ' ');
            }
            row.append(' ');
            rowNums[i] = row.toString();
        }
    }
    public void draw() {
        for (int i = 0; i < colLength; i++) {
            System.out.print(spacer);
            for (int j = 0; j < width; j++) {
                System.out.print(colNums[j].charAt(i));
            }
            System.out.println();
        }
        for (int i = 0; i < height; i++) {
            System.out.print(rowNums[i]);

            for (int j = 0; j < width; j++) {
                switch (board[j][i]) {
                    case UNKNOWN:
                        System.out.print("#");
                        break;
                    case MARKED:
                        System.out.print("X");
                        break;
                    case MINE:
                        System.out.print("*");
                        break;
                }
            }
            System.out.println(rowNums[i]);
        }
    }
}
