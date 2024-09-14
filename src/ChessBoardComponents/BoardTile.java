package ChessBoardComponents;

import javax.swing.*;
import java.awt.*;

public class BoardTile extends JButton {

    public static class FixedStateButtonModel extends DefaultButtonModel    {

        @Override
        public boolean isPressed() {
            return false;
        }

        @Override
        public boolean isRollover() {
            return false;
        }

        @Override
        public void setRollover(boolean b) {
            //NOOP
        }

    }

    ImageIcon renderingPiece;
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
        setFocusable(false);
        setModel(new FixedStateButtonModel());
    }

    public void setColor(Color c) {
        if (c != getBackground()) {
            this.setBackground(c);
            this.currentColor = c;
        }
    }

    public void renderPieceAtSquare() {
        if (renderingPiece != null) { //fix static sizing later
            if (getIcon()!=renderingPiece)setIcon(renderingPiece);
        } else {
            if (getIcon()!=null)setIcon(null);
        }
    }

}
