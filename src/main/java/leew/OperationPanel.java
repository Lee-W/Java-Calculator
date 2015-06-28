package leew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationPanel extends JPanel implements ActionListener{
    private String equation = "";
    private String curToken = "";
    private List<String> equationList = new ArrayList<>(); 
    
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
            equation = " ";
            operIsEnd = false;
        }

        switch (content) {
            case "0":
                equation += content;
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
            case ".":
                equation += content;
                break;
            
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                equation += " " + content + " ";
                break;
            
            case "exp":
                equation += " ^ ";
                break;
            
            case "√":
            case "!":
            case "log":
                equation += " " + content + " ";
                break;
            
            case "+/-":
                break;
            
            case "=":
                String originEq = equation;
                equation = Cal.calculate(equation).toString();
                frame.addHistory(originEq+" = "+ equation);
                operIsEnd = true;
                break;
            
            case "CE":
                try {
                    equation = equation.substring(0, equation.lastIndexOf(" ")+1);
                } catch (Exception ee) {
                    equation = " ";
                }
                break;
            
            case "C":
                equation = " ";
                break;
            
            case "←":
                int index = 0;
                for (int i = equation.length()-1; i > -1; i--)
                    if (equation.charAt(i) != ' ') {
                        index = i;
                        break;
                    }
                
                equation = equation.substring(0, index);
                break;
        }
        
        System.out.println(equation);
        frame.updateEquation(equation);
    }
}
