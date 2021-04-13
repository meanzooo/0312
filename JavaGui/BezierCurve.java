package lecture_1_2st;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;

public class BezierCurve extends JPanel implements MouseListener, MouseMotionListener {
	private int[] xs = {50,150,400,450};
	private int[] ys = {200,50,300,200};
	
	private int drageIndex = -1;
	
	public BezierCurve() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xs[0],ys[0],15,15);
		g.fillRect(xs[2],ys[2],15,15);
		
		g.setColor(Color.red);
		g.fillRect(xs[1],ys[1],15,15);
		g.fillRect(xs[3],ys[3],15,15);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		GeneralPath path = new GeneralPath();
		path.moveTo(xs[0], ys[0]);
		path.curveTo(xs[1], ys[1], xs[2], ys[2], xs[3], ys[3]);
		
		g2d.draw(path);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		drageIndex = -1;
		//4개의 점 중 어떤 것을 찍었는지 검사. -1이면 아무것도 안 건든 상태.
		e.getX(); e.getY();
		
		for(int i=0; i<4; i++) {
			Rectangle r = new Rectangle(xs[i]-4,ys[i]-4,20,20);
			// 점 선택 판정 범위를 넓히기 위해 20,20 으로 설정
			if (r.contains(e.getX(),e.getY())==true) {
				drageIndex = i;
				//영역을 사용자가 선택
				break;
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//마우스를 뗐을 때
		drageIndex = -1;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { 
		//안 누르고 움직이면 무브, 누르고 움직이면 드래그
		if (drageIndex != -1) {
			xs[drageIndex] = e.getX();
			ys[drageIndex] = e.getY();
		}
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e){}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new BezierCurve());
		frame.setTitle("베지얼 곡선");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true);
	}
}