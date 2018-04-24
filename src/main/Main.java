package main;

public class Main {
    public static void main(String[] args) {
        //Board board = new Board(8);
        //board.setQueensPosition(new int[]{5, 2, 4, 7, 3, 0, 6, 1});
        //System.out.println(board.h());
        //board.calculate();
        //board.printBoard();
        //BoardAn an = new BoardAn(500);
        //an.calculate();
        //System.out.println(an.h());
        SA sa = new SA(1000);
        sa.calculate();
        //sa.printBoard();

    }
}
