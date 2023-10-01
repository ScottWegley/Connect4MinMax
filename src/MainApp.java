package src;

public class MainApp {
    public static void main(String[] args) {
        BitBoard bb = new BitBoard();
        int[] moves = { 0, 0, 1, 0, 2, 0, 6 };
        for (int i : moves) {
            bb.makeMove(i);
        }
        System.out.println(bb);
    }
}
