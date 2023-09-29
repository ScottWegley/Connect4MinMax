package src;

/*
 * This file was written as I read through https://github.com/denkspuren/BitboardC4/blob/master/BitboardDesign.md,
 * a very helpful explanation of how to store a Connect4 game state in a long
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

    BitBoard(BitBoard bb) {
        this(bb.getXNum(), bb.getONum());
        for (int move : getMoves()) {
            bb.makeMove(move);
        }
    }

    public int getXNum() {
        return X_PLAYER_NUM;
    }

    public int getONum() {
        return O_PLAYER_NUM;
    }

    public int[] getMoves() {
        return _moves;
    }

    public static void main(String[] args) {
        BitBoard bb = new BitBoard();
        int[] moves = { 0, 0, 1, 0, 2, 0, 6 };
        for (int i : moves) {
            bb.makeMove(i);
        }
        System.out.println(bb);
    }

    /**
     * @return True if it is Player 0's turn, False if it is Player 1's turn.
     */
    public boolean currentTurn() {
        return (_counter & 1) == 0;
    }

    /**
     * @return How many moves have been played so far.
     */
    public int getTurnCount() {
        return _counter;
    }

    /**
     * @return The long representing Player 0.
     */
    public long getPlayer0Board() {
        return this._bb[0];
    }

    /**
     * @return The long representing plauer 2.
     */
    public long getPlayer1Board() {
        return this._bb[1];
    }

    /**
     * @param bitboard The long to check for a win.
     * @return True if the bitboard contains a win.
     */
    public static boolean isWin(long bitboard) {
        int[] directions = { 1, 7, 6, 8 };
        long bb;
        for (int i : directions) {
            bb = bitboard & (bitboard >> i);
            if ((bb & (bb >> (2 * i))) != 0)
                return true;
        }
        return false;
    }

    /**
     * @return True is a player has won, false if no one has won.
     */
    public boolean isWin() {
        return BitBoard.isWin(_bb[0]) | BitBoard.isWin(_bb[1]);
    }

    /**
     * Returns an array of integers that represents the playable moves for this
     * BitBoard.
     * 
     * @return An array of integers, where every column that needs to be skipped is
     *         -1.
     */
    public int[] listMoves() {
        int[] moves = new int[7];
        long OVERFLOW = 0b10000000_10000000_10000000_10000000_10000000_10000000_10000000L;
        for (int i = 0; i < moves.length; i++) {
            moves[i] = -1;
            if ((OVERFLOW & (1L << _height[i])) == 0) {
                moves[i] = i;
            }
        }
        return moves;
    }

    /**
     * Function to generate a CLI View of the Board.
     * 
     * @return A String with the CLI View of the Connect 4 Board.
     */
    public String boardViewFromLong() {
        String out = "";
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                char c = '.';
                if (((_bb[X_PLAYER_NUM] >> (i + j * 7)) & 0x1) == 1) {
                    c = 'X';
                } else if (((_bb[O_PLAYER_NUM] >> (i + j * 7)) & 0x1) == 1) {
                    c = 'O';
                }
                out = out + c + " ";
            }
            out = out + '\n';
        }

        out = out + "-------------\n";
        out = out + "0 1 2 3 4 5 6";
        return out;
    }

    /**
     * Makes a move in our BitBoard. Notably, does not need to know whose turn it
     * is.
     * We calculate that with {@link #_counter our counter} & 1, which returns 0 if
     * odd, 1 if
     * even.
     * We then get the location of the next move that can be played for the
     * specified
     * column c and increment that location so the next move is correct. We move our
     * 1, representing the new token, to that location, and combine it with our
     * BitBoard.
     * 
     * @param c The specified column to add a token to.
     */
    public void makeMove(int c) {
        this._bb[_counter & 1] ^= (1L << _height[c]++);
        _moves[_counter++] = c;
    }

    /**
     * This is pretty much the inverse of {@link #makeMove()}.
     * Get the last move made, find the last token in that column,
     * move a one into that position, and exclusive or it to get rid
     * of the token in that position.
     */
    void undoMove() {
        _bb[--_counter & 1] ^= (1L << --_height[_moves[_counter]]);
    }

    @Override
    public String toString() {
        String s = "";
        s = s + boardViewFromLong() + "\n";
        s = s + "Player 0: " + (X_PLAYER_NUM == 0 ? "X" : "O") + " || Player 1: " + (O_PLAYER_NUM == 1 ? "O" : "X");
        s = s + "\n";
        if (isWin()) {
            s = s + "Victory for " + (!currentTurn() ? "Player 0" : "Player 1");
        } else {
            s = s + "Current Turn: " + (currentTurn() ? "Player 0" : "Player 1");
        }
        return s;
    }
}