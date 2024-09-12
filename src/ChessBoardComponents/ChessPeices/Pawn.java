package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends ChessPiece{


    public Pawn(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);
        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_PAWN;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_PAWN;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> outputList = new ArrayList<>();
        int directionModifier = this.color == ChessPiece.WHITE ? 1 : -1; //this could cause issues if there is ever a color other then black/white
        if (x < board.getWidth() && x > -1 && y + directionModifier < board.getHeight() && y + directionModifier > -1)
            if (board.getPieceAt(x,y + directionModifier)==null) outputList.add(new int[]{x,y + directionModifier});
        if (!hasMovedBefore && x < board.getWidth() && x > -1 && y + directionModifier*2 < board.getHeight() && y + directionModifier*2 > -1)
            if (board.getPieceAt(x,y + directionModifier*2)==null) outputList.add(new int[]{x,y + directionModifier*2});
        return outputList;
    }
}
