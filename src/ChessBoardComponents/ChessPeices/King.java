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
    public ArrayList<int[]> getAvailableMoves(boolean b) {
        ArrayList<int[]> output = getMovesForDefinedMovements(directions, b);
        if (!hasMovedBefore) {
            if (color == WHITE) {
                ChessPiece rook1 = board.getPieceAt(0, 7);
                ChessPiece rook2 = board.getPieceAt(7, 7);
                if (rook1.getClass() == Rook.class && !rook1.hasMovedBefore
                        && board.getPieceAt(1, 7) == null
                        && board.getPieceAt(2, 7) == null
                        && board.getPieceAt(3, 7) == null) {
                    output.add(new int[]{2, 7});
                }
                if (rook2.getClass() == Rook.class && !rook2.hasMovedBefore
                        && board.getPieceAt(5, 7) == null
                        && board.getPieceAt(6, 7) == null) {
                    output.add(new int[]{6, 7});
                }

            } else if (color == BLACK) {
                ChessPiece rook1 = board.getPieceAt(0, 0);
                ChessPiece rook2 = board.getPieceAt(7, 0);
                if (rook1.getClass() == Rook.class && !rook1.hasMovedBefore
                        && board.getPieceAt(1, 0) == null
                        && board.getPieceAt(2, 0) == null
                        && board.getPieceAt(3, 0) == null) {
                    output.add(new int[]{2, 0});
                }
                if (rook2.getClass() == Rook.class && !rook2.hasMovedBefore
                        && board.getPieceAt(5, 0) == null
                        && board.getPieceAt(6, 0) == null) {
                    output.add(new int[]{6, 0});
                }
            }

        }
        return output;
    }


    public ArrayList<int[]> getAvailableMoves() {
        return getAvailableMoves(false);
    }

    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"K";
    }
}
