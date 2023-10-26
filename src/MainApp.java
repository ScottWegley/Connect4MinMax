package src;

public class MainApp {
    public static final int[] ONE_FROM_WIN = { 0, 6, 3, 6, 2, 1, 2, 2, 2, 3, 0, 0, 3, 6, 6, 5, 3, 0, 3,3,2,6,5,5,5,5,4,0,0,6,4,5};

    public static final int SEARCH_DEPTH = 18;

    public static void main(String[] args) {
        BitBoard bb = new BitBoard();
        int[] moves = ONE_FROM_WIN;
        for (int i : moves) {
            bb.makeMove(i);
        }
        long startTime = System.currentTimeMillis();
        int[] scores = Solver.customSolve(bb);
        startTime = System.currentTimeMillis() - startTime;
        System.out.println(bb);
        int bestPos = -17;
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + " ");
            if(bestPos == -17){
                if(bb.listMoves()[i] != -1){
                    bestPos = i;
                }
            } else {
                if(bb.listMoves()[i] != -1){
                    if(scores[bestPos] <= scores[i]){
                        bestPos = i;
                    }
                }
            }
        }
        System.out.print('\n');
        System.out.println("Seconds Elapsed " + ((double) startTime) / 1000);
        System.out.println("Table Size " + Solver.tableSize() + "  ||  "
                + ((double) Solver.tableSize() / (double) Integer.MAX_VALUE) * 100 + "% Full");
        System.out.println("Reccomended Move: Column " + bestPos);
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
