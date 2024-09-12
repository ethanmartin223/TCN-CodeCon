package ChessBoardComponents.ChessPeices;
import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends ChessPiece{

    private int[][] directions = new int[][] {{1,2},{2,1},{-2,1},{2,-1},{-1,2},{1,-2},{-2,-1},{-1,-2}};

    public Knight(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_KNIGHT;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_KNIGHT;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> outputList = new ArrayList<>();
        for (int[] position: directions) {
            int dx = x + position[0];
            int dy = y + position[1];
            if (dx<board.getWidth() && dx>-1 && dy<board.getHeight() && dy>-1) {
                ChessPiece cp = board.getPieceAt(dx, dy);
                if (cp == null || (cp.getColor() != this.color)) {
                    outputList.add(new int[]{dx ,dy});
                }
            }
        }
        return outputList;
    }
}
