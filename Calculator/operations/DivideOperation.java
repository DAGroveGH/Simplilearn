package Calculator.operations;

public class DivideOperation {
    double num1 = 0;
    double num2 = 0;

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public double calculate() {
        return num1 / num2;
    }
}
