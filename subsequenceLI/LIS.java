package subsequenceLI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LIS {

    public static int[] root;
    public static ArrayList<Integer> finalList = new ArrayList<>();
    public static int size;

    public LIS(int[] root) {
        LIS.root = root;
    }

    public static void populateLIS(int length) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < length; i++) {
            System.out.printf("Please input number %d: ", i+1);
            root[i] = sc.nextInt();
        }
    }

    public static void randomPopulateLIS(int length){
        for(int i = 0; i < length; i++) {
            root[i] = (int)(Math.random()*100);
            System.out.println(root[i]);
        }
    }

    public static ArrayList<Integer> findLIS (int length){
        ArrayList<Integer> currentLIS = new ArrayList<>();
        ArrayList<Integer> finalLIS = new ArrayList<>();
        int longest = 0;

        for(int i = 0; i < length; i++) {
            int localMax = root[i];
            int localLength = 1;
            currentLIS.add(root[i]);
            for (int j = i; j < length; j++) {
                if (root[j] > localMax) {
                    localLength++;
                    currentLIS.add(root[j]);
                    localMax = root[j];
                }
            }

            if (localLength > longest) {
                finalLIS.clear();
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

    public static void printLIS() {
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
