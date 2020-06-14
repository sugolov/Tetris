import java.util.*;
import java.awt.*;

public class TetrisPiece {
    public int row;
    public int col;
    private static final int pieceSize = 4;
    private static final Color LIGHT_BLUE = new Color(51, 153, 255);

    public int[][] shape;
    public int num;
    public Color color;

    public int[][][] shapes = new int[][][] { { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 } },
            { { 0, 0, 0, 0 }, { 0, 0, 2, 0 }, { 0, 2, 2, 0 }, { 0, 2, 0, 0 } },
            { { 0, 0, 0, 0 }, { 0, 3, 3, 0 }, { 0, 3, 3, 0 }, { 0, 0, 0, 0 } },
            { { 0, 4, 0, 0 }, { 0, 4, 0, 0 }, { 0, 4, 0, 0 }, { 0, 4, 0, 0 } },
            { { 0, 0, 0, 0 }, { 0, 5, 0, 0 }, { 0, 5, 0, 0 }, { 0, 5, 5, 0 } },
            { { 0, 0, 0, 0 }, { 0, 0, 6, 0 }, { 0, 0, 6, 0 }, { 0, 6, 6, 0 } },
            { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 7, 7, 7 }, { 0, 0, 7, 0 } } };

    public Color[] colors = new Color[] { Color.BLACK, Color.GREEN, Color.RED, Color.YELLOW, LIGHT_BLUE, Color.ORANGE,
            Color.BLUE, Color.MAGENTA };

    public TetrisPiece() {
        newPiece();
    }

    public void newPiece() {
        num = (int) (Math.random() * 6.999);
        shape = shapes[num];
        color = colors[num + 1];
        row = 0;
        col = 7;
    }

    public void display(Graphics g, int[][] field, Point corner, int size) {
        int cx = corner.x + col * size;
        int cy = corner.y + row * size;

        for (int m = 0; m < pieceSize; m++) {
            for (int n = 0; n < pieceSize; n++) {
                if (field[row + m][col + n] == 0) {
                    int var = shape[m][n];
                    g.setColor(colors[var]);
                    g.fillRect(cx + (n - pieceSize) * size, cy + (m - pieceSize) * size, size, size);
                    g.setColor(Color.WHITE);
                    g.drawRect(cx + (n - pieceSize) * size, cy + (m - pieceSize) * size, size, size);
                }
            }
        }
    }

    public void moveDown() {
        row++;
    }

    public void moveUp() {
        row--;
    }

    public void moveRight() {
        col++;
    }

    public void moveLeft() {
        col--;
    }

    public void rotate() {
        int tmp[][] = new int[shape.length][shape.length];

        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape.length; c++) {
                tmp[c][tmp.length - 1 - r] = shape[r][c];
            }
        }

        shape = tmp;
    }

    public void unrotate() {
        for (int i = 0; i < 3; i++) {
            rotate();
        }
    }
}