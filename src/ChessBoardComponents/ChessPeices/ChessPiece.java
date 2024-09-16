package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;

import javax.swing.*;
import java.util.ArrayList;

public class ChessPiece {

    public boolean hasMovedBefore;

    ChessBoardData board;
    int x, y;
    ImageIcon renderImage;
    Integer color;

    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public ChessPiece(ChessBoardData parentBoard, int posX, int poxY, int color) {
        board = parentBoard;
        x = posX;
        y = poxY;
        this.color = color;
        renderImage = null;
        parentBoard.addPiece(this);
    }

    public ArrayList<int[]> getAvailableMoves() {
        return null;
    }

    public boolean canMoveTo(int x, int y) {
        for (int[] move : getAvailableMoves()) {
            if (move[0] == x && move[1] == y) return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getColor() {
        return color;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getRenderImage() {
        return this.renderImage;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    //bishop, queen, and rook
    protected ArrayList<int[]> getMovesForLongDistanceMovements(int[][] directions) {
        ArrayList<int[]> outputList = new ArrayList<>();
        for (int[] position: directions) {
            int distanceModifier = 1;
            int dx = x + position[0];
            int dy = y + position[1];
            while (dy< board.getHeight() && dy>-1 && dx< board.getWidth() && dx>-1) {
                ChessPiece cp = board.getPieceAt(dx, dy);
                if (cp == null) {
                    outputList.add(new int[]{dx, dy});
                } else {
                    if (cp.getColor() != this.color)
                        outputList.add(new int[]{dx, dy});
                    break;
                }
                distanceModifier += 1;
                dy = y + position[1]*distanceModifier;
                dx = x + position[0]*distanceModifier;
            }
        }
        return outputList;
    }

    //king and knight
    protected ArrayList<int[]> getMovesForDefinedMovements(int[][] directions) {
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
