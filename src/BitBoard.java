/*
 * This file was written as I read through https://github.com/denkspuren/BitboardC4/blob/master/BitboardDesign.md,
 * a very helpful explanation of 
 */

class BitBoard {
    /**
     * This is an array of two longs. The first when read in binary stores the
     * positions of Player 0, the second of Player 1.
     */
    private final long[] _bb = { 0, 0 };

    /**
     * This array stores the next index for a move to be played in each column.
     * It's initialized to the bit at the bottom of each column.
     */
    private final int[] _height = { 0, 7, 14, 21, 28, 35, 42 };

    /**
     * Keeps track of how many moves have been made. When it is even, it's player
     * 1's turn, odd is Player 1.
     */
    private int _counter = 0;

    private int X_PLAYER_NUM;
    private int O_PLAYER_NUM;

    /**
     * This array stores every move made in this instance of Connect 4. There are
     * only 42 positions, so we only need to store 42 moves.
     */
    private final int[] _moves = new int[42];

    BitBoard() {
        this(0, 1);
    }

    BitBoard(int xNum, int oNum) {
        if ((xNum == 0 || xNum == 1) && (oNum == 0 || oNum == 1) && xNum != oNum) {
            X_PLAYER_NUM = xNum;
            O_PLAYER_NUM = oNum;
        } else {
            X_PLAYER_NUM = 0;
            O_PLAYER_NUM = 1;
        }
    }
    }

    public static void main(String[] args) {
        System.out.print(boardViewFromLong(
                new long[] { 0b0000010_0000011_0000000_0000000_0000000, 0b0000001_0000100_0000001_0000000_0000000 }));
    }

    public static void printLong(long l) {
        System.out.println(Long.toBinaryString(l));
    }

    public static String boardViewFromLong(long[] bb) {

        String out = "";

        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                char c = '.';
                if (((bb[0] >> (i + j * 7)) & 0x1) == 1) {
                    c = 'X';
                } else if (((bb[1] >> (i + j * 7)) & 0x1) == 1) {
                    c = 'O';
                }
                out = out + c + " ";
            }
            out = out + '\n';
        }

        out = out + "-------------\n";
        out = out + "0 1 2 3 4 5 6\n";
        return out;
    }

    public String boardViewFromLong(){
        return BitBoard.boardViewFromLong(this._bb);
    }
}