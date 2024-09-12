package ChessBoardComponents;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChessPieceImage {

    public static Image BLACK_PAWN, WHITE_PAWN, BLACK_ROOK, WHITE_ROOK, BLACK_KNIGHT, WHITE_KNIGHT, BLACK_BISHOP, WHITE_BISHOP,
            BLACK_QUEEN, WHITE_QUEEN, BLACK_KING, WHITE_KING;

    static {
        try {
            BLACK_QUEEN = ImageIO.read(new File("assets/BlackQueen.png"));
            BLACK_ROOK = ImageIO.read(new File("assets/BlackRook.png"));
            BLACK_KNIGHT = ImageIO.read(new File("assets/BlackKnight.png"));
            BLACK_KING = ImageIO.read(new File("assets/BlackKing.png"));
            BLACK_PAWN = ImageIO.read(new File("assets/BlackPawn.png"));
            BLACK_BISHOP = ImageIO.read(new File("assets/BlackBishop.png"));

            WHITE_QUEEN = ImageIO.read(new File("assets/WhiteQueen.png"));
            WHITE_ROOK = ImageIO.read(new File("assets/WhiteRook.png"));
            WHITE_KNIGHT = ImageIO.read(new File("assets/WhiteKnight.png"));
            WHITE_KING = ImageIO.read(new File("assets/WhiteKing.png"));
            WHITE_PAWN = ImageIO.read(new File("assets/WhitePawn.png"));
            WHITE_BISHOP = ImageIO.read(new File("assets/WhiteBishop.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
