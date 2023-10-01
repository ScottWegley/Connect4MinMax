package src;

public class Solver {
    public static int negamax(BitBoard bb) {
        if (bb.isDraw()) {
            return 0;
        }
        for (int i = 0; i < bb.listMoves().length; i++) {
            if (bb.listMoves()[i] != -1 && bb.isWinningMove(i)) {
                return (43 - bb.getTurnCount()) / 2;
            }
        }

        int bestScore = -42;
        for (int i = 0; i < bb.listMoves().length; i++) {
            if (bb.listMoves()[i] != -1) {
                bb.makeMove(i);
                int score = -negamax(bb);
                if (score > bestScore)
                    bestScore = score;
                bb.undoMove();
            }
        }
        return bestScore;
    }
}
