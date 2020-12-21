package calculator;

import java.util.Scanner;

import Calculator.operations.AddOperation;
import Calculator.operations.DivideOperation;
import Calculator.operations.MultiplyOperation;
import Calculator.operations.SubtractOperation;

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

        sc.close();
        switch(operation) {
            case 1:
                AddOperation add = new AddOperation();
                add.setNum1(num1);
                add.setNum2(num2);
                System.out.println("Result:" + add.calculate());
                break;
            case 2:
                SubtractOperation sub = new SubtractOperation();
                sub.setNum1(num1);
                sub.setNum2(num2);
                System.out.println("Result: " + sub.calculate());
                break;
            case 3:
                MultiplyOperation mult = new MultiplyOperation();
                mult.setNum1(num1);
                mult.setNum2(num2);
                System.out.println("Result: " + mult.calculate());
                break;
            case 4:
                DivideOperation div = new DivideOperation();

                if(num2 == 0) {
                    System.out.println("Illegal Operation");
                    break;
                }else{
                    div.setNum1(num1);
                    div.setNum2(num2);
                    System.out.println("Result: " + div.calculate());
                }
                break;
        }
    }
}
