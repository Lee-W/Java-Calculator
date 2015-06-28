package leew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationPanel extends JPanel implements ActionListener{
    private Deque<String> equation = new ArrayDeque<>(); 
    private String curToken = "";
    
    private boolean disableCalculator = false;
    private boolean dotExisted = false;
    
    private boolean operIsEnd = false;
    private boolean isFirstDigit = true;
    
    private CalculatorFrame frame;
    
    private JButton clearButton = new JButton();
    private JButton backspaceButton = new JButton();
    private JButton equalButton = new JButton();
    private JButton dotButton = new JButton();
    private JButton ceButton = new JButton();
    
    private JButton[] numButtons = new JButton[10];

    // Memory Operation
    private JButton mcButton = new JButton();
    private JButton mrButton = new JButton();
    private JButton mPlusButton = new JButton();
    private JButton mMinusButton = new JButton();
    private JButton msButton = new JButton();

    // Binary Operation
    private JButton plusButton = new JButton();
    private JButton minusButton = new JButton();
    private JButton divideButton = new JButton();
    private JButton multiplyButton = new JButton();
    private JButton expButton = new JButton();

    // Unary Operation
    private JButton sqrtButton = new JButton();
    private JButton modButton = new JButton();
    private JButton logButton = new JButton();
    private JButton factorialButton = new JButton();
    private JButton invertSignButton = new JButton();
    
    public OperationPanel(CalculatorFrame f) {
        frame = f;
        this.setLayout(new GridLayout(6, 5));
        createUIComponents();
    }
    
    private void createUIComponents() {
        setupNumButtons();
        setupBinaryOpButtons();
        setupUnaryOpButtons();
        setupMemoryButtons();
        setupFunctionButtons();

        addComponents();
    }

    private void setupNumButtons() {
        for (Integer i = 0; i < 10; i++) {
            numButtons[i] = new JButton();
            numButtons[i].setText(i.toString());
            numButtons[i].addActionListener(this);
        }
    }

    private void setupBinaryOpButtons() {
        plusButton.setText("+");
        plusButton.addActionListener(this);
        
        minusButton.setText("-");
        minusButton.addActionListener(this);
        
        divideButton.setText("/");
        divideButton.addActionListener(this);
        
        multiplyButton.setText("*");
        multiplyButton.addActionListener(this);
        
        modButton.setText("%");
        modButton.addActionListener(this);
        
        expButton.setText("exp");
        expButton.addActionListener(this);
    }

    private void setupUnaryOpButtons() {
        sqrtButton.setText("√");
        sqrtButton.addActionListener(this);
        
        logButton.setText("log");
        logButton.addActionListener(this);
        
        factorialButton.setText("!");
        factorialButton.addActionListener(this);
        
        invertSignButton.setText("+/-");
        invertSignButton.addActionListener(this);
    }

    private void setupMemoryButtons() {
        mcButton.setText("MC");
        msButton.setText("MS");
        mrButton.setText("MR");
        mPlusButton.setText("M+");
        mMinusButton.setText("M-");
    }

    private void setupFunctionButtons() {
        ceButton.setText("CE");
        ceButton.addActionListener(this);
        
        clearButton.setText("C");
        clearButton.addActionListener(this);
        
        backspaceButton.setText("←");
        backspaceButton.addActionListener(this);
        
        dotButton.setText(".");
        dotButton.addActionListener(this);


        equalButton.setText("=");
        equalButton.addActionListener(this);
    }

    private void addComponents() {
        this.add(mcButton);
        this.add(mrButton);
        this.add(msButton);
        this.add(mPlusButton);
        this.add(mMinusButton);

        this.add(backspaceButton);
        this.add(ceButton);
        this.add(clearButton);
        this.add(invertSignButton);
        this.add(sqrtButton);

        this.add(numButtons[7]);
        this.add(numButtons[8]);
        this.add(numButtons[9]);
        this.add(divideButton);
        this.add(modButton);

        this.add(numButtons[4]);
        this.add(numButtons[5]);
        this.add(numButtons[6]);
        this.add(multiplyButton);
        this.add(factorialButton);

        this.add(numButtons[1]);
        this.add(numButtons[2]);
        this.add(numButtons[3]);
        this.add(minusButton);
        this.add(logButton);

        this.add(numButtons[0]);
        this.add(dotButton);
        this.add(equalButton);
        this.add(plusButton);
        this.add(expButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String content = b.getText();
        
        if (operIsEnd) {
            System.out.println("qweqwe");
            equation.clear();
            operIsEnd = false;
        }
        
        if (disableCalculator && content != "C")
            return ;

        switch (content) {
            case "0":
                // Ensure that leading digit is not zero
                if (curToken.length() == 0 || !isFirstDigit) {
                    curToken += content;
                }
                break;
            
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                // Replace the leading zero to input digit
                if (curToken.length() == 1 && curToken.charAt(0) == '0') {
                    curToken = content;
                } else {
                    curToken += content;
                }
                isFirstDigit = false;
                break;
            
            case ".":
                if (curToken.length() == 0) {
                    // Ensure dot not leads a number
                    curToken = "0.";
                } else if (!dotExisted) {
                    // Ensure only one dot exist
                    curToken += content;
                }
                isFirstDigit = false;
                dotExisted = true;
                
                break;
            
            // Binary Operation
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                if (curToken == "") {
                    equation.removeLast();
                    equation.add(content);
                } else {
                    if (curToken.charAt(curToken.length()-1) == '.')
                        curToken += "0";
                    equation.add(curToken);
                    equation.add(content);
                    curToken = "";
                }
                resetStatus();
                break;
            
            case "exp":
                if (curToken == "") {
                    equation.removeLast();
                    equation.add("^");
                } else {
                    if (curToken.charAt(curToken.length()-1) == '.')
                        curToken += "0";
                    equation.add(curToken);
                    equation.add("^");
                    curToken = "";
                }
                resetStatus();
                break;
            
            // Unary Operation
            case "√":
                if (curToken.length() > 0) {
                    if (curToken.charAt(curToken.length()-1) == '.')
                        curToken += "0";
                    curToken = "sqrt("+curToken+")";
                }
                break;
            
            case "!":
                if (curToken.length() > 0 && Cal.isPositive(curToken)) {
                    if (curToken.charAt(curToken.length()-1) == '.')
                        curToken += "0";
                    curToken = "factorial("+curToken+")";
                }
                break;
            
            case "log":
                if (curToken.length() > 0) {
                    if (curToken.charAt(curToken.length()-1) == '.')
                        curToken += "0";
                    curToken = "log("+curToken+")";
                }
                break;
            
            // Invert
            case "+/-":
                if (Cal.isNumeric(curToken)) {
                    curToken = Cal.invert(curToken);
                }
                break;
            
            // Equal
            case "=":
                if (curToken.length() > 0) {
                    equation.add(curToken);
                    curToken = "";
                } else if (equation.size() > 0)
                    equation.removeLast();

                System.out.println("Calculate Equation: "+equation);
                String originEq = equationJoin();
                String result = Cal.calculate(equation).toString();
                
                if (result == "Infinity" || result == "Nan")
                    disableCalculator = true;
                
                
//                frame.addHistory(originEq+" = "+ result);
                operIsEnd = true;
                
                equation.clear();
                equation.add(result);
                resetStatus();
                break;
            
            // Clear Entry
            case "CE":
                if (curToken.length() > 0) {
                    curToken = "";
                }
                break;
            
            // Clear
            case "C":
                curToken = "";
                equation.clear();
                disableCalculator = false;
                resetStatus();
                break;
            
            // BackSpace
            case "←":
                if (curToken.length() > 0) {
                    curToken = curToken.substring(0, curToken.length()-1);
                }
                break;
        }
        System.out.println("CurToken: "+curToken);
        System.out.println("Equation: " + equation);
        frame.updateEquation(equationJoin() + curToken);
    }
    
    private void resetStatus() {
        dotExisted = false;
        isFirstDigit = true;
    }
    
    private String equationJoin() {
        String result = " ";
        for (String token : equation)
            result += token + " ";
        return result;
    }
}
