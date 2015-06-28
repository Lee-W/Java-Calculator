package leew;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LeeW on 6/27/15.
 */
public class CalculatorFrame extends JFrame {
    private JLabel memoryIndicator = new JLabel();
    private JTextField resultField = new JTextField(24);
    
    private HistoryPanel historyPanel = new HistoryPanel();
    private OperationPanel operationPanel = new OperationPanel(this);
    

    public static void main (String[] args) {
        CalculatorFrame frame = new CalculatorFrame();
        frame.setupPanels();
        frame.setVisible(true);
    }
    
    public CalculatorFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
    }
    
    public void setupPanels() {
        memoryIndicator.setText("M");
        memoryIndicator.setVisible(false);
        
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 0;
        c3.gridheight = 1;
        c3.gridwidth = 1;
        this.add(memoryIndicator, c3);
        
        resultField.setText(" ");
        resultField.setSize(100, 100);
        resultField.setEditable(false);
        GridBagConstraints c0 = new GridBagConstraints();
        c0.gridx = 1;
        c0.gridy = 0;
        c0.gridwidth = 4;
        c0.gridheight = 1;
        this.add(resultField, c0);
        
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        c1.gridheight = 6;
        c1.gridwidth = 5;
        this.add(operationPanel, c1);
        
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 6;
        c2.gridy = 0;
        c2.gridheight = 8;
        c2.gridwidth = 6;
        this.add(historyPanel, c2);
        this.pack();
    }
    
    public void updateEquation(String eq) {
        resultField.setText(eq);
    }
    
    public void addHistory(String eq) {
        historyPanel.addHistory(eq);
    }
    
    public void setMemoryStatus(boolean status) {
        memoryIndicator.setVisible(status);
    }
}
