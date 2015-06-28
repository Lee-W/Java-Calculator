package leew;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LeeW on 6/27/15.
 */
public class HistoryPanel extends JPanel {
    private static final String PIC_PATH = "src/main/resources/img/sauce.jpg";
    private String historyData = "";
    private JScrollPane jScrollPane = new JScrollPane();
    
    private JTextArea displayArea = new JTextArea();
    private JLabel picLabel = new JLabel();
    
    private JButton showPictureButton = new JButton();
    private JButton showHistoryButton = new JButton();
    private JButton clearHistoryButton = new JButton();
    
    public HistoryPanel() {
        this.setLayout(new GridBagLayout());
        createUIComponents();
    }
    
    public void addHistory(String equation) {
        historyData += equation + "\n";
        displayArea.setText(historyData);
    }
    
    private void createUIComponents() {
        showPictureButton.setText("Show Picture");
        showPictureButton.addActionListener(actionListener-> {
            jScrollPane.setViewportView(picLabel);
        });

        showHistoryButton.setText("Show History");
        showHistoryButton.addActionListener(actionListener -> {
            jScrollPane.setViewportView(displayArea);
        });

        clearHistoryButton.setText("Clear History");
        clearHistoryButton.addActionListener(actionListener -> {
            historyData = "";
        });
        
        ImageIcon imageIcon =new ImageIcon(PIC_PATH);
        picLabel.setIcon(imageIcon);
        picLabel.setBounds(100, 100, 100, 100);

        displayArea.setBounds(100, 100, 100, 100);

        jScrollPane.setViewportView(displayArea);
        jScrollPane.setViewportView(picLabel);
        jScrollPane.setBounds(100, 100, 100, 100);
        
        addComponents();
    }
    
    private void addComponents() {
        GridBagConstraints c0 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 0;
        c0.gridwidth = 1;
        c0.gridheight = 1;
        this.add(showPictureButton, c0);
        
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 1;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        this.add(showHistoryButton, c1);
        
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 2;
        c2.gridy = 0;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        this.add(clearHistoryButton, c2);
       
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 1;
        c3.gridwidth = 3;
        c3.gridheight = 6;
        this.add(jScrollPane, c3);
    }
    
    
}
