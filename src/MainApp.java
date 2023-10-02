package src;

public class MainApp {
    public static final int[] ONE_FROM_WIN = {0,6,3,6,2,1,2,4,3,1,2,2,3};

    public static void main(String[] args) {
        BitBoard bb = new BitBoard();
        int[] moves = ONE_FROM_WIN;
        for (int i : moves) {
            bb.makeMove(i);
        }
        System.out.println(bb);
        System.out.println(Solver.negamax(bb, -21, 21));
    }

    public static void onlineToLocal(String input) {
        int[] output = new int[input.length()];
        for (int i = 0; i < output.length; i++) {
            output[i] = Integer.valueOf((String.valueOf(input.charAt(i)))) - 1;
        }
        for (int i : output) {
            System.out.print(i + ", ");
        }
        System.out.print('\n');
    }
}
