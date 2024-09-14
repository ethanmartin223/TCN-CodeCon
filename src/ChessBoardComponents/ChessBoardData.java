package ChessBoardComponents;

import ChessBoardComponents.ChessPeices.*;

import java.util.ArrayList;

public class ChessBoardData {

    ChessPiece[][] boardData;
    int width, height;
    ArrayList<ChessPiece> allPieces;


    public ChessBoardData(int width, int height) {
        this.width = width;
        this.height = height;
        boardData = new ChessPiece[height][width];
        allPieces = new ArrayList<ChessPiece>();
    }

    public ChessPiece getPieceAt(int x, int y) {
        return boardData[y][x];
    }

    public boolean tryToMove(ChessPiece cp, int toX, int toY) {
        if (cp!=null && cp.canMoveTo(toX,toY)) {
            internalMovePiece(cp, toX, toY);
            return true;
        } else return false;
    }

    //keep private on this
    private void internalMovePiece(ChessPiece cp, int toX, int toY) {
        boardData[cp.getY()][cp.getX()] = null;
        boardData[toY][toX] = cp;
        cp.setX(toX);
        cp.setY(toY);
        cp.hasMovedBefore = true;
    }

    private void internalMovePiece(int fromX, int fromY, int toX, int toY) {
        internalMovePiece(getPieceAt(fromX, fromY), toX, toY);
    }

    public void initializeNewGame() {
        boardData = new ChessPiece[height][width];

        for (int x = 0; x<width; x++) boardData[1][x] = new Pawn(this, x,1,ChessPiece.WHITE);
        boardData[0][0] = new Rook(this, 0,0,ChessPiece.WHITE);
        boardData[0][1] = new Knight(this, 1,0,ChessPiece.WHITE);
        boardData[0][2] = new Bishop(this, 2,0,ChessPiece.WHITE);
        boardData[0][3] = new Queen(this, 3,0,ChessPiece.WHITE);
        boardData[0][4] = new King(this, 4,0,ChessPiece.WHITE);
        boardData[0][5] = new Bishop(this, 5,0,ChessPiece.WHITE);
        boardData[0][6] = new Knight(this, 6,0,ChessPiece.WHITE);
        boardData[0][7] = new Rook(this, 7,0,ChessPiece.WHITE);

        for (int x = 0; x<width; x++) boardData[6][x] = new Pawn(this, x,6,ChessPiece.BLACK);
        boardData[7][0] = new Rook(this, 0,7,ChessPiece.BLACK);
        boardData[7][1] = new Knight(this, 1,7,ChessPiece.BLACK);
        boardData[7][2] = new Bishop(this, 2,7,ChessPiece.BLACK);
        boardData[7][3] = new Queen(this, 3,7,ChessPiece.BLACK);
        boardData[7][4] = new King(this, 4,7,ChessPiece.BLACK);
        boardData[7][5] = new Bishop(this, 5,7,ChessPiece.BLACK);
        boardData[7][6] = new Knight(this, 6,7,ChessPiece.BLACK);
        boardData[7][7] = new Rook(this, 7,7,ChessPiece.BLACK);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
