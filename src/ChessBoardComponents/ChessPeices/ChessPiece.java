package ChessBoardComponents.ChessPeices;
import ChessBoardComponents.ChessBoardData;

import javax.swing.*;
import java.util.ArrayList;

public class ChessPiece {

    public boolean hasMovedBefore;
    public boolean wasLastMovedPiece;

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

    public ArrayList<int[]> getAvailableMoves(boolean b) {
        return null;
    }

    public boolean canMoveTo(int x, int y) {
        for (int[] move : getAvailableMoves(false)) {
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

    public ArrayList<int[]> getAvailableMoves() {
        return getAvailableMoves(false);
    }

    public void setX(int x) {
        this.x = x;
    }

    //bishop, queen, and rook
    protected ArrayList<int[]> getMovesForLongDistanceMovements(int[][] directions, boolean isInternalCall) {
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
        if (isInternalCall) return outputList;
        return removeMovesFromCheck(outputList);
    }

    protected ArrayList<int[]> removeMovesFromCheck(ArrayList<int[]> outputList) {
        ChessPiece king = board.getKing(this.color);
        ArrayList<int[]> invalidMoves = new ArrayList<>();
        for (int[] attemptedMove : outputList) {

            ChessPiece taken = board.getPieceAt(attemptedMove[0], attemptedMove[1]);
            int sx=x, sy=y;
            board.getData()[y][x] = null;
            board.getData()[attemptedMove[1]][attemptedMove[0]] = this;
            x = (attemptedMove[0]);
            y = (attemptedMove[1]);

            for (ChessPiece cp : board.getAllPieces()) {
                if (cp != this && cp.getColor() != this.color) {
                    for (int[] move : cp.getAvailableMoves(true)) {
                        if (move[0] == king.getX() && move[1] == king.getY() &&
                                !(cp.getX()==attemptedMove[0] && cp.getY()== attemptedMove[1])) {
                            invalidMoves.add(attemptedMove);
                            break;
                        }
                    }
                }
            }
            board.getData()[attemptedMove[1]][attemptedMove[0]] = taken;
            x = sx;
            y = sy;
            board.getData()[y][x] = this;
        }
        outputList.removeAll(invalidMoves);
        return outputList;
    }

    //king and knight
    protected ArrayList<int[]> getMovesForDefinedMovements(int[][] directions, boolean isInternalCall) {
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
        if (isInternalCall) return outputList;
        return removeMovesFromCheck(outputList);
    }


}
