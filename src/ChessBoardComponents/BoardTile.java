package ChessBoardComponents;

import javax.swing.*;
import java.awt.*;

public class BoardTile extends JButton {

    Image renderingPiece;
    int x, y;
    JPanel parent;
    Color currentColor;
    Color baseColor;

    public BoardTile(ChessBoardUI parent, int x, int y) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        baseColor = (x+y)%2==0?ChessBoardUI.BOARD_BLACK_COLOR:ChessBoardUI.BOARD_WHITE_COLOR;
        setColor(baseColor);
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(null);
        addActionListener(e-> {
            parent.setSelectedTile(this);
        });

        //debug
    }

    public void setColor(Color c) {
        this.setBackground(c);
        this.currentColor = c;
    }

    public void renderPieceAtSquare() {
        if (renderingPiece != null) { //fix static sizing later
            setIcon(new ImageIcon(renderingPiece.getScaledInstance(75,75,  java.awt.Image.SCALE_SMOOTH)));
        } else {
            setIcon(null);
        }
    }

}
