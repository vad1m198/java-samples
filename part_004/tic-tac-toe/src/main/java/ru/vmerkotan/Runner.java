package ru.vmerkotan;

import ru.vmerkotan.input.ConsoleInput;
import ru.vmerkotan.input.Input;
import ru.vmerkotan.output.ConsoleOutput;
import ru.vmerkotan.output.Output;

/**
 * {@code Runner} class starts tic-tac-toe game.
 */
public class Runner {
    /**
     * GameField object to play on.
     */
    private GameField field;
    /**
     * Contender object.
     */
    private Contender contender;
    /**
     * Should user move first.
     */
    private boolean userStart;
    /**
     * Input to read from.
     */
    private Input in;
    /**
     * Output to write to.
     */
    private Output out;
    /**
     * Sign to be used when user make move.
     */
    private String userSign;
    /**
     * Sign to be used when contender make move.
     */
    private String contenderSign;

    /**
     * Creates new Runner instance.
     *
     * @param field     GameField to play on
     * @param contender Contender instance
     * @param userStart should user make the first move?
     * @param in        Input to read from.
     * @param out       Output to write to.
     */
    public Runner(GameField field, Contender contender, boolean userStart, Input in, Output out) {
        this.field = field;
        this.contender = contender;
        this.userStart = userStart;
        this.in = in;
        this.out = out;
        if (userStart) {
            this.userSign = "X";
            this.contenderSign = "Y";
        } else {
            this.userSign = "Y";
            this.contenderSign = "X";
        }
    }

    /**
     * Main method.
     *
     * @param args String[] args
     */
    public static void main(String[] args) {
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        out.write("Start the game...");
        out.write("This is simple tic-tac-toe game.");
        GameField field = getGameField(in, out);
        boolean userStart = getUserStartTheGame(in, out);
        Contender contender = new Contender(field);
        Runner r = new Runner(field, contender, userStart, in, out);
        r.init();
    }

    /**
     * Returns GameField object based on user input.
     *
     * @param in    Input to read from.
     * @param out   Output to write to.
     * @return      GameField object based om user input.
     */
    public static GameField getGameField(Input in, Output out) {
        boolean validSize = false;
        int defFieldSize = 3;
        GameField gf = null;
        while (!validSize) {
            out.write("Please type field size (3 by default): ");
            String fieldSizeStr = in.read().trim();
            if (!fieldSizeStr.trim().isEmpty()) {
                try {
                    gf = new GameField(Integer.parseInt(fieldSizeStr));
                    validSize = true;
                } catch (NumberFormatException nfe) {
                    out.write(fieldSizeStr + " is invalid field size. Size should be an odd number greater or equal 3");
                } catch (RuntimeException rte) {
                    out.write(rte.getMessage());
                }
            } else {
                validSize = true;
                gf = new GameField(defFieldSize);
            }
        }
        return gf;
    }

    /**
     * Returns true if user should make first move.
     *
     * @param in    Input to read from.
     * @param out   Output to write to.
     * @return      true is user should make first move.
     */
    public static boolean getUserStartTheGame(Input in, Output out) {
        out.write("Who will start the game? (Leave field blank and press 'Enter' to start game by your self. Type something and press 'Enter' to computer start the game): ");
        return in.read().trim().isEmpty();
    }

    /**
     * Starts the game.
     */
    public void init() {
        if (this.userStart) {
            this.userMove();
        }
        while (!this.field.isFull()) {
            this.contenderMove();
            if (this.field.isWinner()) {
                out.write("Contender is winner");
                break;
            }
            this.userMove();
            if (this.field.isWinner()) {
                out.write("User wins");
                break;
            }
        }
    }

    /**
     * Reads X and Y values to make user move.
     */
    public void userMove() {
        boolean valid = false;
        while (!valid) {
            this.out.write("Please type the X coordinate: ");
            String xCoordinateStr = in.read();
            this.out.write("Please type the Y coordinate: ");
            String yCoordinateStr = in.read();
            try {
                this.field.makeMove(new Point(Integer.parseInt(xCoordinateStr), Integer.parseInt(yCoordinateStr)), this.userSign);
                valid = true;
            } catch (NumberFormatException nfe) {
                this.out.write(xCoordinateStr + ":" + yCoordinateStr + " coordinates are invalid. should be positive numbers less then field size.");
            } catch (RuntimeException rte) {
                this.out.write(rte.getMessage());
            }
        }
        this.out.write(this.field.toString());
    }

    /**
     * Contender move.
     */
    public void contenderMove() {
        Point p = this.contender.getMove();
        this.out.write("Contender move is: " + p.getX() + ":" + p.getY());
        this.field.makeMove(p, this.contenderSign);
        this.out.write(this.field.toString());
    }

}