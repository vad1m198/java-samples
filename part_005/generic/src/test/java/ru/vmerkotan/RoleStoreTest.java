package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for RoleStore class.
 *
 * Created by vmerkotan on 1/16/2017.
 */
public class RoleStoreTest {

    /**
     * Test add method.
     */
    @Test
    public void whenAddThenValueShouldBeAdded() {
        RoleStore store = new RoleStore(10);
        Role u = new Role();
        u.setId("1");
        store.add(u);

        assertThat(store.get(0).getId(), is("1"));
    }

    /**
     * Test put method.
     */
    @Test
    public void whenUpdateThenValueShouldBeUpdated() {
        RoleStore store = new RoleStore(10);
        Role u = new Role();
        Role updated = new Role();
        store.add(u);
        store.put(0, updated);

        assertThat(store.get(0), is(updated));
    }

    /**
     * Test delete method.
     */
    @Test
    public void whenDeleteThenValueShouldBeRemoved() {
        RoleStore store = new RoleStore(10);
        Role firstUser = new Role();
        Role secondUser = new Role();
        store.add(firstUser);
        store.add(secondUser);
        store.delete(0);
        assertThat(store.get(0), is(secondUser));
    }

}