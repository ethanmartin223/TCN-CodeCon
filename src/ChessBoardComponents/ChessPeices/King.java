package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import java.util.ArrayList;

public class King extends ChessPiece{

    private final static int[][] directions = new int[][] {{1,0},{0,1},{-1,0},{0,-1},{-1,1},{1,-1},{-1,-1},{1,1}};

    public King(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_KING;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_KING;
    }


    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> possibleMoves = getMovesForDefinedMovements(directions);
        return possibleMoves;
    }

}
