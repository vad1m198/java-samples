package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
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

        assertThat(store.get(0).getId(), is("1"));
    }

    /**
     * Test put method.
     */
    @Test
    public void whenUpdateThenValueShouldBeUpdated() {
        UserStore store = new UserStore(10);
        User u = new User();
        User updated = new User();
        store.add(u);
        store.put(0, updated);

        assertThat(store.get(0), is(updated));
    }

    /**
     * Test delete method.
     */
    @Test
    public void whenDeleteThenValueShouldBeRemoved() {
        UserStore store = new UserStore(10);
        User firstUser = new User();
        User secondUser = new User();
        store.add(firstUser);
        store.add(secondUser);
        store.delete(0);
        assertThat(store.get(0), is(secondUser));
    }
}