package ChessBoardComponents;

public class BoardState {
    static final byte WHITE_KING =  0b00000001;
    static final byte WHITE_QUEEN = 0b00000010;
    static final byte WHITE_ROOK =  0b00000011;
    static final byte WHITE_BISHOP =0b00000100;
    static final byte WHITE_KNIGHT =0b00000101;
    static final byte WHITE_PAWN =  0b00000111;

    static final byte BLACK_KING =  0b00001000;
    static final byte BLACK_QUEEN = 0b00001001;
    static final byte BLACK_ROOK =  0b00001011;
    static final byte BLACK_BISHOP =0b00001111;
    static final byte BLACK_KNIGHT =0b00010000;
    static final byte BLACK_PAWN =  0b00010001;

    byte[] boardState;

    public BoardState() {
        boardState = new byte[64];
    }

}
