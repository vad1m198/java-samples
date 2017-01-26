package ru.vmerkotan;

/**
 * Created by vmerkotan on 1/26/2017.
 */
public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Delete user " + name);
        super.finalize();
    }
}
