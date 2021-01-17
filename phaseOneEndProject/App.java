package phaseOneEndProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.exit;

public class App {

    final static String PATH = "phaseOneEndProject/files/";                 //Universal file path for this directory

    public static void main(String[] args){
        printWelcome();
        printMain();
    }                             //Call for Welcome and Main Menu

//    =================================
//    |        WELCOME SCREEN         |
//    =================================

    public static void printWelcome() {
        String bar = "====================================================================================================\n";
        String welcome = "    ___      ____      ___   ______    __         ______     ______     ____    ____    ______\n" +
                "    \\  \\    /    \\    /  /  |  ____|  |  |       |   ___|   |  __  |   |    \\  /    |  |  ____|\n" +
                "     \\  \\  /  /\\  \\  /  /   |  |__    |  |       |  |       | |  | |   |  |\\ \\/ /|  |  |  |__\n" +
                "      \\  \\/  /  \\  \\/  /    |   __|   |  |       |  |       | |  | |   |  | \\  / |  |  |   __|\n" +
                "       \\    /    \\    /     |  |___   |  |____   |  |___    | |__| |   |  |  \\/  |  |  |  |___\n" +
                "        \\__/      \\__/      |______|  |_______|  |______|   |______|   |__|      |__|  |______|\n";

        System.out.println(bar);
        System.out.println(welcome);
        System.out.println(bar + "\n");

        System.out.println(bar);
        System.out.println("        ---------------------------      LockedMe Prototype     ----------------------------");
        System.out.println("        ---------------------------  Developed by: David Grove  ----------------------------\n");
        System.out.println(bar + "\n");
    }

//    =================================
//    |           MAIN MENU           |
//    =================================

    public static void printMain() {
        System.out.println("\n--------HOME PAGE--------");
        System.out.println("Please select an action:");
        System.out.println("   1. View Files");
        System.out.println("   2. Display File Actions");
        System.out.println("   3. Close App");

        Scanner sc = new Scanner(System.in);
        try {
            switch (sc.nextInt()) {
                case 1 -> {
                    printFiles();
                    printMain();
                }
                case 2 -> {
                    printFileActions();
                    printMain();
                }
                case 3 -> exit(0);

                default -> {
                    System.out.println("Unexpected value, please input a number, 1 through 3");
                    printMain();
                }
            }
        }catch (InputMismatchException | IOException e) {
            System.out.println("Unexpected input, please input a valid choice 1 through 3");
        }
        printMain();
    }

//    =================================
//    |       FILE ACTION MENU        |
//    =================================

    public static void printFileActions() throws IOException {
        System.out.println("\n------FILE ACTIONS------");
        System.out.println("Please select an operation:");
        System.out.println("1. Add a file\n" + "2. Delete a file\n" + "3. Search for a file\n" + "4. Return to main menu");
        Scanner sc = new Scanner(System.in);
        try {
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Please input the file name for the file you would like to add");
                    if (addFile(sc.next())) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Failure");
                    }
                }
                case 2 -> {
                    System.out.println("Please input the file name for the file you would like to delete");
                    if (deleteFile(sc.next())) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Failure");
                    }
                }
                case 3 -> {
                    System.out.println("Please input the file name for the file you would like to search (Case Sensitive)");
                    if (searchFile(sc.next())) {
                        System.out.println("File found");
                    } else {
                        System.out.println("File not found");
                    }
                }
                case 4 -> printMain();
            }
        }catch(InputMismatchException | IOException e) {
            System.out.println("Unexpected input, please input a valid choice 1 through 4");
        }
        printFileActions();                         //Continues the program if exception is thrown
    }

//    =================================
//    |    PRINT SORTED FILE NAMES    |
//    =================================

    public static void printFiles(){
        System.out.println("---------------------");
        System.out.println("Showing files in ascending order");
        File[] files = new File(PATH).listFiles();          //Pulls all file names from the files folder
        Set<String> sorted = new TreeSet<>();               //Sorts as added to TreeSet

        assert files != null : "Files folder is empty! Please add files first";       //Makes sure that the files array is not empty
        for(File file: files) {
            if(!file.isFile()) {
                continue;
            }
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("---------------------");
    }

//    =================================
//    |         CREATES FILE          |
//    =================================

    public static boolean addFile(String fileName) {
        boolean added = false;
        Path filePath = Paths.get(PATH+fileName);       //Creates file path
        if(!Files.exists(filePath)) {
            try{
                File file = new File(String.valueOf(filePath));
                if(file.createNewFile()) {                   //Creates file
                    added = true;
                }
            }catch(IOException e) {
                System.out.println("File cannot be created. See console error log");
                e.printStackTrace();
            }
        }
        return added;
    }

//    =================================
//    |         DELETES FILE          |
//    =================================

    public static boolean deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(PATH + fileName);
        return Files.deleteIfExists(filePath);
    }

//    =================================
//    |        SEARCHES FILE          |
//    =================================

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
