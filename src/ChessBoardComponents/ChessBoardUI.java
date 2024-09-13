package ChessBoardComponents;

import ChessBoardComponents.BoardTile;
import ChessBoardComponents.ChessPeices.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardUI extends JPanel{

    static Color BOARD_WHITE_COLOR = Color.DARK_GRAY;
    static Color BOARD_BLACK_COLOR = Color.GRAY;
    static Color BOARD_SELECTED_COLOR = new Color(255, 239, 137);
    static Color BOARD_SELECTED_CAN_MOVE_TO_COLOR = new Color(255, 224, 153);


    private JFrame parentWindow;
    private int width, height;
    private ChessBoardData chessBoardData;
    private BoardTile[][] boardTiles;
    private BoardTile selectedTile;

    public ChessBoardUI(JFrame parent, int width, int height) {
        setLayout(new GridLayout(height, width));

        this.width = width;
        this.height = height;
        this.chessBoardData = new ChessBoardData(width, height);
        boardTiles = new BoardTile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardTiles[y][x] = new BoardTile(this, x, y);
                add(boardTiles[y][x]);
            }
        }
        selectedTile = null;
    }

    public void startNewGame() {
        chessBoardData.initializeNewGame();
    }

    public void redrawBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ChessPiece cp = chessBoardData.getPieceAt(x,y);
                if (cp != null) {
                    boardTiles[y][x].renderingPiece = cp.getRenderImage();
                }
                boardTiles[y][x].renderPieceAtSquare();
                if (boardTiles[y][x] == selectedTile) {
                    boardTiles[y][x].setColor(BOARD_SELECTED_COLOR);
                } else {
                    boardTiles[y][x].setColor(boardTiles[y][x].baseColor);
                }
            }
        }
    }


    public void setSelectedTile(BoardTile sT) {
        if (selectedTile != null && selectedTile != sT) {
            if (chessBoardData.tryToMove(chessBoardData.getPieceAt(selectedTile.x, selectedTile.y), sT.x, sT.y)){
                getBoardTileAt(selectedTile.x, selectedTile.y).renderingPiece = null;
            }
            selectedTile = null;
            redrawBoard();
            return;
        }
        redrawBoard();
        ChessPiece cp;
        if ((cp=chessBoardData.getPieceAt(sT.x, sT.y))!=null) {
            for (int[] move : cp.getAvailableMoves()){
                getBoardTileAt(move[0], move[1]).setColor(BOARD_SELECTED_CAN_MOVE_TO_COLOR);
            }
        }
        selectedTile = sT;
        revalidate();

    }

    private BoardTile getBoardTileAt(int x, int y) {
        return boardTiles[y][x];
    }
}
