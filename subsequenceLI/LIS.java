package subsequenceLI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LIS {

    public static int[] root;                                          //Base array
    public static ArrayList<Integer> finalList = new ArrayList<>();    //final LIS
    public static int size;                                            //Size of the Array

    //Driver for the class
    public LIS(int[] root) {
        LIS.root = root;
    }

    //Populates the array using user input
    public static void populateLIS(int length) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < length; i++) {
            System.out.printf("Please input number %d: ", i+1);
            root[i] = sc.nextInt();
        }
    }

    //Populates the array using random numbers
    public static void randomPopulateLIS(int length){
        for(int i = 0; i < length; i++) {
            root[i] = (int)(Math.random()*100);
            System.out.printf("Adding: %d\n", root[i]);
        }
    }

    //Finds the longest increasing subsequence
    public static ArrayList<Integer> findLIS (int length){
        ArrayList<Integer> currentLIS = new ArrayList<>();             //Used to create a sequence
        ArrayList<Integer> finalLIS = new ArrayList<>();               //Used to hold the currently longest sequence
        int longest = 0;                                               //Length of the longest sequence for quick access

        for(int i = 0; i < length; i++) {
            int localMax = root[i];                                    //Stores the highest value that has been passed over so far
            int localLength = 1;                                       //Stores the current length of the sequence
            currentLIS.add(root[i]);
            for (int j = i; j < length; j++) {
                if (root[j] > localMax) {
                    localLength++;
                    currentLIS.add(root[j]);
                    localMax = root[j];
                }
            }

            if (localLength > longest) {                               //Tests if the local array length is longer than the longest one found so far
                finalLIS.clear();                                      //Clears the former data to avoid overpopulation
                longest = localLength;
                for (int j = 0; j < currentLIS.size(); j++) {
                    finalLIS.add(currentLIS.get(j));
                }
                currentLIS.clear();
            }
            currentLIS.clear();
        }
        return finalLIS;
    }

    //Basic iterator to print the LIS
    public static void printLIS(LIS solution) {
        Iterator itr = finalList.iterator();

        System.out.println("The Longest Increasing Subsequence:");

        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }

        System.out.println();
        System.out.println();

        System.out.printf("Length of the Array: %d", finalList.size());
        System.out.println();
    }

}
