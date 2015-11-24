package com.drawmetry.erik.ringbuffer;

import java.util.Random;

/**
 * Removes numbers from a buffer until interrupted.
 * 
 * @author ecolban
 * 
 */
public class Consumer extends Thread {
	private final BufferInterface buffer;
	private final Random random = new Random();

	/**
	 * Constructs an instance of consumer.
	 * 
	 * @param buffer
	 *            the buffer this instances removes numbers from.
	 */
	public Consumer(BufferInterface buffer) {
		this.buffer = buffer;
	}

	/**
	 * Repeatedly consumes numbers from a buffer until interrupted
	 */
	public void run() {
		boolean keepGoing = true;
		while (keepGoing) {
			try {
				Thread.sleep(random.nextInt(500));
				if (buffer.peek() < 0) {
					keepGoing = false;
				} else {
					buffer.remove();
				}
			} catch (InterruptedException e) {
				System.err.println("Can't interrupt me!");
			}
		}
		System.out.println(Thread.currentThread().getName() + " is dying.");
	}
}
