package ru.vmerkotan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class represents a task.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class Task {
    /**
     * Current task state.
     */
    private State state;
    /**
     * Task created date.
     */
    private Calendar createdDate;
    /**
     * Task last modified date.
     */
    private Calendar lastModifiedDate;
    /**
     * Performed operations.
     */
    private List<Operation> operations;

    /**
     * Creates new Task instance.
     *
     * @param state         State of the task.
     * @param createdDate   Created date.
     */
    Task(State state, Calendar createdDate) {
        this.state = state;
        this.createdDate = createdDate;
        this.lastModifiedDate = createdDate;
        operations = new ArrayList<>();
    }

    /**
     * Adds Operation object to the internal operations list.
     *
     * @param operation  Operation to add.
     */
    void addOperation(Operation operation) {
        this.lastModifiedDate = operation.getExecutedDate();
        this.operations.add(operation);
    }

    /**
     * Returns Task current state.
     *
     * @return  State of the task.
     */
    private State getState() {
        return this.state;
    }

    /**
     * Returns task created date.
     *
     * @return  created date of the task.
     */
    private Calendar getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Returns task last modified date.
     *
     * @return  lastModifiedDAte value.
     */
    private Calendar getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Returns Operation list.
     *
     * @return  list of performed operations.
     */
    List<Operation> getOperations() {
        return this.operations;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        return this.getState() + " ::: " + sdf.format(this.getLastModifiedDate().getTime()) + " ::: " + sdf.format(this.getCreatedDate().getTime());
    }
}
