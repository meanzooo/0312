package lecture_1_2st;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboBoxDemo extends JFrame implements ActionListener {
	private JLabel label;
	private JComboBox animalList;
	
	public ComboBoxDemo() {
		this.setTitle("ÄÞº¸ ¹Ú½º");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500,400);
		
		String[] animals = {"dog", "lion", "tiger"};
		animalList = new JComboBox(animals);
		animalList.setSelectedIndex(0);
		animalList.addActionListener(this);
		
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		changePicture(animals[animalList.getSelectedIndex()]);
		
		this.add(animalList, BorderLayout.NORTH);
		this.add(label,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = (String) animalList.getSelectedItem();
		changePicture(name);
	}
	
	protected void changePicture(String name) {
		ImageIcon icon = new ImageIcon(name+".gif");
		label.setIcon(icon);
		
		label.setText(null);
	}
	
	public static void main(String[] args) {
		new ComboBoxDemo();
	}
}
