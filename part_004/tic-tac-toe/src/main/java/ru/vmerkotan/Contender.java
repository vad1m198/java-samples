package ru.vmerkotan;

/**
 * {@code Contender} class represents a
 * computer to work with.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class Contender {
    /**
     * Game field to work with.
     */
    private final GameField field;

    /**
     * Creates new Contender instance.
     *
     * @param field GameField to play on.
     */
    public Contender(GameField field) {
        this.field = field;
    }

    /**
     * Returns new Point to make move.
     *
     * @return Point to make move to.
     */
    public Point getMove() {
        for (int i = 0; i < this.field.getSize(); i++) {
            for (int j = 0; j < this.field.getSize(); j++) {
                if (!this.field.verifyOccupied(j, i)) {
                    return new Point(j, i);
                }
            }
        }
        throw new RuntimeException("Now free points found on field");
    }


}
