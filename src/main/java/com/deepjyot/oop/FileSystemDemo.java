package com.deepjyot.oop;

import java.util.ArrayList;
import java.util.List;

// Component Interface: Represents common behavior for File and Folder
interface FileSystemComponent {
    String getName();
    int getSize();  // In bytes
    void listContents(String indent);  // Display contents with indentation for hierarchy

    // Default implementations for unsupported operations (files cannot have children)
    default void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to this component.");
    }

    default void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from this component.");
    }
}

// Leaf Class: File
class File implements FileSystemComponent {
    private final String name;
    private final int size;  // Size of the file in bytes

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void listContents(String indent) {
        System.out.println(indent + "- " + name + " (File, " + size + " bytes)");
    }
}

// Composite Class: Folder
class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children;  // List of files and sub-folders

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();  // Sum of all child sizes
        }
        return totalSize;
    }

    @Override
    public void listContents(String indent) {
        System.out.println(indent + "+ " + name + " (Folder)");
        for (FileSystemComponent component : children) {
            component.listContents(indent + "  ");  // Recursive call with increased indentation
        }
    }

    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

}

// Main class to demonstrate the FileSystem
public class FileSystemDemo {
    public static void main(String[] args) {
        // Create individual files
        File file1 = new File("Resume.pdf", 1200);
        File file2 = new File("Presentation.pptx", 5000);
        File file3 = new File("Notes.txt", 800);

        // Create folders and subfolders
        Folder rootFolder = new Folder("Root");
        Folder documentsFolder = new Folder("Documents");
        Folder mediaFolder = new Folder("Media");
        Folder personalFolder = new Folder("Personal");

        // Build the file system structure
        documentsFolder.add(file1);
        documentsFolder.add(file2);
        personalFolder.add(file3);
        rootFolder.add(documentsFolder);
        rootFolder.add(mediaFolder);
        rootFolder.add(personalFolder);

        // List contents of the entire file system
        System.out.println("File System Contents:");
        rootFolder.listContents("");

        // Calculate total size of the file system
        System.out.println("\nTotal Size of Root Folder: " + rootFolder.getSize() + " bytes");
    }
}
