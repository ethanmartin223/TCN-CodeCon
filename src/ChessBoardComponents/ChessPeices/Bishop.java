package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

public class Bishop extends ChessPiece{

    public Bishop(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_BISHOP;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_BISHOP;
    }
}
