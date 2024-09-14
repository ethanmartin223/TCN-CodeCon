import ChessBoardComponents.ChessBoardUI;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("TCN Project");

        ChessBoardUI chessBoard = new ChessBoardUI(this, 8,8);
        chessBoard.addKeyListener(chessBoard);
        chessBoard.setFocusable(true);
        add(chessBoard);

        chessBoard.startNewGame();
        chessBoard.redrawBoard();

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

}