package com.drawmetry.erik.ringbuffer;

import java.util.Random;

/**
 * A Producer adds repeatedly random numbers to a buffer.
 * 
 * @author ecolban
 * 
 */
public class Producer implements Runnable {

	private final BufferInterface buffer;
	private final Random random = new Random();
	private volatile boolean running = true;

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
		try {
			while (running) {
				Thread.sleep(40);
				int x = random.nextInt(20) + 1;
				buffer.add(x);
			}
		} catch (InterruptedException ex) {
			System.err.println("Someone yanked the cable.");
		}
		System.out.println(Thread.currentThread().getName() + " is dying.");
	}

	public void stopProducing() throws InterruptedException {
		this.running = false;
	}

}
