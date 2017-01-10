package ru.vmerkotan;

/**
 * {@code KeysManager class} represents container
 * for Key objects.
 *
 * Created by vmerkotan on 1/5/2017.
 */
class KeysManager {

    /**
     * Array to hold possible program keys.
     */
    private Key[] keys = new Key[1];
    /**
     *
     */
    private int position = 0;

    /**
     * Adds Key object to Keys array.
     * @param key Key to add.
     */
    void addKey(Key key) {
        if (keys.length == position) {
            Key[] newArr = new Key[this.keys.length * 2];
            System.arraycopy(this.keys, 0, newArr, 0, this.keys.length);
            this.keys = newArr;
        }
        this.keys[position++] = key;
    }

    /**
     * Returns description of all keys.
     * @return String description of all added keys.
     */
    String getKeysDescription() {
        StringBuilder sb = new StringBuilder();
        for (Key k: this.keys) {
            if (k != null) {
                sb.append(k.getKey()).append("   ").append(k.getInfo()).append(System.getProperty("line.separator"));
            }
        }
        return sb.toString();
    }
}
