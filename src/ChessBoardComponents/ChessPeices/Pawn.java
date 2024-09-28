package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import java.util.ArrayList;

public class Pawn extends ChessPiece{


    public ArrayList<int[]> getAvailableMoves() {
        return getAvailableMoves(false);
    }

    public void promote(char c) {
        ChessPiece promoted = null;
        board.removePiece(this);
        promoted = switch (c) {
            case ('Q') -> new Queen(board, this.x, this.y, color);
            case ('B') -> new Bishop(board, this.x, this.y, color);
            case ('R') -> new Rook(board, this.x, this.y, color);
            case ('N') -> new Knight(board, this.x, this.y, color);
            default -> promoted;
        };
        assert promoted != null;
        board.getData()[this.y][this.x] = promoted;

    }

    public Pawn(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);
        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_PAWN;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_PAWN;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves(boolean isInternalCall) {
        ArrayList<int[]> outputList = new ArrayList<>();
        int directionModifier = this.color == ChessPiece.WHITE ? -1 : 1; //this could cause issues if there is ever a color other then black/white
        if (x < board.getWidth() && x > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x,y + directionModifier)==null) outputList.add(new int[]{x,y + directionModifier});
        if (!hasMovedBefore && x < board.getWidth() && x > -1 && y + directionModifier*2 < board.getHeight() && y + directionModifier*2 > -1)
            if (board.getPieceAt(x,y + directionModifier*2)==null && board.getPieceAt(x,y + directionModifier)==null)
                outputList.add(new int[]{x,y + directionModifier*2});

        //diagonal taking
        if (x+1 < board.getWidth() && x+1 > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x+1,y + directionModifier)!=null && board.getPieceAt(x+1,y + directionModifier).getColor()!=this.color)
                outputList.add(new int[]{x+1,y + directionModifier});
        if (x-1 < board.getWidth() && x-1 > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x-1,y + directionModifier)!=null && board.getPieceAt(x-1,y + directionModifier).getColor()!=this.color)
                outputList.add(new int[]{x-1,y + directionModifier});

        //en passant
        if (x+1 < board.getWidth() && x+1 > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x+1,y)!=null && board.getPieceAt(x+1,y).getColor()!=this.color &&
                    board.getPieceAt(x+1,y).getClass()== Pawn.class && board.getPieceAt(x+1,y).wasLastMovedPiece &&
                    (directionModifier==-1?y==3:y==4))
                outputList.add(new int[]{x+1,y + directionModifier});
        if (x-1 < board.getWidth() && x-1 > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x-1,y)!=null && board.getPieceAt(x-1,y).getColor()!=this.color &&
                    board.getPieceAt(x-1,y).getClass()== Pawn.class && board.getPieceAt(x-1,y).wasLastMovedPiece &&
                    (directionModifier==-1?y==3:y==4))
                outputList.add(new int[]{x-1,y + directionModifier});

        if (isInternalCall) return outputList;
        return removeMovesFromCheck(outputList);
    }

    @Override
    public String toString() {
        return (color==WHITE?"w":"b")+"P";
    }
}
