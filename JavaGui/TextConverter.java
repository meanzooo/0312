package lecture_1_2st;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class TextConverter extends JFrame implements ActionListener {
    private JButton convertBtn;
    private JButton cancelBtn;
    private JTextArea textIn;
    private JTextArea textOut;

    public TextConverter() {
        super("텍스트 변환");

        textIn = new JTextArea(10, 14);
        textOut = new JTextArea(10, 14);
        textIn.setLineWrap(true);
        textOut.setLineWrap(true);
        textOut.setEditable(false);

        JPanel textAreaPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        textAreaPanel.add(textIn);
        textAreaPanel.add(textOut);

        convertBtn = new JButton("변환");
        cancelBtn = new JButton("취소");
        convertBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        JPanel btnPanel = new JPanel();
        btnPanel.add(convertBtn);
        btnPanel.add(cancelBtn);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(textAreaPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertBtn) {
            textOut.setText("");
            String result = toEnglish(textIn.getText());
            textOut.append(result);
        }

        if (e.getSource() == cancelBtn) {
            textOut.setText("");
        }
    }

    private String toEnglish(String korean) {
        String result = korean;
        result = result.replace("텍스트", "text");
        result = result.replace("영어", "english");
        return result;
    }
    
    public static void main(String[] args) {
        new TextConverter();
    }
}