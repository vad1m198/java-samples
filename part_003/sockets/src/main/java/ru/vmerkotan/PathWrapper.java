package ru.vmerkotan;

import java.nio.file.Path;

/**
 * PathWrapper class presents a wrapper to work with
 * Paths instances in different classes.
 * Created by vmerkotan on 12/29/2016.
 */
public class PathWrapper {
    /**
     * Holds inner Path variable.
     */
    private Path path;

    /**
     * Constructs new PathWrapper instance.
     * @param p Path
     */
    public PathWrapper(Path p) {
        this.path = p;
    }

    /**
     * returns current path.
     * @return  current path.
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * sets path.
     * @param p Path to set
     */
    public void setPath(Path p) {
        this.path = p;
    }
}
