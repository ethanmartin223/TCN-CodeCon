package ChessBoardComponents.ChessPeices;
import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends ChessPiece{

    private final static int[][] directions = new int[][] {{1,2},{2,1},{-2,1},{2,-1},{-1,2},{1,-2},{-2,-1},{-1,-2}};

    public Knight(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_KNIGHT;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_KNIGHT;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        return getMovesForDefinedMovements(directions);
    }


    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"N";
    }
}
