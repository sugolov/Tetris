import java.util.*;
import java.awt.*;

class TetrisGame {
    // instance fields
    private final int fieldRows = 20;
    private final int fieldCols = 10;
    private final int pieceSize = 4;

    int[][] field = new int[fieldRows + 2 * pieceSize][fieldCols + 2 * pieceSize];

    TetrisPiece piece = new TetrisPiece();

    public TetrisGame() {
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[0].length; c++) {
                if (r < field.length - pieceSize) {
                    if (c < pieceSize || c >= field[0].length - pieceSize) {
                        field[r][c] = 1;
                    }
                } else {
                    field[r][c] = 1;
                }
            }
        }
    }

    // public methods
    public void display(Graphics g, Dimension fieldSize) {

        // calculate the size of each square
        int size = Math.min(fieldSize.height / fieldRows, fieldSize.width / fieldCols);

        // coordinates of top left corner
        Point corner = new Point();
        corner.x = (fieldSize.width - size * fieldCols) / 2;
        corner.y = (fieldSize.height - size * fieldRows) / 2;

        // display the field
        for (int i = pieceSize; i < fieldRows + pieceSize; i++) {
            for (int j = pieceSize; j < fieldCols + pieceSize; j++) {
                int val = field[i][j];
                g.setColor(piece.colors[val]);
                g.fillRect(corner.x + (j - pieceSize) * size, corner.y + (i - pieceSize) * size, size, size);
                g.setColor(Color.WHITE);
                g.drawRect(corner.x + (j - pieceSize) * size, corner.y + (i - pieceSize) * size, size, size);

            }
        }

        // display the piece
        piece.display(g, field, corner, size);
    }

    public void moveLeft() {
        piece.moveLeft();
        if (checkCollision() == true)
            piece.moveRight();
    }

    public void moveRight() {
        piece.moveRight();
        if (checkCollision() == true)
            piece.moveLeft();
    }

    public void moveDown() {
        piece.moveDown();
        if (checkCollision() == true) {
            piece.moveUp();
            blend();
            if (piece.row < pieceSize - 1)
                end();
            clearRows();
            piece = new TetrisPiece();
        }
    }

    public void dropDown() {
        while (checkCollision() == false) {
            piece.moveDown();
        }
        piece.moveUp();
        blend();
        if (piece.row < pieceSize - 1)
            end();
        clearRows();
        piece = new TetrisPiece();
    }

    public void rotate() {
        piece.rotate();
        if (checkCollision() == true) {
            piece.unrotate();
        }
    }

    private void blend() {
        for (int i = 0; i < pieceSize; i++) {
            for (int j = 0; j < pieceSize; j++) {
                if (field[piece.row + i][piece.col + j] == 0)
                    field[piece.row + i][piece.col + j] = piece.shape[i][j];
            }
        }
    }

    private void clearRows() {
        for (int i = field.length - pieceSize - 1; i >= pieceSize; i--) {
            while (checkRow(field[i]) == true) {
                for (int m = i; m > 0; m--) {
                    for (int n = 0; n < field[0].length; n++) {
                        field[m][n] = field[m - 1][n];
                    }
                }
            }
        }
    }

    private boolean checkRow(int[] r) {
        int counter = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i] != 0)
                counter++;
        }
        if (counter == field[0].length)
            return true;
        return false;
    }

    private boolean checkCollision() {
        for (int i = 0; i < pieceSize; i++) {
            for (int j = 0; j < pieceSize; j++) {
                if (field[piece.row + i][piece.col + j] != 0 && piece.shape[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void end() {
        System.exit(0);
    }
}