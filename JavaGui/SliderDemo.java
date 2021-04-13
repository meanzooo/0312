package lecture_1_2st;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

import java.awt.event.*;

public class SliderDemo extends JFrame implements ChangeListener {
	static final int INIT_VALUE = 15;
	private JButton btn;
	private JSlider slider;
	
	public SliderDemo() {
		JPanel panel;
		
		this.setTitle("슬라이더 테스트");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		JLabel label = new JLabel("슬라이더를 움직여보세요",SwingConstants.CENTER);
		// label.setAligmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		
		slider = new JSlider(0,30,INIT_VALUE);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		panel.add(slider);
		
		btn = new JButton("");
		ImageIcon icon = new ImageIcon("cat.jpg");
		btn.setIcon(icon);
		btn.setSize(INIT_VALUE * 10, INIT_VALUE *10);
		panel.add(btn);
		
		this.add(panel);
		this.setSize(350,500);
		this.setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) { //드래그 할 때
			int value = source.getValue();
			btn.setSize(value*10, value*10);
		}
	}
	
	public static void main(String[] argsq) {
		new SliderDemo();
	}
}
