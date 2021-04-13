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
				//다른 자료형으로 값 부여 가능
			shapeArray.add(new Rectangle2D.Float(10,10,70,80));
				//시작점이 x10,y10. 폭 70. 높이 80
			shapeArray.add(new RoundRectangle2D.Float(110,10,70,80,20,20));
				//둥근 사각형. 위랑 같은데 뒤에 2020은 머지
			shapeArray.add(new Ellipse2D.Float(210,10,80,80));
				//타원. 80은 차례로 폭과 넓이
			shapeArray.add(new Arc2D.Float(310,10,80,80,90,90,Arc2D.OPEN));
				//원호, 9090은 각도? arc2d.open은 해당 부분 외의 것들은 다 열어둔다는 의미
			shapeArray.add(new Arc2D.Float(410,10,80,80,0,180,Arc2D.CHORD));
				//반원 모양이 됨
			shapeArray.add(new Arc2D.Float(510,10,80,80,45,45,Arc2D.PIE));
				//pie-양 끝점과 중심점을 이음
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