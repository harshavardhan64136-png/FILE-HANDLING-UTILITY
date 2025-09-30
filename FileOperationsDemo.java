import java.io.*;
import java.nio.file.*;
import java.util.*;


public class FileOperationsDemo {

    // ---------- WRITE FILE ----------
    // Creates or overwrites a file with given content
    public static void writeFile(String fileName, List<String> content) throws IOException {
        Files.write(Paths.get(fileName), content);
    }

    // ---------- APPEND FILE ----------
    // Adds content at the end of the file
    public static void appendFile(String fileName, List<String> content) throws IOException {
        Files.write(Paths.get(fileName), content, StandardOpenOption.APPEND);
    }

    // ---------- READ FILE ----------
    // Reads file line by line and returns content as List
    public static List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    // ---------- MODIFY FILE ----------
    // Replace a specific line with new text
    public static void replaceLine(String fileName, int lineNumber, String newText) throws IOException {
        List<String> lines = readFile(fileName);
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            lines.set(lineNumber, newText);
            writeFile(fileName, lines);
        }
    }

    // Insert a new line at a given position
    public static void insertLine(String fileName, int lineNumber, String newText) throws IOException {
        List<String> lines = readFile(fileName);
        if (lineNumber < 0) lineNumber = 0;
        if (lineNumber > lines.size()) lineNumber = lines.size();
        lines.add(lineNumber, newText);
        writeFile(fileName, lines);
    }

    // Delete a line by index
    public static void deleteLine(String fileName, int lineNumber) throws IOException {
        List<String> lines = readFile(fileName);
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            lines.remove(lineNumber);
            writeFile(fileName, lines);
        }
    }

    // ---------- PRINT FILE CONTENT ----------
    public static void printFile(String fileName) throws IOException {
        System.out.println("\n--- " + fileName + " content ---");
        List<String> lines = readFile(fileName);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i) + ": " + lines.get(i));
        }
    }

    // ---------- MAIN DEMO ----------
    public static void main(String[] args) {
        String fileName = "demo.txt";

        try {
            // 1. Write file
            writeFile(fileName, Arrays.asList("Line 1: Hello World", "Line 2: Java File Operations", "Line 3: End of File"));
            printFile(fileName);

            // 2. Append to file
            appendFile(fileName, Arrays.asList("Line 4: This was appended", "Line 5: Another appended line"));
            printFile(fileName);

            // 3. Insert line
            insertLine(fileName, 1, "Inserted Line at index 1");
            printFile(fileName);

            // 4. Replace line
            replaceLine(fileName, 2, "Replaced Line 2 with new content");
            printFile(fileName);

            // 5. Delete line
            deleteLine(fileName, 3);
            printFile(fileName);

            System.out.println("\nDemo complete ");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
