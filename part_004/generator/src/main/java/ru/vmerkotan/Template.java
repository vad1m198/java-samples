package ru.vmerkotan;

import java.util.Map;

/**
 * {@code Template} interface parses template
 * String based on keys.
 *
 * Created by Вадим on 09.01.2017.
 */
public interface Template {
    /**
     * Returns result of replacing Template with keys.
     *
     * @param template      String to parse
     * @param dictionary    Map<String, String> keys
     * @return              String based on template with replaced keys.
     */
    String generate(String template, Map<String, String> dictionary);

}
