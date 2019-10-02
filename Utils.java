package com.sakamoto.clock;

public class Utils {
	Utils(){
		
	}
	public static float map(float X, float A, float B, float C, float D) {
		return (X - A) / (B - A) * (D - C) + C;
	}
}
