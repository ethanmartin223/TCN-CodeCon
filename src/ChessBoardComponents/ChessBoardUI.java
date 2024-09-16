package ChessBoardComponents;

import ChessBoardComponents.BoardTile;
import ChessBoardComponents.ChessPeices.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChessBoardUI extends JPanel implements KeyListener {

    static Color BOARD_WHITE_COLOR = Color.DARK_GRAY;
    static Color BOARD_BLACK_COLOR = Color.GRAY;
    static Color BOARD_SELECTED_COLOR = new Color(255, 239, 137);
    static Color BOARD_SELECTED_CAN_MOVE_TO_WHITE_COLOR = new Color(255, 224, 153);
    static Color BOARD_SELECTED_CAN_MOVE_TO_BLACK_COLOR = new Color(161, 141, 98);


    private JFrame parentWindow;
    private int width, height;
    private ChessBoardData chessBoardData;
    private BoardTile[][] boardTiles;
    private BoardTile selectedTile;
    private Timer aiMoveTimer;
    private boolean renderZeroZeroDown;

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
        renderZeroZeroDown = true;
        grabFocus();

        aiMoveTimer = new Timer(500, e -> doAiMoves());
        aiMoveTimer.setRepeats(true);
        aiMoveTimer.start();
    }

    public void startNewGame() {
        chessBoardData.initializeNewGame();
        redrawBoard();
    }

    public void redrawBoard() {
        long start = System.currentTimeMillis();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ChessPiece cp = chessBoardData.getPieceAt(x,y);
                if (cp != null) {
                    boardTiles[y][x].renderingPiece = cp.getRenderImage();
                } else {
                    boardTiles[y][x].renderingPiece = null;
                }
                boardTiles[y][x].setColor(boardTiles[y][x].baseColor);
                boardTiles[y][x].renderPieceAtSquare();
            }
        }
    }

    public void setSelectedTile(BoardTile sT) {
        ChessPiece sTPiece, selectedTilePiece;
        if (selectedTile != null && !((selectedTilePiece=chessBoardData.getPieceAt(selectedTile.x, selectedTile.y))!=null &&
                (sTPiece=chessBoardData.getPieceAt(sT.x, sT.y))!=null && selectedTilePiece.getColor() == sTPiece.getColor())) {
            if (selectedTile != sT && chessBoardData.tryToMove(chessBoardData.getPieceAt(selectedTile.x, selectedTile.y), sT.x, sT.y)){
                getBoardTileAt(selectedTile.x, selectedTile.y).renderingPiece = null;
            }
            selectedTile = null;
            redrawBoard();
            return;
        }
        redrawBoard();
        ChessPiece cp;
        if ((cp=chessBoardData.getPieceAt(sT.x, sT.y))!=null) {
            boardTiles[sT.y][sT.x].setColor(BOARD_SELECTED_COLOR);
            for (int[] move : cp.getAvailableMoves()){
                BoardTile bt = getBoardTileAt(move[0], move[1]);
                bt.setColor(bt.baseColor==BOARD_WHITE_COLOR?BOARD_SELECTED_CAN_MOVE_TO_BLACK_COLOR:
                        BOARD_SELECTED_CAN_MOVE_TO_WHITE_COLOR);
            }
        }
        selectedTile = sT;
    }

    private BoardTile getBoardTileAt(int x, int y) {
        return boardTiles[y][x];
    }

    private void flipBoard() {
        removeAll();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (renderZeroZeroDown) {
                    add(boardTiles[y][x]);
                } else add(boardTiles[(boardTiles.length-1)-y][x]);
            }
        }
        repaint();
        revalidate();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'f') {
            renderZeroZeroDown=!renderZeroZeroDown;
            System.out.println("Rendering Flipped");
            flipBoard();
            redrawBoard();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}

    public ChessBoardData getBoardData() {
        return chessBoardData;
    }

    public void doAiMoves() {
        if (chessBoardData.whiteBot != null && chessBoardData.currentPlayerTurn == ChessPiece.WHITE) {
            chessBoardData.whiteBot.makeBestMove();
            redrawBoard();
        } else if (chessBoardData.blackBot != null && chessBoardData.currentPlayerTurn == ChessPiece.BLACK) {
            chessBoardData.blackBot.makeBestMove();
            redrawBoard();
        }
    }
}
