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
		this.setDoubleBuffered(true); //판 생성?
		
		File file = new File("spaceship.jpg");
		
		try {
			image = ImageIO.read(file);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1); //파일이 읽히지 않으면 실행X?
		}
			
		x = START_X;
		y = START_Y;
		
		timer = new Timer(20, this);
		timer.start(); //타이머 20초 실행
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, this); //x,y 위치로 그림
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		x += 1;
		y -= 1;
		repaint(); //action이 호출될 때마다 x,y의 좌표를 1씩 변경해 감
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Anime());
		frame.setTitle("애니메이션 테스트");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setVisible(true); //마이패널을 참고하는 실행판?
	}
}
