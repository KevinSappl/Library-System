package data;

import bll.Book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeBooksToCsv(String fileName, List<Book> books) {
        File file = new File(fileName);
        boolean writeHeader = !file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (writeHeader) {
                writer.append("Titel,ISBN,Status\n");
            }

            for (Book book : books) {
                writer.append(book.getTitle())
                        .append(",")
                        .append(book.getIsbn())
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
                writer.append("Titel,ISBN,Status\n");
            }

            writer.append(book.getTitle())
                        .append(",")
                        .append(book.getIsbn())
                        .append(",")
                        .append(book.getStatus().toString())
                        .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
