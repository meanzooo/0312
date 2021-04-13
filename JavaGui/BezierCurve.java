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
		//4���� �� �� � ���� ������� �˻�. -1�̸� �ƹ��͵� �� �ǵ� ����.
		e.getX(); e.getY();
		
		for(int i=0; i<4; i++) {
			Rectangle r = new Rectangle(xs[i]-4,ys[i]-4,20,20);
			// �� ���� ���� ������ ������ ���� 20,20 ���� ����
			if (r.contains(e.getX(),e.getY())==true) {
				drageIndex = i;
				//������ ����ڰ� ����
				break;
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//���콺�� ���� ��
		drageIndex = -1;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { 
		//�� ������ �����̸� ����, ������ �����̸� �巡��
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
		frame.setTitle("������ �");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true);
	}
}