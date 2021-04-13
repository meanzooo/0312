package lecture_1_2st;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GradientPanel extends MyPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		GradientPaint gp = new GradientPaint(0,10, Color.white, 0,70, Color.red);
			//시작은 하양, 끝은 빨강으로 끝내라
		for(Shape s : shapeArray) {
			g2.setPaint(gp);
			//Color.red로 하면 걍 빨강
			g2.fill(s);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(610,140);
		frame.setTitle("Java 2D Shapes");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new GradientPanel());
		frame.setVisible(true);
	}
}
