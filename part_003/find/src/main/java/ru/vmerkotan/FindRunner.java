package ru.vmerkotan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Runner class to run find.
 */
public class FindRunner {
    /**
     * Array to hold possible program keys.
     */
    private Key[] keys = new Key[5];
    /**
     * Main method.
     * @param args String Example: -d c:/ -n *.txt -m(-r) -o log.txt
     * @throws IOException when exception appear.
     */
    public static void main(String[] args) throws IOException {
        FindRunner fr = new FindRunner();
        fr.init(args);
	}

    /**
     * Main method.
     * @param args String[] arguments from main method.
     * @throws IOException when exception appear.
     */
	private void init(String[] args) throws IOException {
	    Key directoryKey = new Key("-d", "Specify folder to start search from");
        Key nameKey = new Key("-n", "File name or mask to search");
        Key maskKey = new Key("-m", "Search by mask");
        Key fullMatchKey = new Key("-f", "Search by full match");
        Key outputKey = new Key("-o", "Specify file name to write results to");
        keys[0] = directoryKey;
        keys[1] = nameKey;
        keys[2] = maskKey;
        keys[3] = fullMatchKey;
        keys[4] = outputKey;

        if (args.length != 7) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid arguments number. Please see the example: -d c:/ -n *.txt -m(-f) -o log.txt" + System.getProperty("line.separator"));
            for (Key k: keys) {
                sb.append(k.getKey() + "   " + k.getInfo() + System.getProperty("line.separator"));
            }
            throw new InvalidArgumentsException(sb.toString());
        }

        this.validateKeys(args[0], new Key[]{directoryKey});
        this.validateKeys(args[2], new Key[]{nameKey});
        this.validateKeys(args[4], new Key[]{maskKey, fullMatchKey});
        this.validateKeys(args[5], new Key[]{outputKey});

        File start = new File(args[1]);
        if (!start.exists() || !start.isDirectory()) {
            throw new InvalidArgumentsException(args[1] + " is invalid location. It should be existing folder");
        }
        File outputFile = new File(System.getProperty("java.io.tmpdir") + args[6]);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        boolean isOutput = outputFile.createNewFile();
        if (!isOutput) {
            throw new InvalidArgumentsException(args[6] + " is invalid file name.");
        }

        boolean isFullMatch = args[4].equals(maskKey.getKey());

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(findValidFileNames(start, args[3]));
        }
    }

    /**
     * Recursively looks for files by name.
     * @param folder    to start from
     * @param fileName  fileName to look for
     * @return  String file paths
     */
    private String findValidFileNames(File folder, String fileName) {
	    String result = "";
        if (folder == null || !folder.exists() || !folder.isDirectory()) {
	        throw new RuntimeException();
        } else {
            File[] files = folder.listFiles() == null ? new File[0] : folder.listFiles();
            for (File f: files) {
	            if (f != null && f.isDirectory()) {
                    result += findValidFileNames(f, fileName);
                } else if (f != null && f.getName().equalsIgnoreCase(fileName)) {
	                result += f.getAbsolutePath() + System.getProperty("line.separator");
                }
            }
        }
        return result;
    }



    /**
     * validates argument.
     * @param arg   argument to validate
     * @param keys  Key[] to compare arg to.
     */
    private void validateKeys(String arg, Key[] keys) {
	    for (Key k: keys) {
	        if (k.getKey().equals(arg)) {
	            return;
            }
        }
        StringBuilder errorMessage = new StringBuilder();
	    errorMessage.append(arg + " is invalid argument. Should be one of the following: " + System.getProperty("line.separator"));
	    for (Key k: keys) {
            errorMessage.append(k.getKey() + "   " + k.getInfo() + System.getProperty("line.separator"));
        }
        throw new InvalidArgumentsException(errorMessage.toString());
    }
}
