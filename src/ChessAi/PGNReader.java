package ChessAi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PGNReader {

    File currentPGN;
    Scanner fileReader;
    public static int MAX_LOAD_INTO_MEMORY = 256;

    private Pattern pgnDecompiler;

    public static void main(String[] args) {
        PGNReader reader = new PGNReader();
        reader.open("test_data.pgn");
        reader.getNextGame();
        reader.getNextGame();

    }

    public BoardState readNext() {
        return new BoardState();
    }


    public PGNReader() {
        pgnDecompiler = Pattern.compile("\\d*?\\. ?(.*?) (.*?) ");
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
//                    else if (data.substring(1).startsWith("BlackElo")) {
//                        output.blackELO = Integer.parseInt(data.substring(11, data.length() - 2));
//                    } else if (data.substring(1).startsWith("WhiteElo")) {
//                        output.whiteELO = Integer.parseInt(data.substring(11, data.length() - 2));
//                    }
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
            for (int j = 1; j <= matcher.groupCount(); j++) {
                moves.add(matcher.group(j));
            }
            numberOfMoves++;
        }
        output.numberOfMoves = numberOfMoves;
        output.game = new BoardState[numberOfMoves+1];
        return output;
    }
}
