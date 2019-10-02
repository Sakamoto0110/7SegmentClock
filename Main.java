package com.sakamoto.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sakamoto.sketch.Sketch;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	// ** //
	public JFrame frame;
	public static int WIDTH = 900;
	public static int HEIGHT = 400;
	public static int SCALE = 1;
	private boolean isRunning;
	private Thread thread;
	public static int red = 255;
	public static int blue = 255;
	public static int green = 255;
	public static int alpha = 255;
	// ** //
	private BufferedImage background;

	//
	public static Sketch sDefault;

	public Main() {
		setPreferredSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
		isRunning = true;
		initFrame();

		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//
		sDefault = new Sketch();
		//

	}

	public void tick() {
		sDefault.tick();

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = background.getGraphics();
		g.setColor(new Color(red, green, blue, alpha));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// **
		sDefault.render(g);

		// **
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(background, 0, 0, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE), null);
		bs.show();
	}

	public static void Background(int _r, int _g, int _b, int _a) {
		red = _r;
		green = _g;
		blue = _b;
		alpha = _a;
	}

	public static void Background(int _r, int _g, int _b) {
		red = _r;
		green = _g;
		blue = _b;

	}

	public static void Background(int _gray) {
		red = _gray;
		green = _gray;
		blue = _gray;
	}

	public static void Background(int _gray, int _a) {
		red = _gray;
		green = _gray;
		blue = _gray;
		alpha = _a;
	}

	public static void main(String[] args) {
		Main game = new Main();
		game.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amoutOfTicks = 60;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void initFrame() {
		frame = new JFrame("7 Segment clock - RGB DEMO");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
