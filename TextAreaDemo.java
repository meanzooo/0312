package lecture_1_2st;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAreaDemo extends JFrame implements ActionListener {
	private JTextField textField;
	private JTextArea textArea;
	
	public TextAreaDemo() {
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField(30); //입력창
		textField.addActionListener(this);
		
		textArea = new JTextArea(10,30);
		textArea.setEditable(false);
		
		this.add(textField, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText(); //입력 값을 저장
		textArea.append(text+"\n");
		
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());
		//문장의 길이를 받고 커서가 그 뒤에 깜빡이게 함
	}
	
	public static void main(String[] args) {
		new TextAreaDemo();
	}
}
