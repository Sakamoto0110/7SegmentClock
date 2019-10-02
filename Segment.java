package com.sakamoto.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

class Segment {

	float x, y, xx, yy, t = 5;
	boolean state = false;
	Properties props;
	float red, green, blue;

	public Segment(float x, float y, float xx, float yy, Properties props) {
		this.x = x;
		this.y = y;
		this.xx = xx;
		this.yy = yy;
		this.props = props;
		t = props.t;
	}

	public void setRGB(float r_, float g_, float b_) {
		red = r_;
		green = g_;
		blue = b_;
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke stroke = new BasicStroke(t, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		BasicStroke stroke1 = new BasicStroke(t * 1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);
		g2.setColor(new Color(100, 100, 100, 0));
		if (state) {
			g2.setColor(new Color((int) red, (int) green, (int) blue, 250));
		}
		g2.setStroke(stroke1);
		g2.drawLine((int) x, (int) y, (int) xx, (int) yy);
		if (state) {
			g2.setColor(new Color((int) red, (int) green, (int) blue, 100));
		}
		g2.setStroke(stroke);
		g2.drawLine((int) x, (int) y, (int) xx, (int) yy);
	}
}
