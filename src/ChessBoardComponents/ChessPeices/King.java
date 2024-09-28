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

    public boolean isInCheck() {
        boolean inCheck = false;
        for (ChessPiece cp : board.getAllPieces()) {
            if (cp != this && cp.getColor() != this.color) {
                for (int[] move : cp.getAvailableMoves(true)) {
                    if (move[0] ==x && move[1] == y) {
                        inCheck = true;
                    }
                }
            }
        }
        return inCheck;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(boolean b) {
        ArrayList<int[]> output = getMovesForDefinedMovements(directions, b);

        if (!b && !hasMovedBefore && !isInCheck()) {
            if (color == WHITE) addMovesForCastle(output, 7);
            else if (color == BLACK) addMovesForCastle(output,0);
        }
        return output;
    }

    private void addMovesForCastle(ArrayList<int[]> output, int yCoord) {
        ChessPiece rook1 = board.getPieceAt(0, yCoord);
        ChessPiece rook2 = board.getPieceAt(7, yCoord);
        if (rook1 != null && rook1.getClass() == Rook.class && !rook1.hasMovedBefore
                && board.getPieceAt(1, yCoord) == null
                && board.getPieceAt(2, yCoord) == null
                && board.getPieceAt(3, yCoord) == null) {
            if (isValidCastle(new int[][]{{2, yCoord}, {3, yCoord}}))
                output.add(new int[]{2, yCoord});
        }
        if (rook2 != null && rook2.getClass() == Rook.class && !rook2.hasMovedBefore
                && board.getPieceAt(5, yCoord) == null
                && board.getPieceAt(6, yCoord) == null) {
            if (isValidCastle(new int[][]{{5, yCoord}, {6,yCoord}}))
                output.add(new int[]{6, yCoord});
        }
    }

    private boolean isValidCastle(int[][] moves) {
        boolean canMoveWithoutBeingInCheck = true;
        for (int[] kingMove: moves) {
            for (ChessPiece cp : board.getAllPieces()) {
                if (cp != this && cp.getColor() != this.color) {
                    for (int[] move : cp.getAvailableMoves(true)) {
                        if (move[0] == kingMove[0] && move[1] == kingMove[1]) {
                            canMoveWithoutBeingInCheck = false;
                            break;
                        }
                    }
                }
            }
        }
        return canMoveWithoutBeingInCheck;
    }

    public ArrayList<int[]> getAvailableMoves() {
        return getAvailableMoves(false);
    }

    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"K";
    }
}
