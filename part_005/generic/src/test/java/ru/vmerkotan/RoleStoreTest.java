package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
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

        assertThat(store.get("1"), is(u));
    }

    /**
     * Test put method.
     */
    @Test
    public void whenUpdateThenValueShouldBeUpdated() {
        RoleStore store = new RoleStore(10);
        Role u = new Role();
        u.setId("1");
        Role updated = new Role();
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
        RoleStore store = new RoleStore(10);
        Role firstUser = new Role();
        firstUser.setId("1");
        Role secondUser = new Role();
        secondUser.setId("2");
        store.add(firstUser);
        store.add(secondUser);
        store.delete("1");
        assertNull(store.get("1"));
    }

}