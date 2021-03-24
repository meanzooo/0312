package lecture_1_2st;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniPingPongGameEditVer extends JPanel implements KeyListener {
	private Ball ball;
	protected Score score;
	private Racquet racquet1;
	private Racquet racquet2;
	
	public MiniPingPongGameEditVer() {
		ball = new Ball(this, Color.white);
		this.setBackground(Color.black);
		racquet1 = new Racquet(this,10,150,Color.blue);
		racquet2 = new Racquet(this,560,150,Color.red);
		score = new Score(600,400);
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}
	
	void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		score.draw(g2d);
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
	}
	
	public class Score {
		private int GAME_WIDTH;
		private int GAME_HEIGHT;
		protected int player1;
		protected int player2;
		
		public Score(int gameWidth, int gameHeight) {
			GAME_WIDTH = gameWidth;
			GAME_HEIGHT = gameHeight;
		}
		
		public void draw(Graphics g) {
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			
			g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT); //줄의 시작점의 x,y와 끝점의 x,y
			
			g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),GAME_WIDTH/2-85,50);
			//첫자리수,끝자리수,점수판의 위치(x,y)
			g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),GAME_WIDTH/2+20,50);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pingpong Game");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		MiniPingPongGameEditVer game = new MiniPingPongGameEditVer();
		frame.add(game);
		frame.setVisible(true);
		while (true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Ball {
		private static final int RADIUS = 20;
		private int x=0, y=0, xSpeed=1, ySpeed=1;
		private MiniPingPongGameEditVer game;
		private Color color;
		
		public Ball(MiniPingPongGameEditVer game, Color color) {
			this.game = game;
			this.color = color;
		}
		
		void move() {
			if (x+xSpeed < 0) //왼벽튕김
				do {
					score.player2 += 1;
					xSpeed = 1;
				} while (xSpeed!=1);
			if (x+xSpeed > game.getWidth() - 2 * RADIUS) //오른벽튕김
				do {
					score.player1 += 1;
					xSpeed = -1;
				} while (xSpeed!=-1);
			if (y+ySpeed < 0) //윗벽튕김
				ySpeed = 1;
			if (y+ySpeed > game.getHeight() - 2 * RADIUS) //아랫벽튕김
				ySpeed = -1;
			if (collision()) {
				xSpeed = -xSpeed;
			}
				
			x += xSpeed;
			y += ySpeed;
		}
		
		private boolean collision() {
			/*
			 * Rectangle r1 = game.racquet1.getBounds();
			 * Rectangle r2 = game.racquet2.getBounds();
			 * Rectangle myr = getBounds();
			 * boolean r1c = r1.intersects(myr);
			 * boolean r2c = r2.intersects(myr);
			 * 
			 * return r1c || r2c;
			 * 
			 * 라켓1과 2의 것을 나한테로 가져와서 비교.
			 */
			return game.racquet1.getBounds().intersects(getBounds())
					|| game.racquet2.getBounds().intersects(getBounds());
		}
		
		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillOval(x, y, 2*RADIUS, 2*RADIUS);
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, 2*RADIUS, 2*RADIUS);
		}
	}
	
	class Racquet {
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int x=0 , y =0;
		private int xSpeed = 0;
		private int ySpeed = 0;
		private MiniPingPongGameEditVer game;
		private Color color;
		
		public Racquet(MiniPingPongGameEditVer game, int x, int y, Color color) {
			this.game = game;
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
		public void move() {
			if (y+ ySpeed> 0 && y+ySpeed < game.getHeight() - HEIGHT) 
				y += ySpeed;
		}
		
		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
		
		public void keyReleased(KeyEvent e) {
			ySpeed = 0;
		}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				racquet2.ySpeed = -3;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				racquet2.ySpeed = 3;
			else if (e.getKeyCode() == KeyEvent.VK_W)
				racquet1.ySpeed = -3;
			else if (e.getKeyCode() == KeyEvent.VK_S)
				racquet1.ySpeed = 3;
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x,y,WIDTH,HEIGHT);
		}
	}
}