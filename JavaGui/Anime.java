package lecture_1_2st;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Anime extends JPanel implements ActionListener {
	private Timer timer;
	private BufferedImage image;
	private final int START_X = 0;
	private final int START_Y = 250;
	private int x,y;
	public Anime() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(500,300));
		this.setDoubleBuffered(true); //�� ����?
		
		File file = new File("spaceship.jpg");
		
		try {
			image = ImageIO.read(file);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1); //������ ������ ������ ����X?
		}
			
		x = START_X;
		y = START_Y;
		
		timer = new Timer(20, this);
		timer.start(); //Ÿ�̸� 20�� ����
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, this); //x,y ��ġ�� �׸�
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		x += 1;
		y -= 1;
		repaint(); //action�� ȣ��� ������ x,y�� ��ǥ�� 1�� ������ ��
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Anime());
		frame.setTitle("�ִϸ��̼� �׽�Ʈ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true); //�����г��� �����ϴ� ������?
	}
}
