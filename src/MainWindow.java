import ChessAi.PGNReader;
import ChessBoardComponents.ChessBoardUI;
import ChessBoardComponents.ChessPeices.ChessPiece;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws InterruptedException {
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("TCN Project");


        ChessBoardUI chessBoard = new ChessBoardUI(this, 8,8);
        chessBoard.addKeyListener(chessBoard);
        chessBoard.setFocusable(true);

        add(chessBoard, BorderLayout.CENTER);

        ChessAi.ChessBot whiteAi = new ChessAi.ChessBot(chessBoard.getBoardData(), ChessPiece.WHITE);
        chessBoard.getBoardData().setAiForColor(whiteAi, ChessPiece.WHITE);

        ChessAi.ChessBot blackAi = new ChessAi.ChessBot(chessBoard.getBoardData(), ChessPiece.BLACK);
        chessBoard.getBoardData().setAiForColor(blackAi, ChessPiece.BLACK);

        chessBoard.startNewGame();

        setVisible(true);
    }

    public MainWindow(PGNReader pgnReader) {
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("PGN Debugging Mode");

        ChessBoardUI chessBoard = new ChessBoardUI(this, 8,8);
        chessBoard.addKeyListener(chessBoard);
        chessBoard.setFocusable(true);

        add(chessBoard, BorderLayout.CENTER);
        chessBoard.startNewGame();

        setVisible(true);
    }


    public static void main(String[] args) throws InterruptedException {
        boolean pgnDebugging = true;
        if (pgnDebugging) {
            PGNReader reader = new PGNReader();
            reader.open("test_data.pgn");
            reader.getNextGame();
            MainWindow mw = new MainWindow(reader);
        } else {
            new MainWindow();
        }
    }

}