package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    private final static int[][] directions = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};

    public Rook(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_ROOK;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_ROOK;
    }
    @Override
    public ArrayList<int[]> getAvailableMoves() {
        return getMovesForLongDistanceMovements(directions);
    }

    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"R";
    }
}
