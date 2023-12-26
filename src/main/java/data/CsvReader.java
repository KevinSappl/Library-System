package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import bll.Book;
import bll.Status;

public class CsvReader {

    public static List<Book> readBooksFromCsv(String fileName) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile

            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                if (bookData.length >= 3) {
                    String title = bookData[0];
                    String isbn = bookData[1];
                    Status status = Status.valueOf(bookData[2]); // Annahme, dass Status ein enum ist

                    books.add(new Book(title, isbn, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}

