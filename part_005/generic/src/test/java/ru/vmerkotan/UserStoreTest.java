package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for UserStore class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class UserStoreTest {
    /**
     * Test add method.
     */
    @Test
    public void whenAddThenValueShouldBeAdded() {
        UserStore store = new UserStore(10);
        User u = new User();
        u.setId("1");
        store.add(u);

        assertThat(store.get("1"), is(u));
    }

    /**
     * Test put method.
     */
    @Test
    public void whenUpdateThenValueShouldBeUpdated() {
        UserStore store = new UserStore(10);
        User u = new User();
        u.setId("1");
        User updated = new User();
        updated.setId("1");
        store.add(u);
        store.put("1", updated);

        assertThat(store.get("1"), is(updated));
    }

    /**
     * Test delete method.
     */
    @Test
    public void whenDeleteThenValueShouldBeRemoved() {
        UserStore store = new UserStore(10);
        User firstUser = new User();
        firstUser.setId("1");
        User secondUser = new User();
        secondUser.setId("2");
        store.add(firstUser);
        store.add(secondUser);
        store.delete("1");
        assertNull(store.get("1"));
    }
}