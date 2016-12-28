package ru.vmerkotan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Runner class to run find.
 */
public class FindRunner {
    /**
     * Array to hold possible program keys.
     */
    private Key[] keys = new Key[3];
    /**
     * Main method.
     * @param args String Example: -d c:/ -n *.txt -o log.txt
     * @throws IOException when exception appear.
     */
    public static void main(String[] args) throws IOException {
        FindRunner fr = new FindRunner();
        String tempFolderPath = System.getProperty("java.io.tmpdir");
        fr.init(args, tempFolderPath);
	}

    /**
     * Main method.
     * @param args String[] arguments from main method.
     * @param tempFolderPath path to temp folder to store output file to.
     * @throws IOException when exception appear.
     */
	public void init(String[] args, String tempFolderPath) throws IOException {
	    Key directoryKey = new Key("-d", "<Folder absolute path to start search from>");
        Key nameKey = new Key("-n", "<File name or mask to search>");
        Key outputKey = new Key("-o", "<Specify relative path to write results to>");
        keys[0] = directoryKey;
        keys[1] = nameKey;

        keys[2] = outputKey;

        if (args.length != 6) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid arguments number. Please see the example: -d c:/ -n *.txt -o log.txt" + System.getProperty("line.separator"));
            for (Key k: keys) {
                sb.append(k.getKey() + "   " + k.getInfo() + System.getProperty("line.separator"));
            }
            throw new InvalidArgumentsException(sb.toString());
        }

        this.validateKeys(args[0], new Key[]{directoryKey});
        this.validateKeys(args[2], new Key[]{nameKey});
        this.validateKeys(args[4], new Key[]{outputKey});
        String startSearchFrom = args[1];
        String searchMask = args[3];
        String outputRelativePath = args[5];

        Path start = Paths.get(startSearchFrom);
        if (!Files.exists(start) || !Files.isDirectory(start)) {
            throw new InvalidArgumentsException(args[1] + " is invalid location. It should be existing folder");
        }

        try {
            Paths.get(tempFolderPath + File.separator + outputRelativePath);
        } catch (InvalidPathException ipe) {
            throw new InvalidArgumentsException(outputRelativePath + " is invalid file name.");
        }

        File outputFile = new File(tempFolderPath + File.separator + outputRelativePath);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();

        StringBuffer sb = new StringBuffer();
        Files.walkFileTree(start, new Finder(searchMask, sb));
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(sb.toString());
        }

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

    /**
     * Finder class represents a class to match file against pattern
     * and write absolute paths to StringBuffer.
     */
    class Finder extends SimpleFileVisitor<Path> {
        /**
         * Holds internal PathMatcher.
         */
        private PathMatcher matcher;
        /**
         * StringBuffer to aggregate results.
         */
        private StringBuffer sb;

        /**
         * Constructs new Finder instance.
         * @param pattern   String global pattern to match.
         * @param sb        StringBuffer to write results to.
         */
        Finder(String pattern, StringBuffer sb) {
            this.sb = sb;
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        /**
         * Invoked for a file in a directory.
         * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
         * CONTINUE}.
         * @param path  Path of current file.
         * @param attrs BasicFileAttributes of current file.
         * @return FileVisitResult.CONTINUE
         */
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            validateFile(path, attrs);
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoked for a file that could not be visited.
         * <p> Unless overridden, this method re-throws the I/O exception that prevented
         * the file from being visited.
         * @param path  Path of current file.
         * @param exc   IOException
         * @return      FileVisitResult.CONTINUE
         * @throws IOException when exception appear
         */
        @Override
        public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        /**
         * Validates that file name is not null and file name mathces
         * against pattern.
         * @param file  Path to validate.
         * @param attrs file attributes.
         */
        private void validateFile(Path file, BasicFileAttributes attrs) {
            if (file.getFileName() != null && attrs.isRegularFile() && matcher.matches(file.getFileName())) {
                sb.append(file.toAbsolutePath() + System.getProperty("line.separator"));
            }
        }
    }
}
