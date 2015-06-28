package leew.Operation;

/**
 * Created by LeeW on 6/27/15.
 */
public abstract class Operation {
    protected double firstNum;
    protected double secondNum;

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }
    
    public abstract double getResult();
}
