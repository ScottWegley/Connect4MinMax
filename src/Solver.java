package src;

import java.util.HashMap;

public class Solver {

    private static final int[] EXPLORE_ORDER = { 3, 2, 4, 1, 5, 0, 6 };

    private static HashMap<Long, Integer> posMap = new HashMap<>();

    public static int negamax(BitBoard bb, int alpha, int beta) {
        if (bb.isDraw()) {
            return 0;
        }
        for (int i = 0; i < bb.listMoves().length; i++) {
            if (bb.listMoves()[EXPLORE_ORDER[i]] != -1 && bb.isWinningMove(EXPLORE_ORDER[i])) {
                return (43 - bb.getTurnCount()) / 2;
            }
        }

        int max = (41 - bb.getTurnCount()) / 2;
        Integer val = posMap.get(bb.generateKey());
        if (val != null) {
            max = val - 19;
        }
        if (beta > max) {
            beta = max;
            if (alpha >= beta) {
                return beta;
            }
        }

        for (int i = 0; i < bb.listMoves().length; i++) {
            if (bb.listMoves()[EXPLORE_ORDER[i]] != -1) {
                BitBoard b2 = new BitBoard(bb);
                b2.makeMove(EXPLORE_ORDER[i]);
                int score = -negamax(b2, -beta, -alpha);
                if (score >= beta)
                    return score;
                if (score > alpha) {
                    alpha = score;
                }
            }
        }
        posMap.put(bb.generateKey(), alpha + 19);
        return alpha;
    }
}
