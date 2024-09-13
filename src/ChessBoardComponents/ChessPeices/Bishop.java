package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
    private final static int[][] directions = new int[][] {{-1,1},{1,-1},{-1,-1},{1,1}};

    public Bishop(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_BISHOP;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_BISHOP;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        return getMovesForLongDistanceMovements(directions);
    }
}
