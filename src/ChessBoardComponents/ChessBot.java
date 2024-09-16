package ChessBoardComponents;

import ChessBoardComponents.ChessPeices.ChessPiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ChessBot {

    int playingColor;
    ChessBoardData boardData;

    public ChessBot(ChessBoardData parentBoard, int color) {
        boardData = parentBoard;
        playingColor = color;
    }

    //make sure communication all uses the public methods defined for player use
    public void makeBestMove() {
        HashMap<ChessPiece, ArrayList<int[]>> moves = boardData.getAllMovesForColor(this.playingColor);
        ChessPiece cp;
        int[] debugMove = (cp=moves.keySet().stream().findFirst().get()).getAvailableMoves().getFirst();
        boardData.tryToMove(cp, debugMove[0], debugMove[1]);
    }

}
