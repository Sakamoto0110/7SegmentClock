package com.sakamoto.sketch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import com.sakamoto.clock.Clock;
import com.sakamoto.clock.Properties;
import com.sakamoto.main.Main;

public class Sketch {

	public Clock clock;
	public Properties properties;
	public int c = 60;
	public int maxI = 60;

	public Sketch() {
		Main.Background(0);
		properties = new Properties();
		properties.t = 2.4f;
		properties.size = 80;
		clock = new Clock(100, 100, properties);
		int[] t = { 0, 0, 0, 0, 0, 0 };
		clock.setTime(t);
	}

	public void tick() {
		if (c % maxI == 0) {
			clock.tick();
		}
		c++;
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		clock.render(g);
	}
}
