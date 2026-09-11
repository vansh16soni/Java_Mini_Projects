package miniProject.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "catalog.txt";

    // 1. Write current inventory to disk
    public static void saveCatalog(List<LibraryItem> items) {
        // try-with-resources: automatically flushes and closes file handles
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (LibraryItem item : items) {
                if (item instanceof Book) {
                    Book b = (Book) item;
                    writer.write("BOOK," + b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.isIssued());
                } else if (item instanceof Magazine) {
                    Magazine m = (Magazine) item;
                    writer.write("MAGAZINE," + m.getId() + "," + m.getTitle() + "," + m.getIssueNumber() + "," + m.isIssued());
                } else if (item instanceof AudioBook) {
                    AudioBook a = (AudioBook) item;
                    writer.write("AUDIOBOOK," + a.getId() + "," + a.getTitle() + "," + a.getDurationHours() + "," + a.isIssued());
                }
                writer.newLine();
            }
            System.out.println("💾 Catalog successfully persisted to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("❌ Failed to save catalog: " + e.getMessage());
        }
    }

    // 2. Load catalog from disk on application startup
    public static List<LibraryItem> loadCatalog() {
        List<LibraryItem> items = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("ℹ️ No existing catalog file found. Starting with a blank or default catalog.");
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 5) continue;

                String type = tokens[0].trim();
                String id = tokens[1].trim();
                String title = tokens[2].trim();
                boolean isIssued = Boolean.parseBoolean(tokens[4].trim());

                LibraryItem item = null;
                switch (type) {
                    case "BOOK":
                        item = new Book(id, title, tokens[3].trim());
                        break;
                    case "MAGAZINE":
                        item = new Magazine(id, title, Integer.parseInt(tokens[3].trim()));
                        break;
                    case "AUDIOBOOK":
                        item = new AudioBook(id, title, Double.parseDouble(tokens[3].trim()));
                        break;
                }

                if (item != null) {
                    if (isIssued) item.issue(); // Restore issue state
                    items.add(item);
                }
            }
            System.out.println("📂 Loaded " + items.size() + " items from " + FILE_PATH);
        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Error loading catalog file: " + e.getMessage());
        }

        return items;
    }
}