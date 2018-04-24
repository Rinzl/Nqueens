package main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Board {

    protected int[] queensPosition;
    protected int totalQueens;

    // each element in queensPosition is row index

    public Board(int totalQueens) {
        this.totalQueens = totalQueens;
        this.queensPosition = new int[totalQueens];
        initQueensPosition();
    }

    protected void initQueensPosition() {
        Random rd = new Random();
        for (int i = 0; i < totalQueens; i++) {
            queensPosition[i] = rd.nextInt(totalQueens);
        }
    }

    public int[] getQueensPosition() {
        return queensPosition;
    }

    public void setQueensPosition(int[] queensPosition) {
        this.queensPosition = queensPosition;
    }

    public void printBoard() {
        System.out.println("Queens position : " + Arrays.toString(queensPosition));
        System.out.println();
        int[][] board = new int[totalQueens][totalQueens];
        for (int i = 0; i < totalQueens; i++) {
            board[queensPosition[i]][i] = 1;
        }
        for (int i = 0; i < totalQueens; i++) {
            for (int j = 0; j < totalQueens; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // return h of current state
    public int h() {
        int score = 0;
        for (int i = 0; i < totalQueens; i++) {
            for (int j = i + 1; j < totalQueens; j++) {
                // check diagonal
                int offset = j - i;
                if (queensPosition[i] == queensPosition[j]) score++;
                if (queensPosition[i] == (queensPosition[j] + offset) || queensPosition[i] == (queensPosition[j] - offset)) {
                    score++;
                }
            }
        }
        return score;
    }
    public static int h(int[] mQueens) {
        int score = 0;
        for (int i = 0; i < mQueens.length; i++) {
            for (int j = i + 1; j < mQueens.length; j++) {
                // check diagonal
                int offset = j - i;
                if (mQueens[i] == mQueens[j]) {
                    score++;
                }
                if (mQueens[i] == (mQueens[j] + offset) || mQueens[i] == (mQueens[j] - offset)) {
                    score++;
                }
            }
        }
        return score;
    }

    public void calculate() {
        printBoard();
        Random rd= new Random();
        System.out.println("\n=================== Start Calculating ! ==================\n");
        int i = 0;
        int tries = 0;
        boolean firstRun = true;
        int moreBetter=1;
        while (true) {
            i++;
            if (moreBetter==0) {
                System.out.println("ReInitial");
                initQueensPosition();
                tries=0;
            }
            tries--;
            firstRun = false;
            int localMax = h();
            int localScore;
            ArrayList<Move> moves = new ArrayList<>();
            for (int col = 0; col < totalQueens; col++) {
//                if (!isThreaten(queensPosition[col],col)) continue;
//                for (int row = 0; row < totalQueens; row++) {
//                    if (queensPosition[col] == row) continue;
//                    int storedPosition = queensPosition[col];
//                    queensPosition[col] = row;
//                    localScore = h();
//                    if (localScore == 0) {
//                        System.out.println("\n=================== Calculation done. ====================\n");
//                        //printBoard();
//                        System.out.println(Arrays.toString(queensPosition));
//                        System.out.println("\nIt took " + i +" steps to complete this calculation !");
//                        return;
//                    }
//                    if (localScore > localMax) queensPosition[col] = storedPosition;
//                    else if (localScore == localMax) {
//                        int p = rd.nextInt(100)+1;
//                        if (p<25) queensPosition[col]=storedPosition;
//                    }
//                    else localMax = localScore;
//                }
                for (int row = 0;row<totalQueens;row++) {
                    if (queensPosition[col] == row) continue;
                    int[] arr = new int[totalQueens];
                    System.arraycopy(queensPosition,0,arr,0,totalQueens);
                    arr[col]=row;
                    int hArr = h(arr);
                    if (hArr <= localMax ) moves.add(new Move(row,col,hArr));
                }
            }
            ArrayList<Move> bestMoves = new ArrayList<>();
            int hToBeat = localMax;
            for (Move m :moves) {
                if (m.h < hToBeat) {
                    hToBeat =m.h;
                }
            }
            for (Move m :moves) {
                if (m.h == hToBeat) {
                    bestMoves.add(new Move(m.row,m.col,m.h));
                }
            }
            if (bestMoves.size()>0) {
                int pick = rd.nextInt(bestMoves.size());
                Move m = bestMoves.get(pick);
                queensPosition[m.col] = m.row;
            }
            moreBetter = bestMoves.size();
            if (h() == 0) {
                System.out.println("\n=================== Calculation done. ====================\n");
                //printBoard();
                System.out.println(Arrays.toString(queensPosition));
                System.out.println("\nIt took " + i +" steps to complete this calculation !");
                return;
            }
            //printBoard();
        }
    }

    boolean isThreaten(int row, int col) {
        for (int c = 0;c<totalQueens;c++) {
            if (c!=col && queensPosition[c] == row) return true;
        }
        for (int i = 0; i < totalQueens; i++) {
            int offset = col - i;
            if (i == col) continue;
            if (queensPosition[col] == queensPosition[i] + offset || queensPosition[col] == queensPosition[i] - offset) {
                return true;
            }
        }
        return false;
    }
}
