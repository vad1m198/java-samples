package ru.vmerkotan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ConvertIterator class.
 * Created by Вадим on 02.01.2017.
 */
public class ConvertIteratorTest {

    /**
     * test convert method.
     */
    @Test
    public void whenPassIteratorThenReturnValidIter() {
        List<Iterator<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{100, 150})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{200})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{300})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{400})).iterator());
        ConvertIterator<Integer> cIterator = new ConvertIterator<Integer>();
        Iterator<Integer> it = cIterator.convert(list.iterator());
        int[] expected = new int[]{100, 150, 200, 300, 400};
        int[] actual = new int[5];
        int pos = 0;
        while (it.hasNext()) {
            actual[pos++] = it.next();
        }
        assertThat(actual, is(expected));
    }

    /**
     * test convert method.
     */
    @Test
    public void whenNullPresentThenReturnValidValues() {
        List<Iterator<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{100, 150})).iterator());
        list.add(null);
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{300})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{400})).iterator());
        ConvertIterator<Integer> cIterator = new ConvertIterator<Integer>();
        Iterator<Integer> it = cIterator.convert(list.iterator());
        int[] expected = new int[]{100, 150, 300, 400};
        int[] actual = new int[4];
        int pos = 0;
        while (it.hasNext()) {
            actual[pos++] = it.next();
        }
        assertThat(actual, is(expected));
    }
}