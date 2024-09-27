package ChessAi;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPeices.ChessPiece;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        List<ChessPiece> cps = moves.keySet().stream().toList();
        ArrayList<int[]> j = (cp=cps.get((int) (Math.random()*cps.size()))).getAvailableMoves();
        int[] debugMove = j.get((int) (Math.random()*j.size()));
        boardData.tryToMove(cp, debugMove[0], debugMove[1]);
    }

}
