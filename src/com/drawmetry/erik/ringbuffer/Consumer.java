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
		try {
			while (true) {
				Thread.sleep(random.nextInt(2000));
					int x = buffer.remove();
				}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() +" is dying.");
		}
	}

}
