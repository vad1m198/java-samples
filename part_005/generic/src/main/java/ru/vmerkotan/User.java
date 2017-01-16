package ru.vmerkotan;

/**
 * User class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class User extends Base {
    /**
     * Id of the object.
     */
    private String id;

    @Override
    String getId() {
        return this.id;
    }

    @Override
    void setId(String id) {
        this.id = id;
    }
}
