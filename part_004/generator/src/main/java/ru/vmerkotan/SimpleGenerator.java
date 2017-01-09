package ru.vmerkotan;

import ru.vmerkotan.exceptions.ExtraKeyFoundException;
import ru.vmerkotan.exceptions.KeyNotFoundException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code SimpleGenerator} class represents a container
 * for methods to parse string with keys.
 *
 * Created by Вадим on 09.01.2017.
 */
public class SimpleGenerator implements Template {
    @Override
    public String generate(String template, Map<String, String> dictionary) {
        for (Map.Entry<String, String> entry: dictionary.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);
            if (matcher.find()) {
                template = template.replaceAll(regex, entry.getValue());
            } else {
                throw new KeyNotFoundException("Key: " + entry.getKey() + " was not found.");
            }
        }

        String regex = "\\$\\{.*\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        if (matcher.find()) {
            throw new ExtraKeyFoundException("Some keys was not replaced properly");
        }
        return template;
    }
}
