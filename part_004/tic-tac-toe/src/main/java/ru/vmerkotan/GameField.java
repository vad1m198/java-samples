package ru.vmerkotan;

/**
 * {@code GameField} represents a field
 * for tic-tac-toe game.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class GameField {
    /**
     * Game field.
     */
    private String[][] field;

    /**
     * Creates new Field object.
     *
     * @param size Field size
     */
    public GameField(int size) {
        if (size < 3 || size % 2 == 0) {
            throw new RuntimeException(size + " is invalid. Size should be odd and greater or equal 3");
        }
        this.field = new String[size][size];
    }

    /**
     * Tries to mark cell with X Y coordinate with Sign.
     *
     * @param point Point to verify.
     * @param s String to use into as sign.
     */
    public void makeMove(Point point, String s) {
        int x = point.getX();
        int y = point.getY();
        if (x >= this.field.length || y >= this.field.length || x < 0 || y < 0) {
            throw new RuntimeException("Invalid move coordinates: " + x + ":" + y + " are out if field.");
        }

        if (!verifyOccupied(x, y)) {
            this.field[y][x] = s;
        } else {
            throw new RuntimeException("Field with coordinates: " + x + ":" + y + " is occupied.");
        }

    }

    /**
     * Verifies that cell is not occupied.
     *
     * @param x X coordinate.
     * @param y Y coordinates
     * @return true if cell with coordinates X Y has some String values.
     */
    public boolean verifyOccupied(int x, int y) {
        return this.field[y][x] != null;
    }

    /**
     * Returns field size.
     *
     * @return size of field.
     */
    public int getSize() {
        return this.field.length;
    }

    /**
     * Checks horizontal, vertical, diagonal lines.
     * If line contains equal Strings method will return true.
     *
     * @return true if some line contains of equal Strings.
     */
    public boolean isWinner() {
        for (int i = 0; i < this.field.length; i++) {
            boolean validHorizontal = true;
            for (int j = 0; j < this.field.length; j++) {
                if (this.field[i][i] == null || !this.field[i][i].equals(this.field[i][j])) {
                    validHorizontal = false;
                    break;
                }
            }
            if (validHorizontal) {
                return true;
            }
            boolean validVertical = true;
            for (int j = 0; j < this.field.length; j++) {
                if (this.field[i][i] == null || !this.field[i][i].equals(this.field[j][i])) {
                    validVertical = false;
                    break;
                }
            }
            if (validVertical) {
                return true;
            }
            boolean validDiagonal = true;
            for (int j = 0; j < this.field.length; j++) {
                if (this.field[0][0] == null || !this.field[0][0].equals(this.field[j][j])) {
                    validDiagonal = false;
                    break;
                }
            }
            if (validDiagonal) {
                return true;
            }
            boolean validDiagonalReverse = true;
            for (int j = 0; j < this.field.length; j++) {
                if (this.field[0][this.field.length - 1] == null
                        || !this.field[0][this.field.length - 1].equals(this.field[j][this.field.length - 1 - j])) {
                    validDiagonalReverse = false;
                    break;
                }
            }
            if (validDiagonalReverse) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies empty cells.
     *
     * @return true is some cells are empty else false.
     */
    public boolean isFull() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field.length; j++) {
                if (this.field[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] x: this.field) {
            sb.append("|");
            for (String y: x) {
                if (y == null) {
                    sb.append(" ");
                } else {
                    sb.append(y);
                }
                sb.append("|");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
