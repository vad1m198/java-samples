package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.exceptions.ExtraKeyFoundException;
import ru.vmerkotan.exceptions.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * {@code TemplateTest} class contains
 * tests for Template class.
 *
 * Created by Вадим on 09.01.2017.
 */
public class TemplateTest {
    /**
     * verify generate method.
     */
    @Test
    public void whenMatcherIsFoundThenReplace() {
        Template template = new SimpleGenerator();
        String startStr = "Hello, ${name} from ${city}";
        String expectedStr = "Hello, John from New York";
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("name", "John");
        dictionary.put("city", "New York");

        assertThat(template.generate(startStr, dictionary), is(expectedStr));
    }

    /**
     * verify generate method.
     * If key from dictionary was not found then throw
     * KeyNotFoundException
     */
    @Test(expected = KeyNotFoundException.class)
    public void whenKeyWasNotFoundThenThrow() {
        Template template = new SimpleGenerator();
        String startStr = "Hello, ${name}";
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("name", "John");
        dictionary.put("title", "student");

        template.generate(startStr, dictionary);
    }

    /**
     * verify generate method.
     * If key was not specified in dictionary throw
     * ExtrtaKeyFoundException
     */
    @Test(expected = ExtraKeyFoundException.class)
    public void whenKeyIsNotInDoctionaryThenThrow() {
        Template template = new SimpleGenerator();
        String startStr = "Hello, ${name} from ${city}";
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("name", "John");

        template.generate(startStr, dictionary);
    }
}