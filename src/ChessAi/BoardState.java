package ChessAi;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPeices.ChessPiece;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, Byte> pieceMap = new HashMap<>();
    static {
        pieceMap.put("wK", WHITE_KING);
        pieceMap.put("wQ", WHITE_QUEEN);
        pieceMap.put("wR", WHITE_ROOK);
        pieceMap.put("wB", WHITE_BISHOP);
        pieceMap.put("wN", WHITE_KNIGHT);
        pieceMap.put("wP", WHITE_PAWN);

        pieceMap.put("bK", BLACK_KING);
        pieceMap.put("bQ", BLACK_QUEEN);
        pieceMap.put("bR", BLACK_ROOK);
        pieceMap.put("bB", BLACK_BISHOP);
        pieceMap.put("bN", BLACK_KNIGHT);
        pieceMap.put("bP", BLACK_PAWN);
    }

    byte[][] boardState;

    public static byte getPieceBinaryValue(String piece) {
        return pieceMap.get(piece);
    }

    public BoardState() {
        boardState = new byte[8][8];
    }

    public static BoardState fromChessBoardData(ChessBoardData cbd) {
        BoardState output = new BoardState();
        for (ChessPiece cp : cbd.getAllPieces()) {
            output.boardState[cp.getY()][cp.getX()] = getPieceBinaryValue(cp.toString());
        }
        return output;
    }

}
