package ru.vmerkotan;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Operations with task.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class Operation {
    /**
     * State changed from.
     */
    private State fromState;
    /**
     * State changed to.
     */
    private State toState;
    /**
     * Date when action was performed.
     */
    private Calendar executedDate;

    /**
     * Creates new Operation instance.
     *
     * @param fromState     State from.
     * @param toState       State to.
     * @param executedDate  Calendar performed date.
     */
    Operation(State fromState, State toState, Calendar executedDate) {
        this.fromState = fromState;
        this.toState = toState;
        this.executedDate = executedDate;
    }

    /**
     * Returns state from.
     *
     * @return  State from was changed.
     */
    private State getFromState() {
        return fromState;
    }

    /**
     * Returns state to.
     *
     * @return  State new State.
     */
    private State getToState() {
        return toState;
    }

    /**
     * Returns Calendar date when operation was performed.
     *
     * @return Calendar executedDate value.
     */
    Calendar getExecutedDate() {
        return executedDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        return this.getFromState() + " : " + this.getToState() + " : " + sdf.format(this.getExecutedDate().getTime());
    }
}
