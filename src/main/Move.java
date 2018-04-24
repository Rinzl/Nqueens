package main;

import java.util.Objects;

public class Move {
    int row;
    int col;
    int h;

    public Move(int row, int col, int h) {
        this.row = row;
        this.col = col;
        this.h = h;
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                ", h=" + h +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return row == move.row &&
                col == move.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, col);
    }
}
