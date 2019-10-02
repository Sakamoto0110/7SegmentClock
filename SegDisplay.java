package com.sakamoto.clock;

import java.awt.Graphics;
import java.util.Random;

public class SegDisplay {
	Segment seg[];
	int[] code;
	public int[] zero  = { 1, 1, 1, 1, 1, 1, 0 };
	public int[] one   = { 0, 1, 1, 0, 0, 0, 0 };
	public int[] two   = { 1, 1, 0, 1, 1, 0, 1 };
	public int[] three = { 1, 1, 1, 1, 0, 0, 1 };
	public int[] four  = { 0, 1, 1, 0, 0, 1, 1 };
	public int[] five  = { 1, 0, 1, 1, 0, 1, 1 };
	public int[] six   = { 1, 0, 1, 1, 1, 1, 1 };
	public int[] seven = { 1, 1, 1, 0, 0, 0, 0 };
	public int[] eight = { 1, 1, 1, 1, 1, 1, 1 };
	public int[] nine  = { 1, 1, 1, 1, 0, 1, 1 };
	public float x, y, scale;
	public Properties props;
	public Random rand = new Random();
	float red, green, blue;
	float rx = rand.nextFloat() * 100;
	float gx = rand.nextFloat() * 100;
	float bx = rand.nextFloat() * 100;

	public SegDisplay(float x, float y, Properties props) {
		seg = new Segment[7];
		this.scale = props.size;
		this.props = props;
		seg[0] = new Segment(x + 1 * scale + 1 * scale * 0.1f, y + 1 * scale + 0 * scale * 0.1f,
				x + 2 * scale - 1 * scale * 0.1f, y + 1 * scale + 0 * scale * 0.1f, props); // v
		
		seg[1] = new Segment(x + 2 * scale + 0 * scale * 0.1f, y + 1 * scale + 1 * scale * 0.1f,
				x + 2 * scale + 0 * scale * 0.1f, y + 2 * scale - 1 * scale * 0.1f, props); // h
		
		seg[2] = new Segment(x + 2 * scale + 0 * scale * 0.1f, y + 2 * scale + 1 * scale * 0.1f,
				x + 2 * scale + 0 * scale * 0.1f, y + 3 * scale - 1 * scale * 0.1f, props); // h
		
		seg[3] = new Segment(x + 2 * scale - 1 * scale * 0.1f, y + 3 * scale + 0 * scale * 0.1f,
				x + 1 * scale + 1 * scale * 0.1f, y + 3 * scale + 0 * scale * 0.1f, props); // v
		
		seg[4] = new Segment(x + 1 * scale + 0 * scale * 0.1f, y + 3 * scale - 1 * scale * 0.1f,
				x + 1 * scale + 0 * scale * 0.1f, y + 2 * scale + 1 * scale * 0.1f, props); // h
		
		seg[5] = new Segment(x + 1 * scale + 0 * scale * 0.1f, y + 2 * scale - 1 * scale * 0.1f,
				x + 1 * scale + 0 * scale * 0.1f, y + 1 * scale + 1 * scale * 0.1f, props); // h
		
		seg[6] = new Segment(x + 1 * scale + 1 * scale * 0.1f, y + 2 * scale + 0 * scale * 0.1f,
				x + 2 * scale - 1 * scale * 0.1f, y + 2 * scale + 0 * scale * 0.1f, props); // v
		
		code = new int[7];
		code = zero;
		this.x = x;
		this.y = y;
	}

	public void setCode(int c) {
		if (c >= 0 && c <= 9) {
			switch (c) {
			case 0:
				this.code = zero;
				break;
			case 1:
				this.code = one;
				break;
			case 2:
				this.code = two;
				break;
			case 3:
				this.code = three;
				break;
			case 4:
				this.code = four;
				break;
			case 5:
				this.code = five;
				break;
			case 6:
				this.code = six;
				break;
			case 7:
				this.code = seven;
				break;
			case 8:
				this.code = eight;
				break;
			case 9:
				this.code = nine;
				break;
			}
			for (int c1 = 0; c1 < 7; c1++) {
				seg[c1].state = true;
				if (code[c1] == 0) {
					seg[c1].state = false;
				}
			}
		}
	}

	public void render(Graphics g) {
		rx += rand.nextFloat() / 40;
		gx += rand.nextFloat() / 40;
		bx += rand.nextFloat() / 40;
		red = Utils.map((float) Math.sin(rx), -1, 1, 0, 255);
		green = Utils.map((float) Math.sin(gx), -1, 1, 0, 255);
		blue = Utils.map((float) Math.sin(bx), -1, 1, 0, 255);
		for (int c = 0; c < 7; c++) {
			seg[c].state = true;
			seg[c].setRGB(red, green, blue);
			if (code[c] == 0) {
				seg[c].state = false;
			}
		}
		for (Segment seg : seg) {
			seg.render(g);
		}

	}

}
