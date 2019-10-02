package com.sakamoto.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Clock {
	float scale;

	int[] time;
	SegDisplay display[];
	float x, sx;
	float y, sy;
	float Width, sWidth;
	float Height, sHeight;
	float t = 0;
	Properties props;

	public Clock(float x, float y, Properties props) {
		this.x = x - props.size;
		this.y = y - props.size;
		this.scale = props.size;
		this.sWidth = this.x + 7 * scale + 7 * (scale * .5f);
		this.sHeight = this.y + 2 * scale + 2 * (scale);
		this.Width = this.x + 6 * scale + 7 * (scale * .5f);
		this.Height = this.y + 1 * scale + 1 * (scale * 2);
		this.sx = this.x + sWidth - Width;
		this.sy = this.y + sHeight - Height;
		this.time = new int[6];
		this.props = props;
		this.display = new SegDisplay[6];
		for (int c = 0; c < 6; c++) {
			time[c] = 0;
			display[c] = new SegDisplay(this.x + (c * scale * 1.5f), this.y, props);
		}
	}

	public void setTime(int[] nTime) {
		int c1 = 0;
		for (int c = 5; c >= 0; c--) {
			time[c1] = nTime[c];
			c1++;
			updateSeg();
		}
	}

	public void updateSeg() {
		display[5].setCode(time[0]);
		display[4].setCode(time[1]);
		display[3].setCode(time[2]);
		display[2].setCode(time[3]);
		display[1].setCode(time[4]);
		display[0].setCode(time[5]);
	}

	public void tick() {
		// 0 1 sec
		// 2 3 min
		// 4 5 hour
		updateSeg();
		time[0]++;
		if (time[0] > 9) {
			time[0] = 0;
			time[1]++;
		}
		if (time[1] > 5) {
			time[1] = 0;
			time[2]++;
		}
		if (time[2] > 9) {
			time[2] = 0;
			time[3]++;
		}
		if (time[3] > 5) {
			time[3] = 0;
			time[4]++;
		}
		if (time[4] > 9) {
			time[4] = 0;
			time[5]++;
		}
		if (time[5] == 2) {
			if (time[4] > 3) {
				time[5] = 0;
				time[4] = 0;
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke stroke = new BasicStroke(t);
		g2.setStroke(stroke);
		for (int c = 0; c < 6; c++) {
			display[c].render(g);
		}
		g2.setColor(new Color(0, 255, 0));
		stroke = new BasicStroke(props.t * 2);
		float xx = (x + ((sWidth - x) / 3)) + scale * .25f;
		float yy = y + 1 * scale + 1 * (scale);
		g2.fillOval((int) xx, (int) (yy + scale * .5f), (int) props.t * 2, (int) props.t * 2);
		g2.fillOval((int) xx, (int) (yy - scale * .5f), (int) props.t * 2, (int) props.t * 2);
		xx = x + (sWidth - xx);
		g2.fillOval((int) xx, (int) (yy + scale * .5f), (int) props.t * 2, (int) props.t * 2);
		g2.fillOval((int) xx, (int) (yy - scale * .5f), (int) props.t * 2, (int) props.t * 2);
	}
}
