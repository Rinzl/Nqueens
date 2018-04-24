package main;


import java.util.*;

public class SA extends Board {
    SA(int totalQueens) {
        super(totalQueens);
    }

    @Override
    public void calculate() {
        int hToBeat = h();
        double temperature = 1000;
        double anneal_rate = 0.99;
        for (int i=0;i< 100000 && hToBeat > 0; i++) {
            makeMove(getConflict(),hToBeat, temperature);
            hToBeat = h();
            System.out.println(hToBeat);
            temperature = Math.max(temperature * anneal_rate, 0.01);
        }
        System.out.println("Complete !");
        System.out.println(Arrays.toString(queensPosition));
        //printBoard();
    }

    private void makeMove(ArrayList<Move> conflictMoves, int hToBeat, double temperature) {
        Random rd = new Random();
        while (true) {
            int pick = rd.nextInt(conflictMoves.size());
            Move m = conflictMoves.get(pick);
            //int nCol = (int) (Math.random() * queensPosition.length);
            int nRow = (int) (Math.random() * queensPosition.length);
            //int temp = queensPosition[nCol];
            int temp = queensPosition[m.col];
            queensPosition[m.col] = nRow;
            if (temp == nRow) continue;
            int cost = h();
            if (cost < hToBeat) break;
            int de  = hToBeat - cost;
            double p = Math.min(1, Math.exp(de/temperature));
            if (Math.random() < p) break;
            queensPosition[m.col] = temp;
        }
    }
    private ArrayList<Move> getConflict() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i=0;i<queensPosition.length;i++) {
            if (isThreaten(queensPosition[i],i)) {
                Move move =new Move(queensPosition[i],i,0);
                moves.add(move);
                //System.out.println(move);
            }
        }
        return moves;
    }
}
