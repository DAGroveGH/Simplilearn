package subsequenceLI;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input the length of the Array");

        int length = sc.nextInt();
        int[] root = new int[length];
        LIS solution = new LIS(root);

        System.out.println("Please choose which method to populate the array");
        System.out.println("1 - User-populated");
        System.out.println("2 - Randomly populated");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                LIS.populateLIS(length);
                break;
            case 2:
                LIS.randomPopulateLIS(length);
                break;
        }

        System.out.println();
        System.out.println("This is your array: ");

        for(int i = 0; i < length; i++) {
            System.out.printf("%d ", root[i]);
        }

        System.out.println(); //Presentation
        System.out.println();

        LIS.finalList = LIS.findLIS(length); //Finds the longest increasing subsequence
        LIS.printLIS(solution); //Prints the longest increasing subsequence
    }
}
