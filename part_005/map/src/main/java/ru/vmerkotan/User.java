package ru.vmerkotan;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * User class for task.
 *
 * Created by vmerkotan on 1/25/2017.
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 17 * result + children;
        result = 17 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }

    public static void main(String[] args) {

        Map<User, Object> map = new HashMap<>();
        Calendar calendar = new GregorianCalendar();
        map.put(new User("Vova", 10, calendar), new Object());
        map.put(new User("Vova", 10, calendar), new Object());
        map.put(new User("Vova", 10, calendar), new Object());
        System.out.println(map);

    }

}
