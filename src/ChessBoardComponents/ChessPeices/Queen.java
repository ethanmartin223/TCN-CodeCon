package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import java.util.ArrayList;

public class Queen extends ChessPiece{
    private final int[][] directions = new int[][] {{1,0},{0,1},{-1,0},{0,-1},{-1,1},{1,-1},{-1,-1},{1,1}};

    public Queen(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_QUEEN;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_QUEEN;
    }

    public ArrayList<int[]> getAvailableMoves() {
        return getAvailableMoves(false);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(boolean b) {
        return getMovesForLongDistanceMovements(directions, b);
    }


    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"Q";
    }
}
