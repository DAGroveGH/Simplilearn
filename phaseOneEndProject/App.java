package phaseOneEndProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.exit;

public class App {

    final static String PATH = "phaseOneEndProject/files";

    public static void main(String[] args){
        printWelcome();
        printMain();
    }

    public static void printWelcome() {}

    public static void printMain() {}

    public static void printFiles(){}

    public static boolean addFile(String fileName) {
        boolean added = false;
        Path filePath = Paths.get(PATH+fileName);
        if(!Files.exists(filePath)) {
            try{
                File file = new File(String.valueOf(filePath));
                if(file.createNewFile()) {
                    added = true;
                }
            }catch(IOException e) {
                System.out.println("File cannot be created. See console log");
                e.printStackTrace();
            }
        }
        return added;
    }

    public static boolean deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(PATH + fileName);
        return Files.deleteIfExists(filePath);
    }

    public static boolean searchFile(String fileName) {
        boolean found = false;
        File[] files = new File(PATH).listFiles();
        assert files != null;
        for(File file: files) {
            if(file.getName().equals(fileName)) {
                found = true;
            }
        }
        return found;
    }
}
