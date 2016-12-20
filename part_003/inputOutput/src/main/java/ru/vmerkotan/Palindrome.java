package ru.vmerkotan;

/**
 * Created by vmerkotan on 12/15/2016.
 * Palindrome class represents container for
 * methods to work with palindomes
 */
public class Palindrome {

    /**
     * verifies that passed String is polindrom
     * @param  str String to check
     * @return true is String length is eqaul to 5 and
     *         String is polindrom
     */
    public boolean isPolindrom(String str) {
        if(str.length() != 5) {
            return false;
        }
        boolean result = true;
        for(int i = 0; i < str.length(); i++) {
            if(str.toLowerCase().charAt(i) != str.toLowerCase().charAt(str.length() - 1 - i)) {
                result = false;
                break;
            }
        }

        return result;
    }

}
