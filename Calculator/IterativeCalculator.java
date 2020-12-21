package Calculator;

import java.util.Scanner;

public class IterativeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please input a number");
        int num1 = sc.nextInt();

        System.out.println("Please input a number");
        int num2 = sc.nextInt();

        System.out.println("Please select an operation:");
        System.out.println("1 - Addition");
        System.out.println("2 - Subtraction");
        System.out.println("3 - Multiplication");
        System.out.println("4 - Division");
        int operation = sc.nextInt();
    }
}
