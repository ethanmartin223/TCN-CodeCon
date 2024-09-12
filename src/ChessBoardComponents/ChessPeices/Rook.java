package ChessBoardComponents.ChessPeices;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPieceImage;

public class Rook extends ChessPiece {
    public Rook(ChessBoardData parentBoard, int posX, int poxY, int color) {
        super(parentBoard, posX, poxY, color);

        if (this.color == ChessPiece.BLACK) renderImage = ChessPieceImage.BLACK_ROOK;
        else if (this.color == ChessPiece.WHITE) renderImage = ChessPieceImage.WHITE_ROOK;
    }
}
