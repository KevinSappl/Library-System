package data;

import bll.Book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;

public class CsvWriter {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    public static void writeBooksToCsv(String fileName, List<Book> books) {
        File file = new File(fileName);
        boolean writeHeader = !file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (writeHeader) {
                writer.append("Title,Author,ISBN,Pages,PublicationDate,Status\n");
            }

            for (Book book : books) {
                writer.append(book.getTitle())
                        .append(",")
                        .append(book.getAuthor())
                        .append(",")
                        .append(book.getIsbn())
                        .append(",")
                        .append(Integer.toString(book.getPages()))
                        .append(",")
                        .append(sdf.format(book.getPublicationDate()))
                        .append(",")
                        .append(book.getStatus().toString())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBookToCsv(String fileName, Book book) {
        File file = new File(fileName);
        boolean writeHeader = !file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (writeHeader) {
                writer.append("Title,Author,ISBN,Pages,PublicationDate,Status\n");
            }

            writer.append(book.getTitle())
                    .append(",")
                    .append(book.getAuthor())
                    .append(",")
                    .append(book.getIsbn())
                    .append(",")
                    .append(Integer.toString(book.getPages()))
                    .append(",")
                    .append(sdf.format(book.getPublicationDate()))
                    .append(",")
                    .append(book.getStatus().toString())
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
