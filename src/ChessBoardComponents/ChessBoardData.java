package ChessBoardComponents;

import ChessAi.ChessBot;
import ChessBoardComponents.ChessPeices.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessBoardData {

    ChessPiece[][] boardData;
    int width, height;
    int currentPlayerTurn; //Form in ChessPiece.WHITE || ChessPiece.BLACK
    ArrayList<ChessPiece> allPieces;

    ChessBot whiteBot, blackBot;

    public ChessBoardData(int width, int height) {
        this.width = width;
        this.currentPlayerTurn = ChessPiece.WHITE;
        this.height = height;
        boardData = new ChessPiece[height][width];
        allPieces = new ArrayList<>();
    }

    public ChessPiece getPieceAt(int x, int y) {
        return boardData[y][x];
    }


    public boolean violatesCheckRules(ChessPiece cp, int toX, int toY) {
        //this should probably be changed later to allow for color != white/black
        ChessPiece king = cp.getColor()==ChessPiece.WHITE?getKing(ChessPiece.WHITE):getKing(ChessPiece.BLACK);
        for (ChessPiece p : allPieces) {
            if (p.getColor() != cp.getColor()) {
                for (int[] f : p.getAvailableMoves()) {
                    assert king != null;
                    if (f[0] == king.getX() && f[1] == king.getY()) return true;
                }
            }
        }
        return false;
    }

    public boolean tryToMove(ChessPiece cp, int toX, int toY) {
        if (cp!=null && cp.canMoveTo(toX,toY)) {//&& !(violatesCheckRules(cp, toX, toY))) {
            internalMovePiece(cp, toX, toY);
            this.currentPlayerTurn = currentPlayerTurn==ChessPiece.WHITE?ChessPiece.BLACK:ChessPiece.WHITE;
            return true;
        } else return false;
    }

    public HashMap<ChessPiece, ArrayList<int[]>> getAllMovesForColor(int color) {
        HashMap<ChessPiece, ArrayList<int[]>> output = new HashMap<>();
        for (ChessPiece cp : allPieces) {
            if (cp.getColor() == color) {
                ArrayList<int[]> pieceMoves = cp.getAvailableMoves();
                if (!pieceMoves.isEmpty()) output.put(cp, pieceMoves);
            }
        }
        return output;
    }

    //keep private on this
    private void internalMovePiece(ChessPiece cp, int toX, int toY) {
        boardData[cp.getY()][cp.getX()] = null;
        if (boardData[toY][toX]!=null) removePiece(boardData[toY][toX]);
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

    private ChessPiece getKing(int color) {
        for (ChessPiece cp : getAllPieces()) {
            if (cp.getClass()== King.class && cp.getColor()==color) {
                return cp;
            }
        }
        return null;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addPiece(ChessPiece chessPiece) {
        allPieces.add(chessPiece);
    }

    public void removePiece(ChessPiece chessPiece) {
        allPieces.remove(chessPiece);
    }

    public ArrayList<ChessPiece> getAllPieces() {
        return allPieces;
    }

    public void setAiForColor(ChessBot ai, int color) {
        if (color==ChessPiece.WHITE) {
            whiteBot = ai;
        } else if (color == ChessPiece.BLACK) {
            blackBot = ai;
        }
    }
}
