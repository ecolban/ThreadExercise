package com.drawmetry.erik.ringbuffer;


public class Util {
	public static void stayBusy(long time) {
		long finishTime = System.currentTimeMillis() + time;
		while(System.currentTimeMillis() < finishTime) {
			Thread.yield();
		}
	}
}
