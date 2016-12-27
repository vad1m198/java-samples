package ru.vmerkotan;

/**
 * Represents abstract key representation.
 * Created by vmerkotan on 12/27/2016.
 */
public class Key implements IKey {
    /**
     * Holds key description.
     */
    private String description;
    /**
     * Holds key String representation.
     */
    private String keyStr;

    /**
     * Creates new Key instance.
     * @param desc      Key description
     * @param keyStr    Key string representation
     */
    public Key(String keyStr, String desc) {
        this.keyStr = keyStr;
        this.description = desc;
    }

    /**
     * Returns key string representation.
     * @return String representation.
     */
    @Override
    public String getKey() {
        return this.keyStr;
    }

    /**
     * Returns description of key.
     * @return  key description.
     */
    @Override
    public String getInfo() {
        return this.description;
    }
}
