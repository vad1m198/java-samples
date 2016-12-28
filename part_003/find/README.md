## This is simple search application to search files by name

To compile jar file run `mvn clean package`
To run the program try `java -jar find.jar -d c:/ -n *.txt -m -o log.txt` from target folder.
After program finishes results will be in specified output path in temporary folder.

Main keys:
- `-d` Folder absolute path to start search from
- `-n` File name or mask to search
- `-o` Specify relative path to write results to
