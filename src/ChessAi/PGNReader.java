package ChessAi;

import ChessBoardComponents.ChessBoardData;
import ChessBoardComponents.ChessPeices.ChessPiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ChessAi.BoardState.*;

public class PGNReader {

    private static final byte[][] STARTING_BOARD_STATE = {
            { WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK },
            { WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN },
            { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },
            { BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN },
            { BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK }
    };

    File currentPGN;
    Scanner fileReader;
    public static int MAX_LOAD_INTO_MEMORY = 256;
    private final Pattern pgnDecompiler, moveDecompiler;

    public static void main(String[] args) {
        PGNReader reader = new PGNReader();
        reader.open("test_data.pgn");
        reader.getNextGame();

    }

    public BoardState readNext() {
        return new BoardState();
    }


    public PGNReader() {
        pgnDecompiler = Pattern.compile("\\d*?\\. ?(.*?) (.*?) ");
        moveDecompiler = Pattern.compile(
                "(?=[^\\s])(?<p>[KBNRQ])?(?<i>[0-8]|[a-h])?(?<t>x)?(?<x>[a-h])(?<y>[0-8])(?<f>\\+)?(?<u>=[KBNRQ])?(?<m>#)?(?=\\s|$)|(?<c>O-O-O|O-O)");
    }

    public void open(String filename) {
        if (fileReader != null) fileReader.close();
        try {
            currentPGN = new File(filename);
            fileReader= new Scanner(currentPGN);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ChessGame getNextGame() {
        ChessGame output = new ChessGame();
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (!data.isEmpty()) {
                if (data.charAt(0) == '[') {
                    System.out.println(data);
                    if (data.substring(1).startsWith("Result")) {
                        output.gameResult = data.substring(9, data.length() - 2);
                    }
                }
                if (data.charAt(0) == '1') {
                    output.pgnGameString = data;
                    break;
                };
            }
        }
        Matcher matcher = pgnDecompiler.matcher(output.pgnGameString);
        int numberOfMoves = 0;
        ArrayList<String> moves = new ArrayList<>();
        while (matcher.find()) {
            numberOfMoves+=matcher.groupCount();
            for (int j = 1; j <= matcher.groupCount(); j++) {
                moves.add(matcher.group(j));
            }
        }
        output.numberOfMoves = numberOfMoves;
        output.game = new BoardState[numberOfMoves+1];
        output.game[0] = new BoardState();
        output.game[0].boardState = STARTING_BOARD_STATE;

        ChessBoardData sim = new ChessBoardData(8,8);
        sim.initializeNewGame();

        String piece, takenModifier, check, checkmate, promotion, castle, x, y, extraIdentifier;
        for (int i=0; i<moves.size(); i+=1) {
            BoardState currentBoardState = (output.game[i]= new BoardState());
            currentBoardState.boardState = deepCopy(output.game[i].boardState);
            matcher = moveDecompiler.matcher(moves.get(i));
            matcher.find();

            piece = tryToMatch(matcher, "p");
            if (piece == null) piece = "P";
            x = tryToMatch(matcher, "x");
            y = tryToMatch(matcher, "y");
            extraIdentifier = tryToMatch(matcher, "i");
            takenModifier = tryToMatch(matcher, "t");
            check =tryToMatch(matcher, "f");
            checkmate = tryToMatch(matcher, "m");
            promotion = tryToMatch(matcher, "u");
            castle = tryToMatch(matcher, "c");

            System.out.println("\nPiece: " + piece + " x: " + x + " y: " + y +
                    " TakenModifier: " + takenModifier +
                    " Extra Identifier: "+extraIdentifier +
                    " Check: " + check +
                    " Checkmate: " + checkmate +
                    " Promotion: " + promotion +
                    " Castle: " + castle);

            HashMap<ChessPiece, ArrayList<int[]>> possiblePieces;
            if (i%2==0) possiblePieces = sim.getAllMovesForColor(ChessPiece.WHITE);
            else possiblePieces = sim.getAllMovesForColor(ChessPiece.BLACK);
            ChessPiece answer = null;
            for (ChessPiece cp : possiblePieces.keySet()) {
                if (cp.toString().charAt(1)==(piece.charAt(0))){
                    for (int[] move: cp.getAvailableMoves()) {
                        if (move[1] == Integer.parseInt(y) && move[0]==fileToCoord(x.charAt(0))) {
                            if (extraIdentifier != null) {
                                if ((extraIdentifier.matches("[a-z]") && cp.getX()==fileToCoord(extraIdentifier.charAt(0)))
                                        || (extraIdentifier.matches("[0-8]") && cp.getY()==Integer.parseInt(extraIdentifier)))
                                    answer = cp;
                            } else answer = cp;
                            break;
                        }
                    }
                }
                if (answer != null) break;
            }
            System.out.println("Found chess Piece: "+answer);
            System.out.println("Move: "+moves.get(i));
            System.out.println("Succeeded Moving Piece: "+sim.tryToMove(answer, fileToCoord(x.charAt(0)), Integer.parseInt(y)));

            sim.debugShowBoard();
        }
        return output;
    }


    public static int fileToCoord(char l) {
        return (l - 'a');
    }

    private String tryToMatch(Matcher matcher, String group) {
        try {
            return matcher.group(group);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private byte[][] deepCopy(byte[][] matrix) {
        return java.util.Arrays.stream(matrix).map(byte[]::clone).toArray($ -> matrix.clone());
    }

    private void dispMatrix(byte[][] matrix) {
        for (byte[] $ : matrix) System.out.println(Arrays.toString($));
    }
}
