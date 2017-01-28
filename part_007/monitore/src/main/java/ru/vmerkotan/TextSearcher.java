package ru.vmerkotan;

import ru.vmerkotan.output.ConsoleOutput;
import ru.vmerkotan.output.Output;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * class to search text in files in some folder.
 *
 * Created by Вадим on 28.01.2017.
 */
public class TextSearcher {

    private List<Thread> threads = new ArrayList<>();
    private boolean interruptOnFind = false;
    private Output output;

    TextSearcher(Output output) {
        this.output = output;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        if(args.length < 2) {
            throw new IllegalArgumentException("Please specify a path to directory and text to search.");
        }

        Path dir = Paths.get(args[0]);

        if(!Files.exists(dir) || !Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Path do not exist or its not a folder");
        }

        String searchText = args[1];
        boolean interruptOnFind = false;
        if(args.length >= 3 && "-f".equals(args[2])) {
            interruptOnFind = true;
        }
        TextSearcher t = new TextSearcher(new ConsoleOutput());
        t.init(dir, searchText, interruptOnFind);
        long end = System.currentTimeMillis();
        System.out.println((float) (end - start) / 1000);
    }

    void init(Path dir, String searchText, boolean interruptOnFind) throws IOException {
        this.interruptOnFind = interruptOnFind;
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
            for (Path file: dirStream) {

                threads.add(new Thread(() -> {
                    try (Stream<String> stream = Files.lines(file)) {
                        try {
                            stream
                                .filter(line -> line.contains(searchText))
                                .findFirst()
                                .ifPresent(line -> print(file.toAbsolutePath()+ " :: " + line));
                        } catch (Exception ignored) {}

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }
        }

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private synchronized void print(String line) {
        output.write(line);
        if(interruptOnFind) threads.forEach(Thread::interrupt);
    }

}
