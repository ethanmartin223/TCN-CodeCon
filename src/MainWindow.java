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

//        ChessAi.ChessBot whiteAi = new ChessAi.ChessBot(chessBoard.getBoardData(), ChessPiece.WHITE);
//        chessBoard.getBoardData().setAiForColor(whiteAi, ChessPiece.WHITE);

//        ChessAi.ChessBot blackAi = new ChessAi.ChessBot(chessBoard.getBoardData(), ChessPiece.BLACK);
//        chessBoard.getBoardData().setAiForColor(blackAi, ChessPiece.BLACK);

        chessBoard.startNewGame();

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

}