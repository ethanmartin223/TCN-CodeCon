package ChessBoardComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChessPieceImage {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    public static ImageIcon BLACK_ROOK;
    public static ImageIcon BLACK_QUEEN;
    public static ImageIcon BLACK_KNIGHT;
    public static ImageIcon BLACK_KING;
    public static ImageIcon BLACK_PAWN;
    public static ImageIcon BLACK_BISHOP;

    public static ImageIcon WHITE_QUEEN;
    public static ImageIcon WHITE_ROOK;
    public static ImageIcon WHITE_KNIGHT;
    public static ImageIcon WHITE_KING;
    public static ImageIcon WHITE_PAWN;
    public static ImageIcon WHITE_BISHOP;

    static {
        try {
            BLACK_ROOK = new ImageIcon(getScaledImage("assets/BlackRook.png"));
            BLACK_QUEEN = new ImageIcon(getScaledImage("assets/BlackQueen.png"));
            BLACK_KNIGHT = new ImageIcon(getScaledImage("assets/BlackKnight.png"));
            BLACK_KING = new ImageIcon(getScaledImage("assets/BlackKing.png"));
            BLACK_PAWN = new ImageIcon(getScaledImage("assets/BlackPawn.png"));
            BLACK_BISHOP = new ImageIcon(getScaledImage("assets/BlackBishop.png"));

            WHITE_QUEEN = new ImageIcon(getScaledImage("assets/WhiteQueen.png"));
            WHITE_ROOK = new ImageIcon(getScaledImage("assets/WhiteRook.png"));
            WHITE_KNIGHT = new ImageIcon(getScaledImage("assets/WhiteKnight.png"));
            WHITE_KING = new ImageIcon(getScaledImage("assets/WhiteKing.png"));
            WHITE_PAWN = new ImageIcon(getScaledImage("assets/WhitePawn.png"));
            WHITE_BISHOP = new ImageIcon(getScaledImage("assets/WhiteBishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Image getScaledImage(String path) throws IOException {
        Image image = ImageIO.read(new File(path));
        return image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    }

}
