package Calculator.operations;

public class SubtractOperation {
    int num1 = 0;
    int num2 = 0;

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int calculate() {
        return num1 - num2;
    }
}