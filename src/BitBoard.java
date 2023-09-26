/*
 * This file was written as I read through https://github.com/denkspuren/BitboardC4/blob/master/BitboardDesign.md,
 * a very helpful explanation of 
 */

class BitBoard {
    private long[] _bb = { 0, 0 };
    private int[] _height = { 0, 7, 14, 21, 28, 35, 42 };

    BitBoard() {
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