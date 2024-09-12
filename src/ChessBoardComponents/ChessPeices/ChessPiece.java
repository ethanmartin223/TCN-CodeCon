package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import java.awt.*;
import java.util.ArrayList;

public class ChessPiece {

    public boolean hasMovedBefore;

    ChessBoardData board;
    int x, y;
    Image renderImage;
    Integer color;

    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public ChessPiece(ChessBoardData parentBoard, int posX, int poxY, int color) {
        board = parentBoard;
        x = posX;
        y = poxY;
        this.color = color;
        renderImage = null;
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

    public Image getRenderImage() {
        return this.renderImage;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
