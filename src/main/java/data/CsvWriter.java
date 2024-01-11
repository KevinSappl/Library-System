package data;

import bll.Book;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CsvWriter {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    public static void writeBooksToCsv(String fileName, List<Book> books) {
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.append("Title,Author,ISBN,Pages,PublicationDate,Status\n");
            int count = 1;
            for (Book book : books) {
                book.setId(count);
                writer.append(Integer.toString(count))
                        .append(",")
                        .append(book.getTitle())
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
                count++;
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

            writer.append(Integer.toString(nextId(fileName)))
                    .append(",")
                    .append(book.getTitle())
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

    private static int nextId(String filePath) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            count++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
