package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bll.Book;
import bll.Status;

public class CsvReader {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);

    public static List<Book> readBooksFromCsv(String fileName) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile

            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                if (bookData.length >= 6) {
                    String title = bookData[0];
                    String author = bookData[1];
                    String isbn = bookData[2];
                    int pages = Integer.parseInt(bookData[3]);
                    Date publicationDate = sdf.parse(bookData[4]);
                    Status status = Status.valueOf(bookData[5]); // Annahme, dass Status ein enum ist

                    books.add(new Book(title, author, isbn, pages, publicationDate, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return books;
    }
}

