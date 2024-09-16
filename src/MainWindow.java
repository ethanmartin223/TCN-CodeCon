import ChessBoardComponents.ChessBoardUI;
import ChessBoardComponents.ChessBot;
import ChessBoardComponents.ChessPeices.ChessPiece;

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

//        ChessBot whiteAi = new ChessBot(chessBoard.getBoardData(), ChessPiece.WHITE);
//        chessBoard.getBoardData().setAiForColor(whiteAi, ChessPiece.WHITE);

        ChessBot blackAi = new ChessBot(chessBoard.getBoardData(), ChessPiece.BLACK);
        chessBoard.getBoardData().setAiForColor(blackAi, ChessPiece.BLACK);

        chessBoard.startNewGame();

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

}