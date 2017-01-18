package ru.vmerkotan;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * OrderBookRunner class represents runner for Book app.
 *
 * Created by vmerkotan on 1/18/2017.
 */
public class OrderBookRunner {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        long start = System.currentTimeMillis();

        Map<String, Book> map = new TreeMap<>();
        int addOrderCounter = 0;
        int delOrderCounter = 0;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = factory.createXMLStreamReader(
                new BufferedReader(new FileReader("c:\\hello\\orders.xml")));

        while (streamReader.hasNext()) {
            streamReader.next();
            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                String localName = streamReader.getLocalName();
                if("AddOrder".equals(localName)) {
                    addOrderCounter++;
                    Order order = new Order(Integer.parseInt(streamReader.getAttributeValue(null, "orderId")),
                            streamReader.getAttributeValue(null, "operation"),
                            Integer.parseInt(streamReader.getAttributeValue(null, "volume")));

                    String bookName = streamReader.getAttributeValue(null, "book");
                    Book book = map.get(bookName);
                    if(book == null) {
                        Book bookToAdd = new Book(bookName,
                            Double.parseDouble(streamReader.getAttributeValue(null, "price")));
                        bookToAdd.addOrder(order);
                        map.put(bookName, bookToAdd);
                    } else {
                        book.addOrder(order);
                    }
                } else if ("DeleteOrder".equals(localName)) {
                    delOrderCounter++;
                    String bookName = streamReader.getAttributeValue(null, "book");
                    Book book = map.get(bookName);
                    if(book != null) {
                        int orderId = Integer.parseInt(streamReader.getAttributeValue(null, "orderId"));
                        book.deleteOrder(orderId);

                    }
                }
            }
        }
        System.out.println("addOrderCounter: " + addOrderCounter);
        System.out.println("delOrderCounter: " + delOrderCounter);
        System.out.println(addOrderCounter - delOrderCounter);
        int bookSize = 0;
        for(Book b : map.values()) {
            System.out.println(b.getName() + ":" + b.getSize());
            bookSize += b.getSize();
        }
        System.out.println("bookSize is " + bookSize);

        long end = System.currentTimeMillis();
        System.out.println("TIME IS: " + (float) (end - start) / 1000);
    }
}

