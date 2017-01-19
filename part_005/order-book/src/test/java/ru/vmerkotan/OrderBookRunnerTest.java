package ru.vmerkotan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.vmerkotan.output.Output;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Tests for OrderBookRunner class.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class OrderBookRunnerTest {
    /**
     * Source file.
     */
    private File testFile;
    /**
     * Creates temp folder.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * SetUp test file.
     *
     * @throws IOException  when occur.
     */
    @Before
    public void setUp() throws IOException {
        testFile = folder.newFile("source.data");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.getProperty("line.separator"));
            writer.write("<Orders>" + System.getProperty("line.separator"));
            writer.write("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />" + System.getProperty("line.separator"));
            writer.write("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"110.50\" volume=\"85\" orderId=\"2\" />" + System.getProperty("line.separator"));
            writer.write("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"101.50\" volume=\"82\" orderId=\"3\" />" + System.getProperty("line.separator"));
            writer.write("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"103.50\" volume=\"85\" orderId=\"4\" />" + System.getProperty("line.separator"));
            writer.write("<AddOrder book=\"book-1\" operation=\"BUY\" price=\"102.50\" volume=\"200\" orderId=\"5\" />" + System.getProperty("line.separator"));
            writer.write("<DeleteOrder book=\"book-1\" orderId=\"2\" />" + System.getProperty("line.separator"));
            writer.write("</Orders>");
        }
    }

    /**
     * Verify calculate method.
     *
     * @throws FileNotFoundException    when exception occur.
     * @throws XMLStreamException       when exception occur.
     */
    @Test
    public void verifyCalculateAndWriteMethod() throws FileNotFoundException, XMLStreamException {
        OrderBookRunner orderBook = new OrderBookRunner();
        TestOutput output = new TestOutput();
        orderBook.init(testFile.getAbsolutePath(), output);
        assertThat(output.getResult(), containsString("37@102.5 --- 85@103.5"));
    }

    /**
     * Simple Output test class.
     */
    private class TestOutput implements Output {
        /**
         * holds all output result.
         */
        private StringBuilder sb = new StringBuilder();
        @Override
        public void write(String message) {
            sb.append(message);
        }

        /**
         * Returns all inputs representation.
         *
         * @return  String representation of all inputs.
         */
        String getResult() {
            return this.sb.toString();
        }
    }

}