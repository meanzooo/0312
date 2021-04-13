package lecture_1_2st;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

public class MoreShapes extends JFrame {
	
	public MoreShapes() {
		this.setSize(610,140);
		this.setTitle("Java 2D Shapes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MyPanel();
		this.add(panel);
		this.setVisible(true);
	}
	
	 public static void main(String[] args) {
		 new MoreShapes();
	 }
}

	class MyPanel extends JPanel {	
		ArrayList<Shape> shapeArray = new ArrayList<Shape>();
		
		public MyPanel() {
			//Shape rec = new Rectangle2D.Float(10,70,70,80);
				//�ٸ� �ڷ������� �� �ο� ����
			shapeArray.add(new Rectangle2D.Float(10,10,70,80));
				//�������� x10,y10. �� 70. ���� 80
			shapeArray.add(new RoundRectangle2D.Float(110,10,70,80,20,20));
				//�ձ� �簢��. ���� ������ �ڿ� 2020�� ����
			shapeArray.add(new Ellipse2D.Float(210,10,80,80));
				//Ÿ��. 80�� ���ʷ� ���� ����
			shapeArray.add(new Arc2D.Float(310,10,80,80,90,90,Arc2D.OPEN));
				//��ȣ, 9090�� ����? arc2d.open�� �ش� �κ� ���� �͵��� �� ����дٴ� �ǹ�
			shapeArray.add(new Arc2D.Float(410,10,80,80,0,180,Arc2D.CHORD));
				//�ݿ� ����� ��
			shapeArray.add(new Arc2D.Float(510,10,80,80,45,45,Arc2D.PIE));
				//pie-�� ������ �߽����� ����
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			
			for (Shape s : shapeArray) {
				g2.draw(s);
			}
		}
	}