package src;

public class MainApp {
    public static final int[] ONE_FROM_WIN = { 0, 6, 3, 6, 2, 1, 2, 2 };

    public static void main(String[] args) {
        BitBoard bb = new BitBoard();
        int[] moves = ONE_FROM_WIN;
        for (int i : moves) {
            bb.makeMove(i);
        }
        long startTime = System.currentTimeMillis();
        int score = Solver.solve(bb);
        startTime = System.currentTimeMillis() - startTime;
        System.out.println(bb);
        System.out.println(score);
        System.out.println("Seconds Elapsed " + ((double) startTime) / 1000);
        System.out.println("Table Size " + Solver.tableSize() + "  ||  "
                + ((double) Solver.tableSize() / (double) Integer.MAX_VALUE) * 100 + "% Full");
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
