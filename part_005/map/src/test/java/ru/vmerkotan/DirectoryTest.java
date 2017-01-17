package ru.vmerkotan;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for Directory class.
 *
 * Created by vmerkotan on 1/17/2017.
 */
public class DirectoryTest {
    /**
     * test insert/get method.
     */
    @Test
    public void whenInsertThanGetShouldReturnAddedValue() {
        Directory<String, String> dir = new Directory<>();
        assertTrue(dir.insert("1", "test"));

        assertThat(dir.get("1"), is("test"));
    }

    /**
     * test hasNext method.
     */
    @Test
    public void whenInsertThanHasNextTrue() {
        Directory<String, String> dir = new Directory<>();
        dir.insert("1", "test");
        assertTrue(dir.hasNext());
    }

    /**
     * test delete method.
     */
    @Test
    public void whenDeleteThanGetReturnNull() {
        Directory<String, String> dir = new Directory<>();
        dir.insert("1", "test");
        dir.delete("1");
        assertNull(dir.get("1"));
    }

    /**
     * test next method.
     */
    @Test
    public void whenNextThanReturnEntry() {
        Directory<String, String> dir = new Directory<>();
        dir.insert("1", "test");
        Directory.DirEntry entry= dir.next();
        assertThat(entry.getValue(), is("test"));
        assertThat(entry.getKey(), is("1"));

    }
}