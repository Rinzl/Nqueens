package main;

import java.util.*;

public class BoardAn extends Board {
    public BoardAn(int totalQueens) {
        super(totalQueens);
    }

    @Override
    protected void initQueensPosition() {
        Random rd = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalQueens; i++) {
            int row;
            do {
                row = rd.nextInt(totalQueens);
            } while (!set.add(row));
            queensPosition[i] = row;
        }
    }

    @Override
    public void calculate() {
        Random rd = new Random();
        int moreBetter;
        Set<Move> moveSet = new HashSet<>();
        int preSize = 0;
        int tries = 0;
        if (h()==0) {
            System.out.println("\n=================== Calculation done. ====================\n");
            //printBoard();
            System.out.println(Arrays.toString(queensPosition));
            return;
        }
        while (true) {
            System.out.println("#"+tries);
            //System.out.println("Start===========");
            //printBoard();
            if (tries==500) {
                System.out.println("ReInitial");
                initQueensPosition();
            }
            ArrayList<Move> betterMove = new ArrayList<>();
            ArrayList<Move> bestMoves = new ArrayList<>();
            ArrayList<Move> conflictMoves = getConflict();
//            Move m;
//            do {
//                int pick = rd.nextInt(conflictMoves.size());
//                m = conflictMoves.get(pick);
//            } while (!moveSet.add(m));
//            moveSet.add(m);
            //printBoard();
            int pick = rd.nextInt(conflictMoves.size());
            Move m = conflictMoves.get(pick);

            int currentSize = conflictMoves.size();

            System.out.println("conflict size : " + currentSize);
//            System.out.println(conflictMoves);
            System.out.println("conflict move : " +m);
            int localMax = h();
            int oldH= localMax;
            System.out.println("first h :"+localMax);
            for (int row=0;row<totalQueens;row++) {
                if (queensPosition[m.col]==row) continue;
                int[] arr = new int[totalQueens];
                System.arraycopy(queensPosition,0,arr,0,totalQueens);
                arr[m.col]=row;
                int hArr = h(arr);
                if (hArr <= localMax ) betterMove.add(new Move(row,m.col,hArr));
            }
            for (Move move : betterMove) {
                if (move.h < localMax) localMax = move.h;
            }
            for (Move move : betterMove) {
                if (move.h == localMax) bestMoves.add(move);
            }
            if (bestMoves.size()>0) {
                int rand = rd.nextInt(bestMoves.size());
                Move choice = bestMoves.get(rand);
                System.out.println("conflict at : " + m);
                System.out.println("best move " + choice);
                queensPosition[choice.col] = choice.row;
            }
            int hafter = h();
            if (hafter == oldH) tries++;
            else tries=0;
            System.out.println("h after move : "+hafter);
            if (hafter == 0) {
                System.out.println("\n=================== Calculation done. ====================\n");
                //printBoard();
                System.out.println(Arrays.toString(queensPosition));
                return;
            }
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
