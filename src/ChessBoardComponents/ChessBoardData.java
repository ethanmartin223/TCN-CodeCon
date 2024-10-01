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
        allPieces = new ArrayList<>();
        boardData = new ChessPiece[height][width];
    }

    boolean isGameOver() {
        boolean onlyKings = true;
        boolean whiteCanMove= false;
        boolean blackCanMove= false;
        for (ChessPiece cp: getAllPieces()) {
            if (cp.getClass() != King.class) onlyKings = false;
            if (cp.getColor()==ChessPiece.WHITE) {
                if (!cp.getAvailableMoves().isEmpty())
                    whiteCanMove = true;
            }
            if (cp.getColor()==ChessPiece.BLACK) {
                if (!cp.getAvailableMoves().isEmpty())
                    blackCanMove = true;
            }
        }
        if (!(blackCanMove && whiteCanMove) || (onlyKings)) {
            if (onlyKings)
                System.out.println("DEBUG: Stalemate");
            else if (blackCanMove && ((King)getKing(ChessPiece.WHITE)).isInCheck())
                System.out.println("DEBUG: Black Wins");
            else if (whiteCanMove && ((King)getKing(ChessPiece.BLACK)).isInCheck())
                System.out.println("DEBUG: White Wins");
            else System.out.println("DEBUG: Stalemate");

        }
        return !(blackCanMove && whiteCanMove) || (onlyKings);

    }

    public ChessPiece getPieceAt(int x, int y) {
        return boardData[y][x];
    }

    public boolean tryToMove(ChessPiece cp, int toX, int toY) {
        if (cp!=null && cp.canMoveTo(toX,toY)) {
            internalMovePiece(cp, toX, toY);
            this.currentPlayerTurn = currentPlayerTurn==ChessPiece.WHITE?ChessPiece.BLACK:ChessPiece.WHITE;
            for (ChessPiece p : allPieces) {
                p.wasLastMovedPiece = false;
            }
            cp.wasLastMovedPiece = true;
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

    private void internalMovePiece(ChessPiece cp, int toX, int toY) {
        //check if king is castling
        if (cp.getClass()== King.class && Math.abs(cp.getX()-toX)>1) {
            if (toX == 2)
                internalMovePiece(getPieceAt(0, toY), 3, toY);
            else if (toX == 6)
                internalMovePiece(getPieceAt(7, toY), 5, toY);
        }

        //check if pawn is attempting en passant
        if (cp.getClass()== Pawn.class && toX!=cp.getX() && getPieceAt(toX, toY)==null) {
            removePiece(getPieceAt(toX,cp.getY()));
            boardData[cp.getY()][toX] = null;
        }

        boardData[cp.getY()][cp.getX()] = null;
        if (boardData[toY][toX]!=null) removePiece(boardData[toY][toX]);
        boardData[toY][toX] = cp;
        cp.setX(toX);
        cp.setY(toY);
        cp.hasMovedBefore = true;

        //promote on pawn reaching other side
        if (cp.getClass()==Pawn.class && cp.getY()==0 || cp.getY()==7) {
            try {
                ((Pawn) cp).promote('Q'); //debug always promote to queen
            } catch (ClassCastException ignored) {};
        }
    }

    public void internalMovePiece(int fromX, int fromY, int toX, int toY) {
        internalMovePiece(getPieceAt(fromX, fromY), toX, toY);
    }

    public void initializeNewGame() {
        allPieces.clear();
        boardData = new ChessPiece[height][width];

        for (int x = 0; x < width; x++) boardData[6][x] = new Pawn(this, x, 6, ChessPiece.WHITE);
        boardData[7][0] = new Rook(this, 0, 7, ChessPiece.WHITE);
        boardData[7][1] = new Knight(this, 1, 7, ChessPiece.WHITE);
        boardData[7][2] = new Bishop(this, 2, 7, ChessPiece.WHITE);
        boardData[7][3] = new Queen(this, 3, 7, ChessPiece.WHITE);
        boardData[7][4] = new King(this, 4, 7, ChessPiece.WHITE);
        boardData[7][5] = new Bishop(this, 5, 7, ChessPiece.WHITE);
        boardData[7][6] = new Knight(this, 6, 7, ChessPiece.WHITE);
        boardData[7][7] = new Rook(this, 7, 7, ChessPiece.WHITE);

        for (int x = 0; x < width; x++) boardData[1][x] = new Pawn(this, x, 1, ChessPiece.BLACK);
        boardData[0][0] = new Rook(this, 0, 0, ChessPiece.BLACK);
        boardData[0][1] = new Knight(this, 1, 0, ChessPiece.BLACK);
        boardData[0][2] = new Bishop(this, 2, 0, ChessPiece.BLACK);
        boardData[0][3] = new Queen(this, 3, 0, ChessPiece.BLACK);
        boardData[0][4] = new King(this, 4, 0, ChessPiece.BLACK);
        boardData[0][5] = new Bishop(this, 5, 0, ChessPiece.BLACK);
        boardData[0][6] = new Knight(this, 6, 0, ChessPiece.BLACK);
        boardData[0][7] = new Rook(this, 7, 0, ChessPiece.BLACK);

    }

    public ChessPiece getKing(int color) {
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

    public void debugShowBoard() {
        System.out.print("  ");
        for (int i = 0; i < boardData.length; i++) System.out.print(i+"  ");
        System.out.println();
        for (int y = 0; y < boardData.length; y++) {
            System.out.print(y+" ");
            for (int x = 0; x < boardData[0].length; x++) {
                System.out.print(boardData[y][x]!=null?boardData[y][x]+" ":"   ");
            }
            System.out.println();
        }

    }

    public ChessPiece[][] getData() {
        return boardData;
    }
}
