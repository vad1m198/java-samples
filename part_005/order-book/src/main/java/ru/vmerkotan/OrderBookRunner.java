package ru.vmerkotan;

import ru.vmerkotan.output.ConsoleOutput;
import ru.vmerkotan.output.Output;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * OrderBookRunner class represents runner for Book app.
 *
 * Created by vmerkotan on 1/18/2017.
 */
public class OrderBookRunner {
    /**
     * Main method.
     *
     * @param args  passed arguments
     * @throws IOException  when occur.
     * @throws XMLStreamException   when occur.
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
        Output output = new ConsoleOutput();
        Properties p = new Properties();
        p.load(new InputStreamReader(new FileInputStream(new File(".\\part_005\\order-book\\app.properties"))));
        String path = p.getProperty("location");
        OrderBookRunner orderBook = new OrderBookRunner();
        orderBook.init(path, output);
    }
    /**
     * starts the app.
     *
     * @param path      path to source file.
     * @param output    Output to write result to.
     * @throws XMLStreamException   when occur.
     * @throws FileNotFoundException    when occur.
     */
    void init(String path, Output output) throws XMLStreamException, FileNotFoundException {
        long start = System.currentTimeMillis();
        Map<String, Book> map = new HashMap<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = factory.createXMLStreamReader(
                new BufferedReader(new FileReader(path)));

        while (streamReader.hasNext()) {
            streamReader.next();
            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                String localName = streamReader.getLocalName();
                if ("AddOrder".equals(localName)) {
                    Order order = new Order(Integer.parseInt(streamReader.getAttributeValue(null, "orderId")),
                            streamReader.getAttributeValue(null, "operation"),
                            Integer.parseInt(streamReader.getAttributeValue(null, "volume")),
                            Double.parseDouble(streamReader.getAttributeValue(null, "price")));

                    String bookName = streamReader.getAttributeValue(null, "book");
                    Book book = map.get(bookName);
                    if (book == null) {
                        Book bookToAdd = new Book(bookName);
                        bookToAdd.addOrder(order);
                        map.put(bookName, bookToAdd);
                    } else {
                        book.addOrder(order);
                    }
                } else if ("DeleteOrder".equals(localName)) {
                    String bookName = streamReader.getAttributeValue(null, "book");
                    Book book = map.get(bookName);
                    if (book != null) {
                        int orderId = Integer.parseInt(streamReader.getAttributeValue(null, "orderId"));
                        book.deleteOrder(orderId);
                    }
                }
            }
        }

        for (Book b : map.values()) {
            b.calculateAndWrite(output);
        }

        long end = System.currentTimeMillis();
        output.write("TIME IS: " + (float) (end - start) / 1000);
    }
}

