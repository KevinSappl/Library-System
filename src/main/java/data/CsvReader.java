package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    int id = Integer.parseInt(bookData[0]);
                    String title = bookData[1];
                    String author = bookData[2];
                    String isbn = bookData[3];
                    int pages = Integer.parseInt(bookData[4]);
                    Date publicationDate = sdf.parse(bookData[5]);
                    Status status = Status.valueOf(bookData[6]); // Annahme, dass Status ein enum ist

                    books.add(new Book(id, title, author, isbn, pages, publicationDate, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return books;
    }
    public static List<Book> readBooksFromCsvByStatus(String fileName, Status status) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile

            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                if (bookData.length >= 6 && Status.valueOf(bookData[6]) == status) {
                    int id = Integer.parseInt(bookData[0]);
                    String title = bookData[1];
                    String author = bookData[2];
                    String isbn = bookData[3];
                    int pages = Integer.parseInt(bookData[4]);
                    Date publicationDate = sdf.parse(bookData[5]);
                    //Status status = Status.valueOf(bookData[6]); // Annahme, dass Status ein enum ist

                    books.add(new Book(id, title, author, isbn, pages, publicationDate, status));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return books;
    }
    public static List<Book> readBooksFromCsvByTitle(String fileName, String title) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Überspringen der Kopfzeile

            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");

                if (bookData.length >= 6 && Objects.equals(bookData[1], title)) {
                    int id = Integer.parseInt(bookData[0]);
                    //String title = bookData[1];
                    String author = bookData[2];
                    String isbn = bookData[3];
                    int pages = Integer.parseInt(bookData[4]);
                    Date publicationDate = sdf.parse(bookData[5]);
                    Status status = Status.valueOf(bookData[6]); // Annahme, dass Status ein enum ist

                    books.add(new Book(id, title, author, isbn, pages, publicationDate, status));
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

