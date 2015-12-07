package com.drawmetry.erik.ringbuffer;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A Producer adds repeatedly random numbers to a buffer.
 * 
 * @author ecolban
 * 
 */
public class Producer implements Runnable {

	private final BufferInterface buffer;
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
		int item = 0;
		while (running) {
			try {
				item = produce();
				buffer.add(item);
			} catch (InterruptedException ex) {
				System.err.println("Can't interrupt me!");
			}
		}
		System.out.println(Thread.currentThread().getName() + " is done.");
	}

	private int produce() {
		// produce..., produce...
		long start = System.currentTimeMillis();
		int t = ThreadLocalRandom.current().nextInt(80);
		while (System.currentTimeMillis() < start + t) {
			Thread.yield();
		}
		return ThreadLocalRandom.current().nextInt(10);
	}

	public void stopProducing() throws InterruptedException {
		this.running = false;
	}

}
