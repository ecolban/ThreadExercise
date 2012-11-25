package com.drawmetry.erik.ringbuffer;

import java.util.Random;

/**
 * A Producer adds repeatedly random numbers to a buffer.
 * 
 * @author ecolban
 * 
 */
public class Producer extends Thread {

	private final BufferInterface buffer;
	private final Random random = new Random();

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
	 * Adds random numbers between 1 and 10 to a buffer until interrupted
	 */
	public void run() {
		try {
			while (true) {
				int x = random.nextInt(10) + 1;
				buffer.add(x);
				// System.out.println(buffer + getName() + " produced " + x);
			}
		} catch (InterruptedException ex) {
			System.out.println(Thread.currentThread().getName() + " is dying.");
		}
	}

}
