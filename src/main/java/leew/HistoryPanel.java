package leew;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by LeeW on 6/27/15.
 */
public class HistoryPanel extends JPanel {
    private static final URL PIC_PATH = ClassLoader.getSystemClassLoader().getResource("img/sauce.jpg");

    private String historyData = "";
    private boolean historyIsEnabled = true;

    private JButton showPictureButton = new JButton();
    private JButton showHistoryButton = new JButton();
    private JButton clearHistoryButton = new JButton();

    private JScrollPane jScrollPane = new JScrollPane();
    private JTextArea historyArea = new JTextArea(10, 30);
    private JLabel picLabel = new JLabel();

    public HistoryPanel() {
        this.setLayout(new GridBagLayout());
        createUIComponents();
        addComponents();
    }
    
    private void createUIComponents() {
        showPictureButton.setText("Show Picture");
        showPictureButton.addActionListener(listener -> {
            jScrollPane.setViewportView(picLabel);
            historyIsEnabled = false;
        });

        showHistoryButton.setText("Show History");
        showHistoryButton.addActionListener(listener -> {
            historyArea.setText(historyData);
            jScrollPane.setViewportView(historyArea);
            historyIsEnabled = true;
        });

        clearHistoryButton.setText("Clear History");
        clearHistoryButton.addActionListener(actionListener -> {
            historyData = "";
            historyArea.setText(historyData);
        });

        ImageIcon imageIcon = new ImageIcon(PIC_PATH);
        picLabel.setIcon(imageIcon);
        picLabel.setBounds(100, 100, 100, 100);

        historyArea.setBounds(100, 100, 100, 100);
        historyArea.setEnabled(false);

        jScrollPane.setViewportView(historyArea);
        jScrollPane.setBounds(100, 100, 100, 100);
    }

    private void addComponents() {
        GridBagConstraints showPicBtnPos = new GridBagConstraints();
        showPicBtnPos.gridx = 0;
        showPicBtnPos.gridy = 0;
        showPicBtnPos.gridwidth = 1;
        showPicBtnPos.gridheight = 1;
        this.add(showPictureButton, showPicBtnPos);

        GridBagConstraints showHistoryBtnPos = new GridBagConstraints();
        showHistoryBtnPos.gridx = 1;
        showHistoryBtnPos.gridy = 0;
        showHistoryBtnPos.gridwidth = 1;
        showHistoryBtnPos.gridheight = 1;
        this.add(showHistoryButton, showHistoryBtnPos);

        GridBagConstraints clearHistoryBtnPos = new GridBagConstraints();
        clearHistoryBtnPos.gridx = 2;
        clearHistoryBtnPos.gridy = 0;
        clearHistoryBtnPos.gridwidth = 1;
        clearHistoryBtnPos.gridheight = 1;
        this.add(clearHistoryButton, clearHistoryBtnPos);

        GridBagConstraints scrollPanePos = new GridBagConstraints();
        scrollPanePos.gridx = 0;
        scrollPanePos.gridy = 1;
        scrollPanePos.gridwidth = 3;
        scrollPanePos.gridheight = 6;
        this.add(jScrollPane, scrollPanePos);
    }

    public void addHistory(String equation) {
        historyData += equation + "\n";
        if (historyIsEnabled)
            historyArea.setText(historyData);
    }
}
