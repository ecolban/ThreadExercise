package com.drawmetry.erik.ringbuffer.simpleapp;

import java.util.concurrent.ThreadLocalRandom;

import com.drawmetry.erik.ringbuffer.BufferInterface;

/**
 * A Producer adds repeatedly random numbers to a buffer.
 * 
 * @author ecolban
 * 
 */
public class Producer implements Runnable {

	private final BufferInterface buffer;

	/**
	 * Constructs an instance of a producer.
	 * 
	 * @param buffer
	 *            the buffer to which this producers adds numbers.
	 */
	public Producer(BufferInterface buffer) {
		this.buffer = buffer;
	}

	/**
	 * Adds random numbers between 1 and 10 to a buffer until stopped
	 */
	public void run() {
		int item = 0;
		try {
			while (true) {
				item = produce();
				buffer.add(item);
			}
		} catch (InterruptedException ex) {
			System.out.println(Thread.currentThread().getName() + " was interrupted.");
		}
	}

	private int produce() {
		// produce..., produce...
		long start = System.currentTimeMillis();
		int t = ThreadLocalRandom.current().nextInt(80);
		while(System.currentTimeMillis() < start + t) {
		}
		return ThreadLocalRandom.current().nextInt(10);
	}

}
